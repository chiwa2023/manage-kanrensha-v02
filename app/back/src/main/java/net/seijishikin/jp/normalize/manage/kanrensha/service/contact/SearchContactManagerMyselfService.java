package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.ContactManagerRepository;

/**
 * 自分自身が問い合わせした問い合わせ検索Service
 */
@Service
public class SearchContactManagerMyselfService {

    /** 運営者連絡Repository */
    @Autowired
    private ContactManagerRepository contactManagerRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchContactManagerResultDto practice(final SearchContactManagerCapsuleDto capsuleDto) {

        SearchContactManagerResultDto resultDto = new SearchContactManagerResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Integer userCode = capsuleDto.getUserDto().getUserPersonCode();

        resultDto.setAllCount(contactManagerRepository.countByInquireUserCodeAndIsLatestTrue(userCode));

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

        resultDto.setListEntity(contactManagerRepository.findByInquireUserCodeAndIsLatestTrue(userCode,
                Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber())));

        return resultDto;
    }

}
