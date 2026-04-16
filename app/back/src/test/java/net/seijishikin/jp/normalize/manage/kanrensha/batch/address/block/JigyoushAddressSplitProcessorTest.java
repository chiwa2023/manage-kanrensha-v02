package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * JigyoushAddressSplitProcessor単体テスト
 */
class JigyoushAddressSplitProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        JigyoushAddressSplitProcessor processor = new JigyoushAddressSplitProcessor();

        AddressPostalIrregularEntity item = new AddressPostalIrregularEntity();
        item.setAddressBlock("6番8号　北見地方合同庁舎内");
        item.setAddressName("６番８号北見地方合同庁舎内");

        WkTblPostalCommonEntity entity00 = processor.process(item);

        assertEquals("６番８号北見地方合同庁舎内", entity00.getAddressOrg());
        assertEquals("北見地方合同庁舎内", entity00.getAddressName());
        assertEquals("6番8号", entity00.getAddressBlock());
    }
}
