package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalCodeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号検索Service
 */
@Service
public class SearchPostalCodeService {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchPostalCodeResultDto practice(final SearchPostalCodeCapsuleDto capsuleDto) {

        SearchPostalCodeResultDto resultDto = new SearchPostalCodeResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        String postalcode1 = capsuleDto.getSearchPostalcode1();
        String postalcode2 = capsuleDto.getSearchPostalcode2();
        String addressName = capsuleDto.getSearchAddressName();

        resultDto.setAllCount(addressPostalRepository
                .countByPostalcode1StartingWithAndPostalcode2StartingWithAndIsLatestTrueAndAddressNameStartingWith(
                        postalcode1, postalcode2, addressName));

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
        resultDto.setListItem(addressPostalRepository
                .findByPostalcode1StartingWithAndPostalcode2StartingWithAndIsLatestTrueAndAddressNameStartingWith(
                        postalcode1, postalcode2, addressName, pageable));

        return resultDto;
    }

}
