package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.AddCombineOrgBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanTasklet;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.GetCombineYearListLogic;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 個人団体紐づけcsv読み取り処理からすべて政治団体Service
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class ExecuteBatchCombineKigyouDtService {

    /** 起動をするJob */
    @Qualifier(AddCombineOrgBatchConfiguration.JOB_NAME)
    @Autowired
    private Job addCombineOrg;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** Stacktrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

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
     * @param year        登録年
     * @param userDto     ユーザ最小限Dto
     * @param planFileDto タスク計画・使用ファイルDto
     */
    @Async
    public void practice(final Integer year, final LeastUserDto userDto, final TaskPlanWithUseFileDto planFileDto) {

        try {
            Path path = Paths.get(storageFolder, planFileDto.getReadFile().toString()); // NOPMD LowOfDemeter

            List<Short> listYear = getCombineYearListLogic.practice();

            JobParameters jobParameters = new JobParametersBuilder(
                    addCombineOrg.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                    .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFilePath", path.toString())
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM,
                            Long.parseLong(userDto.getUserPersonId().toString()))
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM,
                            Long.parseLong(userDto.getUserPersonCode().toString()))
                    .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                    .addString("kanrenshaKbn", String.valueOf(KanrenshaKbnConstants.KIGYOU_DT))
                    .addString("yearMin", String.valueOf(listYear.getFirst()))
                    .addString("yearMax", String.valueOf(listYear.getLast()))
                    .addLong(RecordTaskPlanTasklet.KEY_YEAR, (long) year)
                    .addLong(RecordTaskPlanTasklet.KEY_ID, (long) planFileDto.getTaskPlanId())
                    .addLong(RecordTaskPlanTasklet.KEY_CODE, (long) planFileDto.getTaskPlanCode()).toJobParameters();

            jobLauncher.run(addCombineOrg, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planFileDto.getTaskPlanCode());
        }
    }
}
