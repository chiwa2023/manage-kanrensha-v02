package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;


/**
 * 郵便番号不規則テーブル郵便番号作業テーブル変換Processor
 */
@Component
public class AddressPostalIrregularWorksProcessor
        implements ItemProcessor<AddressPostalIrregularEntity, WkTblPostalCommonEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPostalCommonEntity process(final AddressPostalIrregularEntity item) throws Exception {

        WkTblPostalCommonEntity entity = new WkTblPostalCommonEntity();

        // PG上で@Tableアノテーションが重複するエラーが発生するため、実装には反映していないが
        // AddressPostalWorksEntityはAddressPostalIrregularEntityをextendsしたテーブル
        BeanUtils.copyProperties(item, entity);

        entity.setWkTblPostalCommonId(0); // auto_increment0明示
        entity.setIsAddPostal(true); // 最終的には郵便番号(正規)データを追加する

        return entity;
    }

}
