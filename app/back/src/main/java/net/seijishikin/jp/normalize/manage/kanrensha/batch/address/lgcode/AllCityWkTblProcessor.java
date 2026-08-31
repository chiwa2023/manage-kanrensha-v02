package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;

/**
 * 全行政区コード更新ワークテーブルProcessor
 */
@Component
public class AllCityWkTblProcessor implements ItemProcessor<AddressAllCityEntity, AddressCityDeleteEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressCityDeleteEntity process(final AddressAllCityEntity item) throws Exception {

        AddressCityDeleteEntity entity = new AddressCityDeleteEntity();

        entity.setLgCode(item.getLgCode());
        entity.setOrgName(item.getPref() + item.getCounty() + item.getCity() + item.getWard());

        return entity;
    }
}
