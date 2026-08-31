package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * AddressPostalWorksPostalProcessor単体テスト
 */
class AddressPostalWorksPostalProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalWorksPostalProcessor processor = new AddressPostalWorksPostalProcessor();

        WkTblPostalCommonEntity wkTblCommonEntity = new WkTblPostalCommonEntity();
        wkTblCommonEntity.setAddressOrg("1");
        wkTblCommonEntity.setAddressName("2");
        wkTblCommonEntity.setAddressPostalId(145);
        wkTblCommonEntity.setIsGyoseikuData(true);
        wkTblCommonEntity.setIsLatest(true);
        wkTblCommonEntity.setLgCode("3");
        wkTblCommonEntity.setPostalcode1("4");
        wkTblCommonEntity.setPostalcode2("5");

        AddressPostalEntity postalEntity = processor.process(wkTblCommonEntity);

        assertEquals(wkTblCommonEntity.getAddressOrg(), postalEntity.getAddressOrg());
        assertEquals(wkTblCommonEntity.getAddressName(), postalEntity.getAddressName());
        assertEquals(wkTblCommonEntity.getAddressPostalId(), postalEntity.getAddressPostalId());
        assertEquals(wkTblCommonEntity.getIsGyoseikuData(), postalEntity.getIsGyoseikuData());
        assertEquals(wkTblCommonEntity.getIsLatest(), postalEntity.getIsLatest());
        assertEquals(wkTblCommonEntity.getLgCode(), postalEntity.getLgCode());
        assertEquals(wkTblCommonEntity.getPostalcode1(), postalEntity.getPostalcode1());
        assertEquals(wkTblCommonEntity.getPostalcode2(), postalEntity.getPostalcode2());
    }

}
