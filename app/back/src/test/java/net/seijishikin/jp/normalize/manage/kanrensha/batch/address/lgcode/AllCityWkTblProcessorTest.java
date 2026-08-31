package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;

/**
 * AllCityWkTblProcessor単体テスト
 */
class AllCityWkTblProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AllCityWkTblProcessor processor = new AllCityWkTblProcessor();

        AddressAllCityEntity item = new AddressAllCityEntity();
        item.setLgCode("91372");
        item.setPref("和歌山県");
        item.setCounty("道州郡");
        item.setCity("山麓市");
        item.setWard("湖畔区");

        AddressCityDeleteEntity deleteEntity = processor.process(item);

        assertEquals(item.getLgCode(), deleteEntity.getLgCode());
        assertEquals("和歌山県道州郡山麓市湖畔区", deleteEntity.getOrgName());
    }

}
