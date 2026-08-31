package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;

/**
 * 関連者個人を検索する
 */
@Service
public class SearchKanrenshaKigyouDtListService {

    /** 関連者個人マスタRespoitory */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件(ページング含む)格納Dto
     * @return 検索結果
     */
    public SearchKanrenshaKigyouDtResultDto practice(final @RequestBody SearchKanrenshaKigyouDtCapsuleDto capsuleDto) {

        SearchKanrenshaKigyouDtResultDto resultDto = new SearchKanrenshaKigyouDtResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        // いろいろ言われるまでは前方一致
        final String PERCENT = "%";

        String houjinNo = capsuleDto.getHoujinNo() + PERCENT;
        String orgName = capsuleDto.getName() + PERCENT;
        String address = capsuleDto.getAddress() + PERCENT;
        String delegate = capsuleDto.getDelegate() + PERCENT;

        resultDto.setAllCount(
                kanrenshaKigyouDtMasterRepository.countSearchCondition(houjinNo, orgName, address, delegate));

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

        resultDto.setListMasterKigyouDt(kanrenshaKigyouDtMasterRepository.findSearchCondition(houjinNo, orgName,
                address, delegate, Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber())));

        return resultDto;
    }
}
