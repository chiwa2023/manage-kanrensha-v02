package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * 郵便番号書き出しBachConfig
 */
@Configuration
public class RebuildAddressPostalCodeBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "rebuildAddressPostalCode";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CLEAR = FUNCTION_NAME + "Clear" + STEP;

    /** Step名 */
    public static final String STEP_ONLINE = FUNCTION_NAME + "InsertOneLine" + STEP;

    /** Step名 */
    public static final String STEP_JIGYOUSHA = FUNCTION_NAME + "InsertJigyousha" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** 郵便番号消去Tasklet */
    @Autowired
    private ClearPostalcodeTasklet clearPostalcodeTasklet;

    /** 郵便番号CsvItemReader */
    @Autowired
    private PostalCodeCsvOneLineItemReader postalCodeCsvOneLineItemReader;

    /** 郵便番号CsvEntityProcessor */
    @Autowired
    private PostalCodeOneLineProcessor postalCodeOneLineProcessor;

    /** 郵便番号ItemWriter */
    @Autowired
    private PostalCodeCsvOneLineItemWriter postalCodeCsvOneLineItemWriter;

    /** 郵便番号CsvItemReader */
    @Autowired
    private PostalCodeCsvJigyoushaItemReader postalCodeCsvJigyoushaItemReader;

    /** 郵便番号CsvEntityProcessor */
    @Autowired
    private PostalCodeJigyoushoProcessor postalCodeJigyoushoProcessor;

    /** 郵便番号ItemWriter */
    @Autowired
    private PostalCodeCsvJigyoushaItemWriter postalCodeCsvJigyoushaItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CLEAR) final Step stepCrean,
            @Qualifier(STEP_ONLINE) final Step stepOneLine, @Qualifier(STEP_JIGYOUSHA) final Step stepJigyousha) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepCrean)
                .next(stepOneLine) //
                // .next(stepJigyousha) //
                .end().build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAR)
    protected Step getStepClean(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAR, jobRepository).tasklet(clearPostalcodeTasklet, transactionManager).build();
    }

    /**
     * StepOneLineを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_ONLINE)
    protected Step getStepOneLine(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ONLINE, jobRepository)
                .<PostalCodeCsvOneLineDto, AddressPostalEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(postalCodeCsvOneLineItemReader).processor(postalCodeOneLineProcessor)
                .writer(postalCodeCsvOneLineItemWriter).build();
    }

    /**
     * StepJigyoushaを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_JIGYOUSHA)
    protected Step getStepJigyousha(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_JIGYOUSHA, jobRepository)
                .<PostalCodeCsvJigyoushoDto, AddressPostalIrregularEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(postalCodeCsvJigyoushaItemReader).processor(postalCodeJigyoushoProcessor)
                .writer(postalCodeCsvJigyoushaItemWriter).build();
    }

}
