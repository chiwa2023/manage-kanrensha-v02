package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgResultEntity;

/**
 * 個人団体紐づけBatchConfig
 */
@Configuration
public class AddCombineOrgBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "addCombineOrg";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(削除) */
    public static final String STEP_ERASE = FUNCTION_NAME + "EraseWkTbl" + STEP;

    /** Step名(ファイルを履歴に複写) */
    public static final String STEP_CSV = FUNCTION_NAME + "Csv" + STEP;

    /** Step名(ファイル内重複データ中断) */
    public static final String STEP_SUSPEND = FUNCTION_NAME + "SuspendDuplicate" + STEP;

    /** Step名(マスタ登録) */
    public static final String STEP_RECORD = FUNCTION_NAME + "Record" + STEP;

    /** Step名(処理結果を反映) */
    public static final String STEP_FIX = FUNCTION_NAME + "Fix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** ワークテーブル初期化Tasklet */
    @Autowired
    private EraseWkTblCombineOrgTasklet eraseWkTblCombineOrgTasklet;

    /** 個人団体紐づけCsv読み取りItemReader */
    @Autowired
    private CombineOrgCsvItemReader combineOrgCsvItemReader;

    /** 個人団体紐づけCsv読み取りItemProcessor */
    @Autowired
    private CombineOrgCsvProcessor combineOrgCsvProcessor;

    /** 個人団体紐づけCsv読み取りItemWriter */
    @Autowired
    private CombineOrgCsvItemWriter combineOrgCsvItemWriter;

    /** ファイル内重複データ処理停止Tasklet */
    @Autowired
    private SuspendDuplicateWkTblCombineOrgTasklet suspendDuplicateWkTblCombineOrgTasklet;

    /** 個人団体紐づけマスタ登録ItemReader */
    @Autowired
    private CombineOrgRecordItemReader combineOrgRecordItemReader;

    /** 個人団体紐づけマスタ登録ItemWriter */
    @Autowired
    private CombineOrgRecordItemWriter combineOrgRecordItemWriter;

    /** 個人団体紐づけ処理結果判定ItemReader */
    @Autowired
    private CombineOrgWkTblFixItemReader combineOrgWkTblFixItemReader;

    /** 個人団体紐づけ処理結果判定ItemProcessor */
    @Autowired
    private CombineOrgWkTblFixProcessor combineOrgWkTblFixProcessor;

    /** 個人団体紐づけ処理結果判定ItemWriter */
    @Autowired
    private CombineOrgWkTblFixItemWriter combineOrgWkTblFixItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_ERASE) final Step stepErase,
            @Qualifier(STEP_CSV) final Step stepCsv, @Qualifier(STEP_SUSPEND) final Step stepSuspend,
            @Qualifier(STEP_RECORD) final Step stepRecord, @Qualifier(STEP_FIX) final Step stepFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepErase).next(stepCsv)
                .next(stepSuspend).next(stepRecord).next(stepFix).end().build();
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

        return new StepBuilder(STEP_ERASE, jobRepository).tasklet(eraseWkTblCombineOrgTasklet, transactionManager)
                .build();
    }

    /**
     * StepCsvを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CSV)
    protected Step getStepCsv(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CSV, jobRepository)
                .<KanrenshaCombineOrgDto, WkTblKanrenshaCombineOrgEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(combineOrgCsvItemReader).processor(combineOrgCsvProcessor).writer(combineOrgCsvItemWriter)
                .build();
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
                .tasklet(suspendDuplicateWkTblCombineOrgTasklet, transactionManager).build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RECORD)
    protected Step getStepRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RECORD, jobRepository)
                .<WkTblKanrenshaCombineOrgEntity, WkTblKanrenshaCombineOrgEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(combineOrgRecordItemReader).writer(combineOrgRecordItemWriter).build();
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
                .<WkTblKanrenshaCombineOrgResultEntity, WkTblKanrenshaCombineOrgEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(combineOrgWkTblFixItemReader).processor(combineOrgWkTblFixProcessor)
                .writer(combineOrgWkTblFixItemWriter).build();
    }

}
