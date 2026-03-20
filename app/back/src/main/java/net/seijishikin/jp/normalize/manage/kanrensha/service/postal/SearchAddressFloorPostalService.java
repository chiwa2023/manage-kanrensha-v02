package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBuildingResultDto;

/**
 * 郵便番号を階ごとに割り振られている場合の住所(建物と部屋番号)を検索する
 */
@Service
public class SearchAddressFloorPostalService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     *
     * @param lgCode      地方自治体コード
     * @param postalCode1 郵便番号1
     * @param postalCode2 郵便番号2
     * @return 検索結果
     */
    @SuppressWarnings("unchecked")
    public PostalCodeBuildingResultDto practice(final String lgCode, final String postalCode1,
            final String postalCode2) {

        // 行政区住居を検索しないの場合、郵便番号で該当データを取得する
        String sql = "SELECT address_building AS value , address_building AS text" + " FROM address_rsdt_" + lgCode
                + "  WHERE postalcode1 = '" + postalCode1 + "' AND postalcode2 = '" + postalCode2
                + "' AND address_building <> '' AND is_latest =1";
        Query query = entityManager.createNativeQuery(sql, SelectOptionIntegerDto.class);

        List<SelectOptionIntegerDto> list = (List<SelectOptionIntegerDto>) query.getResultList();

        PostalCodeBuildingResultDto resultDto = new PostalCodeBuildingResultDto();
        resultDto.setListOptions(list);
        resultDto.setLgCode(lgCode);
        return resultDto;
    }
}
