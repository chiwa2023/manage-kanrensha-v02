package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
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
    public List<UserPersonEntity> practice(final SearchUserCapsuleDto capsuleDto) {

        // TODO 現状では全文検索用にフォーマット化したカラムを持たないので全文検索しない
        String nameCondition = "%" + capsuleDto.getName() + "%";
        return userPersonRepository.findNameAndRoles(nameCondition, capsuleDto.getListRole());
    }

}
