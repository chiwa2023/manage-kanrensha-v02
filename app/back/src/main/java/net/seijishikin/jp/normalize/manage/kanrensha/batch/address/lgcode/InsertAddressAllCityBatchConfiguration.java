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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;

/**
 * 市区町村書き出しBachConfig
 */
@Configuration
public class InsertAddressAllCityBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "insertAddressAllCity";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** Step名 */
    public static final String STEP_DELETE_CODE = FUNCTION_NAME + "Delete" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** 市区町村CsvItemReader */
    @Autowired
    private AllCityCsvItemReader allCityCsvItemReader;

    /** 市区町村CsvEntityProcessor */
    @Autowired
    private AddressAllCityProcessor addressAllCityProcessor;

    /** 市区町村CsvItemWriter */
    @Autowired
    private AddressAllCityItemWriter addressAllCityItemWriter;

    /** 地方自治体コードワークテーブルItemReader */
    @Autowired
    private AllCityWkTblItemReader allCityWkTblItemReader;

    /** 地方自治体コードワークテーブルItemReader */
    @Autowired
    private AllCityWkTblProcessor allCityWkTblProcessor;

    /** 地方自治体コードワークテーブルItemReader */
    @Autowired
    private AllCityWkTblItemWriter allCityWkTblItemWriter;
    
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
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_INSERT_NAME) final Step stepInsert,
            @Qualifier(STEP_DELETE_CODE) final Step stepDelete) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer())
                .listener(recordTaskPlanJobExecutionListner).flow(stepInsert).next(stepDelete).end().build();
    }

    /**
     * StepInsertを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_INSERT_NAME)
    protected Step getStepWrite(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INSERT_NAME, jobRepository)
                .<AllCityCsvDto, AddressAllCityEntity>chunk(CHUNK_SIZE, transactionManager).reader(allCityCsvItemReader)
                .processor(addressAllCityProcessor).writer(addressAllCityItemWriter).build();
    }

    /**
     * StepDeleteCodeを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_DELETE_CODE)
    protected Step getStepDeleteCode(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INSERT_NAME, jobRepository)
                .<AddressAllCityEntity, AddressCityDeleteEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(allCityWkTblItemReader).processor(allCityWkTblProcessor).writer(allCityWkTblItemWriter).build();
    }
}
