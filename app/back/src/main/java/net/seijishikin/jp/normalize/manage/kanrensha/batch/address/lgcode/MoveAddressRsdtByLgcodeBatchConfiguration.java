package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * 地方自治体コードで住居データを総複写する
 */
@Configuration
public class MoveAddressRsdtByLgcodeBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "moveAddressRsdtByLgcode";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_MOVE = FUNCTION_NAME + "Move" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** 住居データ移行ItemReader */
    @Autowired
    private MoveAddressRsdtItemReader moveAddressRsdtItemReader;

    /** 住居データ移行ItemWrite */
    @Autowired
    private MoveAddressRsdtItemWriter moveAddressRsdtItemWriter;

    /** ジョブ実行リスナ(タスク計画記録) */
    @Autowired
    private RecordTaskPlanJobExecutionListner recordTaskPlanJobExecutionListner;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_MOVE) final Step stepMove) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer())
                .listener(recordTaskPlanJobExecutionListner).flow(stepMove).end().build();
    }

    /**
     * StepMoveを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_MOVE)
    protected Step getStepMove(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_MOVE, jobRepository)
                .<AddressRsdtBaseEntity, AddressRsdtBaseEntity>chunk(CHUNK_SIZE)
                .reader(moveAddressRsdtItemReader).writer(moveAddressRsdtItemWriter).build();
    }

}
