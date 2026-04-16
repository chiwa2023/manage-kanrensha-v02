package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;

/**
 * 利用者(APIユーザ・運営者・管理者)全検索Service
 */
@Service
public class SearchRiyoushaPartnerAppiService {

    /** 利用者APIユーザRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 利用者検索結果Dto
     */
    public SearchRiyoushaPartnerApiResultDto practice(final SearchRiyoushaPartnerApiCapsuleDto capsuleDto) {

        SearchRiyoushaPartnerApiResultDto resultDto = new SearchRiyoushaPartnerApiResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        String words = "%" + capsuleDto.getSearchNaturalWords() + "%"; // TODO Match Against
        // String words = createSerachWordsBooleanModeUtil.practice(capsuleDto.getSearchNaturalWords());

        resultDto.setAllCount(riyoushaPartnerApiMasterRepository.countFullText(words));

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
        resultDto.setListRiyoushaPartner(riyoushaPartnerApiMasterRepository.findFullText(words, pageable));

        return resultDto;
    }

}
