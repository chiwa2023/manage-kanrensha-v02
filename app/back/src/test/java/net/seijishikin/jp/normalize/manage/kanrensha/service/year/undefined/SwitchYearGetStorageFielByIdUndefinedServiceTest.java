package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetStorageFielByIdService;

/**
 * SwitchYearGetStorageFielByIdService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SwitchYearGetStorageFielByIdUndefinedServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetStorageFielByIdService switchYearGetStorageFielByIdService;

    @Test
    @Tag("TableTruncate")
    void test2019() throws Exception {

        assertThrows(IllegalArgumentException.class, () -> switchYearGetStorageFielByIdService.practice(1001, 12345));

    }

}
