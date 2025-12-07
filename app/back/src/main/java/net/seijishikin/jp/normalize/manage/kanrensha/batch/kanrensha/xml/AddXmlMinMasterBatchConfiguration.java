package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml; // NOPMD

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtAddMiniRecordItemReader;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtAddMiniRecordItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtAddMiniWkTblFixItemReader;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtAddMiniWkTblFixItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtAddMiniWkTblFixProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonAddMiniRecordItemReader;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonAddMiniRecordItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonAddMiniWkTblFixItemReader;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonAddMiniWkTblFixItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonAddMiniWkTblFixProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiAddMiniRecordItemReader;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiAddMiniRecordItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiAddMiniWkTblFixItemReader;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiAddMiniWkTblFixItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiAddMiniWkTblFixProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinResultEntity;

/**
 * 関連者個人最小登録BatchConfig
 */
@Configuration
public class AddXmlMinMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "addXmlMinMaster";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(企業マスタ登録) */
    public static final String STEP_CORP_RECORD = FUNCTION_NAME + "KigyouDtRecord" + STEP;

    /** Step名(企業処理結果を反映) */
    public static final String STEP_CORP_FIX = FUNCTION_NAME + "KigyouDtFix" + STEP;

    /** Step名(個人マスタ登録) */
    public static final String STEP_PERSON_RECORD = FUNCTION_NAME + "PersonRecord" + STEP;

    /** Step名(個人処理結果を反映) */
    public static final String STEP_PERSON_FIX = FUNCTION_NAME + "PersonFix" + STEP;

    /** Step名(政治団体マスタ登録) */
    public static final String STEP_POLI_ORG_RECORD = FUNCTION_NAME + "SeijidantaiRecord" + STEP;

    /** Step名(政治団体処理結果を反映) */
    public static final String STEP_POLI_ORG_FIX = FUNCTION_NAME + "SeijidantaiFix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** 関連者個人マスタ登録ItemReader */
    @Autowired
    private KanrenshaPersonAddMiniRecordItemReader kanrenshaPersonAddMiniRecordItemReader;

    /** 関連者個人マスタ登録ItemWriter */
    @Autowired
    private KanrenshaPersonAddMiniRecordItemWriter kanrenshaPersonAddMiniRecordItemWriter;

    /** 関連者個人処理結果判定ItemReader */
    @Autowired
    private KanrenshaPersonAddMiniWkTblFixItemReader kanrenshaPersonAddMiniWkTblFixItemReader;

    /** 関連者個人処理結果判定ItemProcessor */
    @Autowired
    private KanrenshaPersonAddMiniWkTblFixProcessor kanrenshaPersonAddMiniWkTblFixProcessor;

    /** 関連者個人処理結果判定ItemWriter */
    @Autowired
    private KanrenshaPersonAddMiniWkTblFixItemWriter kanrenshaPersonAddMiniWkTblFixItemWriter;

    /** 関連者企業・団体マスタ登録ItemReader */
    @Autowired
    private KanrenshaKigyouDtAddMiniRecordItemReader kanrenshaKigyouDtAddMiniRecordItemReader;

    /** 関連者企業・団体マスタ登録ItemWriter */
    @Autowired
    private KanrenshaKigyouDtAddMiniRecordItemWriter kanrenshaKigyouDtAddMiniRecordItemWriter;

    /** 関連者企業・団体処理結果判定ItemReader */
    @Autowired
    private KanrenshaKigyouDtAddMiniWkTblFixItemReader kanrenshaKigyouDtAddMiniWkTblFixItemReader;

    /** 関連者企業・団体処理結果判定ItemProcessor */
    @Autowired
    private KanrenshaKigyouDtAddMiniWkTblFixProcessor kanrenshaKigyouDtAddMiniWkTblFixProcessor;

    /** 関連者企業・団体処理結果判定ItemWriter */
    @Autowired
    private KanrenshaKigyouDtAddMiniWkTblFixItemWriter kanrenshaKigyouDtAddMiniWkTblFixItemWriter;

    /** 関連者政治団体マスタ登録ItemReader */
    @Autowired
    private KanrenshaSeijidantaiAddMiniRecordItemReader kanrenshaSeijidantaiAddMiniRecordItemReader;

    /** 関連者政治団体マスタ登録ItemWriter */
    @Autowired
    private KanrenshaSeijidantaiAddMiniRecordItemWriter kanrenshaSeijidantaiAddMiniRecordItemWriter;

    /** 関連者政治団体処理結果判定ItemReader */
    @Autowired
    private KanrenshaSeijidantaiAddMiniWkTblFixItemReader kanrenshaSeijidantaiAddMiniWkTblFixItemReader;

    /** 関連者政治団体処理結果判定ItemProcessor */
    @Autowired
    private KanrenshaSeijidantaiAddMiniWkTblFixProcessor kanrenshaSeijidantaiAddMiniWkTblFixProcessor;

    /** 関連者政治団体処理結果判定ItemWriter */
    @Autowired
    private KanrenshaSeijidantaiAddMiniWkTblFixItemWriter kanrenshaSeijidantaiAddMiniWkTblFixItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, // 
            @Qualifier(STEP_PERSON_RECORD) final Step stepPersonRecord,
            @Qualifier(STEP_PERSON_FIX) final Step stepPersonFix,
            @Qualifier(STEP_CORP_RECORD) final Step stepKigyouDtRecord, // 
            @Qualifier(STEP_CORP_FIX) final Step stepKigyouDtFix,
            @Qualifier(STEP_POLI_ORG_RECORD) final Step stepSeijidantaiRecord,
            @Qualifier(STEP_POLI_ORG_FIX) final Step stepSeijidantaiFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepPersonRecord)
                .next(stepPersonFix).next(stepKigyouDtRecord).next(stepKigyouDtFix).next(stepSeijidantaiRecord).next(stepSeijidantaiFix)
                .end().build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_PERSON_RECORD)
    protected Step getStepPersonRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_PERSON_RECORD, jobRepository)
                .<WkTblKanrenshaPersonAddMinEntity, WkTblKanrenshaPersonAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaPersonAddMiniRecordItemReader).writer(kanrenshaPersonAddMiniRecordItemWriter).build();
    }

    /**
     * StepFixを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_PERSON_FIX)
    protected Step getStepPersonFix(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_PERSON_FIX, jobRepository)
                .<WkTblKanrenshaPersonAddMinResultEntity, WkTblKanrenshaPersonAddMinEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(kanrenshaPersonAddMiniWkTblFixItemReader).processor(kanrenshaPersonAddMiniWkTblFixProcessor)
                .writer(kanrenshaPersonAddMiniWkTblFixItemWriter).build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CORP_RECORD)
    protected Step getStepKigyouDtRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CORP_RECORD, jobRepository)
                .<WkTblKanrenshaKigyouDtAddMinEntity, WkTblKanrenshaKigyouDtAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaKigyouDtAddMiniRecordItemReader).writer(kanrenshaKigyouDtAddMiniRecordItemWriter).build();
    }

    /**
     * StepFixを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CORP_FIX)
    protected Step getStepKigyouDtFix(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CORP_FIX, jobRepository)
                .<WkTblKanrenshaKigyouDtAddMinResultEntity, WkTblKanrenshaKigyouDtAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaKigyouDtAddMiniWkTblFixItemReader).processor(kanrenshaKigyouDtAddMiniWkTblFixProcessor)
                .writer(kanrenshaKigyouDtAddMiniWkTblFixItemWriter).build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_POLI_ORG_RECORD)
    protected Step getStepSeijidantaiRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_POLI_ORG_RECORD, jobRepository)
                .<WkTblKanrenshaSeijidantaiAddMinEntity, WkTblKanrenshaSeijidantaiAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(kanrenshaSeijidantaiAddMiniRecordItemReader).writer(kanrenshaSeijidantaiAddMiniRecordItemWriter).build();
    }

    /**
     * StepFixを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_POLI_ORG_FIX)
    protected Step getStepSeijidantaiFix(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_POLI_ORG_FIX, jobRepository)
                .<WkTblKanrenshaSeijidantaiAddMinResultEntity, WkTblKanrenshaSeijidantaiAddMinEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(kanrenshaSeijidantaiAddMiniWkTblFixItemReader).processor(kanrenshaSeijidantaiAddMiniWkTblFixProcessor)
                .writer(kanrenshaSeijidantaiAddMiniWkTblFixItemWriter).build();
    }

}
