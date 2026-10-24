package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;

/**
 * UserPasskeyCredentialRepository単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
class UserPasskeyCredentialRepositoryTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UserPasskeyCredentialRepository userPasskeyCredentialRepository;

    @Test
    @Tag("TableTruncate")
    void testCrud() {
        Integer userPersonCode = 99991;
        String credId = "test-cred-id-12345";

        UserPasskeyCredentialEntity entity = new UserPasskeyCredentialEntity();
        entity.setUserPersonCode(userPersonCode);
        entity.setCredentialId(credId);
        entity.setPublicKeyCose(new byte[] { 1, 2, 3, 4 });
        entity.setSignCount(0L);
        entity.setAaguid("00000000-0000-0000-0000-000000000000");
        entity.setDeviceName("Test PC");
        entity.setCreatedAt(LocalDateTime.now());
        entity.setLastUsedAt(LocalDateTime.now());

        UserPasskeyCredentialEntity saved = userPasskeyCredentialRepository.save(entity);
        assertNotNull(saved.getPasskeyId());

        // Count
        long count = userPasskeyCredentialRepository.countByUserPersonCode(userPersonCode);
        assertEquals(1, count);

        // Find by credentialId
        Optional<UserPasskeyCredentialEntity> byCred = userPasskeyCredentialRepository.findByCredentialId(credId);
        assertTrue(byCred.isPresent());
        assertEquals("Test PC", byCred.get().getDeviceName());

        // Find by userPersonCode
        List<UserPasskeyCredentialEntity> list = userPasskeyCredentialRepository.findByUserPersonCode(userPersonCode);
        assertEquals(1, list.size());

        // Delete
        long deleted = userPasskeyCredentialRepository.deleteByPasskeyIdAndUserPersonCode(saved.getPasskeyId(),
                userPersonCode);
        assertEquals(1, deleted);

        // Verify count is 0
        assertEquals(0, userPasskeyCredentialRepository.countByUserPersonCode(userPersonCode));
        assertFalse(userPasskeyCredentialRepository.findByCredentialId(credId).isPresent());
    }
}
