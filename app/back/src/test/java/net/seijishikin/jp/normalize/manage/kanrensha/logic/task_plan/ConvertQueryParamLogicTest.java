package net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

/**
 * クエリパラメータ作成Logic
 */
class ConvertQueryParamLogicTest {

    @Test
    void test() {

        final String blank = "";

        // 取得キー設定がない場合は空文字
        ConvertQueryParamLogic convertQueryParamLogic = new ConvertQueryParamLogic();
        assertEquals(blank, convertQueryParamLogic.practice(blank, null));

        // 取得すべきキーがあってもデータがnullの場合は例外
        assertThrows(IllegalArgumentException.class, () -> convertQueryParamLogic.practice("abcd", null));

        // 取得すべきキーがあってもデータがnullの場合は例外
        Map<String, String> map1 = new TreeMap<>();
        assertThrows(IllegalArgumentException.class, () -> convertQueryParamLogic.practice("abcd", map1));

        // キーに対してデータがない場合は例外
        Map<String, String> map2 = new TreeMap<>();
        final String key1 = "abc";
        map2.put(key1, "123");
        assertThrows(IllegalArgumentException.class,
                () -> convertQueryParamLogic.practice(key1 + ConvertQueryParamLogic.SPLITTER + "def", map1));

        Map<String, String> map3 = new TreeMap<>();
        final String key11 = "qwe";
        final String key12 = "asd";
        map3.put(key11, "123");
        map3.put(key12, "456");
        assertEquals("?qwe=123&asd=456",
                convertQueryParamLogic.practice(key11 + ConvertQueryParamLogic.SPLITTER + key12, map3));
    }

}
