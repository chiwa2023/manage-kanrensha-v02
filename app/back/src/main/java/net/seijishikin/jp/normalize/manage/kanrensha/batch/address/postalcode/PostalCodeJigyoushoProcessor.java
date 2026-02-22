package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.PlusCheckDigitUtil;

/**
 * 事業所郵便番号Csv不規則Processor
 */
@Component
public class PostalCodeJigyoushoProcessor
        implements ItemProcessor<PostalCodeCsvJigyoushoDto, AddressPostalIrregularEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalIrregularEntity process(final PostalCodeCsvJigyoushoDto item) throws Exception {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();

        entity.setPostalcode(item.getPostalcode());
        entity.setLgCode(PlusCheckDigitUtil.plusForLgCode(item.getLgCode()));
        entity.setAddressOrg(item.getAddressOrg());
        entity.setAddressName(item.getAddressBlock());
        entity.setAddressPostal(item.getPref() + item.getCity() + item.getAddressOrg());
        entity.setAddressBlock(item.getAddressBlock());
        entity.setIsAddPostal(true); // 仮
        entity.setIsRepairRsdt(true); // 仮

        return entity;
    }

}
