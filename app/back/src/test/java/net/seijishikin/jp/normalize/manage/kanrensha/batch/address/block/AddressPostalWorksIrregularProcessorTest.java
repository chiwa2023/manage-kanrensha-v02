package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * AddressPostalWorksIrregularProcessor単体テスト
 */
class AddressPostalWorksIrregularProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalCommonEntity entity = new WkTblPostalCommonEntity();
        entity.setLgCode("123123");
        entity.setPostalcode1("9876543");
        entity.setPostalcode2("6543");
        entity.setAddressOrg("山麓町（字小山、字大山）");
        entity.setAddressName("架空市山麓町");
        entity.setAddressPostal("宮崎県架空市山麓町");
        entity.setAddressBlock("字小山１９５番地３");
        entity.setAddressPostalIrregularId(1859);
        entity.setIsAddPostal(true);
        entity.setIsRepairRsdt(false);

        AddressPostalWorksIrregularProcessor processor = new AddressPostalWorksIrregularProcessor();

        AddressPostalIrregularEntity worksEntity = processor.process(entity);

        assertEquals(entity.getLgCode(), worksEntity.getLgCode());
        assertEquals(entity.getPostalcode1(), worksEntity.getPostalcode1());
        assertEquals(entity.getPostalcode2(), worksEntity.getPostalcode2());
        assertEquals(entity.getAddressOrg(), worksEntity.getAddressOrg());
        assertEquals(entity.getAddressName(), worksEntity.getAddressName());
        assertEquals(entity.getAddressPostal(), worksEntity.getAddressPostal());
        assertEquals(entity.getAddressBlock(), worksEntity.getAddressBlock());
        assertEquals(entity.getAddressPostalIrregularId(), worksEntity.getAddressPostalIrregularId());
        assertEquals(entity.getIsAddPostal(), worksEntity.getIsAddPostal());
        assertEquals(entity.getIsRepairRsdt(), worksEntity.getIsRepairRsdt());

    }

}
