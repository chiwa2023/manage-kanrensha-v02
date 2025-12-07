package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinResultEntity;

/**
 * 関連者政治団体最小登録BatchConfig
 */
@Configuration
public class AddMinKanrenshaSeijidantaiMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "addMinKanrenshaSeijidantaiMasterBatch";

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
    private EraseWkTblKanrenshaSeijidantaiAddMinTasklet eraseWkTblKanrenshaSeijidantaiAddMinTasklet;

    /** 関連者政治団体Csv読み取りItemReader */
    @Autowired
    private KanrenshaSeijidantaiAddMiniCsvItemReader kanrenshaSeijidantaiAddMiniCsvItemReader;

    /** 関連者政治団体Csv読み取りItemProcessor */
    @Autowired
    private KanrenshaSeijidantaiAddMiniCsvProcessor kanrenshaSeijidantaiAddMiniCsvProcessor;

    /** 関連者政治団体Csv読み取りItemWriter */
    @Autowired
    private KanrenshaSeijidantaiAddMiniCsvItemWriter kanrenshaSeijidantaiAddMiniCsvItemWriter;

    /** ファイル内重複データ処理停止Tasklet */
    @Autowired
    private SuspendDuplicateWkTblKanrenshaSeijidantaiAddMinTasklet suspendDuplicateWkTblKanrenshaSeijidantaiAddMinTasklet;

    /** 関連者政治団体マスタ登録ItemReader */
    @Autowired
    private KanrenshaSeijidantaiAddMiniRecordItemReader kanrenshaSeijidantaiAddMiniRecordItemReader;

    /** 関連者政治団体マスタ登録ItemWriter */
    @Autowired
    private KanrenshaSeijidantaiAddMiniRecordItemWriter kanrenshaSeijidantaiAddMiniRecordItemWriter;

    /** 関連者政治団体処理結果判定ItemReader */
    @Autowired
    private KanrenshaSeijidantaiAddMiniWkTblFixItemReader kanrenshaSeijidantaiAddMiniWkTblFixItemReader;

    /** 関連者政治団体処理結果判定ItemProcessor */
    @Autowired
    private KanrenshaSeijidantaiAddMiniWkTblFixProcessor kanrenshaSeijidantaiAddMiniWkTblFixProcessor;

    /** 関連者政治団体処理結果判定ItemWriter */
    @Autowired
    private KanrenshaSeijidantaiAddMiniWkTblFixItemWriter kanrenshaSeijidantaiAddMiniWkTblFixItemWriter;

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

        return new StepBuilder(STEP_ERASE, jobRepository).tasklet(eraseWkTblKanrenshaSeijidantaiAddMinTasklet, transactionManager)
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
                .<KanrenshaSeijidantaiAddMiniDto, WkTblKanrenshaSeijidantaiAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaSeijidantaiAddMiniCsvItemReader).processor(kanrenshaSeijidantaiAddMiniCsvProcessor)
                .writer(kanrenshaSeijidantaiAddMiniCsvItemWriter).build();
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
                .tasklet(suspendDuplicateWkTblKanrenshaSeijidantaiAddMinTasklet, transactionManager).build();
    }

    /**
     * StepCsvを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RECORD)
    protected Step getStepRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RECORD, jobRepository)
                .<WkTblKanrenshaSeijidantaiAddMinEntity, WkTblKanrenshaSeijidantaiAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaSeijidantaiAddMiniRecordItemReader).writer(kanrenshaSeijidantaiAddMiniRecordItemWriter).build();
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
                .<WkTblKanrenshaSeijidantaiAddMinResultEntity, WkTblKanrenshaSeijidantaiAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaSeijidantaiAddMiniWkTblFixItemReader).processor(kanrenshaSeijidantaiAddMiniWkTblFixProcessor)
                .writer(kanrenshaSeijidantaiAddMiniWkTblFixItemWriter).build();
    }

}
