package net.seijishikin.jp.normalize.manage.kanrensha.batch.security;

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
 * 期限切れトークン・コード削除BatchConfiguration
 */
@Configuration
public class DeleteLimitOverTokenBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "deleteLimitOverTokenBatch";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(新規ユーザ) */
    public static final String STEP_USER_NEW = FUNCTION_NAME + "UserNew" + STEP;

    /** Step名(パスワードリセット) */
    public static final String STEP_PASSWORD_RESET = FUNCTION_NAME + "PasswordReset" + STEP;

    /** Step名(APIトークン) */
    public static final String STEP_API_TOKEN = FUNCTION_NAME + "ApiToken" + STEP;

    /** 新規ユーザ期限切れコード削除Tasklet */
    @Autowired
    private DeleteNewUserLimitRegiCodeTasklet deleteNewUserLimitRegiCodeTasklet;

    /** パスワードリセット期限切れコード削除Tasklet */
    @Autowired
    private DeletePasswordResetLimitRegiCodeTasklet deletePasswordResetLimitRegiCodeTasklet;

    /** APIパートナー期限切れ長期トークン削除Tasklet */
    @Autowired
    private DeletePartnerApiLimitTokenTasklet deletePartnerApiLimitTokenTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_USER_NEW) final Step stepUserNew,
            @Qualifier(STEP_PASSWORD_RESET) final Step stepPasswordReset,
            @Qualifier(STEP_API_TOKEN) final Step stepApiToken) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepUserNew)
                .next(stepPasswordReset).next(stepApiToken).end().build();
    }

    /**
     * StepUserNewを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_USER_NEW)
    protected Step getStepUserNew(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_USER_NEW, jobRepository)
                .tasklet(deleteNewUserLimitRegiCodeTasklet, transactionManager).build();
    }

    /**
     * StepPasswordResetを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_PASSWORD_RESET)
    protected Step getStepPasswordReset(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_PASSWORD_RESET, jobRepository)
                .tasklet(deletePasswordResetLimitRegiCodeTasklet, transactionManager).build();
    }

    /**
     * StepApiTokenを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_API_TOKEN)
    protected Step getStepApiToken(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_API_TOKEN, jobRepository)
                .tasklet(deletePartnerApiLimitTokenTasklet, transactionManager).build();
    }

}
