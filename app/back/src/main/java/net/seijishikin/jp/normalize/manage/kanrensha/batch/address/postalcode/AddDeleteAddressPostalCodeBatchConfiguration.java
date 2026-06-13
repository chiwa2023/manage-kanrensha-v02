package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;

/**
 * 郵便番号差分処理BachConfig
 */
@Configuration
public class AddDeleteAddressPostalCodeBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "addDeleteAddressPostalCode";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CLEAR = FUNCTION_NAME + "Clear" + STEP;

    /** Step名 */
    public static final String STEP_RECORD_ADD = FUNCTION_NAME + "InsertAdd" + STEP;

    /** Step名 */
    public static final String STEP_RECORD_DELETE = FUNCTION_NAME + "InsertDelete" + STEP;

    /** Step名 */
    public static final String STEP_PREPAER = FUNCTION_NAME + "Prepaer" + STEP;

    /** Step名 */
    public static final String STEP_MOVE_CODE = FUNCTION_NAME + "MoveCode" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** 郵便番号消去Tasklet */
    @Autowired
    private ClearWkTblAddressEditTasklet clearWkTblAddressEditTasklet;

    /** 郵便番号追加ファイルItemReader */
    @Autowired
    private EditPostalCodeAddItemReader editPostalCodeAddItemReader;

    /** 郵便番号追加ファイルProcessorr */
    @Autowired
    private EditPostalCodeAddProcessor editPostalCodeAddProcessor;

    /** 郵便番号削除ファイルItemReader */
    @Autowired
    private EditPostalCodeDeleteItemReader editPostalCodeDeleteItemReader;

    /** 郵便番号削除ファイルProcessorr */
    @Autowired
    private EditPostalCodeDeleteProcessor editPostalCodeDeleteProcessor;

    /** 郵便番号削除ファイルItemReader */
    @Autowired
    private EditWkTblePostalCodeItemWriter editWkTblePostalCodeItemWriter;

    /** 郵便番号編集ワークテーブル準備Tasklet */
    @Autowired
    private EditPreparePostalCodeTasklet editPreparePostalCodeTasklet;

    /** 郵便番号移動Tasklet */
    @Autowired
    private EditPostalMoveCodeTasklet editPostalMoveCodeTasklet;

    /** ジョブ実行リスナ(タスク計画記録) */
    @Autowired
    private RecordTaskPlanJobExecutionListner recordTaskPlanJobExecutionListner;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CLEAR) final Step stepCrean,
            @Qualifier(STEP_RECORD_ADD) final Step stepInsertAdd,
            @Qualifier(STEP_RECORD_DELETE) final Step stepInsertDelete, @Qualifier(STEP_PREPAER) final Step stepPrepare,
            @Qualifier(STEP_MOVE_CODE) final Step stepMoveCode) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer())
                .listener(recordTaskPlanJobExecutionListner).flow(stepCrean).next(stepInsertAdd).next(stepInsertDelete)
                .next(stepPrepare).next(stepMoveCode).end().build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAR)
    protected Step getStepClean(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAR, jobRepository).tasklet(clearWkTblAddressEditTasklet, transactionManager)
                .build();
    }

    /**
     * StepOneLineを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RECORD_ADD)
    protected Step getStepInsertAdd(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RECORD_ADD, jobRepository)
                .<EditPostalCodeOneLineDto, WkTblPostalEditEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(editPostalCodeAddItemReader).processor(editPostalCodeAddProcessor)
                .writer(editWkTblePostalCodeItemWriter).build();
    }

    /**
     * StepOneLineを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RECORD_DELETE)
    protected Step getStepInsertDelete(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RECORD_DELETE, jobRepository)
                .<EditPostalCodeOneLineDto, WkTblPostalEditEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(editPostalCodeDeleteItemReader).processor(editPostalCodeDeleteProcessor)
                .writer(editWkTblePostalCodeItemWriter).build();
    }

    /**
     * StepPrepareを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_PREPAER)
    protected Step getStepPrepare(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_PREPAER, jobRepository).tasklet(editPreparePostalCodeTasklet, transactionManager)
                .build();
    }

    /**
     * StepMoveCodeを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_MOVE_CODE)
    protected Step getStepMoveCode(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_MOVE_CODE, jobRepository).tasklet(editPostalMoveCodeTasklet, transactionManager)
                .build();
    }
}
