package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblRsdtDeleteResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;

/**
 * 住所差分ワークテーブル削除検索Service
 */
@Service
public class SearchWkTblRsdtDeleteService {

    /** 住所差分ワークテーブル削除Repository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchWkTblRsdtDeleteResultDto practice(final SearchWkTblAddressRsdtCapsuleDto capsuleDto) {

        SearchWkTblRsdtDeleteResultDto resultDto = new SearchWkTblRsdtDeleteResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Integer userCode = capsuleDto.getUserDto().getUserPersonCode();

        List<Boolean> listSearchLatest = new ArrayList<>();
        listSearchLatest.add(true);
        if (capsuleDto.getIsSearchHistory()) {
            listSearchLatest.add(false);
        }

        resultDto.setAllCount(
                wkTblAddressRsdtDeleteRepository.countByInsertUserCodeAndIsLatestIn(userCode, listSearchLatest));

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
        resultDto.setListEntity(wkTblAddressRsdtDeleteRepository.findByInsertUserCodeAndIsLatestIn(userCode,
                listSearchLatest, pageable));

        return resultDto;
    }
}
