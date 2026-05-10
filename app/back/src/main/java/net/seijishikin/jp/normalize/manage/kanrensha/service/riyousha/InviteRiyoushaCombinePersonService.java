package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaCombinePersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgTempRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan.InsertTaskPlanOtherPersonService;

/**
 * 利用者組織個人招待Service
 */
@Service
public class InviteRiyoushaCombinePersonService {

    /** 他人向けタスク挿入通知Service */
    @Autowired
    private InsertTaskPlanOtherPersonService insertTaskPlanOtherPersonService;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ個人Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** 利用者組織紐づけ仮Repository */
    @Autowired
    private RiyoushaCombineOrgTempRepository riyoushaCombineOrgTempRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto     利用者組織個人招待Dto
     * @param createDateTime 作成時間
     * @return 処理結果
     */
    @Transactional
    public FrameworkMessageAndResultDto practice(final RiyoushaCombinePersonCapsuleDto capsuleDto,
            final LocalDateTime createDateTime) {

        // 入力したメールアドレスで対象者のユーザを取得
        String email = capsuleDto.getEmail();

        Optional<UserPersonEntity> optionalPerson = userPersonRepository.findLatestByMail(email);
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (optionalPerson.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("メールアドレスからユーザが取得できませんでした。メールアドレスを再入力してください");
            return resultDto;
        }

        RiyoushaCombineOrgEntity combineOrgEntity = capsuleDto.getCombineEntity();
        combineOrgEntity.setPersonCode(optionalPerson.get().getUserPersonCode());

        List<UserRoleEntity> listRole = userRoleRepository.findByEmailAndIsLatestTrue(email);
        for (UserRoleEntity entity : listRole) {
            if (combineOrgEntity.getPersonRiyoushaCode().equals(entity.getRiyoushaCode())) {
                combineOrgEntity.setRiyoushaRole(entity.getRole());
                break;
            }
        }

        final String BLANK = "";
        if (BLANK.equals(combineOrgEntity.getRiyoushaRole())) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("所属招待する利用者が見つかりませんでした");
            return resultDto;
        }

        LeastUserDto userDto = capsuleDto.getUserDto();
        // 仮テーブルにデータを複写
        Integer newId = this.saveTemp(combineOrgEntity, userDto);
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("正常に仮テーブルに保存ができませんでした");
            return resultDto;
        }

        // 招待した利用者向けのタスクに登録して連絡
        Map<String, String> mapParam = new TreeMap<>();
        mapParam.put("personCode", String.valueOf(combineOrgEntity.getPersonRiyoushaCode()));
        mapParam.put("orgCode", String.valueOf(combineOrgEntity.getOrgRiyoushaCode()));
        mapParam.put("userRole", combineOrgEntity.getRiyoushaRole());

        resultDto = insertTaskPlanOtherPersonService.practice(email, userDto, createDateTime,
                TaskInfoConstants.ORG_COMBINE_PERSON_RIYOUSHA, mapParam);

        return resultDto;
    }

    private Integer saveTemp(final RiyoushaCombineOrgEntity entity, final LeastUserDto userDto) {

        RiyoushaCombineOrgTempEntity tempEntity = new RiyoushaCombineOrgTempEntity();
        BeanUtils.copyProperties(entity, tempEntity);

        Integer code = 1;
        Optional<RiyoushaCombineOrgTempEntity> optional = riyoushaCombineOrgTempRepository
                .findFirstByOrderByRiyoushaCombineOrgTempCodeDesc();

        if (!optional.isEmpty()) {
            code += optional.get().getRiyoushaCombineOrgTempCode();
        }
        tempEntity.setRiyoushaCombineOrgTempCode(code);
        setTableDataHistoryUtil.practiceInsert(userDto, tempEntity);
        tempEntity.setRiyoushaCombineOrgTempId(0); // auto increment明記

        return riyoushaCombineOrgTempRepository.save(tempEntity).getRiyoushaCombineOrgTempId();
    }
}
