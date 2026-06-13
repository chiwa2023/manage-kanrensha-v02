package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * 郵便番号以下に掲載なし住所番地まで検索Service
 */
@Service
public class SearchAddressRsdtIkaniKeisaiNashiService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param postalEntity 郵便番号Entity
     * @return 検索結果
     */
    @SuppressWarnings("unchecked")
    public PostalCodeBlockResultDto practice(final AddressPostalEntity postalEntity) {

        PostalCodeBlockResultDto resultDto = new PostalCodeBlockResultDto();
        String lgCode = postalEntity.getLgCode();
        resultDto.setLgCode(lgCode);

        String name = postalEntity.getAddressName().replaceAll("以下に掲載がない場合", "");

        String sql = "SELECT address_block AS value, RIGHT(address_block, CHAR_LENGTH(address_block)-" + name.length()
                + ") AS text" + " FROM address_rsdt_" + lgCode + "  WHERE address_block LIKE '" + name
                + "%' AND address_building = '' AND is_latest = 1 AND postalcode1 = '' AND postalcode2 = ''";
        Query query = entityManager.createNativeQuery(sql, SelectOptionStringDto.class);

        List<SelectOptionStringDto> list = (List<SelectOptionStringDto>) query.getResultList();

        resultDto.setIsGyouseikuData(true);
        resultDto.setListOptions(list);

        return resultDto;
    }

}
