package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_history;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RetryWktblBatchCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history.RetryBatchHistorySeijidantaiService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 政治団体履歴Csv登録Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-history")
public class RetryBatchHistorySeijidantaiController {

    /** 非同期処理登録専用Service */
    @Autowired
    private RetryBatchHistorySeijidantaiService retryBatchHistorySeijidantaiService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集後再試行条件Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/retry-seijidantai")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RetryWktblBatchCapsuleDto capsuleDto) {

        // タスク計画挿入時に失敗の可能性を考慮して必要な変数はtryの外で宣言
        LocalDateTime dateTimeStart = LocalDateTime.now();
        Integer year = dateTimeStart.getYear();
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskPlanCode = 0;
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            // 非同期処理はタスク登録をする

            // TODO Queryはfront連結後決定
            Map<String, String> mapParam = new TreeMap<>();

            InsertTaskPlanResultDto planDto = switchYearInsertTaskPlanService.practice(null, userDto, dateTimeStart,
                    TaskInfoConstants.RETRY_SEIJIDANTAI_HISTORY, mapParam);
            taskPlanCode = planDto.getTaskPlanCode();

            retryBatchHistorySeijidantaiService.practice(capsuleDto.getUserDto(), year, planDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, year, taskPlanCode);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

}
