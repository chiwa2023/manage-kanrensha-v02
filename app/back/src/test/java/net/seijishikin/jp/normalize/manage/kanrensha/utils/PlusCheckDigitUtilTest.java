package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PlusCheckDigitUtil単体テスト
 */
class PlusCheckDigitUtilTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertThrows(IllegalArgumentException.class, () -> PlusCheckDigitUtil.plusForLgCode(null));
        assertThrows(IllegalArgumentException.class, () -> PlusCheckDigitUtil.plusForLgCode("123"));
        assertThrows(IllegalArgumentException.class, () -> PlusCheckDigitUtil.plusForLgCode("あああ"));

        // 011002
        assertEquals("011002", PlusCheckDigitUtil.plusForLgCode("01100"));

        // 011011
        assertEquals("011011", PlusCheckDigitUtil.plusForLgCode("01101"));

        // 011029
        assertEquals("011029", PlusCheckDigitUtil.plusForLgCode("01102"));

        // 011037
        assertEquals("011037", PlusCheckDigitUtil.plusForLgCode("01103"));

        // 011045
        assertEquals("011045", PlusCheckDigitUtil.plusForLgCode("01104"));

        // 011053
        assertEquals("011053", PlusCheckDigitUtil.plusForLgCode("01105"));
    }

}
