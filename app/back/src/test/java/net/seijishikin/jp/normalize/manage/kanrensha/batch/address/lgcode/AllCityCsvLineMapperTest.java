package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * AllCityCsvLineMapper単体テスト
 */
class AllCityCsvLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AllCityCsvLineMapper lineMapper = new AllCityCsvLineMapper();

        String line1 = "112233,2,3,4,5,6,7,8,9,10,11,12,13,2022-11-12,2026-09-18,14,";

        AllCityCsvDto dto1 = lineMapper.mapLine(line1, 0);

        assertEquals("2", dto1.getPref());
        assertEquals("5", dto1.getCounty());
        assertEquals("8", dto1.getCity());
        assertEquals("11", dto1.getWard());
        assertEquals("36912", dto1.getAddressNameKana());
        assertEquals("112233", dto1.getLgCode());
        assertEquals(LocalDate.of(2022, 11, 12), dto1.getEffectDate());
        assertEquals(LocalDate.of(2026, 9, 18), dto1.getAbolishDate());

        
        String line2 = "112233,,3,4,,6,7,,9,10,,12,13,2022-11-12,,";

        AllCityCsvDto dto2 = lineMapper.mapLine(line2, 0);

        assertEquals("", dto2.getPref());
        assertEquals("", dto2.getCounty());
        assertEquals("", dto2.getCity());
        assertEquals("", dto2.getWard());
        assertEquals("36912", dto2.getAddressNameKana());
        assertEquals("112233", dto2.getLgCode());
        assertEquals(LocalDate.of(2022, 11, 12), dto2.getEffectDate());
        assertNull(dto2.getAbolishDate());
    }

}
