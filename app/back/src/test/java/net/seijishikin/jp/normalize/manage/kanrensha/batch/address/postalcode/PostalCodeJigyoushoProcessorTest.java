package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * PostalCodeJigyoushoProcessor単体テスト
 */
class PostalCodeJigyoushoProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeJigyoushoProcessor processor = new PostalCodeJigyoushoProcessor();

        PostalCodeCsvJigyoushoDto item = new PostalCodeCsvJigyoushoDto();
        item.setPostalcode("0308556");
        item.setLgCode("02201");
        item.setPref("青森県");
        item.setCity("青森市");
        item.setAddressOrg("長島");
        item.setAddressBlock("二丁目２５－３ニッセイ青森センタービル６階");

        AddressPostalIrregularEntity postalEntity = processor.process(item);

        assertEquals("022012", postalEntity.getLgCode());
        assertEquals(item.getPostalcode(), postalEntity.getPostalcode());
        assertEquals("青森県青森市長島", postalEntity.getAddressPostal());
        assertEquals("二丁目２５－３ニッセイ青森センタービル６階", postalEntity.getAddressBlock());
        assertEquals("長島", postalEntity.getAddressOrg());
        assertEquals("二丁目２５－３ニッセイ青森センタービル６階", postalEntity.getAddressName());
        // assertTrue(postalEntity.getIsAddPostal());
        // assertTrue(postalEntity.getIsRepairRsdt());

        fail("Not yet implemented");
    }

}
