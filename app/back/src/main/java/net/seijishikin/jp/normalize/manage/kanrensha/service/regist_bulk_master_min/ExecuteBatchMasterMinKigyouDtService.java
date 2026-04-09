package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.AddMinKanrenshaKigyouDtMasterBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * Csv読み取り企業団体マスタ最小登録Service
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class ExecuteBatchMasterMinKigyouDtService {

    /** 起動をするJob */
    @Qualifier(AddMinKanrenshaKigyouDtMasterBatchConfiguration.JOB_NAME)
    @Autowired
    private Job addMinKanrenshaKigyouDtMaster;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** Stacktrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /**
     * 処理を行う
     *
     * @param year        登録年
     * @param userDto     ユーザ最小限Dto
     * @param planFileDto タスク計画・使用ファイルDto
     */
    @Async
    public void practice(final Integer year, final LeastUserDto userDto, final TaskPlanWithUseFileDto planFileDto) {

        Path path = Paths.get(storageFolder, planFileDto.getReadFile().toString()); // NOPMD LowOfDemeter

        JobParameters jobParameters = new JobParametersBuilder(
                addMinKanrenshaKigyouDtMaster.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFilePath", path.toString())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM,
                        Long.parseLong(userDto.getUserPersonId().toString()))
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM,
                        Long.parseLong(userDto.getUserPersonCode().toString()))
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year)
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planFileDto.getTaskPlanId())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planFileDto.getTaskPlanCode()).toJobParameters();

        try {
            jobLauncher.run(addMinKanrenshaKigyouDtMaster, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planFileDto.getTaskPlanCode());
        }

    }

}
