package net.seijishikin.jp.normalize.manage.kanrensha.controller.task_info;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.EditTaskInfoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_info.SaveTaskInfoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * タスク情報更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/task-info")
public class SaveTaskInfoController {

    /** 未処理タスク取得Service */
    @Autowired
    private SaveTaskInfoService saveTaskInfoService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto タスク情報検索Dto
     * @return レスポンス
     */
    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final @RequestBody EditTaskInfoCapsuleDto capsuleDto) {

        try {
            Integer newId = saveTaskInfoService.practice(capsuleDto);

            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            if (0 == newId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("正常に更新できませんでした");

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);

            } else {
                resultDto.setMessage("正常に更新しました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容

            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("システム例外が発生しました。");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
