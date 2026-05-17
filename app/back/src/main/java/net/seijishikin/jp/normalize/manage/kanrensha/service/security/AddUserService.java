package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * ユーザを追加する
 */
@Service
public class AddUserService {

    /** ユーザRepository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** テーブル履歴設定util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** ユーザ詳細Manager */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** PasswordEncoder */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return 新規ユーザDto
     */
    @Transactional
    public NewComerDto practice(final NewComerDto newComerDto) {

        // id,codeを悲観ロックで取得する
        Optional<UserPersonEntity> optional = userPersonRepository.findFirstByOrderByUserPersonCodeDesc();
        int code = 1;
        if (!optional.isEmpty()) {
            UserPersonEntity entity = optional.get();
            code = code + entity.getUserPersonCode();
        }

        // ユーザ登録を行う
        UserPersonEntity userPersonEntity = new UserPersonEntity();
        userPersonEntity.setUserPersonCode(code);
        String name = newComerDto.getNickName();
        userPersonEntity.setUserPersonName(name);
        userPersonEntity.setEmail(newComerDto.getMailAddress());
        userPersonEntity.setIsLatest(true);

        // 自分が登録者であることを設定
        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonCode(code);
        userDto.setUserPersonName(name);

        setTableDataHistoryUtil.practiceInsert(userDto, userPersonEntity);
        UserPersonEntity insertEntity = userPersonRepository.save(userPersonEntity);

        // 更新処理を行っているが、対象が未設定自分自身のUserIdかつ有効にしているので超法規的処置で更新時間は変更しない
        if (insertEntity.getUserPersonId() != 0) {
            insertEntity.setInsertUserId(insertEntity.getUserPersonId());
            userDto.setUserPersonId(insertEntity.getUserPersonId());
            userPersonRepository.save(insertEntity);
        }

        // 初回ログイン状態を設定
        List<GrantedAuthority> listRole = new ArrayList<>();
        listRole.add(new SimpleGrantedAuthority(newComerDto.getRole()));
        UserDetails user = new User(newComerDto.getMailAddress(), passwordEncoder.encode(newComerDto.getPassword()),
                Collections.unmodifiableList(listRole));
        customUserDetailsManager.createUser(user);

        // ユーザ権限を挿入
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserRoleId(0); // auto_increment 明示
        userRoleEntity.setEmail(newComerDto.getMailAddress());
        userRoleEntity.setRole(newComerDto.getRole());

        setTableDataHistoryUtil.practiceInsert(userDto, userRoleEntity);

        userRoleRepository.save(userRoleEntity);

        return newComerDto;
    }
}
