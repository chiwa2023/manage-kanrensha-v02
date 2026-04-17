package net.seijishikin.jp.normalize.manage.kanrensha.config; // NOPMD TooManyImport

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.LoginHistory2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.LoginHistory2026Repository;

/**
 * AuthenticationEvents単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("AuthenticationEventsTest.sql")
class AuthenticationEventsTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** ログイン履歴Respository(2026) */
    @Autowired
    private LoginHistory2026Repository loginHistory2026Repository;

    @Test
    @Tag("TableTruncate")
    @Sql("AuthenticationEventsTestHistory.sql")
    void testSuccess() throws Exception {
        final String mail = "aaa@politician.balanse.report.net";
        final String pass = "qwerty1234";

        LoginUserCapsuleDto capsuleDto = new LoginUserCapsuleDto();
        capsuleDto.setUserId(mail);
        capsuleDto.setPassword(pass);

        String path = PathRouteConstants.ROOT + "/login";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

        // 不本意ではあるが、正しくログインできた時に正しい履歴が取れていることでもってevent自体のテストに変える
        List<LoginHistory2026Entity> listAll = loginHistory2026Repository.findAll();
        assertEquals(1, listAll.size());

        LoginHistory2026Entity entity = listAll.get(0);
        assertEquals("aaa@politician.balanse.report.net", entity.getEmail());
        assertEquals("Unknown", entity.getIpAddress());
        assertEquals("", entity.getUserAgent());
        assertTrue(entity.getIsSuccess());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("AuthenticationEventsTestHistory.sql")
    void testFailure() throws Exception {
        final String mail = "wrong@user.com";
        final String pass = "wrongpassword";

        LoginUserCapsuleDto capsuleDto = new LoginUserCapsuleDto();
        capsuleDto.setUserId(mail);
        capsuleDto.setPassword(pass);

        String path = PathRouteConstants.ROOT + "/login";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        // サーバステータスがUnauthorized(401)
        assertEquals(HttpStatus.UNAUTHORIZED.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isUnauthorized()).andReturn().getResponse().getStatus());

        // TODO 正しくログイン失敗履歴保存テストができるようになった時点て修正する
        // 不本意ではあるが、正しくログインできた時に正しい履歴が取れていることでもってevent自体のテストに変える
        // List<LoginHistory2026Entity> listAll = loginHistory2026Repository.findAll();
        // assertEquals(1, listAll.size());
        //
        // LoginHistory2026Entity entity = listAll.get(0);
        // assertEquals("aaa@politician.balanse.report.net",entity.getEmail());
        // assertEquals("Unknown",entity.getIpAddress());
        // assertEquals("",entity.getUserAgent());
        // assertFalse(entity.getIsSuccess());

        fail("Not yet implemented");
    }
}
