package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserEntityResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;

/**
 * ユーザ検索Service
 */
@Service
public class SearchUserServcie {

    /** ユーザ個人Repojitory */
    @Autowired
    private UserPersonRepository userPersonRepository;
    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public SearchUserEntityResultDto practice(final SearchUserCapsuleDto capsuleDto) {

        // TODO 現状では全文検索用にフォーマット化したカラムを持たないので全文検索しない
        String nameCondition = "%" + capsuleDto.getName() + "%";

        SearchUserEntityResultDto resultDto = new SearchUserEntityResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        // 件数が抽出数×ページ番号以下の場合は検索条件が変わっているのでページ番号を初期化する
        resultDto.setAllCount(userPersonRepository.countNameAndRoles(nameCondition, capsuleDto.getListRole()));
        if (capsuleDto.getPageNumber() * capsuleDto.getLimit() > resultDto.getAllCount()) {
            resultDto.setPageNumber(0);
        }

        // 実際の件数をページングで取得する
        Pageable pageable = Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListPersonEntity(
                userPersonRepository.findNameAndRoles(nameCondition, capsuleDto.getListRole(), pageable));

        return resultDto;
    }

}
