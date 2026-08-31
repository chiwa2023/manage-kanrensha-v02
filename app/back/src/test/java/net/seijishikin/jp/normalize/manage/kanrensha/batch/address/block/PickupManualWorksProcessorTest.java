package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;

/**
 * PickupManualWorksProcessor単体テスト
 */
class PickupManualWorksProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** 記載なし表記 */
    private static final String KISAI_NASHI = "以下に掲載がない場合";

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalEntity postalEntity0 = new AddressPostalEntity();
        postalEntity0.setAddressOrg("以下に掲載がない場合");
        postalEntity0.setAddressName("北海道室蘭市以下に掲載がない場合");
        postalEntity0.setAddressPostalId(145);
        postalEntity0.setIsGyoseikuData(true);
        postalEntity0.setIsLatest(true);
        postalEntity0.setLgCode("3");
        postalEntity0.setPostalcode1("4");
        postalEntity0.setPostalcode2("5");

        PickupManualWorksProcessor processor = new PickupManualWorksProcessor();
        AddressPostalRepairLogEntity repairLogEntity0 = processor.process(postalEntity0);

        assertEquals(postalEntity0.getAddressOrg(), repairLogEntity0.getAddressOrg());
        assertEquals(postalEntity0.getAddressName(), repairLogEntity0.getAddressName());
        assertEquals(postalEntity0.getAddressPostalId(), repairLogEntity0.getAddressPostalId());
        assertEquals(postalEntity0.getIsGyoseikuData(), repairLogEntity0.getIsGyoseikuData());
        assertEquals(postalEntity0.getIsLatest(), repairLogEntity0.getIsLatest());
        assertEquals(postalEntity0.getLgCode(), repairLogEntity0.getLgCode());
        assertEquals(postalEntity0.getPostalcode1(), repairLogEntity0.getPostalcode1());
        assertEquals(postalEntity0.getPostalcode2(), repairLogEntity0.getPostalcode2());
        assertEquals(KISAI_NASHI, repairLogEntity0.getStatusText());

        AddressPostalEntity postalEntity1 = new AddressPostalEntity();
        postalEntity1.setAddressOrg("北海道室蘭市(字地名)");
        postalEntity1.setAddressName("北海道室蘭市(字地名)");
        postalEntity1.setAddressPostalId(145);
        postalEntity1.setIsGyoseikuData(true);
        postalEntity1.setIsLatest(true);
        postalEntity1.setLgCode("3");
        postalEntity1.setPostalcode1("4");
        postalEntity1.setPostalcode2("5");

        AddressPostalRepairLogEntity repairLogEntity1 = processor.process(postalEntity1);
        assertEquals("カッコ表記残る", repairLogEntity1.getStatusText());
    }

}
