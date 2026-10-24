package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webauthn4j.WebAuthnManager;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.data.RegistrationData;
import com.webauthn4j.data.RegistrationParameters;
import com.webauthn4j.data.RegistrationRequest;
import com.webauthn4j.data.attestation.AttestationObject;
import com.webauthn4j.data.attestation.authenticator.AAGUID;
import com.webauthn4j.data.attestation.authenticator.AttestedCredentialData;
import com.webauthn4j.data.attestation.authenticator.AuthenticatorData;
import com.webauthn4j.data.attestation.authenticator.COSEKey;
import com.webauthn4j.data.extension.authenticator.RegistrationExtensionAuthenticatorOutput;
import tools.jackson.dataformat.cbor.CBORMapper;

import net.seijishikin.jp.normalize.manage.kanrensha.config.WebAuthnConfig;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationChallengeDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationVerifyRequestDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasskeyCredentialRepository;

/**
 * PasskeyRegistrationService単体テスト
 */
@ExtendWith(MockitoExtension.class)
class PasskeyRegistrationServiceTest {

    /** テスト対象 */
    @InjectMocks
    private PasskeyRegistrationService registrationService;

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

    /** UserPasskeyCredentialRepository */
    @Mock
    private UserPasskeyCredentialRepository credentialRepository;

    @Test
    void testGenerateRegistrationChallenge() {
        final Integer userPersonCode = 40001;

        when(challengeService.generateRegistrationChallenge(userPersonCode)).thenReturn("mock-challenge-base64url");
        when(webAuthnConfig.getRpId()).thenReturn("localhost");
        when(webAuthnConfig.getRpName()).thenReturn("manage-kanrensha");

        UserPasskeyCredentialEntity existingCred = new UserPasskeyCredentialEntity();
        existingCred.setCredentialId("existing-cred-id");
        when(credentialRepository.findByUserPersonCode(userPersonCode)).thenReturn(List.of(existingCred));

        String username = "user@example.com";
        String displayName = "田中太郎";

        PasskeyRegistrationChallengeDto dto = registrationService.generateRegistrationChallenge(userPersonCode,
                username, displayName);

        assertNotNull(dto);
        assertEquals("mock-challenge-base64url", dto.getChallenge());
        assertEquals("localhost", dto.getRpId());
        assertEquals("manage-kanrensha", dto.getRpName());
        assertEquals(userPersonCode, dto.getUserPersonCode());
        assertEquals(username, dto.getUserName());
        assertEquals(displayName, dto.getUserDisplayName());
        assertEquals(1, dto.getExcludeCredentialIds().size());
        assertEquals("existing-cred-id", dto.getExcludeCredentialIds().get(0));
    }

    @Test
    void testVerifyAndRegisterChallengeMissingThrowsException() {
        final Integer userPersonCode = 40002;
        PasskeyRegistrationVerifyRequestDto requestDto = new PasskeyRegistrationVerifyRequestDto();

        when(challengeService.getRegistrationChallenge(userPersonCode)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            registrationService.verifyAndRegister(userPersonCode, requestDto, "http://localhost:5173");
        });
    }

    @Test
    void testVerifyAndRegisterSuccess() {
        final Integer userPersonCode = 40003;
        String credIdBytesStr = "registered-cred-id";
        byte[] credIdBytes = credIdBytesStr.getBytes();
        String credIdBase64 = Base64.getUrlEncoder().withoutPadding().encodeToString(credIdBytes);

        PasskeyRegistrationVerifyRequestDto requestDto = new PasskeyRegistrationVerifyRequestDto();
        requestDto.setAttestationObject(Base64.getUrlEncoder().withoutPadding().encodeToString(new byte[10]));
        requestDto.setClientDataJSON(Base64.getUrlEncoder().withoutPadding().encodeToString("{}".getBytes()));
        requestDto.setDeviceName("MacBook TouchID");

        String challengeStr = Base64.getUrlEncoder().withoutPadding().encodeToString("challenge-bytes".getBytes());
        when(challengeService.getRegistrationChallenge(userPersonCode)).thenReturn(challengeStr);
        when(webAuthnConfig.isOriginAllowed("http://localhost:5173")).thenReturn(true);
        when(webAuthnConfig.getRpId()).thenReturn("localhost");

        when(credentialRepository.findByCredentialId(credIdBase64)).thenReturn(Optional.empty());

        RegistrationData regData = Mockito.mock(RegistrationData.class);
        AttestationObject attestationObject = Mockito.mock(AttestationObject.class);
        @SuppressWarnings("unchecked")
        AuthenticatorData<RegistrationExtensionAuthenticatorOutput> authData = Mockito.mock(AuthenticatorData.class);
        AttestedCredentialData credentialData = Mockito.mock(AttestedCredentialData.class);
        COSEKey coseKey = Mockito.mock(COSEKey.class);

        when(webAuthnManager.verify(ArgumentMatchers.any(RegistrationRequest.class),
                ArgumentMatchers.any(RegistrationParameters.class))).thenReturn(regData);
        when(regData.getAttestationObject()).thenReturn(attestationObject);
        when(attestationObject.getAuthenticatorData()).thenReturn(authData);
        when(authData.getAttestedCredentialData()).thenReturn(credentialData);
        when(credentialData.getCredentialId()).thenReturn(credIdBytes);
        when(credentialData.getAaguid()).thenReturn(AAGUID.ZERO);
        when(credentialData.getCOSEKey()).thenReturn(coseKey);
        when(authData.getSignCount()).thenReturn(0L);

        CBORMapper cborMapper = Mockito.mock(CBORMapper.class);
        when(objectConverter.getCborMapper()).thenReturn(cborMapper);
        when(cborMapper.writeValueAsBytes(coseKey)).thenReturn(new byte[] {1, 2, 3});

        when(credentialRepository.save(ArgumentMatchers.any(UserPasskeyCredentialEntity.class)))
                .thenAnswer(invocation -> {
                    UserPasskeyCredentialEntity e = invocation.getArgument(0);
                    e.setPasskeyId(123L);
                    return e;
                });

        PasskeyRegistrationResultDto result = registrationService.verifyAndRegister(userPersonCode, requestDto,
                "http://localhost:5173");

        assertNotNull(result);
        assertEquals(true, result.isSuccess());
        assertEquals(123L, result.getPasskeyId());
        assertEquals(credIdBase64, result.getCredentialId());
        assertEquals("MacBook TouchID", result.getDeviceName());
    }
}
