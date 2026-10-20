package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.z_force.AsyncForceDumpMinMasterService;

/**
 * タイマー実行マスタ最小強制ダンプService
 */
@Service
public class YoteiFuncForceDumpMinMasterService {

    /** 非同期処理専用Service */
    @Autowired
    private AsyncForceDumpMinMasterService asyncForceDumpMinMasterService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 強制ダンプ処理条件Dto
     * @return 起動結果
     */
    public boolean practice(final ForceDumpCapsuleDto capsuleDto) {

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
                resultDto.setIsFailure(true);
                return false;
            }

            // 非同期処理はタスク登録をする
            // TODO Queryはfront連結後決定
            Map<String, String> mapParam = new TreeMap<>();

            InsertTaskPlanResultDto planDto1 = switchYearInsertTaskPlanService.practice(null, userDto, dateTimeStart,
                    TaskInfoConstants.DUMP_MIN_PERSON, mapParam);
            taskPlanCode = planDto1.getTaskPlanCode();

            InsertTaskPlanResultDto planDto2 = switchYearInsertTaskPlanService.practice(null, userDto, dateTimeStart,
                    TaskInfoConstants.DUMP_MIN_KIGYOU, mapParam);

            InsertTaskPlanResultDto planDto3 = switchYearInsertTaskPlanService.practice(null, userDto, dateTimeStart,
                    TaskInfoConstants.DUMP_MIN_SEIJIDANTAI, mapParam);

            asyncForceDumpMinMasterService.practice(year, planDto1, planDto2, planDto3, capsuleDto);

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
