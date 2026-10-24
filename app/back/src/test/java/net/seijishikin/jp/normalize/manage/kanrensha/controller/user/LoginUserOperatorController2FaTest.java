package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD TooManyImports
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasskeyCredentialRepository;

/**
 * 2FA (パスキー) ログイン動作テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("LoginUserOperatorControllerTest.sql")
class LoginUserOperatorController2FaTest {
    // CHECKSTYLE:OFF MagicNumber

    /** WebApplicationContext */
    @Autowired
    private WebApplicationContext context;

    /** ユーザパスキー信頼性Repository */
    @Autowired
    private UserPasskeyCredentialRepository userPasskeyCredentialRepository;

    /** MockMvc */
    private MockMvc mockMvc;

    /** Setup */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @Tag("TableTruncate")
    void testLoginWithoutPasskey() throws Exception {
        // パスキー未登録のユーザ (user_person_code = 80)
        userPasskeyCredentialRepository.deleteAll();

        LoginUserCapsuleDto capsuleDto = new LoginUserCapsuleDto();
        capsuleDto.setUserId("aaa@politician.balanse.report.net");
        capsuleDto.setPassword("qwerty1234");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();
        String path = PathRouteConstants.ROOT + "/login";

        MvcResult result = mockMvc.perform(post(path).content(objectMapper.writeValueAsString(capsuleDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        LoginUserResultDto resultDto = objectMapper.readValue( //
                result.getResponse().getContentAsString(), LoginUserResultDto.class); // NOPMD LawDemeter

        assertNotNull(resultDto);
        assertFalse(resultDto.getRequiresPasskey());
        assertNotNull(resultDto.getJwtTokenDto());
        assertNotNull(resultDto.getJwtTokenDto().getAccessToken());
    }

    @Test
    @Tag("TableTruncate")
    void testLoginWithPasskeyRequires2Fa() throws Exception {
        userPasskeyCredentialRepository.deleteAll();

        // user_person_code = 80 にパスキーを登録
        UserPasskeyCredentialEntity passkey = new UserPasskeyCredentialEntity();
        passkey.setUserPersonCode(80);
        passkey.setCredentialId("cred-id-for-user-80");
        passkey.setPublicKeyCose(new byte[] { 1, 2, 3, 4 });
        passkey.setSignCount(0L);
        passkey.setDeviceName("iPhone");
        passkey.setCreatedAt(LocalDateTime.now());
        userPasskeyCredentialRepository.save(passkey);

        LoginUserCapsuleDto capsuleDto = new LoginUserCapsuleDto();
        capsuleDto.setUserId("aaa@politician.balanse.report.net");
        capsuleDto.setPassword("qwerty1234");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();
        String path = PathRouteConstants.ROOT + "/login";

        MvcResult result = mockMvc.perform(post(path).content(objectMapper.writeValueAsString(capsuleDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        LoginUserResultDto resultDto = objectMapper.readValue( //
                result.getResponse().getContentAsString(), LoginUserResultDto.class); // NOPMD LawDemeter

        assertNotNull(resultDto);
        assertTrue(resultDto.getRequiresPasskey());
        assertNull(resultDto.getJwtTokenDto());
        assertNotNull(resultDto.getPreAuthToken());
        assertFalse(resultDto.getPreAuthToken().isBlank());
        assertNotNull(resultDto.getChallenge());
        assertFalse(resultDto.getChallenge().isBlank());
        assertEquals(1, resultDto.getAllowCredentials().size());
        assertEquals("cred-id-for-user-80", resultDto.getAllowCredentials().get(0));

        // クリーンアップ
        userPasskeyCredentialRepository.deleteAll();
    }
}
