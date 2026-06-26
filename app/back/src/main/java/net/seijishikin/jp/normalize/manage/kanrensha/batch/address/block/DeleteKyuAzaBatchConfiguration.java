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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * 旧字削除処理BatchConfiguration
 */
@Configuration
public class DeleteKyuAzaBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "deleteKyuAza";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(Clean) */
    public static final String STEP_CLEAN = FUNCTION_NAME + "Clean" + STEP;

    /** Step名(Insert) */
    public static final String STEP_INSERT = FUNCTION_NAME + "Insert" + STEP;

    /** Step名(LogUpdate) */
    public static final String STEP_LOG_UPDATE = FUNCTION_NAME + "LogUpdate" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 1000;

    /** 郵便番号作業全削除Tasklet */
    @Autowired
    private CleanWorksAddressPostalTasklet cleanWorksAddressPostalTasklet;

    /** 旧字削除Itemreader */
    @Autowired
    private DeleteKyuAzaItemReader deleteKyuAzaItemReader;

    /** 旧字削除Itemreader */
    @Autowired
    private DeleteKyuAzaItemWriter deleteKyuAzaItemWriter;

    /** 郵便番号作業ItemReader */
    @Autowired
    private WorksPostalItemReader worksPostalItemReader;

    /** 郵便番号修復ログ修正ItemReader */
    @Autowired
    private DeleteKyuAzaRepairLogItemWriter deleteKyuAzaRepairLogItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CLEAN) final Step stepClean,
            @Qualifier(STEP_INSERT) final Step stepInsert, @Qualifier(STEP_LOG_UPDATE) final Step stepUpdateLog) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepClean)
                .next(stepInsert).next(stepUpdateLog).end().build();
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
                .<AddressPostalRepairLogEntity, AddressPostalRepairLogEntity>chunk(CHUNK_SIZE)
                .reader(deleteKyuAzaItemReader).writer(deleteKyuAzaItemWriter).build();
    }

    /**
     * StepUpdateLogを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_LOG_UPDATE)
    protected Step getStepLogUpdate(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_LOG_UPDATE, jobRepository)
                .<WkTblPostalCommonEntity, WkTblPostalCommonEntity>chunk(CHUNK_SIZE)
                .reader(worksPostalItemReader).writer(deleteKyuAzaRepairLogItemWriter).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN)
    protected Step getStepClean(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

}
