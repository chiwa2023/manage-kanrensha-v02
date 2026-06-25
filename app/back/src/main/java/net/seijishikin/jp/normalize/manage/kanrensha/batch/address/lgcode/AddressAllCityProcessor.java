package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;


/**
 * アドレス・ベース・レジストリCsvからEntity変換Processor
 */
@Component
public class AddressAllCityProcessor  implements ItemProcessor<AllCityCsvDto, AddressAllCityEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressAllCityEntity process(final AllCityCsvDto item) throws Exception {

        AddressAllCityEntity entity = new AddressAllCityEntity();

        BeanUtils.copyProperties(item, entity);

        return entity;
    }

}
