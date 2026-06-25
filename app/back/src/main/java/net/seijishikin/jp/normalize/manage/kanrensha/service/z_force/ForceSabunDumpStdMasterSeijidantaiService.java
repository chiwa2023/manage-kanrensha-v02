package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.sabun.master.DumpSabunMasterSeijidantaiBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants.SabunMasterStd;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 政治団体マスタ標準差分データをダンプする非同期Service
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class ForceSabunDumpStdMasterSeijidantaiService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    // TODO 標準出力が完成次第切り替える
    /** 起動をするJob */
    @Qualifier(DumpSabunMasterSeijidantaiBatchConfiguration.JOB_NAME)
    @Autowired
    private Job dumpSabunMasterPoliticalOrganization;

    /** propertiesからインジェクションされたフロントの共通ダンプCSV保存先 */
    private String frontDumpFolder;

    /**
     * フロントの共通ダンプCSV保存先を取得する
     *
     * @return フロントの共通ダンプCSV保存先
     */
    public String getFrontDumpFolder() {
        return frontDumpFolder;
    }

    /**
     * フロントの共通ダンプCSV保存先を設定する
     *
     * @param frontDumpFolder フロントの共通ダンプCSV保存先
     */
    public void setFrontDumpFolder(final String frontDumpFolder) {
        this.frontDumpFolder = frontDumpFolder;
    }

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 非同期処理を行う
     *
     * @param startDate 抽出開始時間
     * @param endDate   抽出終了時間
     */
    public void practice(final Integer year, final InsertTaskPlanResultDto planDto, final LocalDate startDate,
            final LocalDate endDate, final LeastUserDto userDto) {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();
        final String folder = frontDumpFolder + MasterCsvFileNameConstants.FOLDER_MASTER_SABUN;

        JobParameters jobParameters = new JobParametersBuilder(
                dumpSabunMasterPoliticalOrganization.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addLocalDateTime("datetimeStart", LocalDateTime.of(startDate, LocalTime.MIN))
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(endDate.plusDays(1L), LocalTime.MIN))
                .addString("writeFilePath",
                        Paths.get(pathSaved, folder, SabunMasterStd.SABUN_STD_SEIJIDANTAI).toString())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM, (long) userDto.getUserPersonId())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM, (long) userDto.getUserPersonCode())
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year)
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planDto.getTaskPlanId())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planDto.getTaskPlanCode())
                .toJobParameters();

        try {
            jobLauncher.run(dumpSabunMasterPoliticalOrganization, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planDto.getTaskPlanCode());
        }

    }

}
