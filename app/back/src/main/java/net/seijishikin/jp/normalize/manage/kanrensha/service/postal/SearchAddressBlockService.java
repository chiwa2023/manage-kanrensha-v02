package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 住所番地まで検索Service
 */
@Service
public class SearchAddressBlockService {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** その他住所検索Service */
    @Autowired
    private SearchAddressRsdtOtherService searchAddressRsdtOtherService;

    /** 以下に掲載のない場合住所検索Service */
    @Autowired
    private SearchAddressRsdtIkaniKeisaiNashiService searchAddressRsdtIkaniKeisaiNashiService;

    /** 記載なし表記 */
    private static final String KISAI_NASHI = "以下に掲載がない場合";

    /**
     * 処理を行う
     *
     * @param tableid         テーブルId
     * @param isGyouseikuData 自治体住居検索
     * @return 検索結果
     */
    @SuppressWarnings("unchecked")
    public PostalCodeBlockResultDto practice(final Integer tableid, final boolean isGyouseikuData) {

        if (isGyouseikuData) {

            // 自治体住居を検索する場合、自治体住居を住居の前方一致で取得する
            AddressPostalEntity postalEntity = addressPostalRepository.findById(tableid).get();

            String addressOrg = postalEntity.getAddressOrg();

            // 以下に掲載のない場合は別の方法で検索する
            if (KISAI_NASHI.equals(addressOrg)) {
                return searchAddressRsdtIkaniKeisaiNashiService.practice(postalEntity);
            }

            // （その他）に該当する場合は別の方法で検索する
            if (addressOrg.contains("（その他）")) {
                return searchAddressRsdtOtherService.practice(postalEntity);
            }

            PostalCodeBlockResultDto resultDto = new PostalCodeBlockResultDto();
            String lgCode = postalEntity.getLgCode();
            resultDto.setLgCode(lgCode);

            String addressName = postalEntity.getAddressName();

            String sql = "SELECT address_block AS value, RIGHT(address_block, CHAR_LENGTH(address_block)-"
                    + addressName.length() + ") AS text" + " FROM address_rsdt_" + lgCode
                    + "  WHERE address_block LIKE '" + addressName + "%' AND address_building = '' AND is_latest = 1";
            Query query = entityManager.createNativeQuery(sql, SelectOptionStringDto.class);

            List<SelectOptionStringDto> list = (List<SelectOptionStringDto>) query.getResultList();

            // 空が返ってくる場合は郵便番号住所=住居住所の場合(範囲展開)。
            // この場合は1件だけの範囲前住所と分割する
            // ex.北海道足寄郡足寄町中矢9番地
            // address_orgが"中矢"なので9番地だけを分割して1件のリストにする
            if (1 == list.size()) { // NOPMD AvodLiteralIf
                this.changeEqualBlockAddressToPostalName(list, addressOrg, addressName);
            }

            resultDto.setIsGyouseikuData(true);
            resultDto.setListOptions(list);

            return resultDto;

        } else {
            // 自治体住居を検索しない場合不規則に郵便番号でアクセスして取得
            AddressPostalIrregularEntity irregularEntity = addressPostalIrregularRepository
                    .findById(Math.toIntExact(tableid)).get();

            SelectOptionStringDto dto = new SelectOptionStringDto("", "");
            dto.setValue(irregularEntity.getAddressPostal() + irregularEntity.getAddressBlock());
            dto.setText(irregularEntity.getAddressBlock());

            List<SelectOptionStringDto> list = new ArrayList<>();
            list.add(dto);

            PostalCodeBlockResultDto resultDto = new PostalCodeBlockResultDto();
            resultDto.setLgCode(irregularEntity.getLgCode());
            resultDto.setIsGyouseikuData(false);
            resultDto.setListOptions(list);

            return resultDto;
        }

    }

    private void changeEqualBlockAddressToPostalName(final List<SelectOptionStringDto> list, final String addressOrg,
            final String addressName) {

        SelectOptionStringDto stringDtoOne = list.get(0);
        if (addressName.equals(stringDtoOne.getValue())) {
            int pos = addressName.lastIndexOf(addressOrg);
            String dataPlusKey = addressOrg + "★"
                    + addressName.substring(pos + addressOrg.length(), addressName.length());
            stringDtoOne.setText(dataPlusKey);
        }

    }
}
