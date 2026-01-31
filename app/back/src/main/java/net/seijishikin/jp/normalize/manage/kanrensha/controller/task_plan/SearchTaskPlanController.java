package net.seijishikin.jp.normalize.manage.kanrensha.controller.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSearchTaskPlanService;

/**
 * タスク計画検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/task-plan")
public class SearchTaskPlanController {

    /** 年切替タスク計画検索Service */
    @Autowired
    private SwitchYearSearchTaskPlanService switchYearSearchTaskPlanService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchTaskPlanResultDto> practice(final @RequestBody SearchTaskPlanCapsuleDto capsuleDto) {

        try {
            
            System.out.println("------検索条件");            
            System.out.println("**" + capsuleDto.getStartDate());            
            System.out.println("**" + capsuleDto.getEndDate());            
            System.out.println("**" + capsuleDto.getSearchTaskWord());            
            System.out.println("**" + capsuleDto.getFlgFinished());            
            System.out.println("**" + capsuleDto.getFlgStart());            
            System.out.println("**" + capsuleDto.getFlgSuspended());            
            System.out.println("**" + capsuleDto.getInfoCodeList());            
            
            
            
            SearchTaskPlanResultDto resultDto = switchYearSearchTaskPlanService.practice(capsuleDto);

            final Integer zero = 0;
            if (zero.equals(resultDto.getAllCount())) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD すべての例外をCatchが目的
            saveStackTraceService.practice(exception, null, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SearchTaskPlanResultDto());
        }
    }

}
