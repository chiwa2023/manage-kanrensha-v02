package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTblAddByXmlPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;

/**
 * XMLからマスタ最小ワークテーブル検索サービス
 */
@Service
public class SearchAddByXmlService {

    /** XMLからマスタ最小ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public SearchWkTblAddByXmlPagingResultDto practice(final SearchWkTbPagingCapsuleDto capsuleDto) {

        // 基本は最新のみ、影響させるデータのみ、未終了データのみ
        List<Boolean> searchHistoryList = this.createSearchConditionList(true, capsuleDto.getHasHistorry());
        List<Boolean> searchAffectedList = this.createSearchConditionList(true, capsuleDto.getHasAffectNot());
        List<Boolean> searchLFinishList = this.createSearchConditionList(false, capsuleDto.getHasFinished());

        Integer userCode = capsuleDto.getUserDto().getUserPersonCode();
        Integer allCount = wkTblMasterAllByXmlRepository.countByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(
                userCode, searchHistoryList, searchAffectedList, searchLFinishList);

        Integer limit = capsuleDto.getLimit();
        Integer pageNumber = capsuleDto.getPageNumber();

        // 検索条件が異なるなどして指定ページ番号でそのまま検索すると全件以上を指してしまう場合は初期化
        if (allCount < limit * pageNumber) {
            pageNumber = 0;
        }

        SearchWkTblAddByXmlPagingResultDto resultDto = new SearchWkTblAddByXmlPagingResultDto();
        resultDto.setAllCount(allCount);
        resultDto.setLimit(limit);
        resultDto.setPageNumber(pageNumber);
        resultDto.setListXmlEntity(wkTblMasterAllByXmlRepository
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
