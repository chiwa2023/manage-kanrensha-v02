package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.code_move.DumpCodeMoveBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants.CodeMove;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record.InsertDupmRunRecordService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者コード移動強制ダンプService
 */
@Service
public class ForceDumpKanrenshaCodeMoveService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobOperator jobOperator;

    /** 起動をするJob */
    @Qualifier(DumpCodeMoveBatchConfiguration.JOB_NAME)
    @Autowired
    private Job dumpCodeMove;

    /** propertiesからインジェクションされたフロントの共通ダンプCSV保存先 */
    @Value("${net.seijishikin.jp.normalize.kanrensha.front_dump_folder:/front/public/dump}")
    private String frontDumpFolder;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    @Value("${net.seijishikin.jp.normalize.kanrensha.storage_folder:/home/app/store_storage/kanrensha-v02}")
    private String storageFolder;

    /** ダンプ実行記録Service */
    @Autowired
    private InsertDupmRunRecordService insertDupmRunRecordService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 非同期処理を行う
     *
     * @param endDate 抽出終了時間
     */
    @Async
    public void practice(final Integer year, final InsertTaskPlanResultDto planDto, final LocalDate endDate,
            final LeastUserDto userDto) {

        final String pathSaved = storageFolder;

        final String folder = frontDumpFolder + MasterCsvFileNameConstants.FOLDER_MASTER;

        Path pathFile = Paths.get(pathSaved, folder, CodeMove.ACCEPTED);

        final String endKey = "datetimeEnd";
        
        JobParameters jobParameters = new JobParametersBuilder(
                dumpCodeMove.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addLocalDateTime(endKey, LocalDateTime.of(endDate.plusDays(1L), LocalTime.MIN))
                .addString("writeFilePath", pathFile.toString())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM, (long) userDto.getUserPersonId())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM, (long) userDto.getUserPersonCode())
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year)
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planDto.getTaskPlanId())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planDto.getTaskPlanCode())
                .toJobParameters();

        try {
            jobOperator.run(dumpCodeMove, jobParameters);

            // 実行記録
            LocalDateTime startDatetime = DtoEntityInitialValueInterface.INIT_TIMESTAMP;
            LocalDateTime endDatetime = jobParameters.getLocalDateTime(endKey);
            List<Integer> listTask = new ArrayList<>();
            listTask.add(TaskInfoConstants.DUMP_CODE_MOVE);
            insertDupmRunRecordService.practice(listTask, startDatetime, endDatetime, userDto);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planDto.getTaskPlanCode());
        }
    }

}
