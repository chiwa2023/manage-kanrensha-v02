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
public class RetryStdKanrenshaKigyouDtMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "retryStdKanrenshaKigyouDtMaster";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(マスタ登録) */
    public static final String STEP_RECORD = FUNCTION_NAME + "Record" + STEP;

    /** Step名(処理結果を反映) */
    public static final String STEP_FIX = FUNCTION_NAME + "Fix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

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
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_RECORD) final Step stepRecord,
            @Qualifier(STEP_FIX) final Step stepFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepRecord)
                .next(stepFix).end().build();
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
                .<WkTblKanrenshaKigyouDtMasterEntity, WkTblKanrenshaKigyouDtMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterKigyouDtAddStdRecordItemReader).writer(masterKigyouDtAddStdRecordItemWriter)
                .build();
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
