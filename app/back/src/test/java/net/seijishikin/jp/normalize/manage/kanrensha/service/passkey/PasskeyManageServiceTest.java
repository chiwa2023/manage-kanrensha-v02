package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD TooManyImports
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeySummaryDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasskeyCredentialRepository;

/**
 * PasskeyManageService単体テスト
 */
@ExtendWith(MockitoExtension.class)
class PasskeyManageServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @InjectMocks
    private PasskeyManageService passkeyManageService;

    /** UserPasskeyCredentialRepository */
    @Mock
    private UserPasskeyCredentialRepository credentialRepository;

    @Test
    void testListPasskeys() {

        UserPasskeyCredentialEntity entity = new UserPasskeyCredentialEntity();
        entity.setPasskeyId(101L);
        entity.setCredentialId("cred-abc-123");
        entity.setDeviceName("iPhone TouchID");
        entity.setAaguid("00000000-0000-0000-0000-000000000000");
        entity.setCreatedAt(LocalDateTime.of(2026, 10, 23, 10, 0));
        entity.setLastUsedAt(LocalDateTime.of(2026, 10, 23, 12, 0));

        final Integer userPersonCode = 30001;
        when(credentialRepository.findByUserPersonCode(userPersonCode)).thenReturn(List.of(entity));

        List<PasskeySummaryDto> list = passkeyManageService.listPasskeys(userPersonCode);
        assertEquals(1, list.size());
        assertEquals(101L, list.get(0).getPasskeyId());
        assertEquals("cred-abc-123", list.get(0).getCredentialId());
        assertEquals("iPhone TouchID", list.get(0).getDeviceName());
    }

    @Test
    void testListPasskeysEmpty() {
        final Integer userPersonCode = 30002;
        when(credentialRepository.findByUserPersonCode(userPersonCode)).thenReturn(Collections.emptyList());

        List<PasskeySummaryDto> list = passkeyManageService.listPasskeys(userPersonCode);
        assertTrue(list.isEmpty());
    }

    @Test
    void testDeletePasskeySuccess() {
        final Long passkeyId = 202L;
        final Integer userPersonCode = 30001;

        when(credentialRepository.deleteByPasskeyIdAndUserPersonCode(passkeyId, userPersonCode)).thenReturn(1L);

        boolean result = passkeyManageService.deletePasskey(passkeyId, userPersonCode);
        assertTrue(result);
        verify(credentialRepository).deleteByPasskeyIdAndUserPersonCode(passkeyId, userPersonCode);
    }

    @Test
    void testDeletePasskeyNotFound() {
        final Long passkeyId = 999L;
        final Integer userPersonCode = 30001;

        when(credentialRepository.deleteByPasskeyIdAndUserPersonCode(passkeyId, userPersonCode)).thenReturn(0L);

        boolean result = passkeyManageService.deletePasskey(passkeyId, userPersonCode);
        assertFalse(result);
    }
}
