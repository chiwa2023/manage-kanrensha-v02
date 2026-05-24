package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * GetPreParenthesesLogic単体テスト
 */
class GetPreParenthesesLogicTest {

    @Test
    void test() {

        // 半角かっこでは何も起きない。データの全半角関係なし
        final String data1 = "あああ12345(=";
        assertEquals(data1, GetPreParenthesesLogic.practice(data1));

        // かっこより前を抜き出し
        final String data2 = "架空市（山麓町）";
        assertEquals("架空市", GetPreParenthesesLogic.practice(data2));

        // 複数ある場合は最初の『』が基準かっこより前を抜き出し
        final String data3 = "架空市（山麓町）（湖畔町）";
        assertEquals("架空市", GetPreParenthesesLogic.practice(data3));
    }

}
