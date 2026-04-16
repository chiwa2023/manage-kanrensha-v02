package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAdminResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaAdminMasterRepository;

/**
 * 利用者(APIユーザ・運営者・管理者)全検索Service
 */
@Service
public class SearchRiyoushaAdminService {

    /** 利用者管理者Repository */
    @Autowired
    private RiyoushaAdminMasterRepository riyoushaAdminMasterRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 利用者検索結果Dto
     */
    public SearchRiyoushaAdminResultDto practice(final SearchRiyoushaAdminCapsuleDto capsuleDto) {

        SearchRiyoushaAdminResultDto resultDto = new SearchRiyoushaAdminResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        String words = "%" + capsuleDto.getSearchNaturalWords() + "%"; // TODO Match Against
        // String words = createSerachWordsBooleanModeUtil.practice(capsuleDto.getSearchNaturalWords());

        resultDto.setAllCount(riyoushaAdminMasterRepository.countFullText(words));

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
        resultDto.setListRiyoushaAdmin(riyoushaAdminMasterRepository.findFullText(words, pageable));

        return resultDto;
    }

}
