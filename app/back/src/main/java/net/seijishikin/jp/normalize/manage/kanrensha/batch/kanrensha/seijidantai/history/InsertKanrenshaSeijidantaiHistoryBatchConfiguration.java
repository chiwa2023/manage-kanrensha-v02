package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryResultEntity;

/**
 * 関連者政治団体履歴挿入BatchConfig
 */
@Configuration
public class InsertKanrenshaSeijidantaiHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "insertKanrenshaSeijidantaiHistory";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(削除) */
    public static final String STEP_ERASE = FUNCTION_NAME + "EraseWkTbl" + STEP;

    /** Step名(ファイルを履歴に複写) */
    public static final String STEP_HISTORY = FUNCTION_NAME + "History" + STEP;

    /** Step名(ファイル内重複データ中断) */
    public static final String STEP_SUSPEND = FUNCTION_NAME + "SuspendDuplicate" + STEP;

    /** Step名(履歴を判定して判定に複写) */
    public static final String STEP_JUDGE = FUNCTION_NAME + "Judge" + STEP;

    /** Step名(判定からワークテーブル修正) */
    public static final String STEP_FIX = FUNCTION_NAME + "Fix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** ワークテーブル消去Tasklet */
    @Autowired
    private EraseWkTblKanrenshaSeijidantaiHistoryTasklet eraseWkTblKanrenshaSeijidantaiHistoryTasklet;

    /** ワークテーブル履歴ItemReader */
    @Autowired
    private KanrenshaSeijidantaiHistoryItemReader kanrenshaSeijidantaiHistoryItemReader;

    /** ワークテーブル履歴ItemWriter */
    @Autowired
    private KanrenshaSeijidantaiHistoryItemWriter kanrenshaSeijidantaiHistoryItemWriter;

    /** ワークテーブル履歴Processor */
    @Autowired
    private KanrenshaSeijidantaiHistoryProcessor kanrenshaSeijidantaiHistoryProcessor;

    /** ファイル内重複データ中断Tasklet */
    @Autowired
    private SuspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet suspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet;

    /** ワークテーブル判定ItemReader */
    @Autowired
    private KanrenshaSeijidantaiResultItemReader kanrenshaSeijidantaiResultItemReader;

    /** ワークテーブル判定ItemWriter */
    @Autowired
    private KanrenshaSeijidantaiResultItemWriter kanrenshaSeijidantaiResultItemWriter;

    /** ワークテーブル判定ItemProcessor */
    @Autowired
    private KanrenshaSeijidantaiResultProcessor kanrenshaSeijidantaiResultProcessor;

    /** ワークテーブル修正ItemReader */
    @Autowired
    private KanrenshaSeijidantaiWkTblFixItemReader kanrenshaSeijidantaiWkTblFixItemReader;

    /** ワークテーブル修正ItemWriter */
    @Autowired
    private KanrenshaSeijidantaiWkTblFixItemWriter kanrenshaSeijidantaiWkTblFixItemWriter;

    /** ワークテーブル修正Processor */
    @Autowired
    private KanrenshaSeijidantaiWkTblFixProcessor kanrenshaSeijidantaiWkTblFixProcessor;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_ERASE) final Step stepErase,
            @Qualifier(STEP_HISTORY) final Step stepHistory, @Qualifier(STEP_SUSPEND) final Step stepSuspend,
            @Qualifier(STEP_JUDGE) final Step stepJudge, @Qualifier(STEP_FIX) final Step stepFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepErase)
                .next(stepHistory).next(stepSuspend).next(stepJudge).next(stepFix).end().build();
    }

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_ERASE)
    protected Step getStepErase(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ERASE, jobRepository)
                .tasklet(eraseWkTblKanrenshaSeijidantaiHistoryTasklet, transactionManager).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY)
    protected Step getStepHistory(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY, jobRepository)
                .<KanrenshaSeijidantaiHistoryDto, WkTblKanrenshaSeijidantaiHistoryEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaSeijidantaiHistoryItemReader).processor(kanrenshaSeijidantaiHistoryProcessor)
                .writer(kanrenshaSeijidantaiHistoryItemWriter).build();
    }

    /**
     * StepSuspendを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_SUSPEND)
    protected Step getStepSuspend(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_SUSPEND, jobRepository)
                .tasklet(suspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet, transactionManager).build();
    }

    /**
     * StepJudgeを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_JUDGE)
    protected Step getStepJudge(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_JUDGE, jobRepository)
                .<WkTblKanrenshaSeijidantaiHistoryEntity, WkTblKanrenshaSeijidantaiHistoryResultEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaSeijidantaiResultItemReader).processor(kanrenshaSeijidantaiResultProcessor)
                .writer(kanrenshaSeijidantaiResultItemWriter).build();
    }

    /**
     * StepFixを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_FIX)
    protected Step getStepFix(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_FIX, jobRepository)
                .<WkTblKanrenshaSeijidantaiHistoryResultEntity, WkTblKanrenshaSeijidantaiHistoryEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaSeijidantaiWkTblFixItemReader).processor(kanrenshaSeijidantaiWkTblFixProcessor)
                .writer(kanrenshaSeijidantaiWkTblFixItemWriter).build();
    }

}
