package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaKigyouDtAddStdLineMapper単体テスト
 */
class KanrenshaKigyouDtAddStdLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void testMapLine() throws Exception {
        KanrenshaKigyouDtAddStdLineMapper lineMapper = new KanrenshaKigyouDtAddStdLineMapper();

        // All fields are populated correctly
        String line = "\"Test Name\",\"Test Address\",\"Test Delegate\",\"1234567890123\",\"Test Postal Address\",\"Test Block Address\",\"Test Building Address\",\"123\",\"4567\",\"090\",\"1234\",\"5678\",\"test@example.com\",\"http://example.com\",\"はい\",\"株式会社\",\"テストカナ\",\"1\",\"DEL123\",\"Twitter\",\"@test\",\"13101\",\"machiaza123\",\"block123\",\"prc123\",\"rsdt123\",\"rsdt2123\"";
        KanrenshaKigyouDtAddStdDto dto = lineMapper.mapLine(line, 1);

        assertEquals("Test Name", dto.getKanrenshaName());
        assertEquals("Test Address", dto.getAllAddress());
        assertEquals("Test Delegate", dto.getKigyouDtDelegate());
        assertEquals("1234567890123", dto.getHoujinNo());
        assertEquals("Test Postal Address", dto.getAddressPostal());
        assertEquals("Test Block Address", dto.getAddressBlock());
        assertEquals("Test Building Address", dto.getAddressBuilding());
        assertEquals("123", dto.getPostalcode1());
        assertEquals("4567", dto.getPostalcode2());
        assertEquals("090", dto.getPhon1());
        assertEquals("1234", dto.getPhon2());
        assertEquals("5678", dto.getPhon3());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("http://example.com", dto.getMyPortalUrl());
        assertTrue(dto.getIsForeign());
        assertEquals("株式会社", dto.getHoujinSbts());
        assertEquals("テストカナ", dto.getOrgNameKana());
        assertTrue(dto.getIsShiten());
        assertEquals("DEL123", dto.getOrgDelegateCode());
        assertEquals("Twitter", dto.getSnsServiceName());
        assertEquals("@test", dto.getSnsAccount());
        assertEquals("13101", dto.getLgCode());
        assertEquals("machiaza123", dto.getMachiazaId());
        assertEquals("block123", dto.getBlkId());
        assertEquals("prc123", dto.getPrcId());
        assertEquals("rsdt123", dto.getRsdtId());
        assertEquals("rsdt2123", dto.getRsdt2Id());

        // Test boolean conversion for 'false' values
        String lineFalse = "\"Test Name\",\"Test Address\",\"Test Delegate\",\"1234567890123\",\"Test Postal Address\",\"Test Block Address\",\"Test Building Address\",\"123\",\"4567\",\"090\",\"1234\",\"5678\",\"test@example.com\",\"http://example.com\",\"いいえ\",\"株式会社\",\"テストカナ\",\"0\",\"DEL123\",\"Twitter\",\"@test\",\"13101\",\"machiaza123\",\"block123\",\"prc123\",\"rsdt123\",\"rsdt2123\"";
        KanrenshaKigyouDtAddStdDto dtoFalse = lineMapper.mapLine(lineFalse, 2);
        assertFalse(dtoFalse.getIsForeign());
        assertFalse(dtoFalse.getIsShiten());

        // Test normalization and hyphen removal for houjin_no
        String lineHoujinNo = "\"Test Name\",\"Test Address\",\"Test Delegate\",\"１２３-４５６-７８９０１２３\",\"Test Postal Address\",\"Test Block Address\",\"Test Building Address\",\"123\",\"4567\",\"090\",\"1234\",\"5678\",\"test@example.com\",\"http://example.com\",\"はい\",\"株式会社\",\"テストカナ\",\"1\",\"DEL123\",\"Twitter\",\"@test\",\"13101\",\"machiaza123\",\"block123\",\"prc123\",\"rsdt123\",\"rsdt2123\"";
        KanrenshaKigyouDtAddStdDto dtoHoujinNo = lineMapper.mapLine(lineHoujinNo, 3);
        assertEquals("1234567890123", dtoHoujinNo.getHoujinNo());

        // Test with insufficient columns
        String incompleteLine = "\"Test Name\"";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lineMapper.mapLine(incompleteLine, 4));
    }
}
