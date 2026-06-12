package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchWkTblPostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchWkTblPostalCodeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;

/**
 * 郵便番号差分ワークテーブル検索Service
 */
@Service
public class SearchWktblPostalCodeService {

    /** 郵便番号差分ワークテーブルRepository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchWkTblPostalCodeResultDto practice(final SearchWkTblPostalCodeCapsuleDto capsuleDto) {

        SearchWkTblPostalCodeResultDto resultDto = new SearchWkTblPostalCodeResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        List<Boolean> listSearchLatest = new ArrayList<>();
        listSearchLatest.add(true);
        if (capsuleDto.getIsSearchHistory()) {
            listSearchLatest.add(false);
        }

        List<Boolean> listSearchRepair = new ArrayList<>();
        listSearchRepair.add(true);
        if (capsuleDto.getIsSearchRepair()) {
            listSearchRepair.add(false);
        }

        Integer userCode = capsuleDto.getUserDto().getUserPersonCode();

        resultDto.setAllCount(wkTblPostalEditRepository.countByInsertUserCodeAndIsLatestInAndIsRepairIn(userCode,
                listSearchLatest, listSearchRepair));

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
        resultDto.setListEntity(
                wkTblPostalEditRepository.findByInsertUserCodeAndIsLatestInAndIsRepairInOrderByFlgHenkouRiyu(userCode,
                        listSearchLatest, listSearchRepair, pageable));

        return resultDto;
    }
}
