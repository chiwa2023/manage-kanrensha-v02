package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.webauthn4j.WebAuthnManager;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.data.AuthenticationData;
import com.webauthn4j.data.AuthenticationParameters;
import com.webauthn4j.data.AuthenticationRequest;
import com.webauthn4j.data.attestation.authenticator.COSEKey;
import tools.jackson.dataformat.cbor.CBORMapper;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.config.WebAuthnConfig;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyAuthenticationChallengeResponseDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyAuthenticationVerifyRequestDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasskeyCredentialRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PreAuthTokenService.PreAuthSession;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.JwtService;

/**
 * PasskeyAuthenticationService単体テスト
 */
@ExtendWith(MockitoExtension.class)
class PasskeyAuthenticationServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @InjectMocks
    private PasskeyAuthenticationService authenticationService;

    /** WebAuthnConfig */
    @Mock
    private WebAuthnConfig webAuthnConfig;

    /** WebAuthnManager */
    @Mock
    private WebAuthnManager webAuthnManager;

    /** ObjectConverter */
    @Mock
    private ObjectConverter objectConverter;

    /** WebAuthnChallengeService */
    @Mock
    private WebAuthnChallengeService challengeService;

    /** PreAuthTokenService */
    @Mock
    private PreAuthTokenService preAuthTokenService;

    /** UserPasskeyCredentialRepository */
    @Mock
    private UserPasskeyCredentialRepository credentialRepository;

    /** JwtService */
    @Mock
    private JwtService jwtService;

    @Test
    void testGenerateAuthenticationChallenge() {
        String preAuthToken = "token-12345";
        Integer userPersonCode = 50001;

        Authentication auth = new UsernamePasswordAuthenticationToken("user@example.com", "pass",
                Collections.emptyList());
        PreAuthSession session = new PreAuthSession(preAuthToken, userPersonCode, "user@example.com", auth,
                new LeastUserDto(), Instant.now().plusSeconds(300));

        when(preAuthTokenService.getPreAuthSession(preAuthToken)).thenReturn(session);
        when(challengeService.generateAuthenticationChallenge(preAuthToken)).thenReturn("auth-challenge-xyz");
        when(webAuthnConfig.getRpId()).thenReturn("localhost");

        UserPasskeyCredentialEntity cred = new UserPasskeyCredentialEntity();
        cred.setCredentialId("cred-user-key");
        when(credentialRepository.findByUserPersonCode(userPersonCode)).thenReturn(List.of(cred));

        PasskeyAuthenticationChallengeResponseDto dto = authenticationService
                .generateAuthenticationChallenge(preAuthToken);
        assertNotNull(dto);
        assertEquals("auth-challenge-xyz", dto.getChallenge());
        assertEquals("localhost", dto.getRpId());
        assertEquals(1, dto.getAllowCredentials().size());
        assertEquals("cred-user-key", dto.getAllowCredentials().get(0));
    }

    @Test
    void testGenerateAuthenticationChallengeInvalidToken() {
        when(preAuthTokenService.getPreAuthSession("invalid")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.generateAuthenticationChallenge("invalid");
        });
    }

    @Test
    void testVerifyAndAuthenticateSessionInvalidThrowsException() {
        PasskeyAuthenticationVerifyRequestDto requestDto = new PasskeyAuthenticationVerifyRequestDto();
        requestDto.setPreAuthToken("expired-token");

        when(preAuthTokenService.getPreAuthSession("expired-token")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.verifyAndAuthenticate(requestDto, "http://localhost:5173");
        });
    }

    @Test
    void testVerifyAndAuthenticateChallengeMissingThrowsException() {
        String token = "valid-token";
        PasskeyAuthenticationVerifyRequestDto requestDto = new PasskeyAuthenticationVerifyRequestDto();
        requestDto.setPreAuthToken(token);

        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p", Collections.emptyList());
        PreAuthSession session = new PreAuthSession(token, 10, "u", auth, new LeastUserDto(),
                Instant.now().plusSeconds(300));

        when(preAuthTokenService.getPreAuthSession(token)).thenReturn(session);
        when(challengeService.getAuthenticationChallenge(token)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.verifyAndAuthenticate(requestDto, "http://localhost:5173");
        });
    }

    @Test
    void testVerifyAndAuthenticateSuccess() {
        String token = "valid-pre-auth-token";
        Integer userPersonCode = 50001;
        String credId = Base64.getUrlEncoder().withoutPadding().encodeToString("test-cred-id".getBytes());

        PasskeyAuthenticationVerifyRequestDto requestDto = new PasskeyAuthenticationVerifyRequestDto();
        requestDto.setPreAuthToken(token);
        requestDto.setCredentialId(credId);
        requestDto.setAuthenticatorData(Base64.getUrlEncoder().withoutPadding().encodeToString(new byte[37]));
        requestDto.setClientDataJSON(Base64.getUrlEncoder().withoutPadding().encodeToString("{}".getBytes()));
        requestDto.setSignature(Base64.getUrlEncoder().withoutPadding().encodeToString("sig".getBytes()));

        Authentication auth = new UsernamePasswordAuthenticationToken("user@example.com", "pass",
                Collections.emptyList());
        LeastUserDto leastUserDto = new LeastUserDto();
        leastUserDto.setUserPersonCode(userPersonCode);
        PreAuthSession session = new PreAuthSession(token, userPersonCode, "user@example.com", auth,
                leastUserDto, Instant.now().plusSeconds(300));

        when(preAuthTokenService.getPreAuthSession(token)).thenReturn(session);
        String challengeStr = Base64.getUrlEncoder().withoutPadding().encodeToString("test-challenge".getBytes());
        when(challengeService.getAuthenticationChallenge(token)).thenReturn(challengeStr);

        UserPasskeyCredentialEntity credential = new UserPasskeyCredentialEntity();
        credential.setUserPersonCode(userPersonCode);
        credential.setCredentialId(credId);
        credential.setSignCount(10L);
        credential.setPublicKeyCose(new byte[10]);
        credential.setAaguid("00000000-0000-0000-0000-000000000000");
        when(credentialRepository.findByCredentialId(credId)).thenReturn(Optional.of(credential));

        when(webAuthnConfig.isOriginAllowed("http://localhost:5173")).thenReturn(true);
        when(webAuthnConfig.getRpId()).thenReturn("localhost");

        CBORMapper cborMapper = Mockito.mock(CBORMapper.class);
        COSEKey coseKey = Mockito.mock(COSEKey.class);
        when(objectConverter.getCborMapper()).thenReturn(cborMapper);
        when(cborMapper.readValue(credential.getPublicKeyCose(), COSEKey.class)).thenReturn(coseKey);

        AuthenticationData authData = Mockito.mock(AuthenticationData.class);
        @SuppressWarnings("unchecked")
        com.webauthn4j.data.attestation.authenticator.AuthenticatorData<com.webauthn4j.data.extension.authenticator.AuthenticationExtensionAuthenticatorOutput> authDataInner = Mockito
                .mock(com.webauthn4j.data.attestation.authenticator.AuthenticatorData.class);
        when(authData.getAuthenticatorData()).thenReturn(authDataInner);
        when(authDataInner.getSignCount()).thenReturn(15L);

        when(webAuthnManager.verify(ArgumentMatchers.any(AuthenticationRequest.class),
                ArgumentMatchers.any(AuthenticationParameters.class))).thenReturn(authData);

        JwtTokenDto jwtTokenDto = new JwtTokenDto("generated-jwt-token", "refresh-token", null);
        when(jwtService.generateToken(auth)).thenReturn(jwtTokenDto);

        LoginUserResultDto result = authenticationService.verifyAndAuthenticate(requestDto, "http://localhost:5173");

        assertNotNull(result);
        assertEquals("generated-jwt-token", result.getJwtTokenDto().getAccessToken());
        assertEquals(userPersonCode, result.getUserDto().getUserPersonCode());
        org.junit.jupiter.api.Assertions.assertFalse(result.getRequiresPasskey());
        assertEquals(15L, credential.getSignCount());
    }
}
