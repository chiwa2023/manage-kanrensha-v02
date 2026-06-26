package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;

/**
 * 手動修正候補登録バッチ処理
 */
@Configuration
public class PickupManualWorksBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "pickupManualWorks";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(Initialize) */
    public static final String STEP_INITIALIZE = FUNCTION_NAME + "Initialize" + STEP;

    /** Step名(Insert) */
    public static final String STEP_INSERT = FUNCTION_NAME + "Insert" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 1000;

    /** 手動修正候補登録地方行政区コード初期化Tasklet */
    @Autowired
    private PickupManualWorksInitializeTasklet pickupManualWorksInitializeTasklet;

    /** 手動修正候補登録ItemReader */
    @Autowired
    private PickupManualWorksItemReader pickupManualWorksItemReader;

    /** 手動修正候補登録Processor */
    @Autowired
    private PickupManualWorksProcessor pickupManualWorksProcessor;

    /** 手動修正候補登録ItemWriter */
    @Autowired
    private PickupManualWorksItemWriter pickupManualWorksItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_INSERT) final Step stepInsert,
            @Qualifier(STEP_INITIALIZE) final Step stepInitialize) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepInitialize)
                .next(stepInsert).end().build();
    }

    /**
     * StepInsertを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_INSERT)
    protected Step getStepInsert(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INSERT, jobRepository)
                .<AddressPostalEntity, AddressPostalRepairLogEntity>chunk(CHUNK_SIZE)
                .reader(pickupManualWorksItemReader).processor(pickupManualWorksProcessor)
                .writer(pickupManualWorksItemWriter).build();
    }

    /**
     * StepInsertを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_INITIALIZE)
    protected Step getStepInitialize(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INITIALIZE, jobRepository)
                .tasklet(pickupManualWorksInitializeTasklet, transactionManager).build();
    }

}
