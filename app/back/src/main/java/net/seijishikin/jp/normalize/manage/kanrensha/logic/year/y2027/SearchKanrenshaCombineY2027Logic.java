package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.KanrenshaCombineOrg2027Repository;

/**
 * 関連者紐づけ検索Logic(2027)
 */
@Component
public class SearchKanrenshaCombineY2027Logic {

    /** 個人団体紐づけRepository(2027) */
    @Autowired
    private KanrenshaCombineOrg2027Repository kanrenshaCombineOrg2027Repository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     */
    public SearchKanrenshaCombineResultDto practice(final SearchKanrenshaCombineCapsuleDto capsuleDto) {

        final String BLANK = "";

        String personCode = BLANK;
        String orgCode = BLANK;
        final String WILD = "%";

        // 語句検索にはワイルドカードを付加
        if (!BLANK.equals(capsuleDto.getPersonCode())) {
            personCode = WILD + capsuleDto.getPersonCode() + WILD;
        }
        if (!BLANK.equals(capsuleDto.getOrgCode())) {
            orgCode = WILD + capsuleDto.getOrgCode() + WILD;
        }
        Short kanrenshaKbn = capsuleDto.getKanrenshaKbn();

        SearchKanrenshaCombineResultDto resultDto = new SearchKanrenshaCombineResultDto();
        resultDto.setAllCount(kanrenshaCombineOrg2027Repository.countCodeAndKbn(personCode, orgCode, kanrenshaKbn));

        resultDto.setLimit(capsuleDto.getLimit());

        // 全件数が0の場合は結果を返却
        final Integer zero = 0;
        if (zero.equals(resultDto.getAllCount())) {
            resultDto.setPageNumber(0);
            return resultDto;
        }

        // 検索条件変更等でページ番号が合わないときはページ番号をリセット
        if (resultDto.getAllCount() < resultDto.getLimit() * resultDto.getPageNumber()) {
            resultDto.setPageNumber(0);
        }

        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListDto(
                kanrenshaCombineOrg2027Repository.findCodeAndKbn(personCode, orgCode, kanrenshaKbn, pageable));

        return resultDto;
    }

}
