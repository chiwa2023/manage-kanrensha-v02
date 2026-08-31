package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaPersonHistoryLineMapper単体テスト
 */
class KanrenshaPersonHistoryLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("UnitTest")
    void testMapLine() throws Exception {
        KanrenshaPersonHistoryLineMapper lineMapper = new KanrenshaPersonHistoryLineMapper();

        // 正常な行のテスト
        String line = "\"テスト\u3000太郎\",\"東京都テスト区\",\"会社員\",\"P-CODE-001\"";
        KanrenshaPersonHistoryDto dto = lineMapper.mapLine(line, 1);

        assertEquals("テスト\u3000太郎", dto.getKanrenshaName());
        assertEquals("東京都テスト区", dto.getAllAddress());
        assertEquals("会社員", dto.getPersonShokugyou());
        assertEquals("P-CODE-001", dto.getPersonKanrenshaCode());

        // クォーテーションが含まれない場合のテスト
        String noQuoteLine = "テスト\u3000太郎,東京都テスト区,会社員,P-CODE-001";
        KanrenshaPersonHistoryDto noQuoteDto = lineMapper.mapLine(noQuoteLine, 2);
        assertEquals("テスト\u3000太郎", noQuoteDto.getKanrenshaName());
        assertEquals("東京都テスト区", noQuoteDto.getAllAddress());
        assertEquals("会社員", noQuoteDto.getPersonShokugyou());
        assertEquals("P-CODE-001", noQuoteDto.getPersonKanrenshaCode());

        // カラム数が足りない場合のテスト
        String shortLine = "\"テスト\u3000太郎\",\"東京都テスト区\"";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            lineMapper.mapLine(shortLine, 3);
        });
    }
}
