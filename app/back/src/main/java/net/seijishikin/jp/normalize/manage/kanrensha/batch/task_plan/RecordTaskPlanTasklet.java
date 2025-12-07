package net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearUpdateTaskPlanService;

/**
 * タスク計画終了更新Tasklet
 */
@Component
public class RecordTaskPlanTasklet implements Tasklet, StepExecutionListener {

    /** タスク計画年切替保存Service */
    @Autowired
    private SwitchYearUpdateTaskPlanService switchYearUpdateTaskPlanService;

    /** BatchユーザDto復元Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最小限Dto */
    private LeastUserDto userDto = new LeastUserDto();

    /** 起動条件記録年呼び出しKey */
    public static final String KEY_YEAR = "tableYear";

    /** 起動条件タスク計画コード呼び出しKey */
    public static final String KEY_ID = "taskId";

    /** 記録年 */
    private Integer tableYear = 0;

    /** タスク計画コード */
    private Integer taskId = 0;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        // ユーザ最小限取り出し
        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);

        // その他の起動条件取り出し
        JobParameters parameters = stepExecution.getJobParameters();
        tableYear = Math.toIntExact(parameters.getLong(KEY_YEAR));
        taskId = Math.toIntExact(parameters.getLong(KEY_ID));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // このタスクレットまでたどり着いた場合、すべてのステップで例外で落ちなかったときになるので処理終了を登録する
        switchYearUpdateTaskPlanService.practice(tableYear, userDto, taskId, true);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
