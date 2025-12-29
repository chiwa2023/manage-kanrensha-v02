package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.min;

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master.DumpMasterPersonItemReader;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;


/**
 * 関連者企業・団体マスタCsv出力BatchConfiguration
 */
@Configuration
public class DumpMinPersonBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "dumpMinPerson";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_DUMP = FUNCTION_NAME + "Dump" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /* その他は住居に対応データが存在するのを確認して有効にする */

    /** 関連者企業・団体マスタCsv出力ItemReader */
    @Autowired
    private DumpMasterPersonItemReader dumpMasterPersonItemReader;

    /** 関連者企業・団体マスタCsv出力ItemWriter */
    @Autowired
    private DumpMinPersonItemWriter dumpMinPersonItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_DUMP) final Step stepDump) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepDump).end().build();
    }

    /**
     * StepOtherを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_DUMP)
    protected Step getStepDump(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_DUMP, jobRepository)
                .<KanrenshaPersonMasterEntity, KanrenshaPersonMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpMasterPersonItemReader)
                .writer(dumpMinPersonItemWriter).build();
    }

}
