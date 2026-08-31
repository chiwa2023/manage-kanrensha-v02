package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * PostalCodeToIrregularProceesor単体テスト
 */
class PostalCodeToIrregularProceesorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeToIrregularProceesor proceesor = new PostalCodeToIrregularProceesor();

        AddressPostalEntity entity01 = new AddressPostalEntity();
        entity01.setLgCode("112233");
        entity01.setAddressName("テスト市テスト郡");
        entity01.setAddressOrg("テスト郡");
        entity01.setPostalcode1("1234567");
        entity01.setPostalcode2("4567");
        entity01.setIsGyoseikuData(true);

        AddressPostalIrregularEntity answerEntity00 = proceesor.process(entity01);

        assertEquals(entity01.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity01.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity01.getAddressOrg(), answerEntity00.getAddressOrg());
        assertEquals(entity01.getPostalcode1(), answerEntity00.getPostalcode1());
        assertEquals(entity01.getPostalcode2(), answerEntity00.getPostalcode2());

        assertEquals("", answerEntity00.getAddressPostal());
        assertEquals("", answerEntity00.getAddressBlock());
    }

}
