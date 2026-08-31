package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory.WriteLogAddressFormatLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.JigyoushoBlockNormalizeLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.PlusCheckDigitUtil;

/**
 * 事業所郵便番号Csv不規則Processor
 */
@Component
public class PostalCodeJigyoushoProcessor
        implements ItemProcessor<PostalCodeCsvJigyoushoDto, AddressPostalIrregularEntity> {

    /** 郵便番号桁数 */
    private static final int POS_DIGIT = 7;

    /** 郵便番号前桁数 */
    private static final int POS_MAE = 3;

    /** Logger */
    @Autowired
    private WriteLogAddressFormatLogic writeLogAddressFormatLogic;

    /** 事業所番地まで住所用標準化Logic */
    @Autowired
    private JigyoushoBlockNormalizeLogic jigyoushoBlockNormalizeLogic;

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalIrregularEntity process(final PostalCodeCsvJigyoushoDto item) throws Exception {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();

        String postalCode = item.getPostalcode();
        if (POS_DIGIT == postalCode.length()) {
            entity.setPostalcode1(postalCode.substring(0, POS_MAE));
            entity.setPostalcode2(postalCode.substring(POS_MAE, POS_DIGIT));
        } else {
            writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, postalCode, "郵便番号が7桁でありません");
        }

        String addressBlock = jigyoushoBlockNormalizeLogic.practice(item.getAddressBlock());

        entity.setLgCode(PlusCheckDigitUtil.plusForLgCode(item.getLgCode()));
        entity.setAddressOrg(item.getAddressOrg());
        entity.setAddressName(item.getAddressBlock());
        entity.setAddressPostal(item.getPref() + item.getCity() + item.getAddressOrg());
        entity.setAddressBlock(addressBlock);
        entity.setIsAddPostal(true); // 仮
        entity.setIsRepairRsdt(true); // 仮

        return entity;
    }

}
