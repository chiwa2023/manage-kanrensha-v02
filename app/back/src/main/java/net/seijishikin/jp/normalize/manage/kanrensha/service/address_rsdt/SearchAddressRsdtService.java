package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressRsdtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * 住居検索Service
 */
@Service
public class SearchAddressRsdtService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    @SuppressWarnings("unchecked")
    public SearchAddressRsdtResultDto practice(final SearchAddressRsdtCapsuleDto capsuleDto) {

        SearchAddressRsdtResultDto resultDto = new SearchAddressRsdtResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        String lgCode = capsuleDto.getSearchLgCode();

        String sqlCondition = this.createCondition(capsuleDto.getSearchNaturalWords());

        String sqlCount = "SELECT count(*) FROM address_rsdt_" + lgCode + sqlCondition;
        Query queryCount = entityManager.createNativeQuery(sqlCount, Integer.class);
        resultDto.setAllCount((Integer) queryCount.getResultList().get(0));

        // 全件数が0の場合は結果を返却
        final Integer zero = 0;
        if (zero.equals(resultDto.getAllCount())) {
            resultDto.setPageNumber(0);
            return resultDto;
        }

        // 検索語を変更するなど、ページング条件で齟齬が発生した場合はページ番号を初期化
        if (resultDto.getAllCount() < resultDto.getLimit() * resultDto.getPageNumber()) {
            resultDto.setPageNumber(0);
        }

        // 実検索
        String sqlList = "SELECT * FROM address_rsdt_" + lgCode + sqlCondition + createPaging(resultDto);
        Query queryList = entityManager.createNativeQuery(sqlList, AddressRsdtTemplateEntity.class);
        resultDto.setListEntity((List<AddressRsdtTemplateEntity>) queryList.getResultList());

        return resultDto;
    }

    private String createCondition(final String addressName) {

        StringBuilder builder = new StringBuilder(" WHERE address_block LIKE '%");
        builder.append(addressName).append("%' AND is_latest = 1");

        return builder.toString();
    }

    private String createPaging(final SearchAddressRsdtResultDto resultDto) {

        StringBuilder builder = new StringBuilder(" LIMIT ");
        builder.append(resultDto.getLimit()).append(" OFFSET ")
                .append(resultDto.getLimit() * resultDto.getPageNumber());

        return builder.toString();
    }

}
