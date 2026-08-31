package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.ViewCombineAliveRiyoushaRepository;

/**
 * 利用者(APIユーザ・運営者・管理者)全検索Service
 */
@Service
public class SearchRiyoushaAllService {

    /** 最新全権限利用者Repository */
    @Autowired
    private ViewCombineAliveRiyoushaRepository viewCombineAliveRiyoushaRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 利用者検索結果Dto
     */
    public SearchRiyoushaAllResultDto practice(final SearchRiyoushaAllCapsuleDto capsuleDto) {

        SearchRiyoushaAllResultDto resultDto = new SearchRiyoushaAllResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        // 検索権限リストを作成
        List<String> listRole = new ArrayList<>();
        if (capsuleDto.getIsAdminSearch()) {
            listRole.add(UserRoleConstants.ADMIN);
        }
        if (capsuleDto.getIsManagerSearch()) {
            listRole.add(UserRoleConstants.MANAGER);
        }
        if (capsuleDto.getIsPartnerApiSearch()) {
            listRole.add(UserRoleConstants.PARTNER_API);
        }

        // 想定数が少ないので自然検索するほどではない
        String words = "%" + capsuleDto.getSearchNaturalWords() + "%";

        resultDto.setAllCount(viewCombineAliveRiyoushaRepository.countByFullTextAndRole(words, listRole));

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
        resultDto.setListAllRiyousha(
                viewCombineAliveRiyoushaRepository.findByFullTextAndRole(words, listRole, pageable));

        return resultDto;
    }

}
