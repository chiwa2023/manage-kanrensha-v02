package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.NaturalTextSearchPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SearchSnsServiceResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.SnsServiceRepository;

/**
 * SNSサービス検索Service
 */
@Service
public class SearchSnsDataService {

    /** SNSサービスRepository */
    @Autowired
    private SnsServiceRepository snsServiceRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchSnsServiceResultDto practice(final NaturalTextSearchPagingCapsuleDto capsuleDto) {

        SearchSnsServiceResultDto resultDto = new SearchSnsServiceResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        String words = "%" + capsuleDto.getSearchNaturalWords() + "%"; // TODO Match Against
        // String words =
        // createSerachWordsBooleanModeUtil.practice(capsuleDto.getSearchNaturalWords());

        resultDto.setAllCount(snsServiceRepository.countFullText(words));

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
        resultDto.setList(snsServiceRepository.findFullText(words, pageable));

        return resultDto;
    }

}
