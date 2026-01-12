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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
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
    public FrameworkMessageAndResultDto practice(final FrameworkCapsuleDto capsuleDto) {

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

        // 該当Entityを履歴にする
        setTableDataHistoryUtil.practiceDelete(operatorUserDto, oldPersonEntity);
        userPersonRepository.save(oldPersonEntity).getUserPersonId();

        // 新規Entityを設定
        UserPersonEntity newPersonEntity = new UserPersonEntity();
        BeanUtils.copyProperties(oldPersonEntity, newPersonEntity);
        String newName = capsuleDto.getUserDto().getUserPersonName();
        newPersonEntity.setUserPersonName(newName);
        setTableDataHistoryUtil.practiceInsert(operatorUserDto, newPersonEntity);
        newPersonEntity.setUserPersonId(0); // auto iuncrement明記

        Integer newId = userPersonRepository.save(newPersonEntity).getUserPersonId();

        System.out.println("personId" + newId);
        
        
        // 既存のロールを無効化
        List<UserRoleEntity> oldRoles = userRoleRepository.findByEmailAndIsLatestTrue(oldPersonEntity.getEmail());
        for (UserRoleEntity oldRole : oldRoles) {
            setTableDataHistoryUtil.practiceDelete(operatorUserDto, oldRole);
            userRoleRepository.save(oldRole);
        }

        // 新しいロールを追加
        for (String role : capsuleDto.getUserDto().getListRoles()) {
            UserRoleEntity newRole = new UserRoleEntity();
            newRole.setEmail(newPersonEntity.getEmail());
            newRole.setRole(role);
            newRole.setUserRoleId(newId);
            newRole.setDeleteUserName(newName);
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

}
