package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * ConvertKansujiUtil単体テスト
 */
class ConvertKansujiUtilTest {

    @Test
    void test() {

        assertEquals("", ConvertKansujiUtil.practice(null));
        assertEquals("北大路四十一条", ConvertKansujiUtil.practice("北大路４１条"));
        assertEquals("北大路四十一", ConvertKansujiUtil.practice("北大路４１"));
        assertEquals("四十一条", ConvertKansujiUtil.practice("４１条"));
    }

}
