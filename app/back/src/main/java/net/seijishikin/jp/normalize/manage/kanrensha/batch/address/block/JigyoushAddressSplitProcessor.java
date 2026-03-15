package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * 事業所データワークテーブル変換Processor
 */
@Component
public class JigyoushAddressSplitProcessor
        implements ItemProcessor<AddressPostalIrregularEntity, WkTblPostalCommonEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPostalCommonEntity process(final AddressPostalIrregularEntity item) throws Exception {

        WkTblPostalCommonEntity entity = new WkTblPostalCommonEntity();
        BeanUtils.copyProperties(item, entity);

        // 分割可能なデータを格納する
        entity.setAddressOrg(entity.getAddressName());
        String[] cell = entity.getAddressBlock().split("　");
        entity.setAddressName(cell[1]);
        entity.setAddressBlock(cell[0]);

        // 引き続き使用するので更新だけする
        entity.setIsLatest(true);
        
        return entity;
    }

}
