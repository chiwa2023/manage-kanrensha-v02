package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * PostalCodeOneLineProcessor単体テスト
 */
class PostalCodeOneLineProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeOneLineProcessor processor = new PostalCodeOneLineProcessor();

        PostalCodeCsvOneLineDto item = new PostalCodeCsvOneLineDto();
        item.setLgCode("26366");
        item.setPostalcode("6190248");
        item.setPref("京都府");
        item.setCity("相楽郡精華町");
        item.setAddressOrg("新精台");

        AddressPostalEntity postalEntity = processor.process(item);

        assertEquals("263664", postalEntity.getLgCode());
        assertEquals("619", postalEntity.getPostalcode1());
        assertEquals("0248", postalEntity.getPostalcode2());
        assertEquals("京都府相楽郡精華町新精台", postalEntity.getAddressName());
        assertEquals(item.getAddressOrg(), postalEntity.getAddressOrg());
        assertTrue(postalEntity.getIsGyoseikuData());
        
        
        
        
        
        fail("Not yet implemented");
    }

}
