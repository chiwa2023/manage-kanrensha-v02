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
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ChangeUserRoleLogic;
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

    /** ユーザ権限変更Logic */
    @Autowired
    private ChangeUserRoleLogic changeUserRoleLogic;

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
            resultDto.setMessage("編集ユーザ情報が正常に取得できませんでした");
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

        userPersonRepository.save(newPersonEntity).getUserPersonId();

        String email = oldPersonEntity.getEmail();
        List<UserRoleEntity> oldRoles = userRoleRepository.findByEmailAndIsLatestTrue(email);
        List<UserRoleEntity> listNewRole = changeUserRoleLogic.practice(oldRoles,
                capsuleDto.getUserDto().getListRoles(), operatorUserDto);

        if (!listNewRole.isEmpty()) {

            for (UserRoleEntity oldRoleEntity : oldRoles) {
                setTableDataHistoryUtil.practiceDelete(operatorUserDto, oldRoleEntity);
                userRoleRepository.save(oldRoleEntity);
            }

            for (UserRoleEntity newRoleEntity : listNewRole) {
                newRoleEntity.setEmail(email);
                setTableDataHistoryUtil.practiceInsert(operatorUserDto, newRoleEntity);
                newRoleEntity.setUserRoleId(0); // auto increment明記
                userRoleRepository.save(newRoleEntity);
            }
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
}
