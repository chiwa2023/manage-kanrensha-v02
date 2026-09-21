package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * CreateDokujiCodeForCorpUtil単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CreateDokujiCodeForKigyouDtUtilTest.sql")
class CreateDokujiCodeForKigyouDtUtilTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CreateDokujiCodeForKigyouDtUtil createDokujiCodeForKigyouDtUtil;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertDoesNotThrow(() -> createDokujiCodeForKigyouDtUtil.practice(null), "積極的ではないがnull許容");
        assertDoesNotThrow(() -> createDokujiCodeForKigyouDtUtil.practice(""), "正規コードが存在しない場合");

        // 判定するパターンを生成
        // 1-2345-67-890123-4567890が最終形
        Pattern pattern = Pattern.compile(".{1}-.{4}-.{2}-.{6}-.{7}");

        final String length24 = "24文字";
        final String match = "形式にマッチ";
        String answer1 = createDokujiCodeForKigyouDtUtil.practice("1-234-567-890123");
        assertEquals(24, answer1.length(), length24);
        assertTrue(answer1.startsWith("1-2345-67-890123"), "ハイフンを取り除いて成形");
        assertTrue(pattern.matcher(answer1).find(), match);

        String answer2 = createDokujiCodeForKigyouDtUtil.practice("1-234-567-89*012");
        assertEquals(24, answer2.length(), length24);
        assertTrue(answer2.startsWith("1-2345-67-89*012"), "ハイフン以外の記号はそのまま");
        assertTrue(pattern.matcher(answer2).find(), match);

        String answer3 = createDokujiCodeForKigyouDtUtil.practice("１－２３４-567-890Ａｂc");
        assertEquals(24, answer3.length(), length24);
        assertTrue(answer3.startsWith("1-2345-67-890Abc"), "全角英数は半角に");
        assertTrue(pattern.matcher(answer3).find(), match);

        // 正規コード=法人番号なのでかなは想定しなくていい
        // String answer4 = createDokujiCodeForCorpUtil.practice("あ-いウエお-67-890123");
        // assertEquals(24, answer4.length(), length24);
        // assertTrue(answer4.startsWith("あ-いウエお-67-890123"),
        // "正規コードにひらがなカタカナが存在(ないと思うけど)");
        // assertTrue(p.matcher(answer4).find(), match);
    }

    @Test
    @Tag("TableTruncate")
    void testDuplicate() throws Exception {
        // ランダム文字を付加する余地(文字数)がないと、完全に同一のコードしか戻らないので重複となる
        assertThrows(DuplicateKeyException.class,
                () -> createDokujiCodeForKigyouDtUtil.practice("1234567890abcdefghij"));
    }

}
