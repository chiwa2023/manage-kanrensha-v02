package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * AddressPostalWorksProcessor単体テスト
 */
class AddressPostalWorksProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalEntity postalEntity = new AddressPostalEntity();
        postalEntity.setAddressOrg("1");
        postalEntity.setAddressName("2");
        postalEntity.setAddressPostalId(145);
        postalEntity.setIsGyoseikuData(true);
        postalEntity.setIsLatest(true);
        postalEntity.setLgCode("3");
        postalEntity.setPostalcode1("4");
        postalEntity.setPostalcode2("5");

        AddressPostalWorksProcessor processor = new AddressPostalWorksProcessor();
        WkTblPostalCommonEntity wkTblEntity = processor.process(postalEntity);

        assertEquals(postalEntity.getAddressOrg(), wkTblEntity.getAddressOrg());
        assertEquals(postalEntity.getAddressName(), wkTblEntity.getAddressName());
        assertEquals(postalEntity.getAddressPostalId(), wkTblEntity.getAddressPostalId());
        assertEquals(postalEntity.getIsGyoseikuData(), wkTblEntity.getIsGyoseikuData());
        assertEquals(postalEntity.getIsLatest(), wkTblEntity.getIsLatest());
        assertEquals(postalEntity.getLgCode(), wkTblEntity.getLgCode());
        assertEquals(postalEntity.getPostalcode1(), wkTblEntity.getPostalcode1());
        assertEquals(postalEntity.getPostalcode2(), wkTblEntity.getPostalcode2());
    }

}
