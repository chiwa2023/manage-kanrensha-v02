package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;

/**
 * タスク計画検索Logic(2026)
 */
@Component
public class SearchTaskPlanY2026Logic {

    // /** 検索語整形Logic */
    // @Autowired
    // private CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    /** 空文字 */
    private static final String BLANK = "";

    /** タスクの種類数 */
    private static final Integer TASK_AMOUNT = 3;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchTaskPlanResultDto practice(final SearchTaskPlanCapsuleDto capsuleDto) {

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
        Boolean hasTaskCode = TASK_AMOUNT != infoCodeList.size(); // 全件と同一の場合は検索条件に含めない

        int count = taskPlan2026Repository.countTaskPlan(userCode, start, end, searchWord, flgFinished, flgStart,
                flgSuspended, infoCodeList, hasTaskCode);

        SearchTaskPlanResultDto resultDto = new SearchTaskPlanResultDto();
        resultDto.setAllCount(count);
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(capsuleDto.getPageNumber());
        resultDto.setListTaskPlan(taskPlan2026Repository.findTaskPlan(userCode, start, end, searchWord, flgFinished,
                flgStart, flgSuspended, infoCodeList, hasTaskCode, pageable));

        return resultDto;
    }

}
