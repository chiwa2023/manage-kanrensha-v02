package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBuildingResultDto;

/**
 * 住所建物を住居テーブルから検索する(郵便番号なし)
 */
@Service
public class SearchAddressBuildingService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     *
     * @param lgCode 地方自治体コード
     * @param value  選択された番地まで住所
     * @return 検索結果
     */
    @SuppressWarnings("unchecked")
    public PostalCodeBuildingResultDto practice(final String lgCode, final String value) {

        // 行政区住居を検索するの場合、行政区住居を番地までと一致で取得する
        String sql = "SELECT address_building AS value,address_building AS text" + " FROM address_rsdt_" + lgCode
                + "  WHERE address_block = '" + value + "' AND address_building <> '' AND is_latest = 1";
        Query query = entityManager.createNativeQuery(sql, SelectOptionStringDto.class);

        List<SelectOptionStringDto> list = (List<SelectOptionStringDto>) query.getResultList();

        PostalCodeBuildingResultDto resultDto = new PostalCodeBuildingResultDto();
        resultDto.setListOptions(list);
        resultDto.setLgCode(lgCode);
        return resultDto;

    }
}
