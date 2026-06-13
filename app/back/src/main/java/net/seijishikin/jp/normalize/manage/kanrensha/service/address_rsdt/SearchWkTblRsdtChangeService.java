package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblRsdtChangeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;

/**
 * 住所差分ワークテーブル削除検索Service
 */
@Service
public class SearchWkTblRsdtChangeService {

    /** 住所差分ワークテーブル更新Repository */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchWkTblRsdtChangeResultDto practice(final SearchWkTblAddressRsdtCapsuleDto capsuleDto) {

        SearchWkTblRsdtChangeResultDto resultDto = new SearchWkTblRsdtChangeResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Integer userCode = capsuleDto.getUserDto().getUserPersonCode();

        List<Integer> listSearchLatest = new ArrayList<>();
        listSearchLatest.add(1);
        if (capsuleDto.getIsSearchHistory()) {
            listSearchLatest.add(0);
        }

        resultDto.setAllCount(
                wkTblAddressRsdtChangeRepository.countByInsertUserCodeAndIsLatestIn(userCode, listSearchLatest));

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
        resultDto.setListEntity(wkTblAddressRsdtChangeRepository.findByInsertUserCodeAndIsLatestIn(userCode,
                listSearchLatest, pageable));

        return resultDto;
    }

}
