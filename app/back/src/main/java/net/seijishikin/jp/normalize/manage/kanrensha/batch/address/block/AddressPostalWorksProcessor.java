package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * 郵便番号からワークテーブル変換Processor
 */
@Component
public class AddressPostalWorksProcessor implements ItemProcessor<AddressPostalEntity, WkTblPostalCommonEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPostalCommonEntity process(final AddressPostalEntity item) throws Exception {

        WkTblPostalCommonEntity entity = new WkTblPostalCommonEntity();
        BeanUtils.copyProperties(item, entity);

        return entity;
    }

}
