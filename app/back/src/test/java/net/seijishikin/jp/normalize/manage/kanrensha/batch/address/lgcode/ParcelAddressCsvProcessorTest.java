package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * ParcelAddressCsvProcessor単体テスト
 */
class ParcelAddressCsvProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ParcelAddressCsvProcessor csvProcessor = new ParcelAddressCsvProcessor();

        ParcelAddressCsvDto dto00 = new ParcelAddressCsvDto();

        dto00.setLgCode("011011");
        dto00.setMachiazaId("0001001");
        dto00.setPrcId("000010000000000");
        dto00.setCity("札幌市");
        dto00.setWard("中央区");
        dto00.setOazaCho("旭ケ丘");
        dto00.setChome("一丁目");
        dto00.setKoaza("aaa");
        dto00.setMachiazaDist("bbb");
        dto00.setPrcNum1("99");
        dto00.setPrcNum2("98");
        dto00.setPrcNum3("97");
        // 住居表示フラグ
        dto00.setRsdtAddrFlg(0);
        dto00.setPrcRecFlg(0);
        dto00.setPrcAreaCode(0);
        dto00.setEffectDate(LocalDate.of(1947, 4, 17));

        AddressRsdtBaseEntity baseEntity = csvProcessor.process(dto00);

        assertEquals("011011", baseEntity.getLgCode());
        assertEquals("", baseEntity.getPostalcode1()); // 以降の処理で追加
        assertEquals("", baseEntity.getPostalcode2()); // 以降の処理で追加
        assertEquals("0001001", baseEntity.getMachiazaId());
        assertEquals("000010000000000", baseEntity.getPrcId());
        assertEquals(LocalDate.of(1947, 4, 17), baseEntity.getEffectDate());
        assertEquals("札幌市中央区旭ケ丘一丁目aaa99番地98号の97", baseEntity.getAddressBlock());
        assertEquals("", baseEntity.getAddressBuilding()); // 地番データに建物名は存在しない

        // 住居フラグがオン(すでにテーブルに登録済想定)
        ParcelAddressCsvDto dto01 = new ParcelAddressCsvDto();

        dto01.setLgCode("011011");
        dto01.setMachiazaId("0001001");
        dto01.setPrcId("000010000000000");
        dto01.setCity("札幌市");
        dto01.setWard("中央区");
        dto01.setOazaCho("旭ケ丘");
        dto01.setChome("一丁目");
        dto01.setKoaza("aaa");
        dto01.setMachiazaDist("bbb");
        dto01.setPrcNum1("99");
        dto01.setPrcNum2("98");
        dto01.setPrcNum3("97");
        // 住居表示フラグ
        dto01.setRsdtAddrFlg(1); // 住居表示ON→住居CSVにデータが存在するので二重登録を避けるために空Entityを返す
        dto01.setPrcRecFlg(0);
        dto01.setPrcAreaCode(0);
        dto01.setEffectDate(LocalDate.of(1947, 4, 17));

        AddressRsdtBaseEntity baseEntity1 = csvProcessor.process(dto01);

        assertEquals("", baseEntity1.getLgCode());
        assertEquals("", baseEntity1.getPostalcode1());
        assertEquals("", baseEntity1.getPostalcode2());
        assertEquals("", baseEntity1.getMachiazaId());
        assertEquals("", baseEntity1.getPrcId());
        assertEquals(LocalDate.of(1948, 7, 28), baseEntity1.getEffectDate());
        assertEquals("", baseEntity1.getAddressBlock());
        assertEquals("", baseEntity.getAddressBuilding());

    }

}
