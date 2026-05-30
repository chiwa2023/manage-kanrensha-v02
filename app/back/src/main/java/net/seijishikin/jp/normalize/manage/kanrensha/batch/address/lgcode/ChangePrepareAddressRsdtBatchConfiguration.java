package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;

/**
 * 差異変更処理のため、差異をワークてブルに登録処理
 */
@Configuration
public class ChangePrepareAddressRsdtBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "changePrepareAddressRsdt";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CLEAR_NAME = FUNCTION_NAME + "Clear" + STEP;

    /** Step名 */
    public static final String STEP_RSDT_INSERT_NAME = FUNCTION_NAME + "InsertRsdt" + STEP;

    /** Step名 */
    public static final String STEP_PARCEL_INSERT_NAME = FUNCTION_NAME + "InsertParcel" + STEP;

    /** Step名 */
    public static final String STEP_DIFFER_NAME = FUNCTION_NAME + "WriteDefffer" + STEP;

    /** Step名 */
    public static final String STEP_DELETE_NAME = FUNCTION_NAME + "WriteDelete" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** アドレスワークテーブル一括削除 */
    @Autowired
    private DeleteWkTblAddressTasklet deleteWkTblAddressTasklet;

    /** アドレス・ベース・レジストリ地番CsvItemReader */
    @Autowired
    private ParcelAddressCsvItemReader parcelAddressCsvItemReader;

    /** アドレス・ベース・レジストリ地番CsvEntityProcessor */
    @Autowired
    private ParcelAddressCsvProcessor parcelAddressCsvProcessor;

    /** アドレス・ベース・レジストリ住居CsvItemReader */
    @Autowired
    private RsdtAddressCsvItemReader rsdtAddressCsvItemReader;

    /** アドレス・ベース・レジストリ住居CsvEntityProcessor */
    @Autowired
    private RsdtAddressProcessor rsdtAddressProcessor;

    /** アドレス・ベース・レジストリ住居変更用ワークテーブル書き出しFileItemWriter */
    @Autowired
    private RsdtWkTblAddressFileItemWriter rsdtWkTblAddressFileItemWriter;

    /** アドレス・ベース・レジストリワークテーブルItemReader */
    @Autowired
    private RsdtWkTblAddressFileLgCodeItemReader rsdtWkTblAddressFileLgCodeItemReader;

    /** アドレス・ベース・レジストリワークテーブル差異書き出しFileItemWriter */
    @Autowired
    private RsdtWkTblChangeAddressItemWriter rsdtWkTblChangeAddressItemWriter;

    /** アドレス・ベース・レジストリ読み出しItemWriter */
    @Autowired
    private RsdtAddressItemReader rsdtAddressItemReader;

    /** アドレス・ベース・レジストリ削除書き出しFileItemWriter */
    @Autowired
    private RsdtWkTblDeleteAddressItemWriter rsdtWkTblDeleteAddressItemWriter;

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
    protected Job getJob(final JobRepository jobRepository,
            @Qualifier(STEP_CLEAR_NAME) final Step stepClear,
            @Qualifier(STEP_RSDT_INSERT_NAME) final Step stepRsdtWrite,
            @Qualifier(STEP_PARCEL_INSERT_NAME) final Step stepParcelWrite,
            @Qualifier(STEP_DIFFER_NAME) final Step stepWriteDiffer,
            @Qualifier(STEP_DELETE_NAME) final Step stepWriteDelete) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer())
                .listener(recordTaskPlanJobExecutionListner).flow(stepClear)
                .next(stepParcelWrite)
                .next(stepRsdtWrite)
                .next(stepWriteDiffer).next(stepWriteDelete)
                .end().build();
    }

    /**
     * StepClearを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAR_NAME)
    protected Step getClear(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAR_NAME, jobRepository).tasklet(deleteWkTblAddressTasklet, transactionManager)
                .build();
    }

    /**
     * StepRsdtInsertRsdtを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RSDT_INSERT_NAME)
    protected Step getStepRsdtWrite(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RSDT_INSERT_NAME, jobRepository)
                .<RsdtAddressCsvDto, AddressRsdtBaseEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(rsdtAddressCsvItemReader).processor(rsdtAddressProcessor).writer(rsdtWkTblAddressFileItemWriter)
                .build();
    }

    /**
     * StepInsertParcelを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_PARCEL_INSERT_NAME)
    protected Step getStepParcelWrite(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_PARCEL_INSERT_NAME, jobRepository)
                .<ParcelAddressCsvDto, AddressRsdtBaseEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(parcelAddressCsvItemReader).processor(parcelAddressCsvProcessor)
                .writer(rsdtWkTblAddressFileItemWriter).build();
    }

    /**
     * StepWriteDefferを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_DIFFER_NAME)
    protected Step getStepWriteDiffer(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_DIFFER_NAME, jobRepository)
                .<WkTblAddressRsdtFileEntity, WkTblAddressRsdtFileEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(rsdtWkTblAddressFileLgCodeItemReader).writer(rsdtWkTblChangeAddressItemWriter).build();
    }

    /**
     * StepWriteDefferを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_DELETE_NAME)
    protected Step getStepWriteDelete(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_DELETE_NAME, jobRepository)
                .<AddressRsdtBaseEntity, AddressRsdtBaseEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(rsdtAddressItemReader).writer(rsdtWkTblDeleteAddressItemWriter).build();
    }

}
