package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.AddCombineOrgBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.GetCombineYearListLogic;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 個人団体紐づけcsv読み取り処理からすべて企業／団体Service
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class ExecuteBatchCombineSeijidantaiService {

    /** 起動をするJob */
    @Qualifier(AddCombineOrgBatchConfiguration.JOB_NAME)
    @Autowired
    private Job addCombineOrg;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /** 個人団体紐づけ作業可能年取得Logic */
    @Autowired
    private GetCombineYearListLogic getCombineYearListLogic;

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

        List<Short> listYear = getCombineYearListLogic.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                addCombineOrg.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFilePath", path.toString())
                .addLong("userId", Long.parseLong(userDto.getUserPersonId().toString()))
                .addLong("userCode", Long.parseLong(userDto.getUserPersonCode().toString()))
                .addString("userName", userDto.getUserPersonName())
                .addString("kanrenshaKbn", String.valueOf(KanrenshaKbnConstants.SEIJIDANTAI))
                .addString("yearMin", String.valueOf(listYear.getFirst()))
                .addString("yearMax", String.valueOf(listYear.getLast())).toJobParameters();

        try {
            jobLauncher.run(addCombineOrg, jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException exception) {
            // TODO: handle exception
            writeLogService.practiceError("",exception);
        }
    }
}
