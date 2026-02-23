package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * ParcelAddressCsvLineMapper単体テスト
 */
class ParcelAddressCsvLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ParcelAddressCsvLineMapper lineMapper = new ParcelAddressCsvLineMapper();

        String line0 = "011011,0001001,000010000000000,札幌市,中央区,旭ケ丘,一丁目,aaa,bbb,99,98,97,1,1,0,1947-04-17,2022-12-05,1,,";

        ParcelAddressCsvDto dto00 = lineMapper.mapLine(line0, 0);

        // 地方自治体コード
        assertEquals("011011", dto00.getLgCode());
        // 町字コード
        assertEquals("0001001", dto00.getMachiazaId());
        // 地番コード
        assertEquals("000010000000000", dto00.getPrcId());
        // 市区町村名
        assertEquals("札幌市", dto00.getCity());
        // 政令市区名
        assertEquals("中央区", dto00.getWard());
        // 大字・町名
        assertEquals("旭ケ丘", dto00.getOazaCho());
        // 丁目名
        assertEquals("一丁目", dto00.getChome());
        // 小字名
        assertEquals("aaa", dto00.getKoaza());
        // 同一町字識別情報
        assertEquals("bbb", dto00.getMachiazaDist());
        // 地番1
        assertEquals("99", dto00.getPrcNum1());
        // 地番2
        assertEquals("98", dto00.getPrcNum2());
        // 地番3
        assertEquals("97", dto00.getPrcNum3());
        // 住居表示フラグ
        assertEquals(1, dto00.getRsdtAddrFlg());
        // 地番レコード区分フラグ
        assertEquals(1, dto00.getPrcRecFlg());
        // 地番区域コード
        assertEquals(0, dto00.getPrcAreaCode());
        // 適用開始日
        assertEquals(LocalDate.of(1947, 4, 17), dto00.getEffectDate());
        // 廃止日
        assertEquals(LocalDate.of(2022, 12, 5), dto00.getAbolishDate());

        // すべて空文字
        String line1 = ",,,,,,,,,,,,,,,,,,,0,";

        ParcelAddressCsvDto dto01 = lineMapper.mapLine(line1, 0);

        // 地方自治体コード
        assertEquals("", dto01.getLgCode());
        // 町字コード
        assertEquals("", dto01.getMachiazaId());
        // 地番コード
        assertEquals("", dto01.getPrcId());
        // 市区町村名
        assertEquals("", dto01.getCity());
        // 政令市区名
        assertEquals("", dto01.getWard());
        // 大字・町名
        assertEquals("", dto01.getOazaCho());
        // 丁目名
        assertEquals("", dto01.getChome());
        // 小字名
        assertEquals("", dto01.getKoaza());
        // 同一町字識別情報
        assertEquals("", dto01.getMachiazaDist());
        // 地番1
        assertEquals("", dto01.getPrcNum1());
        // 地番2
        assertEquals("", dto01.getPrcNum2());
        // 地番3
        assertEquals("", dto01.getPrcNum3());
        // 住居表示フラグ
        assertEquals(0, dto01.getRsdtAddrFlg());
        // 地番レコード区分フラグ
        assertEquals(0, dto01.getPrcRecFlg());
        // 地番区域コード
        assertEquals(0, dto01.getPrcAreaCode());
        // 適用開始日
        assertEquals(LocalDate.of(1948, 7, 28), dto01.getEffectDate());
        // 廃止日
        assertNull(dto01.getAbolishDate());
    }

}
