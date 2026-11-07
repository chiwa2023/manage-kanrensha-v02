package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ConvertRoleListBeforeLoginLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2019.TaskPlan2019Repository;

/**
 * 権限限定タスク検索Logic
 */
@Service
public class SearchRoleSomeoneTaskY2019Logic {

    /** タスク計画Repository(2019) */
    @Autowired
    private TaskPlan2019Repository taskPlan2019Repository;

    /** ログイン前名称権限リスト変換Logic */
    @Autowired
    private ConvertRoleListBeforeLoginLogic convertRoleListBeforeLoginLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchTaskPlanResultDto practice(final SearchTaskPlanCapsuleDto capsuleDto) {

        // 検索日時
        LocalDateTime start = capsuleDto.getStartDate();
        LocalDateTime end = capsuleDto.getEndDate();

        // 状態
        Integer flgFinished = capsuleDto.getFlgFinished();
        Integer flgStart = capsuleDto.getFlgStart();
        Integer flgSuspended = capsuleDto.getFlgSuspended();

        List<String> listRole = convertRoleListBeforeLoginLogic.practice(capsuleDto.getUserDto());

        int count = taskPlan2019Repository.countRoleSomeoneTaskCondition(listRole, start, end, flgFinished, flgStart,
                flgSuspended);

        SearchTaskPlanResultDto resultDto = new SearchTaskPlanResultDto();
        resultDto.setAllCount(count);
        resultDto.setLimit(capsuleDto.getLimit());

        // 全件数が0の場合は結果を返却
        final Integer zero = 0;
        if (zero.equals(resultDto.getAllCount())) {
            resultDto.setPageNumber(0);
            return resultDto;
        }

        // 検索条件変更等でページ番号が合わないときはページ番号をリセット
        if (resultDto.getAllCount() < resultDto.getLimit() * resultDto.getPageNumber()) {
            resultDto.setPageNumber(0);
        }

        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListTaskPlan(taskPlan2019Repository.findRoleSomeoneTaskCondition(listRole, start, end, flgFinished,
                flgStart, flgSuspended, pageable));

        return resultDto;
    }

}
