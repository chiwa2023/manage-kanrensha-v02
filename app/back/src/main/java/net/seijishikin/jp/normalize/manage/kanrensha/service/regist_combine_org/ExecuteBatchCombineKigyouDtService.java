package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.AddCombineOrgBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
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

            List<Short> listYear = getCombineYearListLogic.practice();

            JobParameters jobParameters = new JobParametersBuilder(
                    addCombineOrg.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                    .addLocalDateTime("executeTime", LocalDateTime.now()) //
                    .addString("readFilePath", planFileDto.getReadFile().toString()) // NOPMD LawOfDemeter
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM,
                            Long.parseLong(userDto.getUserPersonId().toString()))
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM,
                            Long.parseLong(userDto.getUserPersonCode().toString()))
                    .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                    .addString("kanrenshaKbn", String.valueOf(KanrenshaKbnConstants.KIGYOU_DT))
                    .addString("yearMin", String.valueOf(listYear.getFirst()))
                    .addString("yearMax", String.valueOf(listYear.getLast()))
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year)
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planFileDto.getTaskPlanId())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planFileDto.getTaskPlanCode())
                    .toJobParameters();

            jobLauncher.run(addCombineOrg, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planFileDto.getTaskPlanCode());
        }
    }
}
