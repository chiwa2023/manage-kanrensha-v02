package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * 独自ユーザ詳細Manager
 */
@Component
public class CustomUserDetailsManager implements UserDetailsManager {

    /** パスワード変更期限 */
    public static final long LIMIT_PASS_CHANGE = 12L;

    /** 無活動期限 */
    public static final long LIMIT_ACTIVE = 2L;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** テーブル履歴設定util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * ユーザ名で該当データを呼び出す
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws NoSuchElementException { // NOPMD

        LoginStatusEntity statusEntity = loginStatusRepository.findById(username).get();
        LocalDateTime now = LocalDateTime.now();

        // 権限を呼び出してUserDetailを作成する
        List<String> listRole = userRoleRepository.findLatestRoleByMail(username);

        return User.builder().username(statusEntity.getEmail()).password(statusEntity.getPassword())
                .accountExpired(statusEntity.getLoginTime().plusYears(LIMIT_ACTIVE).isBefore(now)) // x年無活動なのでアカウントロックしたなど
                .accountLocked(false) // 現状未使用
                .credentialsExpired(statusEntity.getPassChangeTime().plusMonths(LIMIT_PASS_CHANGE).isBefore(now)) // xか月パスワード更新なしなのでアカウントロックしたなど
                .disabled(statusEntity.getDisabled()).roles(listRole.toArray(new String[listRole.size()])).build(); // NOPMD
    }

    /**
     * 新規ユーザを追加する
     */
    @Override
    public void createUser(final UserDetails user) {
        // 初回ログイン処理を行う
        LoginStatusEntity loginStatusEntity = new LoginStatusEntity();
        LocalDateTime now = LocalDateTime.now();
        String mail = user.getUsername();
        loginStatusEntity.setEmail(mail);
        loginStatusEntity.setPassword(user.getPassword());
        loginStatusEntity.setIsSuccess(true);
        loginStatusEntity.setDisabled(false);
        loginStatusEntity.setLoginTime(now);
        loginStatusEntity.setPassChangeTime(now);

        loginStatusRepository.save(loginStatusEntity);
    }

    /**
     * ユーザ更新作業を行う ユーザ更新作業と言いながら、権限更新作業であるため非推奨
     */
    @Override
    public void updateUser(final UserDetails user) {
        
        String email = user.getUsername();
        
        if (loginStatusRepository.findById(email).isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        // パスワード変更は別ページで行う
        // if (user.getPassword() != null) {
        // statusEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        // }
        // statusEntity.setDisabled(!user.isEnabled());
        // loginStatusRepository.save(statusEntity);

        // ログイン中の操作ユーザを取得
        LeastUserDto operatorUserDto = this.getCurrentUser(user.getUsername());
        
        // 既存のロールを無効化
        List<UserRoleEntity> oldRoles = userRoleRepository.findByEmailAndIsLatestTrue(user.getUsername());
        for (UserRoleEntity oldRole : oldRoles) {
            setTableDataHistoryUtil.practiceDelete(operatorUserDto, oldRole);
            userRoleRepository.save(oldRole);
        }

        // 新しいロールを追加
        user.getAuthorities().forEach(authority -> {
            UserRoleEntity newRole = new UserRoleEntity();
            newRole.setEmail(user.getUsername());
            newRole.setRole(authority.getAuthority());
            setTableDataHistoryUtil.practiceDelete(operatorUserDto, newRole);
            userRoleRepository.save(newRole);
        });
    }

    /**
     * ユーザを削除する
     */
    @Override
    public void deleteUser(final String username) {

        // 操作者の取得
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            throw new IllegalStateException("Cannot change password for anonymous user.");
        }

        String operateUserName = currentUser.getName();

        // 操作対象の取得
        // login_statusの更新
        Optional<LoginStatusEntity> optional = loginStatusRepository.findById(username);

        if (!optional.isEmpty()) {
            final String blank = "";
            LoginStatusEntity statusEntity = optional.get();

            if (blank.equals(statusEntity.getDisabledReason())) {

                if (username.equals(operateUserName)) { // SUPPRESS CHECKSTYLE NestDepth
                    statusEntity.setDisabledReason("ユーザ自身による退会操作(理由未記入)");
                } else {
                    statusEntity.setDisabledReason("権限者による強制退会(理由未記入)");
                }
            }

            statusEntity.setDisabled(true);
            statusEntity.setLoginTime(LocalDateTime.now());
            loginStatusRepository.save(statusEntity);
        }

        // ログイン中の操作ユーザを取得
        LeastUserDto operatorUserDto = this.getCurrentUser(operateUserName);
        Optional<UserPersonEntity> personOptional = userPersonRepository.findByEmailAndIsLatestTrue(username);

        // user_personの更新
        if (personOptional.isPresent()) {
            UserPersonEntity personEntity = personOptional.get();
            setTableDataHistoryUtil.practiceDelete(operatorUserDto, personEntity);
            userPersonRepository.save(personEntity);
        }

        // user_roleの更新
        List<UserRoleEntity> listRole = userRoleRepository.findByEmailAndIsLatestTrue(username);
        for (UserRoleEntity roleEntity : listRole) {
            setTableDataHistoryUtil.practiceDelete(operatorUserDto, roleEntity);
            userRoleRepository.save(roleEntity);
        }

    }

    /**
     * パスワードを変更する
     */
    @Override
    public void changePassword(final String oldPassword, final String newPassword) {

        // 操作者の取得
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            throw new IllegalStateException("Cannot change password for anonymous user.");
        }

        String operateUserName = currentUser.getName();

        LoginStatusEntity statusEntity = loginStatusRepository.findById(operateUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + operateUserName + "' not found."));

        statusEntity.setPassword(newPassword);
        statusEntity.setPassChangeTime(LocalDateTime.now());
        loginStatusRepository.save(statusEntity);
    }

    /**
     * ユーザの存在確認を行う
     */
    @Override
    public boolean userExists(final String username) {
        return !loginStatusRepository.findById(username).isEmpty();
    }

    private LeastUserDto getCurrentUser(final String oprtateUserName) {
        LeastUserDto userDto = new LeastUserDto();
        Optional<UserPersonEntity> optional = userPersonRepository.findLatestByMail(oprtateUserName);

        // メールアドレスと最新でユーザが取れない
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("ログイン情報で例外が発生しました");
        }

        UserPersonEntity personEntity = optional.get();
        userDto.setUserPersonId(personEntity.getUserPersonId());
        userDto.setUserPersonCode(personEntity.getUserPersonCode());
        userDto.setUserPersonName(personEntity.getUserPersonName());

        return userDto;
    }
}
