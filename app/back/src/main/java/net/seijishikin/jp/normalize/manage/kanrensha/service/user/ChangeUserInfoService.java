package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.EditUserPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * ユーザ情報を編集する(名称と権限)
 */
@Service
public class ChangeUserInfoService {

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 更新ユーザDto
     * @return 処理結果
     */
    @Transactional
    public FrameworkMessageAndResultDto practice(final EditUserPersonCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();

        UserPersonEntity oldPersonEntity = this.getNowPersonEntity(capsuleDto.getUserDto().getUserPersonId());
        // 編集対象ユーザ情報が正常に取得できない
        if (Objects.isNull(oldPersonEntity)) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("編集ユーザ情報に正常に取得できませんでした");
            return resultDto;
        }

        // 操作者ユーザ情報が正常に取得できない
        LeastUserDto operatorUserDto = this.getOperatorPerson();
        if (Objects.isNull(operatorUserDto)) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("操作者ログイン情報が正常に取得できませんでした");
            return resultDto;
        }

        UserPersonEntity newPersonEntity = new UserPersonEntity();
        BeanUtils.copyProperties(oldPersonEntity, newPersonEntity);

        // 該当Entityを履歴にする
        setTableDataHistoryUtil.practiceDelete(operatorUserDto, oldPersonEntity);
        userPersonRepository.save(oldPersonEntity).getUserPersonId();

        // 新規Entityを設定
        String newName = capsuleDto.getUserDto().getUserPersonName();
        newPersonEntity.setUserPersonName(newName);
        newPersonEntity.setIsAlertTaskStart(capsuleDto.getIsAlertTaskStart());
        newPersonEntity.setIsAlertTaskEnd(capsuleDto.getIsAlertTaskEnd());

        setTableDataHistoryUtil.practiceInsert(operatorUserDto, newPersonEntity);
        newPersonEntity.setUserPersonId(0); // auto iuncrement明記

        Integer newId = userPersonRepository.save(newPersonEntity).getUserPersonId();

        // 既存のロールを無効化
        List<UserRoleEntity> oldRoles = userRoleRepository.findByEmailAndIsLatestTrue(oldPersonEntity.getEmail());
        for (UserRoleEntity oldRole : oldRoles) {
            setTableDataHistoryUtil.practiceDelete(operatorUserDto, oldRole);
            userRoleRepository.save(oldRole);
        }

        // 新しいロールを追加
        for (String role : capsuleDto.getUserDto().getListRoles()) {
            UserRoleEntity oldRoleEntity = this.pickupRole(oldRoles, role);
            UserRoleEntity newRole = this.createRoleEntitty(role, newId, oldRoleEntity, newName,
                    newPersonEntity.getEmail());
            setTableDataHistoryUtil.practiceInsert(operatorUserDto, newRole);
            newRole.setUserRoleId(0); // auto increment明記
            userRoleRepository.save(newRole);
        }

        return resultDto;
    }

    private UserPersonEntity getNowPersonEntity(final Integer personId) {

        Optional<UserPersonEntity> optional = userPersonRepository.findById(personId);
        if (optional.isEmpty()) {
            return null;
        } else {
            return optional.get();
        }

    }

    private LeastUserDto getOperatorPerson() {

        // 操作者の取得
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            return null;
        }

        Optional<UserPersonEntity> optionalOperator = userPersonRepository
                .findByEmailAndIsLatestTrue(currentUser.getName());
        if (optionalOperator.isEmpty()) {
            return null;
        }

        UserPersonEntity entityOperator = optionalOperator.get();
        LeastUserDto operatorUserDto = new LeastUserDto();
        operatorUserDto.setUserPersonId(entityOperator.getUserPersonId());
        operatorUserDto.setUserPersonCode(entityOperator.getUserPersonCode());
        operatorUserDto.setUserPersonName(entityOperator.getUserPersonName());

        return operatorUserDto;
    }

    private UserRoleEntity createRoleEntitty(final String role, final Integer newId, final UserRoleEntity oldEntity,
            final String newName, final String email) {

        UserRoleEntity newRole = new UserRoleEntity();
        newRole.setEmail(email);
        newRole.setRole(role);
        newRole.setKanrenshaCode(oldEntity.getKanrenshaCode());
        newRole.setRiyoushaCode(oldEntity.getRiyoushaCode());
        newRole.setUserRoleId(newId);
        newRole.setDeleteUserName(newName);

        return newRole;
    }

    private UserRoleEntity pickupRole(final List<UserRoleEntity> list, final String key) {

        for (UserRoleEntity entity : list) {
            if (key.equals(entity.getRole())) {
                return entity;
            }
        }

        // 該当roleがなければ空
        return new UserRoleEntity();
    }

}
