package net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchApprovalShokugyouResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;


/**
 * 関連者個人職業承認用検索
 */
@Service
public class SearchApprovalShokugyouService {

    /** 関連者個人基本Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件格納Dto
     * @return 検索結果Dto
     */
    public SearchApprovalShokugyouResultDto practice(final SearchWorksApprovalCapsuleDto capsuleDto) {

        // 基本的には作業まだのデータを検索するが、ユーザ指定によっては両方検索する
        List<Boolean> listIsEdit = new ArrayList<>();
        listIsEdit.add(false);
        if (!capsuleDto.getIsExcludeFinishedTask()) {
            listIsEdit.add(true);
        }

        final boolean isEdit = true;

        Integer count = capsuleDto.getAllCount();

        // 開始日・終了日を日時に変換
        LocalDateTime start = LocalDateTime.of(capsuleDto.getStartDate(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(capsuleDto.getEndDate(), LocalTime.MAX);
        
        // 全件数が0件の時は該当の件数を取得
        if (0 == capsuleDto.getAllCount()) {
            count = kanrenshaPersonPropertyRepository
                    .countByInsertTimestampBetweenAndIsShokyouAcceptInAndIsShokyouEditAndIsLatest(
                            start, end, listIsEdit, isEdit,
                            SetTableDataHistoryUtil.INSERT_STATE);
        }

        SearchApprovalShokugyouResultDto resultDto = new SearchApprovalShokugyouResultDto();
        // 件数が0件の時は空の結果Dtoを返却
        if (count == 0) {
            return resultDto;
        }

        // 全件数がページング件数とページ番号の積にないときはページング初期化
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());
        resultDto.setAllCount(count);
        if (count <= resultDto.getLimit() * (resultDto.getPageNumber() - 1)) {
            resultDto.setPageNumber(0);
        }

        // 内容をページングを考慮して取得
        Pageable pageable = Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListShokugyou(kanrenshaPersonPropertyRepository
                .findByInsertTimestampBetweenAndIsShokyouAcceptInAndIsShokyouEditAndIsLatest(start,
                        end, listIsEdit, isEdit, SetTableDataHistoryUtil.INSERT_STATE, pageable));

        return resultDto;
    }

}
