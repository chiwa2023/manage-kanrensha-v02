package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD HighImports
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.DeleteUserCapsuleDto;

/**
 * GetLoginLeastUserLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "GetLoginLeastUserLogicTest.sql", "../../service/user/RefreshPasswordServiceTest.sql" })
class GetLoginLeastUserLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetLoginLeastUserLogic getLoginLeastUserLogic;

    /** WebApplicationContext */
    @Autowired
    private WebApplicationContext context;

    /** MockMvc */
    private MockMvc mockMvc;

    /** setup */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) //
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    /** 認証プロバイダ */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testNoLogin() {

        // 単体でログインしておらず、Controllを経由していないときは空Dtoが戻ってくる
        LeastUserDto userDto = getLoginLeastUserLogic.practice();
        assertEquals(0, userDto.getUserPersonId());
        assertEquals(0, userDto.getUserPersonCode());
        assertEquals("", userDto.getUserPersonName());
        List<String> listRoles = userDto.getListRoles();
        assertEquals(0, listRoles.size());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testLogin() {

        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        // ログイン処理
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 上記ログイン方法でログイン情報が取れる(UserDetail経由)
        LeastUserDto userDto = getLoginLeastUserLogic.practice();
        assertEquals(81, userDto.getUserPersonId());
        assertEquals(80, userDto.getUserPersonCode());
        assertEquals("aaa", userDto.getUserPersonName());
        List<String> listRoles = userDto.getListRoles();
        assertEquals(1, listRoles.size());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testController() throws Exception {

        // 本人退会
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        UserDetails testUser = User.withUsername(mail).password(password).roles("manager").build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        DeleteUserCapsuleDto capsuleDto = new DeleteUserCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(81);
        capsuleDto.getUserDto().setUserPersonCode(80);
        capsuleDto.getUserDto().setUserPersonName("aaa");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/edit-user/delete";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

        // Controllerレスポンスが戻った後はログイン状態でないのでSecurity機構がないといわれる
        // 単体テスト時、Controller内部ではUserDetailでなくString(username=email)が戻り、それを基に組み立てている
        // ↑がメモしたかった
        assertThrows(NullPointerException.class, () -> getLoginLeastUserLogic.practice());
    }

}
