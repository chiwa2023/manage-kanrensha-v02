package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.PlusCheckDigitUtil;

/**
 * 郵便番号CSV登録Enitty変換Processor
 */
@Component
public class PostalCodeOneLineProcessor implements ItemProcessor<PostalCodeCsvOneLineDto, AddressPostalEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalEntity process(final PostalCodeCsvOneLineDto item) throws Exception {

        AddressPostalEntity entity = new AddressPostalEntity();

        entity.setPostalcode(item.getPostalcode());
        entity.setLgCode(PlusCheckDigitUtil.plusForLgCode(item.getLgCode()));
        entity.setAddressName(item.getPref() + item.getCity() + item.getAddressOrg());
        entity.setAddressOrg(item.getAddressOrg());
        entity.setIsGyoseikuData(true);

        return entity;
    }

}
