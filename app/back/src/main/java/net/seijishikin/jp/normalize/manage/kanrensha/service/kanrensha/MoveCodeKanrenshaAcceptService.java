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
public class MoveCodeKanrenshaAcceptService { // NOPMD CouplingWithin

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

        Optional<KanrenshaCodeMoveEntity> optionalPromote = kanrenshaCodeMoveRepository
                .findById(capsuleDto.getKanrenshaCodeMoveEntity().getKanrenshaCodeMoveId());
        if (optionalPromote.isEmpty()) {
            throw new EmptyResultDataAccessException("関連者移動申請が取得できませんでした", 1);
        }

        KanrenshaCodeMoveEntity oldEntity = optionalPromote.get();
        // 第三者が申請している場合があるので前回作業者ではユーザコードは取得できない
        // また紐づけユーザが存在していない可能性がある

        String role = KanrenshaKbnConstants.getUserRole(oldEntity.getKanrenshaKbn());

        // 申請承認の場合は移動処理
        KanrenshaCodeMoveEntity newEntity = capsuleDto.getKanrenshaCodeMoveEntity();

        Optional<UserPersonEntity> optionalOrgin = userPersonRepository
                .findKanrenshaCode(oldEntity.getOriginKanrenshaCode(), role);

        Optional<UserPersonEntity> optionalAbolish = userPersonRepository
                .findKanrenshaCode(oldEntity.getAbolishKanrenshaCode(), role);

        if (ShinseiStatusConstants.ACCEPT == (short) newEntity.getMoveStatus()) { // NOPMD
            // 関連者区分に合わせてコード移行処理を行う
            switch ((short) capsuleDto.getKanrenshaCodeMoveEntity().getKanrenshaKbn()) { // NOPMD
                case KanrenshaKbnConstants.PERSON:
                    moveCodeMasterPersonService.practice(capsuleDto);
                    moveCodeHistoryPersonService.practice(capsuleDto);
                    break;
                case KanrenshaKbnConstants.KIGYOU_DT:
                    moveCodeMasterKigyouDtService.practice(capsuleDto);
                    moveCodeHistoryKigyouDtService.practice(capsuleDto);
                    break;
                case KanrenshaKbnConstants.SEIJIDANTAI:
                    moveCodeMasterSeijidantaiService.practice(capsuleDto);
                    moveCodeHistorySeijidantaiService.practice(capsuleDto);
                    break;
                default:
                    throw new IllegalArgumentException("関連者区分が想定される値ではありません");
            }

            // 廃止コードにユーザが紐づいている場合、廃止コードユーザ権限のコードを変更する
            // 廃止コードのユーザが存在しない場合はなにもしなくてよい
            if (!optionalAbolish.isEmpty()) {
                this.modifyRole(role, optionalOrgin, optionalAbolish, capsuleDto);
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

        // 存続コードに紐づくユーザが存在すればタスク計画を作成しメールを送る
        if (!optionalOrgin.isEmpty()) {
            UserPersonEntity personEntiy = optionalOrgin.get();
            insertTaskPlanOtherPersonService.practice(personEntiy.getEmail(), this.createUserDto(personEntiy),
                    capsuleDto.getUserDto(), createDatetime, TaskInfoConstants.MOVE_KANRENSHA_CODE_RESULT, map);
        }

        // 廃止コードに紐づくユーザが存在すればタスク計画を作成しメールを送る
        if (!optionalAbolish.isEmpty()) {
            UserPersonEntity personEntiy = optionalAbolish.get();
            insertTaskPlanOtherPersonService.practice(personEntiy.getEmail(), this.createUserDto(personEntiy),
                    capsuleDto.getUserDto(), createDatetime, TaskInfoConstants.MOVE_KANRENSHA_CODE_RESULT, map);
        }

        return kanrenshaCodeMoveRepository.save(newEntity).getKanrenshaCodeMoveId();
    }

    private void modifyRole(final String role,final Optional<UserPersonEntity> optionalOrgin,
            final Optional<UserPersonEntity> optionalAbolish,final MoveKanrenshaCodeAcceptCapsuleDto capsuleDto) {

        // (1)存続コードユーザが存在し、廃止コードのユーザが存在する
        // 廃止コードユーザの権限剥奪
        String email = optionalAbolish.get().getEmail();
        List<UserRoleEntity> listOldRole = userRoleRepository.findByEmailAndRoleAndIsLatestTrue(email, role);
        List<UserRoleEntity> listEdit = new ArrayList<>();
        for (UserRoleEntity entity : listOldRole) {
            setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entity);
            listEdit.add(entity);
        }

        // (2)存続コードユーザが存在せず、廃止コードのユーザが存在する
        // 廃止コードユーザの廃止コード権限を存続コードでの権限に積み替え
        if (optionalOrgin.isEmpty()) {
            UserRoleEntity newRoleEntity = new UserRoleEntity();
            newRoleEntity.setEmail(email);
            newRoleEntity.setRole(role);
            newRoleEntity.setKanrenshaCode(capsuleDto.getKanrenshaCodeMoveEntity().getOriginKanrenshaCode());
            setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), newRoleEntity);
            newRoleEntity.setUserRoleId(0); // auto increment明記
            listEdit.add(newRoleEntity);
        }

        userRoleRepository.saveAll(listEdit);

    }

    private LeastUserDto createUserDto(final UserPersonEntity entity) {
        LeastUserDto userDto = new LeastUserDto();

        userDto.setUserPersonId(entity.getUserPersonId());
        userDto.setUserPersonCode(entity.getUserPersonCode());
        userDto.setUserPersonName(entity.getUserPersonName());

        return userDto;
    }
}
