package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;

/**
 * タスク計画検索Logic(2025)
 */
@Component
public class SearchTaskPlanY2025Logic {

    // /** 検索語整形Logic */
    // @Autowired
    // private CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchTaskPlanResultDto practice(final SearchTaskPlanCapsuleDto capsuleDto) {

        LocalDateTime start = capsuleDto.getStartDate();
        LocalDateTime end = capsuleDto.getEndDate();
        // String searchWord =
        // createSerachWordsBooleanModeLogic.practice(capsuleDto.getSearchTaskWord());
        String searchWord = "%"+capsuleDto.getSearchTaskWord() + "%";

        // TODO カウント処理は改めてブラッシュアップする

        int count = taskPlan2025Repository.countTaskPlan(start, end, searchWord);

        SearchTaskPlanResultDto resultDto = new SearchTaskPlanResultDto();
        resultDto.setAllCount(count);
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(capsuleDto.getPageNumber());
        resultDto.setListTaskPlan(taskPlan2025Repository.findTaskPlan(start, end, searchWord, pageable));

        return resultDto;
    }

}
