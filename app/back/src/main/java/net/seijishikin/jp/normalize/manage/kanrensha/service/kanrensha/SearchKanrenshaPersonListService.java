package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaPersonResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;

/**
 * 関連者個人を検索する
 */
@Service
public class SearchKanrenshaPersonListService {

    /** 関連者個人マスタRespoitory */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件(ページング含む)格納Dto
     * @return 検索結果
     */
    public SearchKanrenshaPersonResultDto practice(final @RequestBody SearchKanrenshaPersonCapsuleDto capsuleDto) {

        SearchKanrenshaPersonResultDto resultDto = new SearchKanrenshaPersonResultDto();

        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        // いろいろ言われるまでは前方一致
        final String PERCENT = "%";

        String orgName = capsuleDto.getName() + PERCENT;
        String address = capsuleDto.getAddress() + PERCENT;
        String shokkugyou = capsuleDto.getShokugyou() + PERCENT;

        resultDto.setAllCount(kanrenshaPersonMasterRepository.countSearchCondition(orgName, address, shokkugyou));

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

        resultDto.setListMasterPerson(kanrenshaPersonMasterRepository.findSearchCondition(orgName, address, shokkugyou,
                Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber())));

        return resultDto;
    }
}
