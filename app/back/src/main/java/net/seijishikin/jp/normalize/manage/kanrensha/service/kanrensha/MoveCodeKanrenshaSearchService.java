package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeSearchCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeSearchResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;

/**
 * 関連者コード移動申請検索Service
 */
@Service
public class MoveCodeKanrenshaSearchService {

    /** 関連者コード移動申請Repository */
    @Autowired
    private KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public MoveKanrenshaCodeSearchResultDto practice(final MoveKanrenshaCodeSearchCapsuleDto capsuleDto) {

        MoveKanrenshaCodeSearchResultDto resultDto = new MoveKanrenshaCodeSearchResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        List<Short> listStatus = new ArrayList<>();

        if (capsuleDto.getIsPromoteSearch()) {
            listStatus.add(ShinseiStatusConstants.PROMOTE);
        }
        if (capsuleDto.getIsRejectSearch()) {
            listStatus.add(ShinseiStatusConstants.REJECT);
        }
        if (capsuleDto.getIsAcceptSearch()) {
            listStatus.add(ShinseiStatusConstants.ACCEPT);
        }
        if (capsuleDto.getIsResearchSearch()) {
            listStatus.add(ShinseiStatusConstants.RESEARCH);
        }

        LocalDateTime startDatetime = LocalDateTime.of(capsuleDto.getStartDate(), LocalTime.MIN);
        LocalDateTime endDatetime = LocalDateTime.of(capsuleDto.getEndDate(), LocalTime.MAX);

        resultDto.setAllCount(kanrenshaCodeMoveRepository
                .countByIsLatestTrueAndInsertTimestampBetweenAndMoveStatusIn(startDatetime, endDatetime, listStatus));

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

        resultDto.setListEntity(kanrenshaCodeMoveRepository.findByIsLatestTrueAndInsertTimestampBetweenAndMoveStatusIn(
                startDatetime, endDatetime, listStatus,
                Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber())));

        return resultDto;

    }
}
