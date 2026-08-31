package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalIllegularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalIllegularResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 郵便番号不規則検索Service
 */
@Service
public class SearchPostalIrregularBuildingDelegateService {

    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchPostalIllegularResultDto practice(final SearchPostalIllegularCapsuleDto capsuleDto) {
        SearchPostalIllegularResultDto resultDto = new SearchPostalIllegularResultDto();

        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        resultDto.setAllCount(addressPostalIrregularRepository
                .countByAddressOrgContainingAndIsRepairRsdtAndIsLatestTrue("（地階・階層不明）", false));

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
        Pageable pageable = Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber());

        resultDto.setListItem(addressPostalIrregularRepository
                .findByAddressOrgContainingAndIsRepairRsdtAndIsLatestTrue("（地階・階層不明）", false, pageable));

        return resultDto;
    }

}
