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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;

/**
 * アドレス・ベース・レジストリワークテーブル実テーブル反映処理
 */
@Configuration
public class UpdateAddressRsdtBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "updateAddressRsdt";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CHANGE_NAME = FUNCTION_NAME + "SelectChange" + STEP;

    /** Step名 */
    public static final String STEP_DELET_NAME = FUNCTION_NAME + "SelectDelete" + STEP;

    /** Step名 */
    public static final String STEP_MARK_NAME = FUNCTION_NAME + "SelectMark" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** アドレスワークテーブル更新ItemReader */
    @Autowired
    private WkTblAddressChangeItemReader wkTblAddressChangeItemReader;

    /** アドレスワークテーブル更新ItemWriter */
    @Autowired
    private WkTblAddressChangeItemWriter wkTblAddressChangeItemWriter;

    /** アドレスワークテーブル削除ItemReader */
    @Autowired
    private WkTblAddressDeleteItemReader wkTblAddressDeleteItemReader;

    /** アドレスワークテーブル削除ItemWriter */
    @Autowired
    private WkTblAddressDeleteItemWriter wkTblAddressDeleteItemWriter;

    /** アドレスワークテーブル処理マークItemReader */
    @Autowired
    private WkTblAddressMarkItemReader wkTblAddressMarkItemReader;

    /** アドレスワークテーブル処理マークItemWriter */
    @Autowired
    private WkTblAddressMarkItemWriter wkTblAddressMarkItemWriter;

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
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CHANGE_NAME) final Step stepChange,
            @Qualifier(STEP_DELET_NAME) final Step stepDelete, @Qualifier(STEP_MARK_NAME) final Step stepMark) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer())
                .listener(recordTaskPlanJobExecutionListner).flow(stepChange).next(stepDelete).next(stepMark) //
                .end().build();
    }

    /**
     * StepSelectChangeを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CHANGE_NAME)
    protected Step getStepSelectChange(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CHANGE_NAME, jobRepository)
                .<WkTblAddressRsdtChangeEntity, WkTblAddressRsdtChangeEntity>chunk(CHUNK_SIZE)
                .reader(wkTblAddressChangeItemReader).writer(wkTblAddressChangeItemWriter).build();
    }

    /**
     * StepSelecttDeletetを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_DELET_NAME)
    protected Step getStepSelectDelete(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_DELET_NAME, jobRepository)
                .<WkTblAddressRsdtDeleteEntity, WkTblAddressRsdtDeleteEntity>chunk(CHUNK_SIZE)
                .reader(wkTblAddressDeleteItemReader).writer(wkTblAddressDeleteItemWriter).build();
    }

    /**
     * StepSelecttMarkを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_MARK_NAME)
    protected Step getStepSelectMark(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_MARK_NAME, jobRepository)
                .<WkTblAddressRsdtMarkEntity, WkTblAddressRsdtMarkEntity>chunk(CHUNK_SIZE)
                .reader(wkTblAddressMarkItemReader).writer(wkTblAddressMarkItemWriter).build();
    }

}
