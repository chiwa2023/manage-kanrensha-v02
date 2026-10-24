package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import java.time.LocalDateTime; // NOPMD TooManyImports
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webauthn4j.WebAuthnManager;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.credential.CredentialRecord;
import com.webauthn4j.credential.CredentialRecordImpl;
import com.webauthn4j.data.AuthenticationData;
import com.webauthn4j.data.AuthenticationParameters;
import com.webauthn4j.data.AuthenticationRequest;
import com.webauthn4j.data.attestation.authenticator.AAGUID;
import com.webauthn4j.data.attestation.authenticator.AttestedCredentialData;
import com.webauthn4j.data.attestation.authenticator.COSEKey;
import com.webauthn4j.data.client.Origin;
import com.webauthn4j.data.client.challenge.Challenge;
import com.webauthn4j.data.client.challenge.DefaultChallenge;
import com.webauthn4j.server.ServerProperty;

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
 * パスキー2FA認証Service
 */
@Service
public class PasskeyAuthenticationService { // NOPMD CouplingObjects

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

    /** Pre-Auth Token管理Service */
    @Autowired
    private PreAuthTokenService preAuthTokenService;

    /** パスキークレデンシャルRepository */
    @Autowired
    private UserPasskeyCredentialRepository credentialRepository;

    /** JWT Service */
    @Autowired
    private JwtService jwtService;

    /**
     * 2段階目のWebAuthn認証用Challengeを発行する
     *
     * @param preAuthToken 1段階目認証で払い出されたPre-Auth Token
     * @return 認証用Challenge応答Dto
     */
    public PasskeyAuthenticationChallengeResponseDto generateAuthenticationChallenge(final String preAuthToken) {
        PreAuthSession session = preAuthTokenService.getPreAuthSession(preAuthToken);
        if (session == null) {
            throw new IllegalArgumentException("認証セッションが存在しないか有効期限が切れています。もう一度最初からログインしてください。");
        }

        String challengeStr = challengeService.generateAuthenticationChallenge(preAuthToken);

        List<UserPasskeyCredentialEntity> credList = credentialRepository
                .findByUserPersonCode(session.userPersonCode());
        List<String> allowCredentials = credList.stream().map(UserPasskeyCredentialEntity::getCredentialId).toList();

        PasskeyAuthenticationChallengeResponseDto dto = new PasskeyAuthenticationChallengeResponseDto();
        dto.setChallenge(challengeStr);
        dto.setRpId(webAuthnConfig.getRpId());
        dto.setAllowCredentials(allowCredentials);
        return dto;
    }

