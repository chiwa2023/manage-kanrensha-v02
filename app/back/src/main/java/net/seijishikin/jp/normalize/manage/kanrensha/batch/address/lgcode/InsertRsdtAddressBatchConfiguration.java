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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * アドレス・ベース・レジストリ住居データ挿入処理
 */
@Configuration
public class InsertRsdtAddressBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "insertRsdtAddress";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** アドレス・ベース・レジストリ住居CsvItemReader */
    @Autowired
    private RsdtAddressCsvItemReader rsdtAddressCsvItemReader;

    /** アドレス・ベース・レジストリ住居CsvEntityProcessor */
    @Autowired
    private RsdtAddressProcessor rsdtAddressProcessor;

    /** アドレス・ベース・レジストリ住居ItemWriter */
    @Autowired
    private RsdtAddressItemWriter rsdtAddressItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_INSERT_NAME) final Step stepWrite) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepWrite).end()
                .build();
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
                .<RsdtAddressCsvDto, AddressRsdtBaseEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(rsdtAddressCsvItemReader).processor(rsdtAddressProcessor)
                .writer(rsdtAddressItemWriter).build();
    }

}
