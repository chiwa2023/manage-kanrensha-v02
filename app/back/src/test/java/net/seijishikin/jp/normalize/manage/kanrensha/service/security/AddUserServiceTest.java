package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * AddUserService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("AddUserServiceTest.sql")
class AddUserServiceTest {

    /** テスト対象 */
    @Autowired
    private AddUserService addUserService;

    /** ログイン権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** ユーザRepository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    @Test
    @Tag("TableTruncate")
    void test() {
        
        String mail = "aaa@politician.balanse.report.net";
        String pass = "qwerty1234";
        String role = "manager";
        
        NewComerDto newComerDto = new NewComerDto();
        newComerDto.setMailAddress(mail);
        newComerDto.setNickName("たろー");
        newComerDto.setPassword(pass);
        newComerDto.setRole(role);
        
        // 返り値未定
        addUserService.practice(newComerDto);

        // ユーザエンティティ
        Optional<UserPersonEntity> optionalUser = userPersonRepository.findLatestByMail(mail);
        UserPersonEntity personEntity = optionalUser.get();
        assertFalse(optionalUser.isEmpty());
        assertEquals(personEntity.getUserPersonId(), personEntity.getInsertUserId()); // 新規Idとデータ本体のIdが一致
        assertEquals(personEntity.getUserPersonCode(), personEntity.getInsertUserCode()); // 新規Codeとデータ本体のCodeが一致
        assertEquals(personEntity.getUserPersonName(), personEntity.getInsertUserName()); // 新規Nameとデータ本体のNameが一致
        assertEquals(true, personEntity.getIsLatest());
        assertEquals(newComerDto.getMailAddress(), personEntity.getEmail());
        assertEquals(newComerDto.getNickName(), personEntity.getUserPersonName());
        
        // ログイン状態
        Optional<LoginStatusEntity> optionalLogin = loginStatusRepository.findById(mail);
        assertFalse(optionalLogin.isEmpty());
        LoginStatusEntity statusEntity = optionalLogin.get();
        assertEquals(mail, statusEntity.getEmail());
        assertEquals(false, statusEntity.getDisabled());
        assertEquals(true, statusEntity.getIsSuccess());
        assertEquals(statusEntity.getPassChangeTime(), statusEntity.getLoginTime()); // 登録現在時刻で一致
        // パスワードEncoderが毎回結果が違う?
        // assertEquals(passwordEncoder.encode(newComerDto.getPassword()), statusEntity.getPassword());

        // ユーザ権限
        List<String> listRole = userRoleRepository.findLatestRoleByMail(mail);
        assertEquals(1, listRole.size());
        assertEquals(role, listRole.get(0));
    }

}
