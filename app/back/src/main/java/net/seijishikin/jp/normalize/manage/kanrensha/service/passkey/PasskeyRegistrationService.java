package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.webauthn4j.data.client.Origin;
import com.webauthn4j.data.client.challenge.Challenge;
import com.webauthn4j.data.client.challenge.DefaultChallenge;
import com.webauthn4j.data.extension.authenticator.RegistrationExtensionAuthenticatorOutput;
import com.webauthn4j.server.ServerProperty;

import net.seijishikin.jp.normalize.manage.kanrensha.config.WebAuthnConfig;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationChallengeDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationVerifyRequestDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasskeyCredentialRepository;

/**
 * パスキー登録Service
 */
@Service
public class PasskeyRegistrationService { // NOPMD CouplingObjects

    /** WebAuthn設定 */
    @Autowired
    private WebAuthnConfig webAuthnConfig;

    /** WebAuthnManager */
    @Autowired
    private WebAuthnManager webAuthnManager;

    /** ObjectConverter */
    @Autowired
    private ObjectConverter objectConverter;

    /** Challenge管理Service */
    @Autowired
    private WebAuthnChallengeService challengeService;

    /** パスキークレデンシャルRepository */
    @Autowired
    private UserPasskeyCredentialRepository credentialRepository;

    /**
     * パスキー登録用Challengeを生成する
     *
     * @param userPersonCode ユーザ人物コード
     * @param username       ユーザ名 (ログインID)
     * @param displayName    ユーザ表示名
     * @return 登録用Challenge Dto
     */
    public PasskeyRegistrationChallengeDto generateRegistrationChallenge(final Integer userPersonCode,
            final String username, final String displayName) {

        String challengeStr = challengeService.generateRegistrationChallenge(userPersonCode);

        PasskeyRegistrationChallengeDto dto = new PasskeyRegistrationChallengeDto();
        dto.setChallenge(challengeStr);
        dto.setRpId(webAuthnConfig.getRpId());
        dto.setRpName(webAuthnConfig.getRpName());
        dto.setUserPersonCode(userPersonCode);
        if (username == null) {
            dto.setUserName("");
        } else {
            dto.setUserName(username);
        }
        if (displayName == null) {
            dto.setUserDisplayName("");
        } else {
            dto.setUserDisplayName(displayName);
        }

        // 既存のクレデンシャルID一覧を取得（重複登録防止）
        List<UserPasskeyCredentialEntity> existingList = credentialRepository.findByUserPersonCode(userPersonCode);
        List<String> excludeIds = existingList.stream().map(UserPasskeyCredentialEntity::getCredentialId).toList();
        dto.setExcludeCredentialIds(excludeIds);

        return dto;
    }

    /**
     * パスキー登録Attestationを検証しDBに保存する
     *
     * @param userPersonCode ユーザ人物コード
     * @param requestDto     検証リクエストDto
     * @param clientOrigin   リクエスト元Origin
     * @return 登録結果Dto
     */
    @Transactional
    public PasskeyRegistrationResultDto verifyAndRegister(final Integer userPersonCode,
            final PasskeyRegistrationVerifyRequestDto requestDto, final String clientOrigin) {

        String storedChallenge = challengeService.getRegistrationChallenge(userPersonCode);
        if (storedChallenge == null || storedChallenge.isBlank()) {
            throw new IllegalArgumentException("登録チャレンジが存在しないか有効期限が切れています。もう一度最初からお試しください。");
        }

        // Originの判定
        Origin origin = resolveOrigin(clientOrigin);

        try {
            byte[] attestationObjectBytes = Base64.getUrlDecoder().decode(requestDto.getAttestationObject());
            byte[] clientDataJSONBytes = Base64.getUrlDecoder().decode(requestDto.getClientDataJSON());

            RegistrationRequest registrationRequest = new RegistrationRequest(attestationObjectBytes,
                    clientDataJSONBytes);

            Challenge challenge = new DefaultChallenge(Base64.getUrlDecoder().decode(storedChallenge));
            ServerProperty serverProperty = ServerProperty.builder()
                    .origin(origin)
                    .rpId(webAuthnConfig.getRpId())
                    .challenge(challenge)
                    .build();
            RegistrationParameters registrationParameters = new RegistrationParameters(serverProperty, null, false);

            RegistrationData registrationData = webAuthnManager.verify(registrationRequest, registrationParameters);

            AttestationObject attestationObject = registrationData.getAttestationObject(); // NOPMD LawDemeter
            AuthenticatorData<RegistrationExtensionAuthenticatorOutput> authData = attestationObject
                    .getAuthenticatorData();
            AttestedCredentialData credentialData = authData.getAttestedCredentialData();

            byte[] credentialIdBytes = credentialData.getCredentialId();
            String credentialId = Base64.getUrlEncoder().withoutPadding().encodeToString(credentialIdBytes);

            // 既に同一CredentialIdが存在するかチェック
            Optional<UserPasskeyCredentialEntity> existing = credentialRepository.findByCredentialId(credentialId);
            if (existing.isPresent()) {
                throw new IllegalStateException("このクレデンシャルは既に登録されています。");
            }

            AAGUID aaguid = credentialData.getAaguid();
            String aaguidStr;
            if (Objects.isNull(aaguid)) {
                aaguidStr = "";
            } else {
                aaguidStr = aaguid.toString();
            }

            UserPasskeyCredentialEntity entity = new UserPasskeyCredentialEntity();
            entity.setUserPersonCode(userPersonCode);
            entity.setCredentialId(credentialId);

            COSEKey coseKey = credentialData.getCOSEKey();
            byte[] coseKeyBytes = objectConverter.getCborMapper().writeValueAsBytes(coseKey); // NOPMD LawDemeter
            entity.setPublicKeyCose(coseKeyBytes);

            entity.setSignCount(authData.getSignCount());
            entity.setAaguid(aaguidStr);
            if (requestDto.getDeviceName() != null && !requestDto.getDeviceName().isBlank()) {
                entity.setDeviceName(requestDto.getDeviceName());
            } else {
                entity.setDeviceName("Passkey");
            }
            entity.setCreatedAt(LocalDateTime.now());
            entity.setLastUsedAt(LocalDateTime.now());

            UserPasskeyCredentialEntity saved = credentialRepository.save(entity);

            // チャレンジを消費
            challengeService.removeRegistrationChallenge(userPersonCode);

            PasskeyRegistrationResultDto result = new PasskeyRegistrationResultDto();
            result.setSuccess(true);
            result.setPasskeyId(saved.getPasskeyId());
            result.setCredentialId(saved.getCredentialId());
            result.setDeviceName(saved.getDeviceName());
            result.setMessage("パスキーの登録が正常に完了しました。");
            return result;

        } catch (IllegalArgumentException | IllegalStateException e) {
            throw e;
        } catch (Exception exception) { // NOPMD AvoidGenericException
            throw new IllegalArgumentException("パスキー登録検証に失敗しました: " + exception.getMessage(), exception);
        }
    }

    /**
     * Originを解決する
     */
    private Origin resolveOrigin(final String clientOrigin) {
        if (clientOrigin != null && webAuthnConfig.isOriginAllowed(clientOrigin)) {
            return new Origin(clientOrigin);
        }
        if (webAuthnConfig.getAllowedOrigins() != null && !webAuthnConfig.getAllowedOrigins().isEmpty()) {
            return new Origin(webAuthnConfig.getAllowedOrigins().get(0));
        }
        return new Origin("http://localhost:5173");
    }
}
