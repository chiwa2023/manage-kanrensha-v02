package net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
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

        LocalDateTime dateTimeStart = LocalDateTime.now();
        Integer year = dateTimeStart.getYear();
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskPlanCode = 0;
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            // 処理が選択されていないときは即終了
            if (!capsuleDto.getIsExecuteKigyouDt() && !capsuleDto.getIsExecutePerson()
                    && !capsuleDto.getIsExecuteSeijidantai()) {
                resultDto.setMessage("実行するダンプ処理が選択されていません");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            }

            // TODO Queryはfront連結後決定
            Map<String, String> mapParam = new TreeMap<>();

            // 非同期処理はタスク登録をする
            InsertTaskPlanResultDto planDto1 = switchYearInsertTaskPlanService.practice(userDto, dateTimeStart,
                    TaskInfoConstants.DUMP_HISTORY_PERSON, mapParam);
            taskPlanCode = planDto1.getTaskPlanCode();

            InsertTaskPlanResultDto planDto2 = switchYearInsertTaskPlanService.practice(userDto, dateTimeStart,
                    TaskInfoConstants.DUMP_HISTORY_KIGYOU, mapParam);

            InsertTaskPlanResultDto planDto3 = switchYearInsertTaskPlanService.practice(userDto, dateTimeStart,
                    TaskInfoConstants.DUMP_HISTORY_SEIJIDANTAI, mapParam);

            asyncForceDumpHistoryService.practice(year, planDto1, planDto2, planDto3, capsuleDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, year, taskPlanCode);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
