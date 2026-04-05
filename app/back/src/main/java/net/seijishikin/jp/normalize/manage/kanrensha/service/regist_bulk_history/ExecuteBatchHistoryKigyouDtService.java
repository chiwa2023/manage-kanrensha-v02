package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history.InsertKanrenshaKigyouDtHistoryBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanTasklet;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * Csv読み取り企業団体履歴登録Service
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class ExecuteBatchHistoryKigyouDtService {

    /** 起動をするJob */
    @Qualifier(InsertKanrenshaKigyouDtHistoryBatchConfiguration.JOB_NAME)
    @Autowired
    private Job insertKanrenshaKigyouDtHistory;

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
                insertKanrenshaKigyouDtHistory.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFilePath", path.toString())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM,
                        Long.parseLong(userDto.getUserPersonId().toString()))
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM,
                        Long.parseLong(userDto.getUserPersonCode().toString()))
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanTasklet.KEY_YEAR, (long) year)
                .addLong(RecordTaskPlanTasklet.KEY_ID, (long) planFileDto.getTaskPlanId())
                .addLong(RecordTaskPlanTasklet.KEY_CODE, (long) planFileDto.getTaskPlanCode()).toJobParameters();

        try {
            jobLauncher.run(insertKanrenshaKigyouDtHistory, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planFileDto.getTaskPlanCode());
        }

    }

}
