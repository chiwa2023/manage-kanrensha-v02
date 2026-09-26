package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.ContactManagerRepository;

/**
 * 問い合わせ案件履歴取得Service
 */
@Service
public class HistoryContactManagerService {

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

        resultDto.setListEntity(contactManagerRepository
                .findByContactManagerCodeOrderByContactManagerIdAsc(capsuleDto.getContactManagerCode()));

        return resultDto;
    }

}
