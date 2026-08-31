package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaKigyouDtHistoryLineMapper単体テスト
 */
class KanrenshaKigyouDtHistoryLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("UnitTest")
    void testMapLine() throws Exception {
        KanrenshaKigyouDtHistoryLineMapper lineMapper = new KanrenshaKigyouDtHistoryLineMapper();

        // 正常な行のテスト
        String line = "\"株式会社テスト\",\"東京都テスト区\",\"テスト太郎\",\"K-CODE-001\",\"D-CODE-001\"";
        KanrenshaKigyouDtHistoryDto dto = lineMapper.mapLine(line, 1);

        assertEquals("株式会社テスト", dto.getKanrenshaName());
        assertEquals("東京都テスト区", dto.getAllAddress());
        assertEquals("テスト太郎", dto.getKigyouDtDelegate());
        assertEquals("K-CODE-001", dto.getKigyouDtKanrenshaCode());
        assertEquals("D-CODE-001", dto.getOrgDelegateCode());

        // クォーテーションが含まれない場合のテスト
        String noQuoteLine = "株式会社テスト,東京都テスト区,テスト太郎,K-CODE-001,D-CODE-001";
        KanrenshaKigyouDtHistoryDto noQuoteDto = lineMapper.mapLine(noQuoteLine, 2);
        assertEquals("株式会社テスト", noQuoteDto.getKanrenshaName());
        assertEquals("東京都テスト区", noQuoteDto.getAllAddress());
        assertEquals("テスト太郎", noQuoteDto.getKigyouDtDelegate());
        assertEquals("K-CODE-001", noQuoteDto.getKigyouDtKanrenshaCode());
        assertEquals("D-CODE-001", noQuoteDto.getOrgDelegateCode());

        // カラム数が足りない場合のテスト
        String shortLine = "\"株式会社テスト\",\"東京都テスト区\"";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            lineMapper.mapLine(shortLine, 3);
        });
    }
}
