package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.RefreshPasswordCapsuleDto;

/**
 * RefreshPasswordController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("../../service/user/RefreshPasswordServiceTest.sql")
class RefreshPasswordControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testBadUser() throws Exception {

        RefreshPasswordCapsuleDto capsuleDto = new RefreshPasswordCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(75);
        capsuleDto.setOldPassword("qwerty1234");
        capsuleDto.setNewPassword("asdfg12345");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/edit-user/refresh-password";

        // サーバステータスがNO_CONTEMT(204)
        assertEquals(HttpStatus.NO_CONTENT.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isNoContent()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testWrongOldPassword() throws Exception {

        // パスワードが間違っている場合は強制401が返ってしまうため、frontのキャッチを
        RefreshPasswordCapsuleDto capsuleDto = new RefreshPasswordCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(81);
        capsuleDto.setOldPassword("qwerty12345");
        capsuleDto.setNewPassword("asdfg12345");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/edit-user/refresh-password";

        // サーバステータスがNO_CONTENT(204)
        assertEquals(HttpStatus.NO_CONTENT.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isNoContent()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        // パスワードが間違っている場合は強制401が返ってしまうため、frontのキャッチを
        RefreshPasswordCapsuleDto capsuleDto = new RefreshPasswordCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(81);
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";
        capsuleDto.setOldPassword(password);
        capsuleDto.setNewPassword("asdfg12345");

        UserDetails testUser = User.withUsername(mail).password(password) // パスワードはダミー
                .roles("manager").build();

        // 架空のユーザでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/edit-user/refresh-password";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
