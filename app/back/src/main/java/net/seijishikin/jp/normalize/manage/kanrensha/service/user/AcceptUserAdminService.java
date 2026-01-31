package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.AcceptUserAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.AcceptUserAdminSendMailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PromoteAdminRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearUpdateTaskStartAndEndService;

/**
 * SE権限追加推薦諾否登録処理
 */
@Service
public class AcceptUserAdminService {

    /** SE権限追加推薦Repository */
    @Autowired
    private PromoteAdminRepository promoteAdminRepository;

    /** SE権限追加推薦Repository */
    @Autowired
    private SwitchYearUpdateTaskStartAndEndService switchYearUpdateTaskStartAndEndService;

    /** テーブル履歴設定ユーティリティ */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** SE権限追加推薦諾否作業完了通知Logic */
    @Autowired
    private AcceptUserAdminSendMailLogic acceptUserAdminSendMailLogic;

    /** ユーザ役割Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @param endTime    終了時間
     * @return 該当更新件数
     */
    @Transactional
    public Integer practice(final AcceptUserAdminCapsuleDto capsuleDto, final LocalDateTime endTime) {

        // 推薦のあったデータすべてに対して、この回答を適用する
        LeastUserDto userDto = capsuleDto.getUserDto();
        List<PromoteAdminEntity> listPromote = promoteAdminRepository
                .findByPromoteUserCodeAndIsLatestTrueOrderByInsertTimestampDesc(userDto.getUserPersonCode());
        Boolean isAccept = capsuleDto.getPromoteAdminEntity().getIsAccept();
        List<PromoteAdminEntity> listAll = new ArrayList<>();
        for (PromoteAdminEntity entitySrc : listPromote) {

            // 新しいデータを履歴かつ結果を残す
            listAll.add(this.createEndData(entitySrc, userDto, isAccept));

            // 元データを終了にだけする
            setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
            listAll.add(entitySrc);
        }

        final Integer savedCount = promoteAdminRepository.saveAll(listAll).size();

        // 権限追加を承諾した場合、現在の権限をリスト取得し、adminが存在しないことを確認したうえで、権限追加
        if (isAccept) {
            this.addRole(userDto);
        }

        // 紐づくタスク計画をすべて終了済とする
        for (PromoteAdminEntity entitySrc : listPromote) {
            switchYearUpdateTaskStartAndEndService.practice(userDto, entitySrc.getTaskYear(), entitySrc.getTaskPlanId(),
                    endTime);
        }

        // 紐づくタスク計画を登録を行った作業者に完了報告を行う(最後の最後、transactionの対象にならないことを確認してから)
        for (PromoteAdminEntity entitySrc : listPromote) {
            acceptUserAdminSendMailLogic.pracitce(entitySrc.getInsertUserId());
        }

        return savedCount;
    }

    private PromoteAdminEntity createEndData(final PromoteAdminEntity entitySrc, final LeastUserDto userDto,
            final Boolean isAccept) {

        PromoteAdminEntity entity = new PromoteAdminEntity();
        BeanUtils.copyProperties(entitySrc, entity);
        entity.setIsAccept(isAccept);

        setTableDataHistoryUtil.practiceDelete(userDto, entity);
        entity.setPromoteAdminId(0); // auto increment明記

        return entity;
    }

    private void addRole(final LeastUserDto userDto) {

        Optional<UserPersonEntity> optionalPerson = userPersonRepository.findById(userDto.getUserPersonId());
        if (optionalPerson.isEmpty()) {
            throw new EmptyResultDataAccessException("ユーザ情報が見つかりませんでした", 1);
        }
        List<UserRoleEntity> listUser = userRoleRepository.findByEmailAndIsLatestTrue(optionalPerson.get().getEmail());

        if (listUser.isEmpty()) {
            throw new EmptyResultDataAccessException("ユーザ権限が1件も見つかりませんでした", 1);
        }

        // admin権限を持っているか再度チェック
        boolean hasAdmin = false;
        for (UserRoleEntity entity : listUser) {
            if (UserRoleConstants.ADMIN.equals(entity.getRole())) {
                hasAdmin = true;
            }
        }
        if (!hasAdmin) {
            userRoleRepository.save(this.createRoleEntity(listUser.get(0), userDto));
        }
    }

    private UserRoleEntity createRoleEntity(final UserRoleEntity roleEntity, final LeastUserDto userDto) {

        UserRoleEntity entity = new UserRoleEntity();
        BeanUtils.copyProperties(roleEntity, entity);
        entity.setRole(UserRoleConstants.ADMIN);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setUserRoleId(0); // auto increment明記
        return entity;
    }
}
