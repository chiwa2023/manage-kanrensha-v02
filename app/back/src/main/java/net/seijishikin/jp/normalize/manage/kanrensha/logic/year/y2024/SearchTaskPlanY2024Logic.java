package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.TaskPlan2024Repository;

/**
 * タスク計画検索Logic(2024)
 */
@Component
public class SearchTaskPlanY2024Logic {

    // /** 検索語整形Logic */
    // @Autowired
    // private CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic;

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    /** 空文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchTaskPlanResultDto practice(final Integer taskInfoCount, final SearchTaskPlanCapsuleDto capsuleDto) {

        Integer userCode = capsuleDto.getUserDto().getUserPersonCode();

        // 検索日時
        LocalDateTime start = capsuleDto.getStartDate();
        LocalDateTime end = capsuleDto.getEndDate();

        // 名称
        // String searchWord =
        // createSerachWordsBooleanModeLogic.practice(capsuleDto.getSearchTaskWord());
        String searchWord = BLANK;
        if (!BLANK.equals(capsuleDto.getSearchTaskWord())) {
            searchWord = "%" + capsuleDto.getSearchTaskWord() + "%";
        }

        // 状態
        Integer flgFinished = capsuleDto.getFlgFinished();
        Integer flgStart = capsuleDto.getFlgStart();
        Integer flgSuspended = capsuleDto.getFlgSuspended();

        // チェック
        List<Integer> infoCodeList = capsuleDto.getInfoCodeList();

        Boolean hasTaskCode = taskInfoCount != infoCodeList.size(); // 全件と同一の場合は検索条件に含めない

        int count = taskPlan2024Repository.countTaskPlan(userCode, start, end, searchWord, flgFinished, flgStart,
                flgSuspended, infoCodeList, hasTaskCode);

        
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
        resultDto.setListTaskPlan(taskPlan2024Repository.findTaskPlan(userCode, start, end, searchWord, flgFinished,
                flgStart, flgSuspended, infoCodeList, hasTaskCode, pageable));

        return resultDto;
    }

}
