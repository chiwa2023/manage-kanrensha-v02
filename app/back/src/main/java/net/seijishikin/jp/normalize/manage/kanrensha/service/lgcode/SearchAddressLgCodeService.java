package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchLgCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchLgCodeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * 地方自治体コード検索Service
 */
@Service
public class SearchAddressLgCodeService {

    /** 地方自治体コードRepository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchLgCodeResultDto practice(final SearchLgCodeCapsuleDto capsuleDto) {

        SearchLgCodeResultDto resultDto = new SearchLgCodeResultDto();

        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        String prefCode = capsuleDto.getPrefCode() + "%";
        String words = "%" + capsuleDto.getSearchWords() + "%";

        resultDto.setAllCount(addressAllCityRepository.countPrefAndWords(words, prefCode));

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
        resultDto.setListEntity(addressAllCityRepository.findPrefAndWords(words, prefCode, pageable));

        return resultDto;
    }

}
