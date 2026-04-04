package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.AddMinKanrenshaKigyouDtMasterBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

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

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

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
     * @param readFilePath 読み取りcsvファイルパス
     * @param userDto      ユーザDto
     */
    @Async
    public void practice(final String readFilePath, final LeastUserDto userDto) {

        Path path = Paths.get(storageFolder, readFilePath);

        JobParameters jobParameters = new JobParametersBuilder(
                addMinKanrenshaKigyouDtMaster.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFilePath", path.toString())
                .addLong("userId", Long.parseLong(userDto.getUserPersonId().toString()))
                .addLong("userCode", Long.parseLong(userDto.getUserPersonCode().toString()))
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        try {
            jobLauncher.run(addMinKanrenshaKigyouDtMaster, jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException exception) {
            // TODO: handle exception
            writeLogService.practiceError("",exception);
        }

    }

}
