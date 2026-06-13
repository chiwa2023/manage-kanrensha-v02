package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodePostalResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 住所郵便番号まで取得Service
 */
@Service
public class SearchAddressPostalService {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /**
     * 処理を行う
     *
     * @param postalCode1 郵便番号1
     * @param postalCode2 郵便番号2
     * @return 検索結果
     */
    public PostalCodePostalResultDto practice(final String postalCode1, final String postalCode2) {

        // 正規郵便番号リスト件数を郵便番号、自治体住居を検索するで取得する
        List<SelectOptionIntegerDto> list = addressPostalRepository.findByPostalCodeAndSearchGyoseiku(postalCode1,
                postalCode2);

        if (list.isEmpty()) {
            // 空の場合は不規則を郵便番号で取得する
            PostalCodePostalResultDto resultDto = new PostalCodePostalResultDto();
            resultDto.setIsGyouseikuData(false);
            resultDto.setListOptions(addressPostalIrregularRepository.findByPostalCode(postalCode1, postalCode2));

            return resultDto;
        } else {
            // 空でない場合はそのまま送付
            PostalCodePostalResultDto resultDto = new PostalCodePostalResultDto();
            resultDto.setIsGyouseikuData(true);
            resultDto.setListOptions(list);

            return resultDto;
        }
    }

}
