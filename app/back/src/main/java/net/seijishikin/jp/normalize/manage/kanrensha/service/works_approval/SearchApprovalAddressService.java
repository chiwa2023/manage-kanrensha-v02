package net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchApprovalAddressResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;


/**
 * 作業承認住所検索Service
 */
@Service
public class SearchApprovalAddressService {

    /** 関連者政治団体住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件格納Dto
     * @return 検索結果Dto
     */
    public SearchApprovalAddressResultDto practice(final SearchWorksApprovalCapsuleDto capsuleDto) {

        // 基本的には作業まだのデータを検索するが、ユーザ指定によっては両方検索する
        List<Boolean> listIsEdit = new ArrayList<>();
        listIsEdit.add(false);
        if (!capsuleDto.getIsExcludeFinishedTask()) {
            listIsEdit.add(true);
        }

        Integer count = capsuleDto.getAllCount();
        // 開始日・終了日を日時に変換
        LocalDateTime start = LocalDateTime.of(capsuleDto.getStartDate(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(capsuleDto.getEndDate(), LocalTime.MAX);
        // 全件数が0件の時は該当の件数を取得
        if (0 == capsuleDto.getAllCount()) {
            count = kanrenshaSeijidantaiAddressRepository.countIsEditData(start, end, listIsEdit);
        }

        SearchApprovalAddressResultDto resultDto = new SearchApprovalAddressResultDto();
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
        resultDto.setListAddress(
                kanrenshaSeijidantaiAddressRepository.findIsEditData(start, end, listIsEdit, pageable));

        return resultDto;
    }

}
