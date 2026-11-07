package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.z_force.ForceDumpKanrenshaCodeMoveService;

/**
 * タイマー実行関連者コード移動強制ダンプService
 */
@Service
public class YoteiFuncForceDumpKanrenshaCodeMoveService {

    /** 非同期処理専用Service */
    @Autowired
    private ForceDumpKanrenshaCodeMoveService forceDumpKanrenshaCodeMoveService;

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
    public boolean practice(final @RequestBody ForceDumpCapsuleDto capsuleDto) {

        LocalDateTime dateTimeStart = LocalDateTime.now();
        Integer year = dateTimeStart.getYear();
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskPlanCode = 0;
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {

            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            // 非同期処理はタスク登録をする
            Map<String, String> mapParam = new TreeMap<>();

            InsertTaskPlanResultDto planDto1 = switchYearInsertTaskPlanService.practice(null, userDto, dateTimeStart,
                    TaskInfoConstants.DUMP_CODE_MOVE, mapParam);
            taskPlanCode = planDto1.getTaskPlanCode();

            forceDumpKanrenshaCodeMoveService.practice(year, planDto1, capsuleDto.getDateEnd(),
                    capsuleDto.getUserDto());

            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");
            return true;

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, year, taskPlanCode);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return false;
        }

    }

}
