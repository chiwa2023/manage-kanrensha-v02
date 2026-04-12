package net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.z_force.AsyncForceDumpHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 履歴ダンプController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/dump-history")
public class ForceDumpHistoryController {

    /** 非同期処理専用Service */
    @Autowired
    private AsyncForceDumpHistoryService asyncForceDumpHistoryService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ダンプ実行条件
     */
    @PostMapping("/execute")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final @RequestBody ForceDumpCapsuleDto capsuleDto) {

        Integer year = Year.now().getValue();
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskPlanCode = 0;
        try {
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            // 処理が選択されていないときは即終了
            if (!capsuleDto.getIsExecuteKigyouDt() && !capsuleDto.getIsExecutePerson()
                    && !capsuleDto.getIsExecuteSeijidantai()) {
                resultDto.setMessage("実行するダンプ処理が選択されていません");
                return ResponseEntity.status(HttpResponseStatus.ACCEPTED.code()).body(resultDto);
            }

            // 非同期処理はタスク登録をする
            TaskPlanInfoDto planDto1 = switchYearInsertTaskPlanService.practice(year, userDto,
                    TaskInfoConstants.DUMP_HISTORY_PERSON);
            taskPlanCode = planDto1.getTaskPlanCode();

            TaskPlanInfoDto planDto2 = switchYearInsertTaskPlanService.practice(year, userDto,
                    TaskInfoConstants.DUMP_HISTORY_KIGYOU);

            TaskPlanInfoDto planDto3 = switchYearInsertTaskPlanService.practice(year, userDto,
                    TaskInfoConstants.DUMP_HISTORY_SEIJIDANTAI);

            asyncForceDumpHistoryService.practice(year, planDto1, planDto2, planDto3, capsuleDto);

            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, year, taskPlanCode);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();
        }
    }

}
