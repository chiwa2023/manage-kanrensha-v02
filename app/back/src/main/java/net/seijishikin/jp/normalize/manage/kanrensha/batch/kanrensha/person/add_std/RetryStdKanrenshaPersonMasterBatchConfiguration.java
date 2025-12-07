package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterResultEntity;


/**
 * 関連者個人標準登録BatchConfig
 */
@Configuration
public class RetryStdKanrenshaPersonMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "retryStdKanrenshaPersonMaster";

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

    /** 関連者個人マスタ登録ItemReader */
    @Autowired
    private MasterPersonAddStdRecordItemReader masterPersonAddStdRecordItemReader;

    /** 関連者個人マスタ登録ItemWriter */
    @Autowired
    private MasterPersonAddStdRecordItemWriter masterPersonAddStdRecordItemWriter;

    /** 関連者個人処理結果判定ItemReader */
    @Autowired
    private MasterPersonAddStdWkTblFixItemReader masterPersonAddStdWkTblFixItemReader;

    /** 関連者個人処理結果判定ItemProcessor */
    @Autowired
    private MasterPersonAddStdWkTblFixProcessor masterPersonAddStdWkTblFixProcessor;

    /** 関連者個人処理結果判定ItemWriter */
    @Autowired
    private MasterPersonAddStdWkTblFixItemWriter masterPersonAddStdWkTblFixItemWriter;

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
                .<WkTblKanrenshaPersonMasterEntity, WkTblKanrenshaPersonMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterPersonAddStdRecordItemReader).writer(masterPersonAddStdRecordItemWriter).build();
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
                .<WkTblKanrenshaPersonMasterResultEntity, WkTblKanrenshaPersonMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterPersonAddStdWkTblFixItemReader).processor(masterPersonAddStdWkTblFixProcessor)
                .writer(masterPersonAddStdWkTblFixItemWriter).build();
    }

}
