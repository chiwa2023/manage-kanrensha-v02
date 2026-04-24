package net.seijishikin.jp.normalize.manage.kanrensha.controller.task_info;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.SearchTaskInfoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.SearchTaskInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_info.SearchTaskInfoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * タスク情報検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/task-info")
public class SearchTaskInfoController {

    /** 未処理タスク取得Service */
    @Autowired
    private SearchTaskInfoService searchTaskInfoService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto タスク情報検索Dto
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchTaskInfoResultDto> practice(final @RequestBody SearchTaskInfoCapsuleDto capsuleDto) {

        Integer year = LocalDate.now().getYear();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(searchTaskInfoService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容

            saveStackTraceService.practice(exception, year, 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SearchTaskInfoResultDto());
        }
    }

}
