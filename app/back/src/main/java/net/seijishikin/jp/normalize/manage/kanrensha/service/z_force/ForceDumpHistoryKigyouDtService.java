package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaKigyouDtHistoryBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 企業／団体履歴データをダンプする非同期Service
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class ForceDumpHistoryKigyouDtService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobOperator jobOperator;

    /** 起動をするJob */
    @Qualifier(DumpKanrenshaKigyouDtHistoryBatchConfiguration.JOB_NAME)
    @Autowired
    private Job dumpPartnerKigyouDtHistory;

    /** propertiesからインジェクションされたフロントの共通ダンプCSV保存先 */
    @Value("${net.seijishikin.jp.normalize.kanrensha.front_dump_folder:/front/public/dump}")
    private String frontDumpFolder;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    @Value("${net.seijishikin.jp.normalize.kanrensha.storage_folder:/home/app/store_storage/kanrensha-v02}")
    private String storageFolder;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 非同期処理を行う
     *
     * @param endDate 抽出終了時間
     */
    public void practice(final Integer year, final InsertTaskPlanResultDto planDto, final LocalDate endDate,
            final LeastUserDto userDto) {

        final String pathSaved = storageFolder;
        
        final String folder = frontDumpFolder + "/dump_history_kigyou_dt";

        JobParameters jobParameters = new JobParametersBuilder(
                dumpPartnerKigyouDtHistory.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(endDate.plusDays(1L), LocalTime.MIN))
                .addString("srcPath", Paths.get(pathSaved, folder).toString())
                .addString("writeFilePath01",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_01.csv").toString())
                .addString("writeFilePath02",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_02.csv").toString())
                .addString("writeFilePath03",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_03.csv").toString())
                .addString("writeFilePath04",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_04.csv").toString())
                .addString("writeFilePath05",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_05.csv").toString())
                .addString("writeFilePath06",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_06.csv").toString())
                .addString("writeFilePath07",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_07.csv").toString())
                .addString("writeFilePath08",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_08.csv").toString())
                .addString("writeFilePath09",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_09.csv").toString())
                .addString("writeFilePath10",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_10.csv").toString())
                .addString("writeFilePath11",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_11.csv").toString())
                .addString("writeFilePath12",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_12.csv").toString())
                .addString("writeFilePath13",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_13.csv").toString())
                .addString("writeFilePath14",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_14.csv").toString())
                .addString("writeFilePath15",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_15.csv").toString())
                .addString("writeFilePath16",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_16.csv").toString())
                .addString("writeFilePath17",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_17.csv").toString())
                .addString("writeFilePath18",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_18.csv").toString())
                .addString("writeFilePath19",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_19.csv").toString())
                .addString("writeFilePath20",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_20.csv").toString())
                .addString("writeFilePath21",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_21.csv").toString())
                .addString("writeFilePath22",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_22.csv").toString())
                .addString("writeFilePath23",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_23.csv").toString())
                .addString("writeFilePath24",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_24.csv").toString())
                .addString("writeFilePath25",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_25.csv").toString())
                .addString("writeFilePath26",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_26.csv").toString())
                .addString("writeFilePath27",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_27.csv").toString())
                .addString("writeFilePath28",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_28.csv").toString())
                .addString("writeFilePath29",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_29.csv").toString())
                .addString("writeFilePath30",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_30.csv").toString())
                .addString("writeFilePath31",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_31.csv").toString())
                .addString("writeFilePath32",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_32.csv").toString())
                .addString("writeFilePath33",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_33.csv").toString())
                .addString("writeFilePath34",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_34.csv").toString())
                .addString("writeFilePath35",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_35.csv").toString())
                .addString("writeFilePath36",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_36.csv").toString())
                .addString("writeFilePath37",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_37.csv").toString())
                .addString("writeFilePath38",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_38.csv").toString())
                .addString("writeFilePath39",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_39.csv").toString())
                .addString("writeFilePath40",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_40.csv").toString())
                .addString("writeFilePath41",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_41.csv").toString())
                .addString("writeFilePath42",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_42.csv").toString())
                .addString("writeFilePath43",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_43.csv").toString())
                .addString("writeFilePath44",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_44.csv").toString())
                .addString("writeFilePath45",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_45.csv").toString())
                .addString("writeFilePath46",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_46.csv").toString())
                .addString("writeFilePath47",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_47.csv").toString())
                .addString("writeFilePath99",
                        Paths.get(pathSaved, folder, "partner_kigyou_dt_history_99.csv").toString())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM, (long) userDto.getUserPersonId())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM, (long) userDto.getUserPersonCode())
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year)
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planDto.getTaskPlanId())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planDto.getTaskPlanCode())
                .toJobParameters();
        try {
            jobOperator.run(dumpPartnerKigyouDtHistory, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planDto.getTaskPlanCode());
        }
    }
}
