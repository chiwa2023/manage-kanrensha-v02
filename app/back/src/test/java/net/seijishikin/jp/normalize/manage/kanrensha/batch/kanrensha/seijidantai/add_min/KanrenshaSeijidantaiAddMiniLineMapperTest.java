package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaSeijidantaiAddMiniLineMapper単体テスト
 */
class KanrenshaSeijidantaiAddMiniLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("UnitTest")
    void testMapLine() throws Exception {
        KanrenshaSeijidantaiAddMiniLineMapper lineMapper = new KanrenshaSeijidantaiAddMiniLineMapper();

        // 正常な行のテスト
        String line = "\"テスト政治団体\",\"東京都テスト区\",\"テスト代表\",\"01\"";
        KanrenshaSeijidantaiAddMiniDto dto = lineMapper.mapLine(line, 1);

        assertEquals("テスト政治団体", dto.getKanrenshaName());
        assertEquals("東京都テスト区", dto.getAllAddress());
        assertEquals("テスト代表", dto.getSeijidantaiDelegate());
        assertEquals("01", dto.getDantaiKbn());

        // クォーテーションが含まれない場合のテスト
        String noQuoteLine = "テスト政治団体,東京都テスト区,テスト代表,01";
        KanrenshaSeijidantaiAddMiniDto noQuoteDto = lineMapper.mapLine(noQuoteLine, 2);
        assertEquals("テスト政治団体", noQuoteDto.getKanrenshaName());
        assertEquals("東京都テスト区", noQuoteDto.getAllAddress());
        assertEquals("テスト代表", noQuoteDto.getSeijidantaiDelegate());
        assertEquals("01", noQuoteDto.getDantaiKbn());

        // カラム数が足りない場合のテスト
        String shortLine = "\"テスト政治団体\",\"東京都テスト区\"";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            lineMapper.mapLine(shortLine, 3);
        });
    }
}