package net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * WriteLogAddressFormatLogic単体テスト
 */
class WriteLogAddressFormatLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WriteLogAddressFormatLogic formatLogic = new WriteLogAddressFormatLogic();

        formatLogic.practice(WriteLogAddressFormatLogic.ERROR, "aaa");
        formatLogic.practice(WriteLogAddressFormatLogic.WARN, "aaa", "bbb");
        formatLogic.practice(WriteLogAddressFormatLogic.INFO, "123", "456");

        assertThrows(IllegalArgumentException.class, () -> formatLogic.practice(100, "aaa"));

        String str = null;
        assertDoesNotThrow(() -> formatLogic.practice(WriteLogAddressFormatLogic.ERROR, str));
    }

}
