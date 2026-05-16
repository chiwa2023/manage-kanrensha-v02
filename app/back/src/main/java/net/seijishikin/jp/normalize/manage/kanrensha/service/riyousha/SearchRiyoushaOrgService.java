package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaOrgResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;

/**
 * 利用者組織検索Service
 */
@Service
public class SearchRiyoushaOrgService {

    /** 利用者組織マスタReposiyory */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchRiyoushaOrgResultDto practice(final SearchRiyoushaOrgCapsuleDto capsuleDto) {

        SearchRiyoushaOrgResultDto resultDto = new SearchRiyoushaOrgResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        // String words =
        // createSerachWordsBooleanModeUtil.practice(capsuleDto.getSearchNaturalWords());
        String words = "%" + capsuleDto.getSearchNaturalWords() + "%"; // TODO Match Against

        int cnt = riyoushaOrgMasterRepository.countFullText(words);
        resultDto.setAllCount(cnt);

        // 全件数が0の場合は結果を返却
        final int zero = 0;
        if (zero == cnt) {
            resultDto.setPageNumber(0);
            return resultDto;
        }

        // 検索語を変更するなど、ページング条件で齟齬が発生した場合はページ番号を初期化
        if (resultDto.getAllCount() < resultDto.getLimit() * (resultDto.getPageNumber() + 1)) {
            resultDto.setPageNumber(0);
        }

        // 実検索
        Pageable pageable = Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListRiyoushaOrg(riyoushaOrgMasterRepository.findFullText(words, pageable));

        return resultDto;
    }

}
