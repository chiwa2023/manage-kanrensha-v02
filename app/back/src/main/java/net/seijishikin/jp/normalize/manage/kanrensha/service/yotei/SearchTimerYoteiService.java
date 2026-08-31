package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.SearchTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.SearchTimerYoteiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;

/**
 * 予定実行検索Service
 */
@Service
public class SearchTimerYoteiService {

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件dto
     * @return 検索結果
     */
    public SearchTimerYoteiResultDto practice(final SearchTimerYoteiCapsuleDto capsuleDto) {

        if (capsuleDto.getIsPeriodSearch()) {
            // 検索条件に日時条件がある場合
            return this.getByDatetime(capsuleDto);

        } else {
            // 検索条件に日時条件がない場合
            return this.getByListOnly(capsuleDto);
        }

    }

    private SearchTimerYoteiResultDto getByDatetime(final SearchTimerYoteiCapsuleDto capsuleDto) {

        SearchTimerYoteiResultDto resultDto = new SearchTimerYoteiResultDto();
        resultDto.setLimit(capsuleDto.getLimit());

        Integer allCount = timerYoteiRepository.countByYoyakuTaskKbnInAndNextTimestampBetweenAndIsLatestTrue(
                capsuleDto.getListYoteiKbn(), capsuleDto.getStartDateTime(), capsuleDto.getEndDateTime());

        // 0件ならそのまま返却
        if (0 == allCount) {
            return resultDto;
        } else {
            resultDto.setAllCount(allCount);
        }

        // 全件数がページ番号×取得件数未満であればページ番号初期化する
        if (allCount < capsuleDto.getLimit() * capsuleDto.getPageNumber()) {
            resultDto.setPageNumber(0);
        } else {
            resultDto.setPageNumber(capsuleDto.getPageNumber());
        }

        // リスト本体を取得して結果を返却
        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListEntity(timerYoteiRepository.findByYoyakuTaskKbnInAndNextTimestampBetweenAndIsLatestTrue(
                capsuleDto.getListYoteiKbn(), capsuleDto.getStartDateTime(), capsuleDto.getEndDateTime(), pageable));

        return resultDto;
    }

    private SearchTimerYoteiResultDto getByListOnly(final SearchTimerYoteiCapsuleDto capsuleDto) {

        SearchTimerYoteiResultDto resultDto = new SearchTimerYoteiResultDto();
        resultDto.setLimit(capsuleDto.getLimit());

        Integer allCount = timerYoteiRepository.countByYoyakuTaskKbnInAndIsLatestTrue(capsuleDto.getListYoteiKbn());

        // 0件ならそのまま返却
        if (0 == allCount) {
            return resultDto;
        } else {
            resultDto.setAllCount(allCount);
        }

        // 全件数がページ番号×取得件数未満であればページ番号初期化する
        if (allCount < capsuleDto.getLimit() * capsuleDto.getPageNumber()) {
            resultDto.setPageNumber(0);
        } else {
            resultDto.setPageNumber(capsuleDto.getPageNumber());
        }

        // リスト本体を取得して結果を返却
        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListEntity(
                timerYoteiRepository.findByYoyakuTaskKbnInAndIsLatestTrue(capsuleDto.getListYoteiKbn(), pageable));

        return resultDto;

    }

}
