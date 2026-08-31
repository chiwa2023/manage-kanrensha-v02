package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.AcceptRiyoushaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgTempRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearUpdateTaskStartAndEndService;

/**
 * 利用者招待承諾Service
 */
@Service
public class AcceptRiyoushaOrgCombineService {

    /** 年切り替えタスク計画終了Service */
    @Autowired
    private SwitchYearUpdateTaskStartAndEndService switchYearUpdateTaskStartAndEndService;

    /** 年切り替えタスク計画終了Service */
    @Autowired
    private InsertRiyoushaCombinePersonService insertRiyoushaCombinePersonService;

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgTempRepository riyoushaCombineOrgTempRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 利用者組織承諾Dto
     * @param endTime    終了時間
     * @return 結果Dto
     */
    @Transactional
    public FrameworkMessageAndResultDto practice(final AcceptRiyoushaCombineCapsuleDto capsuleDto,
            final LocalDateTime endTime) {

        // 仮登録テーブルから該当データを呼び出して履歴とする
        Optional<RiyoushaCombineOrgTempEntity> optional = riyoushaCombineOrgTempRepository
                .findById(capsuleDto.getOrgTempId());
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("仮テーブルから対象データを呼び出せませんでした", 1);
        }

        RiyoushaCombineOrgTempEntity orgTempEntity = optional.get();
        LeastUserDto userDto = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceDelete(userDto, orgTempEntity);
        riyoushaCombineOrgTempRepository.save(orgTempEntity);

        // 承諾を得た時のみ本テーブルにデータを複写する
        if (capsuleDto.getIsAsscept()) {
            RiyoushaCombineOrgEntity orgEntity = new RiyoushaCombineOrgEntity();
            BeanUtils.copyProperties(orgTempEntity, orgEntity);
            Integer newCombineId = insertRiyoushaCombinePersonService.practice(orgEntity, userDto);

            if (0 == newCombineId) {
                throw new IncorrectUpdateSemanticsDataAccessException("正常に保存できませんした");
            }
        }

        // 反応によらずタスク計画を終了とする(ただしタスクから遷移した場合のみ)
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (0 != capsuleDto.getTaskPlanId()) {
            Integer newTaskPlanId = switchYearUpdateTaskStartAndEndService.practice(userDto, capsuleDto.getTaskYear(),
                    capsuleDto.getTaskPlanId(), endTime);

            if (0 == newTaskPlanId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("タスク計画が終了できませんでした");
                return resultDto;
            }
        }
        return resultDto;
    }

}
