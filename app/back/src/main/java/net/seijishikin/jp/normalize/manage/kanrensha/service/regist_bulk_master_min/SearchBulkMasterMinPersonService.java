package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.SearchWkTblMinPersonPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;


/**
 *  ワークテーブルマスタ企業／団体最小検索Service
 */
@Service
public class SearchBulkMasterMinPersonService {

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public SearchWkTblMinPersonPagingResultDto practice(final SearchWkTbPagingCapsuleDto capsuleDto) {

        // 基本は最新のみ、影響させるデータのみ、未終了データのみ
        List<Boolean> searchHistoryList = this.createSearchConditionList(true, capsuleDto.getHasHistorry());
        List<Boolean> searchAffectedList = this.createSearchConditionList(true, capsuleDto.getHasAffectNot());
        List<Boolean> searchLFinishList = this.createSearchConditionList(false, capsuleDto.getHasFinished());

        Integer userCode = capsuleDto.getUserDto().getUserPersonCode();
        Integer allCount = wkTblKanrenshaPersonAddMinRepository.countByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(
                userCode, searchHistoryList, searchAffectedList, searchLFinishList);

        Integer limit = capsuleDto.getLimit();
        Integer pageNumber = capsuleDto.getPageNumber();

        // 検索条件が異なるなどして指定ページ番号でそのまま検索すると全件以上を指してしまう場合は初期化
        if (allCount < limit * pageNumber) {
            pageNumber = 0;
        }

        SearchWkTblMinPersonPagingResultDto resultDto = new SearchWkTblMinPersonPagingResultDto();
        resultDto.setAllCount(allCount);
        resultDto.setLimit(limit);
        resultDto.setPageNumber(pageNumber);
        resultDto.setListWktblPerson(wkTblKanrenshaPersonAddMinRepository
                .findByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(userCode, searchHistoryList,
                        searchAffectedList, searchLFinishList, Pageable.ofSize(limit).withPage(pageNumber)));

        return resultDto;
    }

    private List<Boolean> createSearchConditionList(final Boolean baseFlg, final Boolean isSearch) {
        List<Boolean> list = new ArrayList<>();
        list.add(baseFlg);
        if (isSearch) {
            list.add(!baseFlg);
        }
        return list;
    }

}
