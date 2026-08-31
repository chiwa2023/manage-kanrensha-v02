package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * 作業テーブルから不規則テーブルに変換Processor
 */
@Component
public class AddressPostalWorksIrregularProcessor
        implements ItemProcessor<WkTblPostalCommonEntity, AddressPostalIrregularEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalIrregularEntity process(final WkTblPostalCommonEntity item) throws Exception {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(item, entity);

        return entity;
    }
}
