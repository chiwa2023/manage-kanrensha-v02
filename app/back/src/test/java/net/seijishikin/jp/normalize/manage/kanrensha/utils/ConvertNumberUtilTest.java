package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * ConvertNumberUtil単体テスト
 */
class ConvertNumberUtilTest {

    @Test
    @Tag("TableTruncate")
    void test() {

        assertThrows(NullPointerException.class, () -> ConvertNumberUtil.practice(null));

        final String suujiNashi = "和歌山県実在市湖畔町";
        assertEquals(suujiNashi, ConvertNumberUtil.practice(suujiNashi));

        assertEquals("0123456789丁目", ConvertNumberUtil.practice("０１２３４５６７８９丁目"));
    }

}
