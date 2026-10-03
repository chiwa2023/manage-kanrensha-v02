package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.TaskPlan2027Repository;

/**
 * タスク計画検索Logic(2027)
 */
@Component
public class SearchTaskPlanY2027Logic {

    // /** 検索語整形Logic */
    // @Autowired
    // private CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic;

    /** タスク計画Repository(2027) */
    @Autowired
    private TaskPlan2027Repository taskPlan2027Repository;

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

        int count = taskPlan2027Repository.countTaskPlan(start, end, searchWord);

        SearchTaskPlanResultDto resultDto = new SearchTaskPlanResultDto();
        resultDto.setAllCount(count);
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(capsuleDto.getPageNumber());
        resultDto.setListTaskPlan(taskPlan2027Repository.findTaskPlan(start, end, searchWord, pageable));

        return resultDto;
    }

}
