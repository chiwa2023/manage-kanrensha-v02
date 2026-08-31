package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;

/**
 * 手動修正候補登録Processor
 */
@Component
public class PickupManualWorksProcessor implements ItemProcessor<AddressPostalEntity, AddressPostalRepairLogEntity> {

    /** 記載なし表記 */
    private static final String KISAI_NASHI = "以下に掲載がない場合";

    /** カッコ表記 */
    private static final String KAKKO = "カッコ表記残る";

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalRepairLogEntity process(final AddressPostalEntity item) throws Exception {

        AddressPostalRepairLogEntity entity = new AddressPostalRepairLogEntity();
        BeanUtils.copyProperties(item, entity);

        // 現状2種類が抽出していない
        if (KISAI_NASHI.equals(entity.getAddressOrg())) {
            entity.setStatusText(KISAI_NASHI);
        } else {
            entity.setStatusText(KAKKO);
        }

        return entity;
    }

}
