package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * 郵便番号ワークテーブルから正規郵便番号変換Processor
 */
@Component
public class AddressPostalWorksPostalProcessor implements ItemProcessor<WkTblPostalCommonEntity, AddressPostalEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalEntity process(final WkTblPostalCommonEntity item) throws Exception {

        AddressPostalEntity entity = new AddressPostalEntity();
        BeanUtils.copyProperties(item, entity);

        return entity;

    }

}
