package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * ConvertNumberToKansujiUtil単体テスト
 */
class ConvertNumberToKansujiUtilTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    void test() {

        ConvertNumberToKansujiUtil kansujiUtil = new ConvertNumberToKansujiUtil();

        assertEquals("一", kansujiUtil.practice(1));
        assertEquals("二", kansujiUtil.practice(2));
        assertEquals("三", kansujiUtil.practice(3));
        assertEquals("四", kansujiUtil.practice(4));
        assertEquals("五", kansujiUtil.practice(5));
        assertEquals("六", kansujiUtil.practice(6));
        assertEquals("七", kansujiUtil.practice(7));
        assertEquals("八", kansujiUtil.practice(8));
        assertEquals("九", kansujiUtil.practice(9));
        assertEquals("十", kansujiUtil.practice(10));
        assertEquals("十一", kansujiUtil.practice(11));
        assertEquals("十二", kansujiUtil.practice(12));
        assertEquals("十三", kansujiUtil.practice(13));
        assertEquals("十四", kansujiUtil.practice(14));
        assertEquals("十五", kansujiUtil.practice(15));
        assertEquals("十六", kansujiUtil.practice(16));
        assertEquals("十七", kansujiUtil.practice(17));
        assertEquals("十八", kansujiUtil.practice(18));
        assertEquals("十九", kansujiUtil.practice(19));
        assertEquals("二十", kansujiUtil.practice(20));
        assertEquals("二十一", kansujiUtil.practice(21));
        assertEquals("二十二", kansujiUtil.practice(22));
        assertEquals("二十三", kansujiUtil.practice(23));
        assertEquals("二十四", kansujiUtil.practice(24));
        assertEquals("二十五", kansujiUtil.practice(25));
        assertEquals("二十六", kansujiUtil.practice(26));
        assertEquals("二十七", kansujiUtil.practice(27));
        assertEquals("二十八", kansujiUtil.practice(28));
        assertEquals("二十九", kansujiUtil.practice(29));
        assertEquals("三十", kansujiUtil.practice(30));
        assertEquals("三十一", kansujiUtil.practice(31));
        assertEquals("三十二", kansujiUtil.practice(32));
        assertEquals("三十三", kansujiUtil.practice(33));
        assertEquals("三十四", kansujiUtil.practice(34));
        assertEquals("三十五", kansujiUtil.practice(35));
        assertEquals("三十六", kansujiUtil.practice(36));
        assertEquals("三十七", kansujiUtil.practice(37));
        assertEquals("三十八", kansujiUtil.practice(38));
        assertEquals("三十九", kansujiUtil.practice(39));
        assertEquals("四十", kansujiUtil.practice(40));
        assertEquals("四十一", kansujiUtil.practice(41));
        assertEquals("四十二", kansujiUtil.practice(42));
        assertEquals("四十三", kansujiUtil.practice(43));
        assertEquals("四十四", kansujiUtil.practice(44));
        assertEquals("四十五", kansujiUtil.practice(45));
        assertEquals("四十六", kansujiUtil.practice(46));
        assertEquals("四十七", kansujiUtil.practice(47));
        assertEquals("四十八", kansujiUtil.practice(48));
        assertEquals("四十九", kansujiUtil.practice(49));
        assertEquals("五十", kansujiUtil.practice(50));

        assertThrows(IllegalArgumentException.class, () -> kansujiUtil.practice(51));
    }

}