    /**
     * パスキーAssertionを検証し、正式なJWTトークンを発行する
     *
     * @param requestDto   検証リクエストDto
     * @param clientOrigin リクエスト元Origin
     * @return 正式JWTトークンを含むLoginUserResultDto
     */
    @Transactional
    public LoginUserResultDto verifyAndAuthenticate(final PasskeyAuthenticationVerifyRequestDto requestDto,
            final String clientOrigin) {

        String token = requestDto.getPreAuthToken();
        PreAuthSession session = this.getPreAuthSession(token);
        // PreAuthSession session = preAuthTokenService.getPreAuthSession(token);
        // if (session == null) {
        // throw new
        // IllegalArgumentException("認証セッションが存在しないか有効期限が切れています。もう一度最初からログインしてください。");
        // }

        String storedChallenge = this.getStoredChallenge(token);
        // String storedChallenge = challengeService.getAuthenticationChallenge(token);
        // if (storedChallenge == null || storedChallenge.isBlank()) {
        // throw new IllegalArgumentException("認証チャレンジが存在しないか有効期限が切れています。");
        // }

        Optional<UserPasskeyCredentialEntity> optCred = this.getOptCred(requestDto.getCredentialId());
        // Optional<UserPasskeyCredentialEntity> optCred = credentialRepository
        // .findByCredentialId(requestDto.getCredentialId());
        // if (optCred.isEmpty()) {
        // throw new IllegalArgumentException("指定されたクレデンシャルが見つかりません。");
        // }

        UserPasskeyCredentialEntity credential = optCred.get();
        if (!credential.getUserPersonCode().equals(session.userPersonCode())) {
            throw new SecurityException("クレデンシャルの所有者が一致しません。");
        }

        Origin origin = resolveOrigin(clientOrigin);

        try {
            byte[] credentialIdBytes = Base64.getUrlDecoder().decode(requestDto.getCredentialId());
            byte[] userHandleBytes = this.getUserHandleBytes(requestDto);
            // byte[] userHandleBytes = (requestDto.getUserHandle() != null &&
            // !requestDto.getUserHandle().isBlank())
            // ? Base64.getUrlDecoder().decode(requestDto.getUserHandle())
            // : null;
            byte[] authenticatorDataBytes = Base64.getUrlDecoder().decode(requestDto.getAuthenticatorData());
            byte[] clientDataJSONBytes = Base64.getUrlDecoder().decode(requestDto.getClientDataJSON());
            byte[] signatureBytes = Base64.getUrlDecoder().decode(requestDto.getSignature());

            AuthenticationRequest authenticationRequest = new AuthenticationRequest(credentialIdBytes, userHandleBytes,
                    authenticatorDataBytes, clientDataJSONBytes, signatureBytes);

            COSEKey coseKey = objectConverter.getCborMapper().readValue( // NOPMD LawDemeter
                    credential.getPublicKeyCose(), COSEKey.class);

            AAGUID aaguid = this.getAaguid(credential);
            AttestedCredentialData attestedCredentialData = new AttestedCredentialData(aaguid, credentialIdBytes,
                    coseKey);

            CredentialRecord credentialRecord = new CredentialRecordImpl(
                    null,
                    null,
                    null,
                    null,
                    credential.getSignCount(),
                    attestedCredentialData,
                    null,
                    null,
                    null,
                    null);

            Challenge challenge = new DefaultChallenge(Base64.getUrlDecoder().decode(storedChallenge));
            ServerProperty serverProperty = ServerProperty.builder()
                    .origin(origin)
                    .rpId(webAuthnConfig.getRpId())
                    .challenge(challenge)
                    .build();
            AuthenticationParameters authenticationParameters = new AuthenticationParameters(serverProperty,
                    credentialRecord, null, false);

            AuthenticationData authenticationData = webAuthnManager.verify(authenticationRequest,
                    authenticationParameters);

            long newSignCount = authenticationData.getAuthenticatorData().getSignCount(); // NOPMD LawDemeter

            // クローン検知（署名カウンタ検証）: 認証器が署名カウンタをインクリメントしている場合、過去の値以下なら複製疑い
            if (newSignCount > 0 && newSignCount <= credential.getSignCount()) {
                throw new SecurityException("認証器の複製（クローン）が検知されたため、認証を拒否しました。");
            }

            // カウンタと利用日時を更新
            credential.setSignCount(newSignCount);
            credential.setLastUsedAt(LocalDateTime.now());
            credentialRepository.save(credential);

            // チャレンジとPreAuthセッションを消費
            challengeService.removeAuthenticationChallenge(token);
            preAuthTokenService.removePreAuthSession(token);

            // 正式なJWTトークンを生成
            JwtTokenDto jwtToken = jwtService.generateToken(session.authentication());

            LoginUserResultDto resultDto = new LoginUserResultDto();
            resultDto.setJwtTokenDto(jwtToken);
            resultDto.setUserDto(session.leastUserDto());
            resultDto.setRequiresPasskey(false);

            return resultDto;

        } catch (IllegalArgumentException | SecurityException e) {
            throw e;
        } catch (Exception e) { // NOPMD AvoidGenericException
            throw new IllegalArgumentException("パスキー認証検証に失敗しました: " + e.getMessage(), e);
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

    private PreAuthSession getPreAuthSession(final String token) {
        PreAuthSession session = preAuthTokenService.getPreAuthSession(token);
        if (session == null) {
            throw new IllegalArgumentException("認証セッションが存在しないか有効期限が切れています。もう一度最初からログインしてください。");
        }

        return session;
    }

    private String getStoredChallenge(final String token) {
        String storedChallenge = challengeService.getAuthenticationChallenge(token);
        if (storedChallenge == null || storedChallenge.isBlank()) {
            throw new IllegalArgumentException("認証チャレンジが存在しないか有効期限が切れています。");
        }

        return storedChallenge;
    }

    private Optional<UserPasskeyCredentialEntity> getOptCred(final String credentialId) {
        Optional<UserPasskeyCredentialEntity> optCred = credentialRepository.findByCredentialId(credentialId);
        if (optCred.isEmpty()) {
            throw new IllegalArgumentException("指定されたクレデンシャルが見つかりません。");
        }

        return optCred;
    }

    private AAGUID getAaguid(final UserPasskeyCredentialEntity credential) {

        if (credential.getAaguid() != null && !credential.getAaguid().isBlank()) {
            return new AAGUID(credential.getAaguid());
        } else {
            return AAGUID.ZERO;
        }
        // AAGUID aaguid = credential.getAaguid() != null &&
        // !credential.getAaguid().isBlank()
        // ? new AAGUID(credential.getAaguid())
        // : AAGUID.ZERO;
    }

    private byte[] getUserHandleBytes(final PasskeyAuthenticationVerifyRequestDto requestDto) {

        if (requestDto.getUserHandle() != null && !requestDto.getUserHandle().isBlank()) {
            return Base64.getUrlDecoder().decode(requestDto.getUserHandle());
        } else {
            return null;
        }
        // byte[] userHandleBytes = (requestDto.getUserHandle() != null &&
        // !requestDto.getUserHandle().isBlank())
        // ? Base64.getUrlDecoder().decode(requestDto.getUserHandle())
        // : null;
    }
}
