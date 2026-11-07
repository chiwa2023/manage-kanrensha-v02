package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.year;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 年更新ソースBatchConfig
 */
@Configuration
public class RefleshYearSourceBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "refleshYearSource";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(Logic) */
    public static final String STEP_LOGIC = FUNCTION_NAME + "Logic" + STEP;

    /** Step名(Service) */
    public static final String STEP_SERVICE = FUNCTION_NAME + "Service" + STEP;

    /** ワークテーブル修正Processor */
    @Autowired
    private RefleshYearSourceTasklet refleshYearSourceTasklet;

    /** ワークテーブル修正Processor */
    @Autowired
    private AddSwithYearCaseTasklet addSwithYearCaseTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_LOGIC) final Step stepLogic,
            @Qualifier(STEP_SERVICE) final Step stepService) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()) //
                .flow(stepLogic) //
                .next(stepService) //
                .end().build();
    }

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_LOGIC)
    protected Step getStepLogic(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_LOGIC, jobRepository).tasklet(refleshYearSourceTasklet, transactionManager).build();
    }

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_SERVICE)
    protected Step getStepService(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_SERVICE, jobRepository).tasklet(addSwithYearCaseTasklet, transactionManager)
                .build();
    }

}
