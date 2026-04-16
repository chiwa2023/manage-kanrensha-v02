package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * アドレス・ベース・レジストリ公式Csv用自システム格納Dto自システムテーブル変換Processor
 */
@Component
public class RsdtAddressProcessor implements ItemProcessor<RsdtAddressCsvDto, AddressRsdtBaseEntity> {

    /** 空白文字 */
    private static final String BLANK = "";

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressRsdtBaseEntity process(final RsdtAddressCsvDto item) throws Exception {

        AddressRsdtBaseEntity entity = new AddressRsdtBaseEntity();

        BeanUtils.copyProperties(item, entity);

        // 建物までの住所変換
        entity.setAddressBlock(this.convertBlockAddress(item));

        // 建物は住居番号2Idが空でないときに住居番号2番号を補う TODO 規約で全角文字であることを要求される場合は変換する
        if (!BLANK.equals(item.getRsdtNum2())) {
            entity.setAddressBuilding(item.getRsdtNum2() + "号室");
        }

        return entity;
    }

    private String convertBlockAddress(final RsdtAddressCsvDto item) {

        StringBuilder builder = new StringBuilder();
        builder.append(item.getCity()).append(item.getWard()).append(item.getOazaCho()).append(item.getChome())
                .append(item.getKoaza());

        // TODO 規約で全角文字であることを要求される場合は変換する
        if (!BLANK.equals(item.getBlkNum())) {
            builder.append(item.getBlkNum()).append("番地");
        }
        if (!BLANK.equals(item.getRsdtNum())) {
            builder.append(item.getRsdtNum()).append('号');
        }

        return builder.toString();
    }
}
