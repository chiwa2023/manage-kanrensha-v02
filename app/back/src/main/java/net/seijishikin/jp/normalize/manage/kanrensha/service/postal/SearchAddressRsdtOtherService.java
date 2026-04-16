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
 * 郵便番号(その他)から住所検索Service
 */
@Service
public class SearchAddressRsdtOtherService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param postalEntity 郵便番号Entity
     * @return 番地まで住所結果Dto
     */
    @SuppressWarnings("unchecked")
    public PostalCodeBlockResultDto practice(final AddressPostalEntity postalEntity) {

        PostalCodeBlockResultDto resultDto = new PostalCodeBlockResultDto();
        String lgCode = postalEntity.getLgCode();
        resultDto.setLgCode(lgCode);

        String name = postalEntity.getAddressName();

        String sql = "SELECT address_block AS value, RIGHT(address_block, CHAR_LENGTH(address_block)-" + name.length()
                + ") AS text" + " FROM address_rsdt_" + lgCode + "  WHERE postalcode1='" + postalEntity.getPostalcode1()
                + "' AND postalcode2='" + postalEntity.getPostalcode2() + "' AND is_latest = 1";
        Query query = entityManager.createNativeQuery(sql, SelectOptionStringDto.class);

        List<SelectOptionStringDto> list = (List<SelectOptionStringDto>) query.getResultList();
        resultDto.setIsGyouseikuData(true);
        resultDto.setListOptions(list);

        return resultDto;
    }

}
