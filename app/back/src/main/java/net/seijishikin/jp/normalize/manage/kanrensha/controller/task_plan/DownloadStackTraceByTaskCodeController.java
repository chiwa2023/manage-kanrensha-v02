package net.seijishikin.jp.normalize.manage.kanrensha.controller.task_plan;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.GetTaskStackTraceCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.PickupStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * タスク計画からStackTrace取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/stack-trace")
public class DownloadStackTraceByTaskCodeController {

    /** StackTrace取得Service */
    @Autowired
    private PickupStackTraceService pickupStackTraceService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto タスク計画Dto
     * @return ダウンロードファイルレスポンス
     */
    @PostMapping("/get-by-code")
    public ResponseEntity<OneFileBlobResultDto> practice(final @RequestBody GetTaskStackTraceCapsuleDto capsuleDto) {

        OneFileBlobResultDto resultDto = new OneFileBlobResultDto();
        try {
            resultDto = pickupStackTraceService.practiceByTaskCode(capsuleDto.getTaskYear(),
                    capsuleDto.getTaskPlanCode());

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

}
