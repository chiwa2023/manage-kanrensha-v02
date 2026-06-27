package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * RsdtAddressProcessor単体テスト
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RsdtAddressProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RsdtAddressProcessor rsdtAddressProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        RsdtAddressCsvLineMapper lineMapper = new RsdtAddressCsvLineMapper();
        String line0 = "011053,0013018,017,011,123,札幌市,豊平区,月寒東５条,１８丁目,aaa,bbb,17,11,99,0,1,1,0,2022-11-19,2046-08-11,0,";

        RsdtAddressCsvDto csvDto0 = lineMapper.mapLine(line0, 0);

        AddressRsdtBaseEntity baseEntity = rsdtAddressProcessor.process(csvDto0);

        assertEquals("011053", baseEntity.getLgCode());
        assertEquals("", baseEntity.getPostalcode1()); // 以降の処理で追加
        assertEquals("", baseEntity.getPostalcode2()); // 以降の処理で追加
        assertEquals("札幌市豊平区月寒東五条十八丁目aaa17番地11号", baseEntity.getAddressBlock());
        assertEquals("99号室", baseEntity.getAddressBuilding());
        assertEquals("0013018", baseEntity.getMachiazaId());
        assertEquals("017", baseEntity.getBlkId());
        assertEquals("", baseEntity.getPrcId());
        assertEquals("011", baseEntity.getRsdtId());
        assertEquals("123", baseEntity.getRsdt2Id());
        assertEquals(LocalDate.of(2022, 11, 19), baseEntity.getEffectDate());
        assertEquals(LocalDate.of(2046, 8, 11), baseEntity.getAbolishDate());
    }

}
