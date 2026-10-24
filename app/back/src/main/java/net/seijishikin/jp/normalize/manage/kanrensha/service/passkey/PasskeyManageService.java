package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeySummaryDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasskeyCredentialRepository;

/**
 * 登録済みパスキー一覧取得・削除Service
 */
@Service
public class PasskeyManageService {

    /** パスキークレデンシャルRepository */
    @Autowired
    private UserPasskeyCredentialRepository credentialRepository;

    /**
     * 登録済みパスキー一覧を取得する
     *
     * @param userPersonCode ユーザ人物コード
     * @return パスキーサマリーリスト
     */
    public List<PasskeySummaryDto> listPasskeys(final Integer userPersonCode) {
        List<UserPasskeyCredentialEntity> entityList = credentialRepository.findByUserPersonCode(userPersonCode);
        List<PasskeySummaryDto> result = new ArrayList<>();

        for (UserPasskeyCredentialEntity entity : entityList) {
            result.add(this.createPasskeySummary(entity));
        }

        return result;
    }

    /**
     * パスキーを削除する
     *
     * @param passkeyId      パスキーID
     * @param userPersonCode ユーザ人物コード
     * @return 削除成功時true
     */
    @Transactional
    public boolean deletePasskey(final Long passkeyId, final Integer userPersonCode) {
        long deletedCount = credentialRepository.deleteByPasskeyIdAndUserPersonCode(passkeyId, userPersonCode);
        return deletedCount > 0;
    }

    private PasskeySummaryDto createPasskeySummary(final UserPasskeyCredentialEntity entity) {
        PasskeySummaryDto dto = new PasskeySummaryDto();
        dto.setPasskeyId(entity.getPasskeyId());
        dto.setCredentialId(entity.getCredentialId());
        dto.setDeviceName(entity.getDeviceName());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setLastUsedAt(entity.getLastUsedAt());
        if (entity.getAaguid() == null) {
            dto.setAaguid("");
        } else {
            dto.setAaguid(entity.getAaguid());
        }
        return dto;
    }

}
