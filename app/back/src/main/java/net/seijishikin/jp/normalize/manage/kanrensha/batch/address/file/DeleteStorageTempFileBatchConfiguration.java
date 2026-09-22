package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.file;

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
 * ストレージ一時ファイル削除BatchConfiguration
 */
@Configuration
public class DeleteStorageTempFileBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "deleteStorageTempFile";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_EXECUTE_NAME = FUNCTION_NAME + "Execute" + STEP;

    /** アドレスワークテーブル一括削除 */
    @Autowired
    private DeleteStorageTempFileTasklet deleteStorageTempFileTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_EXECUTE_NAME) final Step stepExecute) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepExecute).end()
                .build();
    }

    /**
     * StepClearを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_EXECUTE_NAME)
    protected Step getClear(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_EXECUTE_NAME, jobRepository)
                .tasklet(deleteStorageTempFileTasklet, transactionManager).build();
    }

}
