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

        AddressCityDeleteEntity deleteEntity = processor.process(item);

        assertEquals(item.getLgCode(), deleteEntity.getLgCode());
    }

}
