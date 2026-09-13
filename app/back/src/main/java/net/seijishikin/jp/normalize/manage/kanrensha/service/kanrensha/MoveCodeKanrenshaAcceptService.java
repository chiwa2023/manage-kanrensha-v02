package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan.InsertTaskPlanOtherPersonService;

/**
 * 関連者コード移動承認Service
 */
@Service
public class MoveCodeKanrenshaAcceptService {

    /** 関連者個人コード移動Service */
    @Autowired
    private MoveCodeMasterPersonService moveCodeMasterPersonService;

    /** 関連者企業・団体コード移動Service */
    @Autowired
    private MoveCodeMasterKigyouDtService moveCodeMasterKigyouDtService;

    /** 関連者政治団体コード移動Service */
    @Autowired
    private MoveCodeMasterSeijidantaiService moveCodeMasterSeijidantaiService;

    /** 関連者個人コード移動Service */
    @Autowired
    private MoveCodeHistoryPersonService moveCodeHistoryPersonService;

    /** 関連者企業・団体コード移動Service */
    @Autowired
    private MoveCodeHistoryKigyouDtService moveCodeHistoryKigyouDtService;

    /** 関連者政治団体コード移動Service */
    @Autowired
    private MoveCodeHistorySeijidantaiService moveCodeHistorySeijidantaiService;

    /** 関連者コード移動申請Repository */
    @Autowired
    private KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 他者タスク追加Service */
    @Autowired
    private InsertTaskPlanOtherPersonService insertTaskPlanOtherPersonService;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return 関連者移動コード申請Id
     */
    @Transactional
    public Integer practice(final MoveKanrenshaCodeAcceptCapsuleDto capsuleDto, final LocalDateTime createDatetime) {
        
        // final short kbnPerson = KanrenshaKbnConstants.PERSON;

        Optional<KanrenshaCodeMoveEntity> optional = kanrenshaCodeMoveRepository
                .findById(capsuleDto.getKanrenshaCodeMoveEntity().getKanrenshaCodeMoveId());
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("関連者移動申請が取得できませんでした", 1);
        }

        KanrenshaCodeMoveEntity oldEntity = optional.get();
        final LeastUserDto taskUserDto = new LeastUserDto();
        taskUserDto.setUserPersonId(oldEntity.getInsertUserId());
        taskUserDto.setUserPersonCode(oldEntity.getInsertUserCode());
        taskUserDto.setUserPersonName(oldEntity.getInsertUserName());

        Optional<UserPersonEntity> optionalPerson = userPersonRepository.findById(taskUserDto.getUserPersonId());
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("ユーザが存在しませんでした", 1);
        }

        final String email = optionalPerson.get().getEmail();

        // 申請承認の場合は移動処理
        KanrenshaCodeMoveEntity newEntity = capsuleDto.getKanrenshaCodeMoveEntity();
        final String BLANK = "";
        if (ShinseiStatusConstants.ACCEPT == (short) newEntity.getMoveStatus()) { // NOPMD
            String role = BLANK;
            // 関連者区分に合わせてコード移行処理を行う
            switch ((short) capsuleDto.getKanrenshaCodeMoveEntity().getKanrenshaKbn()) { // NOPMD
                case KanrenshaKbnConstants.PERSON:
                    moveCodeMasterPersonService.practice(capsuleDto);
                    moveCodeHistoryPersonService.practice(capsuleDto);
                    role = UserRoleConstants.KANRENSHA_PERSON;
                    break;
                case KanrenshaKbnConstants.KIGYOU_DT:
                    moveCodeMasterKigyouDtService.practice(capsuleDto);
                    moveCodeHistoryKigyouDtService.practice(capsuleDto);
                    role = UserRoleConstants.KANRENSHA_KIGYOU_DT;
                    break;
                case KanrenshaKbnConstants.SEIJIDANTAI:
                    moveCodeMasterSeijidantaiService.practice(capsuleDto);
                    moveCodeHistorySeijidantaiService.practice(capsuleDto);
                    role = UserRoleConstants.KANRENSHA_SEIJIDANTAI;
                    break;
                default:
                    throw new IllegalArgumentException("関連者区分が想定される値ではありません");
            }

            // ユーザ権限のコードを変更する
            if (!BLANK.equals(role)) {
                List<UserRoleEntity> listOldRole = userRoleRepository.findByEmailAndRoleAndIsLatestTrue(email, role);
                List<UserRoleEntity> listEdit = new ArrayList<>();
                for (UserRoleEntity entity : listOldRole) {
                    setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entity);
                    listEdit.add(entity);
                }
                UserRoleEntity newRoleEntity = new UserRoleEntity();
                newRoleEntity.setEmail(email);
                newRoleEntity.setRole(role);
                newRoleEntity.setKanrenshaCode(capsuleDto.getKanrenshaCodeMoveEntity().getOriginKanrenshaCode());
                setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), newRoleEntity);
                newRoleEntity.setUserRoleId(0); // auto increment明記
                listEdit.add(newRoleEntity);

                userRoleRepository.saveAll(listEdit);
            }
        }

        // 戻りを待って承認テーブルを更新する
        // 現在の最新を履歴にする
        setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), oldEntity);
        kanrenshaCodeMoveRepository.save(oldEntity);

        // 最新にする
        setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), newEntity);
        newEntity.setKanrenshaCodeMoveId(0); // auto increment明記

        // 権限タスクを戻す?←Controllerでする

        // 対象ユーザのユーザ設定を変更し、対象ユーザのタスク計画を挿入しメールを送る
        Map<String, String> map = new TreeMap<>();
        
        insertTaskPlanOtherPersonService.practice(email, taskUserDto, capsuleDto.getUserDto(), createDatetime,
                TaskInfoConstants.MOVE_KANRENSHA_CODE_RESULT, map);

        return kanrenshaCodeMoveRepository.save(newEntity).getKanrenshaCodeMoveId();
    }
}
