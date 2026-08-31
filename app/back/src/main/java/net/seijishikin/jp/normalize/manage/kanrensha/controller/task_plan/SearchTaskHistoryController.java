package net.seijishikin.jp.normalize.manage.kanrensha.controller.task_plan;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskHistoryResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSearchTaskHistoryService;

/**
 * タスク計画履歴取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/task-plan")
public class SearchTaskHistoryController {

    /** タスク計画履歴取得Service */
    @Autowired
    private SwitchYearSearchTaskHistoryService switchYearSearchTaskHistoryService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search-history")
    public ResponseEntity<SearchTaskHistoryResultDto> practice(
            final @RequestBody SearchTaskHistoryCapsuleDto capsuleDto) {
        try {
            SearchTaskHistoryResultDto resultDto = new SearchTaskHistoryResultDto();
            resultDto.setListTaskHistory(switchYearSearchTaskHistoryService.practice(capsuleDto));

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
