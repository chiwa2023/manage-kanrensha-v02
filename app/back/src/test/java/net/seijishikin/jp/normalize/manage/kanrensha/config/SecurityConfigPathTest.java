package net.seijishikin.jp.normalize.manage.kanrensha.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserCapsuleDto;

/**
 * SecurityConfigPath単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SecurityConfigPathTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testPostal() throws Exception {

        SearchUserCapsuleDto capsuleDto = new SearchUserCapsuleDto();

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/edit-user/search";

        // ユーザが存在しないため401
        assertEquals(HttpStatus.UNAUTHORIZED.value(),
                mockMvc.perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // NOPMD
                        .contentType(MediaType.APPLICATION_JSON_VALUE) // Content Typeを指定
                ).andExpect(status().isUnauthorized()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testPostalUser() throws Exception {

        SearchUserCapsuleDto capsuleDto = new SearchUserCapsuleDto();
        capsuleDto.setLimit(5);
        capsuleDto.getListRole().add("manager");
        capsuleDto.getListRole().add("kanrensha_person");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/edit-user/search";

        // MockUserと一緒に実行しているため正常終了
        assertEquals(HttpStatus.OK.value(),
                mockMvc.perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // NOPMD
                        .contentType(MediaType.APPLICATION_JSON_VALUE) // Content Typeを指定
                ).andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    void testLogin() throws Exception {

        String path = "/login";

        // 認証外のため、ボディにが引数が入っていないことによるサーバステータスUNAUTTHORIZED(401)
        // ログインそのものの有効性はLoginUserOperatorControllerTestで確認
        assertEquals(HttpStatus.UNAUTHORIZED.value(),
                mockMvc.perform(post(path)).andExpect(status().isUnauthorized()).andReturn().getResponse().getStatus()); // NOPMD
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testLogout() throws Exception {
        // MockUserを設定しているのでログイン情報がMockで存在する
        assertEquals(UsernamePasswordAuthenticationToken.class,
                SecurityContextHolder.getContext().getAuthentication().getClass());
        String path = "/logout";

        // logoutは自動で/に遷移しようとする
        assertEquals(HttpStatus.FOUND.value(),
                mockMvc.perform(post(path)).andExpect(status().isFound()).andReturn().getResponse().getStatus()); // NOPMD

        // ログアウト後はログイン情報を取得しようとしても存在しない
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
    }
}
