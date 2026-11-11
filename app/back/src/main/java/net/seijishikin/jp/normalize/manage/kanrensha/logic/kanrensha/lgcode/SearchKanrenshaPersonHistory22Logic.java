package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaPersonResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory22Repository;

/**
 * 関連者企業・団体履歴検索Logic(22)
 */
@Component
public class SearchKanrenshaPersonHistory22Logic {

    /** 関連者企業・団体履歴Repository */
    @Autowired
    private KanrenshaPersonHistory22Repository kanrenshaPersonHistory22Repository;

    //    /** 検索語全文検索用Util */
    //    @Autowired
    //    private CreateSerachWordsBooleanModeUtil createSerachWordsBooleanModeUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者企業・団体履歴検索条件Dto
     * @return 検索結果Dto
     */
    public SearchHistoryKanrenshaPersonResultDto practice(final SearchHistoryKanrenshaCapsuleDto capsuleDto) {

        SearchHistoryKanrenshaPersonResultDto resultDto = new SearchHistoryKanrenshaPersonResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        // String searchWords = createSerachWordsBooleanModeUtil.practice(capsuleDto.getSearchNaturalWords());
        String searchWords = "%" + capsuleDto.getSearchNaturalWords() + "%";

        resultDto.setAllCount(kanrenshaPersonHistory22Repository.countFullText(searchWords));

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

        resultDto.setListHistoryDto(kanrenshaPersonHistory22Repository.findFullText(searchWords,
                Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber())));

        return resultDto;
    }

}
