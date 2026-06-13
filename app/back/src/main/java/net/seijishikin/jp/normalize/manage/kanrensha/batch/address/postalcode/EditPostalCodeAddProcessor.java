package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;

/**
 * 郵便番号追加から編集ワークテーブルEntity変換Processor
 */
@Component
public class EditPostalCodeAddProcessor implements ItemProcessor<EditPostalCodeOneLineDto, WkTblPostalEditEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPostalEditEntity process(final EditPostalCodeOneLineDto item) throws Exception {

        WkTblPostalEditEntity entity = new WkTblPostalEditEntity();

        BeanUtils.copyProperties(item, entity);

        entity.setFlgEdit(EditPostalConstants.ADD);
        entity.setIsRepair(null);

        return entity;
    }

}
