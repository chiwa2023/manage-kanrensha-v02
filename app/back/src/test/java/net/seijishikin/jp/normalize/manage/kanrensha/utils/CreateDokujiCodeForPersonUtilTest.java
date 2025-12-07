package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * CreateDokujiCodeForPersonUtil単体テスト
 */
class CreateDokujiCodeForPersonUtilTest {
    // CHECKSTYLE:OFF

    @Test
    void test() {

        CreateDokujiCodeForPersonUtil createDokujiCodeForPoliOrgUtil = new CreateDokujiCodeForPersonUtil();

        assertDoesNotThrow(() -> createDokujiCodeForPoliOrgUtil.practice(null), "積極的ではないがnull許容");
        assertDoesNotThrow(() -> createDokujiCodeForPoliOrgUtil.practice(""), "正規コードが存在しない場合");

        // 判定するパターンを生成
        // 12-34567-8901-2345-67890が最終形
        Pattern pattern = Pattern.compile(".{2}-.{5}-.{4}-.{4}-.{5}");

        final String length24 = "24文字";
        final String match = "形式にマッチ";
        String answer1 = createDokujiCodeForPoliOrgUtil.practice("1-234-567-890");
        // System.out.println(answer1);
        assertEquals(24, answer1.length(), length24);
        assertTrue(answer1.startsWith("12-34567-890"), "ハイフンを取り除いて成形");
        assertTrue(pattern.matcher(answer1).find(), match);

        String answer2 = createDokujiCodeForPoliOrgUtil.practice("1-234-567-89*0");
        // System.out.println(answer2);
        assertEquals(24, answer2.length(), length24);
        assertTrue(answer2.startsWith("12-34567-89*0"), "ハイフン以外の記号はそのまま");
        assertTrue(pattern.matcher(answer2).find(), match);

        String answer3 = createDokujiCodeForPoliOrgUtil.practice("１－２３４-567-890ＡＢ");
        // System.out.println(answer3);
        assertEquals(24, answer3.length(), length24);
        assertTrue(answer3.startsWith("12-34567-890A-B"), "全角英数は半角に");
        assertTrue(pattern.matcher(answer3).find(), match);

        String answer4 = createDokujiCodeForPoliOrgUtil.practice("あ-いウエお-67-890");
        // System.out.println(answer4);
        assertEquals(24, answer4.length(), length24);
        assertTrue(answer4.startsWith("あい-ウエお67-890"), "正規コードにひらがなカタカナが存在(ないと思うけど)");
        assertTrue(pattern.matcher(answer4).find(), match);
    }

}
