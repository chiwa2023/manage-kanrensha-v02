package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * PostalCodeJigyoushoProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeJigyoushoProcessorTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeJigyoushoProcessor postalCodeJigyoushoProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeCsvJigyoushoDto item = new PostalCodeCsvJigyoushoDto();
        item.setPostalcode("0308556");
        item.setLgCode("02201");
        item.setPref("青森県");
        item.setCity("青森市");
        item.setAddressOrg("長島");
        item.setAddressBlock("二丁目２５－３ニッセイ青森センタービル６階");

        AddressPostalIrregularEntity postalEntity = postalCodeJigyoushoProcessor.process(item);

        assertEquals("022012", postalEntity.getLgCode());
        assertEquals("030", postalEntity.getPostalcode1());
        assertEquals("8556", postalEntity.getPostalcode2());
        assertEquals("青森県青森市長島", postalEntity.getAddressPostal());
        assertEquals("二丁目25番地3ニッセイ青森センタービル6階", postalEntity.getAddressBlock());
        assertEquals("長島", postalEntity.getAddressOrg());
        // assertEquals("二丁目25番地3ニッセイ青森センタービル6階", postalEntity.getAddressName());
        // assertTrue(postalEntity.getIsAddPostal());
        // assertTrue(postalEntity.getIsRepairRsdt());

        fail("Not yet implemented");
    }

}
