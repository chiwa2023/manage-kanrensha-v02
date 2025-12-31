package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * CreateDokujiCodeForPoliOrgUtil単体テスト
 */
class CreateDokujiCodeForSeijidantaiUtilTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test() {

        CreateDokujiCodeForSeijidantaiUtil createDokujiCodeForSeijidantaiUtil = new CreateDokujiCodeForSeijidantaiUtil();

        assertDoesNotThrow(() -> createDokujiCodeForSeijidantaiUtil.practice(null), "積極的ではないがnull許容");
        assertDoesNotThrow(() -> createDokujiCodeForSeijidantaiUtil.practice(""), "正規コードが存在しない場合");

      //判定するパターンを生成
        // 123-4567-8901-2345-67890が最終形
        Pattern pattern = Pattern.compile(".{3}-.{4}-.{4}-.{4}-.{5}");
        
        final String length24 = "24文字";
        final String match = "形式にマッチ";
        String answer1 = createDokujiCodeForSeijidantaiUtil.practice("1-234-567-890");
        assertEquals(24, answer1.length(), length24);
        assertTrue(answer1.startsWith("123-4567-890"), "ハイフンを取り除いて成形");
        assertTrue(pattern.matcher(answer1).find(), match);

        String answer2 = createDokujiCodeForSeijidantaiUtil.practice("1-234-567-89*0");
        assertEquals(24, answer2.length(), length24);
        assertTrue(answer2.startsWith("123-4567-89*0"), "ハイフン以外の記号はそのまま");
        assertTrue(pattern.matcher(answer2).find(), match);

        String answer3 = createDokujiCodeForSeijidantaiUtil.practice("１－２３４-567-890ＡＢ");
        assertEquals(24, answer3.length(), length24);
        assertTrue(answer3.startsWith("123-4567-890A-B"), "全角英数は半角に");
        assertTrue(pattern.matcher(answer3).find(), match);

        String answer4 = createDokujiCodeForSeijidantaiUtil.practice("あ-いウエお-67-890");
        assertEquals(24, answer4.length(), length24);
        assertTrue(answer4.startsWith("あいウ-エお67-890"), "正規コードにひらがなカタカナが存在(ないと思うけど)");
        assertTrue(pattern.matcher(answer4).find(), match);

    }

}
