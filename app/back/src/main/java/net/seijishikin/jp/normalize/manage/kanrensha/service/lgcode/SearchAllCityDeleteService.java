package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.PagingIntegerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressCityDeleteResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressCityDeleteRepository;

/**
 * 地方自治体コード削除検索Service
 */
@Service
public class SearchAllCityDeleteService {

    /** 地方自治体コード削除Repository */
    @Autowired
    private AddressCityDeleteRepository addressCityDeleteRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto ページング条件
     * @return 検索結果Dto
     */
    public SearchAddressCityDeleteResultDto practice(final PagingIntegerCapsuleDto capsuleDto) {

        SearchAddressCityDeleteResultDto resultDto = new SearchAddressCityDeleteResultDto();

        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());
        resultDto.setAllCount(addressCityDeleteRepository.countByIsLatestTrue());

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
        resultDto.setListEntity(addressCityDeleteRepository.findByIsLatestTrue(pageable));

        return resultDto;
    }
}
