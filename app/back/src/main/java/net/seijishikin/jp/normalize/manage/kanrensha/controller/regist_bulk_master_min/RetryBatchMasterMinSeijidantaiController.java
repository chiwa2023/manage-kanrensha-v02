package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_min;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RetryWktblBatchCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min.RetryBatchMasterMinSeijidantaiService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 政治団体体マスタ最小Csv登録Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-master-min")
public class RetryBatchMasterMinSeijidantaiController {

    /** 非同期処理登録専用Service */
    @Autowired
    private RetryBatchMasterMinSeijidantaiService retryBatchMasterMinSeijidantaiService;

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
    @PostMapping("/retry-poli-org")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RetryWktblBatchCapsuleDto capsuleDto) {

        // タスク計画挿入時に失敗の可能性を考慮して必要な変数はtryの外で宣言
        Integer year = Year.now().getValue();
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskPlanCode = 0;
        try {
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            // 非同期処理はタスク登録をする
            TaskPlanInfoDto planDto = switchYearInsertTaskPlanService.practice(year, userDto,
                    TaskInfoConstants.RETRY_SEIJIDANTAI_MIN);
            taskPlanCode = planDto.getTaskPlanCode();

            retryBatchMasterMinSeijidantaiService.practice(capsuleDto.getUserDto(), year, planDto);

            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, year, taskPlanCode);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();
        }

    }

}
