package net.seijishikin.jp.normalize.manage.kanrensha.controller.passkey;

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD TooManyImports
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyAuthenticationChallengeRequestDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyAuthenticationChallengeResponseDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationChallengeDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeySummaryDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.GetLoginLeastUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PasskeyAuthenticationService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PasskeyManageService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PasskeyRegistrationService;

/**
 * PasskeyController単体テスト
 */
@ExtendWith(MockitoExtension.class)
class PasskeyControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @InjectMocks
    private PasskeyController passkeyController;

    /** ログインユーザ最小限Logic */
    @Mock
    private GetLoginLeastUserLogic getLoginLeastUserLogic;

    /** パスキー登録Service */
    @Mock
    private PasskeyRegistrationService passkeyRegistrationService;

    /** パスキー2FA認証Service */
    @Mock
    private PasskeyAuthenticationService passkeyAuthenticationService;

    /** 登録済みパスキー一覧取得・削除Service */
    @Mock
    private PasskeyManageService passkeyManageService;

    /** MockMvc */
    private MockMvc mockMvc;
    /** ObjectMapper */
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(passkeyController).build();
        objectMapper = GetObjectMapperWithTimeModuleUtil.practice();
    }

    @Test
    void testRegisterChallengeSuccess() throws Exception {
        LeastUserDto user = new LeastUserDto();
        user.setUserPersonCode(80);
        user.setUserPersonName("aaa");
        when(getLoginLeastUserLogic.practice()).thenReturn(user);

        PasskeyRegistrationChallengeDto challengeDto = new PasskeyRegistrationChallengeDto();
        challengeDto.setChallenge("sample-reg-challenge");
        challengeDto.setRpId("localhost");
        challengeDto.setRpName("manage-kanrensha");
        when(passkeyRegistrationService.generateRegistrationChallenge(eq(80), eq("aaa"), eq("aaa")))
                .thenReturn(challengeDto);

        String path = PathRouteConstants.ROOT + "/passkey/register/challenge";
        MvcResult result = mockMvc.perform(post(path)).andExpect(status().isOk()).andReturn();

        PasskeyRegistrationChallengeDto res = objectMapper.readValue( //
                result.getResponse().getContentAsString() // NOPMD LawDemeter
                , PasskeyRegistrationChallengeDto.class);
        assertEquals("sample-reg-challenge", res.getChallenge());
        assertEquals("localhost", res.getRpId());
    }

    @Test
    void testAuthenticateChallengeSuccess() throws Exception {
        PasskeyAuthenticationChallengeRequestDto req = new PasskeyAuthenticationChallengeRequestDto();
        req.setPreAuthToken("sample-pre-auth-token");

        PasskeyAuthenticationChallengeResponseDto resDto = new PasskeyAuthenticationChallengeResponseDto();
        resDto.setChallenge("auth-challenge-abc");
        resDto.setRpId("localhost");
        resDto.setAllowCredentials(List.of("cred-1", "cred-2"));

        when(passkeyAuthenticationService.generateAuthenticationChallenge("sample-pre-auth-token")).thenReturn(resDto);

        String path = PathRouteConstants.ROOT + "/passkey/authenticate/challenge";
        MvcResult result = mockMvc.perform(
                post(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk()).andReturn();

        PasskeyAuthenticationChallengeResponseDto res = objectMapper
                .readValue(result.getResponse().getContentAsString() // NOPMD LawDemeter
                        , PasskeyAuthenticationChallengeResponseDto.class);
        assertEquals("auth-challenge-abc", res.getChallenge());
        assertEquals(2, res.getAllowCredentials().size());
    }

    @Test
    void testListPasskeysSuccess() throws Exception {
        LeastUserDto user = new LeastUserDto();
        user.setUserPersonCode(80);
        when(getLoginLeastUserLogic.practice()).thenReturn(user);

        PasskeySummaryDto summary = new PasskeySummaryDto();
        summary.setPasskeyId(1L);
        summary.setCredentialId("cred-1");
        summary.setDeviceName("MacBook");
        summary.setCreatedAt(LocalDateTime.now());
        when(passkeyManageService.listPasskeys(80)).thenReturn(List.of(summary));

        String path = PathRouteConstants.ROOT + "/passkey/list";
        MvcResult result = mockMvc.perform(get(path)).andExpect(status().isOk()).andReturn();

        List<?> list = objectMapper.readValue(result.getResponse().getContentAsString() // NOPMD LawDemeter
                , List.class);
        assertEquals(1, list.size());
    }

    @Test
    void testDeletePasskeySuccess() throws Exception {
        LeastUserDto user = new LeastUserDto();
        user.setUserPersonCode(80);
        when(getLoginLeastUserLogic.practice()).thenReturn(user);
        when(passkeyManageService.deletePasskey(1L, 80)).thenReturn(true);

        String path = PathRouteConstants.ROOT + "/passkey/1";
        mockMvc.perform(delete(path)).andExpect(status().isNoContent());
    }

    @Test
    void testDeletePasskeyNotFound() throws Exception {
        LeastUserDto user = new LeastUserDto();
        user.setUserPersonCode(80);
        when(getLoginLeastUserLogic.practice()).thenReturn(user);
        when(passkeyManageService.deletePasskey(999L, 80)).thenReturn(false);

        String path = PathRouteConstants.ROOT + "/passkey/999";
        mockMvc.perform(delete(path)).andExpect(status().isNotFound());
    }
}
