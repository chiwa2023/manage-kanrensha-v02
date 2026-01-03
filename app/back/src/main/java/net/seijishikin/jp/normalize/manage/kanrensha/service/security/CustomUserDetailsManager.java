package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

//import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;
//import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
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

//    /** ユーザ人物Repository */
//    @Autowired
//    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

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
     * ユーザ情報を更新する(必要な情報がないので実装がない)
     */
    @Override
    @Deprecated
    public void updateUser(final UserDetails user) {
        // ユーザ名とパスワードだけだと更新処理の情報として少なすぎるので実装しない
    }

    /**
     * ユーザを削除する(基本的にこのメソッドを他クラスから使用しない)
     */
    @Override
    public void deleteUser(final String username) {
//
//        Optional<LoginStatusEntity> optional = loginStatusRepository.findById(username);
//        if (!optional.isEmpty()) {
//            LoginStatusEntity statusEntity = optional.get();
//            LocalDateTime now = LocalDateTime.now();
//            statusEntity.setDisabled(true);
//            statusEntity.setDiabledReason("人為による退会操作");
//            statusEntity.setLoginTime(now);
//            loginStatusRepository.save(statusEntity);
//        }
//
//        // 権限その他の処理は別メソッドで実装(操作者情報が入れられない)
    }

//    /**
//     * ユーザを削除する
//     *
//     * @param personDeleteDto  削除ユーザ最低限Dto
//     * @param personOperateDto 操作者ユーザ最低限Dto
//     * @return 処理結果
//     */
//    public boolean deleteUser(final UserPersonLeastDto personDeleteDto, final UserPersonLeastDto personOperateDto) {
//
//        Optional<UserPersonEntity> optional = userPersonRepository.findById(personDeleteDto.getUserPersonId());
//        if (optional.isEmpty()) {
//            // 呼び出せなかったら処理中断
//            return false;
//        }
//
//        // ログイン情報の削除
//        UserPersonEntity personEntity = optional.get();
//        String email = personEntity.getEmail();
//        this.deleteUser(email);
//
//        // ユーザ削除処理
//        setTableDataHistoryUtil.practiceDelete(personOperateDto, personEntity);
//        userPersonRepository.saveAndFlush(personEntity);
//
//        // 権限の削除
//        List<UserRoleEntity> listRole = userRoleRepository.findByIsLatestAndEmail(true, email);
//        for (UserRoleEntity roleEntity : listRole) {
//            setTableDataHistoryUtil.practiceDelete(personOperateDto, roleEntity);
//        }
//        userRoleRepository.saveAllAndFlush(listRole);
//
//        return true;
//    }

    /**
     * 誰のパスワードを変更するか、指定できないinterfaceなので実装不能
     */
    @Override
    @Deprecated
    public void changePassword(final String oldPassword, final String newPassword) {
//        // 古いパスワードを基にユーザを呼び出すべきだ、ということ？
//        // 十分に強度があるパスワードがユーザ間で重複しない想定?
    }

//
//    /**
//     * パスワード(encode済)を更新する
//     *
//     * @param username           ユーザ名
//     * @param oldPasswordEncoded 旧パスワード(encode済)
//     * @param newPasswordEncoded 新パスワード(encode済)
//     */
//    public boolean changePassword(final String username, final String oldPasswordEncoded,
//            final String newPasswordEncoded) {
//
//        Optional<LoginStatusEntity> optional = loginStatusRepository.findById(username);
//        if (!optional.isEmpty()) {
//            LoginStatusEntity statusEntity = optional.get();
//
//            // TODO 旧パスワード比較
//
//            statusEntity.setPassword(newPasswordEncoded);
//            loginStatusRepository.saveAndFlush(statusEntity);
//            return true;
//        }
//
//        return false;
//    }

    /**
     * ユーザの存在確認を行う
     */
    @Override
    public boolean userExists(final String username) {
        return !loginStatusRepository.findById(username).isEmpty();
    }

}
