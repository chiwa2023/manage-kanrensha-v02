package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaSeijidantaiHistoryLineMapper単体テスト
 */
class KanrenshaSeijidantaiHistoryLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("UnitTest")
    void testMapLine() throws Exception {
        KanrenshaSeijidantaiHistoryLineMapper lineMapper = new KanrenshaSeijidantaiHistoryLineMapper();

        // 正常な行のテスト
        String line = "\"テスト政治団体\",\"東京都テスト区\",\"テスト代表\",\"S-CODE-001\",\"D-CODE-001\"";
        KanrenshaSeijidantaiHistoryDto dto = lineMapper.mapLine(line, 1);

        assertEquals("テスト政治団体", dto.getKanrenshaName());
        assertEquals("東京都テスト区", dto.getAllAddress());
        assertEquals("テスト代表", dto.getSeijidantaiDelegate());
        assertEquals("S-CODE-001", dto.getSeijidantaiKanrenshaCode());
        assertEquals("D-CODE-001", dto.getOrgDelegateCode());

        // クォーテーションが含まれない場合のテスト
        String noQuoteLine = "テスト政治団体,東京都テスト区,テスト代表,S-CODE-001,D-CODE-001";
        KanrenshaSeijidantaiHistoryDto noQuoteDto = lineMapper.mapLine(noQuoteLine, 2);
        assertEquals("テスト政治団体", noQuoteDto.getKanrenshaName());
        assertEquals("東京都テスト区", noQuoteDto.getAllAddress());
        assertEquals("テスト代表", noQuoteDto.getSeijidantaiDelegate());
        assertEquals("S-CODE-001", noQuoteDto.getSeijidantaiKanrenshaCode());
        assertEquals("D-CODE-001", noQuoteDto.getOrgDelegateCode());

        // カラム数が足りない場合のテスト
        String shortLine = "\"テスト政治団体\",\"東京都テスト区\"";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            lineMapper.mapLine(shortLine, 3);
        });
    }
}
