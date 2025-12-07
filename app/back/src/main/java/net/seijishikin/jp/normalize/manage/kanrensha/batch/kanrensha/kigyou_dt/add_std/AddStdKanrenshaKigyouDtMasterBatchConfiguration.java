package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterResultEntity;


/**
 * 関連者企業・団体標準登録BatchConfig
 */
@Configuration
public class AddStdKanrenshaKigyouDtMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "addStdKanrenshaKigyouDtMaster";

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
    private EraseWkTblMasterKigyouDtAddStdTasklet eraseWkTblMasterKigyouDtAddStdTasklet;

    /** 関連者企業・団体Csv読み取りItemReader */
    @Autowired
    private MasterKigyouDtAddStdCsvItemReader masterKigyouDtAddStdCsvItemReader;

    /** 関連者企業・団体Csv読み取りItemProcessor */
    @Autowired
    private KanrenshaKigyouDtAddStdCsvProcessor kanrenshaKigyouDtAddStdCsvProcessor;

    /** 関連者企業・団体Csv読み取りItemWriter */
    @Autowired
    private MasterKigyouDtAddStdCsvItemWriter masterKigyouDtAddStdCsvItemWriter;

    /** ファイル内重複データ処理停止Tasklet */
    @Autowired
    private SuspendDuplicateWkTblMasterKigyouDtAddStdTasklet suspendDuplicateWkTblMasterKigyouDtAddStdTasklet;

    /** 関連者企業・団体マスタ登録ItemReader */
    @Autowired
    private MasterKigyouDtAddStdRecordItemReader masterKigyouDtAddStdRecordItemReader;

    /** 関連者企業・団体マスタ登録ItemWriter */
    @Autowired
    private MasterKigyouDtAddStdRecordItemWriter masterKigyouDtAddStdRecordItemWriter;

    /** 関連者企業・団体処理結果判定ItemReader */
    @Autowired
    private MasterKigyouDtAddStdWkTblFixItemReader masterKigyouDtAddStdWkTblFixItemReader;

    /** 関連者企業・団体処理結果判定ItemProcessor */
    @Autowired
    private MasterKigyouDtAddStdWkTblFixProcessor masterKigyouDtAddStdWkTblFixProcessor;

    /** 関連者企業・団体処理結果判定ItemWriter */
    @Autowired
    private MasterKigyouDtAddStdWkTblFixItemWriter masterKigyouDtAddStdWkTblFixItemWriter;

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

        return new StepBuilder(STEP_ERASE, jobRepository)
                .tasklet(eraseWkTblMasterKigyouDtAddStdTasklet, transactionManager).build();
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
                .<KanrenshaKigyouDtAddStdDto, WkTblKanrenshaKigyouDtMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterKigyouDtAddStdCsvItemReader).processor(kanrenshaKigyouDtAddStdCsvProcessor)
                .writer(masterKigyouDtAddStdCsvItemWriter).build();
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
                .tasklet(suspendDuplicateWkTblMasterKigyouDtAddStdTasklet, transactionManager).build();
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
                .<WkTblKanrenshaKigyouDtMasterEntity, WkTblKanrenshaKigyouDtMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterKigyouDtAddStdRecordItemReader).writer(masterKigyouDtAddStdRecordItemWriter).build();
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
                .<WkTblKanrenshaKigyouDtMasterResultEntity, WkTblKanrenshaKigyouDtMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterKigyouDtAddStdWkTblFixItemReader).processor(masterKigyouDtAddStdWkTblFixProcessor)
                .writer(masterKigyouDtAddStdWkTblFixItemWriter).build();
    }

}
