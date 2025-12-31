package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * CreateAllAddressUtil単体テスト
 */
class CreateAllAddressUtilTest {

    @Test
    void test() {

        // すべてnullの時は全角スペース
        assertEquals("　", CreateAllAddressUtil.practice(null, null, null));

        // 郵便番号まで+番地まで+全角スペース+建物
        assertEquals("AB　C", CreateAllAddressUtil.practice("A", "B", "C"));
    }

    @Test
    void testTanshuku() {

        // すべてnullの時は空文字
        assertEquals("", CreateAllAddressUtil.practiceTanshuku(null, null, null));

        // 郵便番号まで住所のみが出力される
        assertEquals("A", CreateAllAddressUtil.practiceTanshuku("A", "B", "C"));
    }

}
