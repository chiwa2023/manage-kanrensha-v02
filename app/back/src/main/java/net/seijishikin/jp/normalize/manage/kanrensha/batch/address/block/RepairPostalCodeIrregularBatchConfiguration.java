package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block; // NOPMD

import org.springframework.batch.core.job.Job;
//import org.springframework.batch.core.job.Job;
//import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
//import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * 郵便番号不規則修正BachConfig
 */
@Configuration
public class RepairPostalCodeIrregularBatchConfiguration { // NOPMD CouplingBetweenObjects

    /** 機能名 */
    private static final String FUNCTION_NAME = "repairPostalCodeIrregular";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** Step名(Other) */
    public static final String STEP_OTHER = FUNCTION_NAME + "Other" + STEP;

    /** Step名(Range) */
    public static final String STEP_RANGE = FUNCTION_NAME + "Range" + STEP;

    /** Step名(Single) */
    public static final String STEP_SINGLE = FUNCTION_NAME + "Single" + STEP;

    /** Step名(SplitTouten) */
    public static final String STEP_SPLIT_TOUTEN = FUNCTION_NAME + "SplitTouten" + STEP;

    /** Step名(JigyoushoSplit) */
    public static final String STEP_JIGYOUSHO_SPLIT = FUNCTION_NAME + "JigyoushoSplit" + STEP;

    /** Step名(PostalUpdateNormal) */
    public static final String STEP_POSTAL_NORMAL = FUNCTION_NAME + "PostalUpdateNormal" + STEP;

    /** Step名(PostalUpdateOther) */
    public static final String STEP_POSTAL_OTHER = FUNCTION_NAME + "PostalUpdateOther" + STEP;

    /** Step名(FixKeisaiNashi) */
    public static final String STEP_KEISAI_NASHI = FUNCTION_NAME + "FixKeisaiNashi" + STEP;

    /** Step名(History) */
    public static final String STEP_HISTORY1 = FUNCTION_NAME + "History1" + STEP;
    /** Step名(History) */
    public static final String STEP_HISTORY2 = FUNCTION_NAME + "History2" + STEP;
    /** Step名(History) */
    public static final String STEP_HISTORY3 = FUNCTION_NAME + "History3" + STEP;
    /** Step名(History) */
    public static final String STEP_HISTORY4 = FUNCTION_NAME + "History4" + STEP;
    /** Step名(History) */
    public static final String STEP_HISTORY5 = FUNCTION_NAME + "History5" + STEP;
    /** Step名(History) */
    public static final String STEP_HISTORY6 = FUNCTION_NAME + "History6" + STEP;

    /** Step名(Clean) */
    public static final String STEP_CLEAN = FUNCTION_NAME + "Clean" + STEP;
    /** Step名(Clean) */
    public static final String STEP_CLEAN1 = FUNCTION_NAME + "Clean1" + STEP;
    /** Step名(Clean) */
    public static final String STEP_CLEAN2 = FUNCTION_NAME + "Clean2" + STEP;
    /** Step名(Clean) */
    public static final String STEP_CLEAN3 = FUNCTION_NAME + "Clean3" + STEP;
    /** Step名(Clean) */
    public static final String STEP_CLEAN4 = FUNCTION_NAME + "Clean4" + STEP;
    /** Step名(Clean) */
    public static final String STEP_CLEAN5 = FUNCTION_NAME + "Clean5" + STEP;
    /** Step名(Clean) */
    public static final String STEP_CLEAN6 = FUNCTION_NAME + "Clean6" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 1000;

    /* その他は住居に対応データが存在するのを確認して有効にする */

    /** 郵便番号不規則その他CsvItemReader */
    @Autowired
    private SelectPostalCodeOtherItemReader selectPostalCodeOtherItemReader;

    /** 郵便番号CsvEntityProcessor */
    @Autowired
    private AddressPostalIrregularWorksProcessor addressPostalIrregularWorksProcessor;

    /** 郵便番号不規則その他ItemWriter */
    @Autowired
    private SelectPostalCodeOtherItemWriter selectPostalCodeOtherItemWriter;

    /* 単純な範囲データは郵便番号正規データを複製する */

    /** 郵便番号CsvItemReader */
    @Autowired
    private ChoicePostalCodeIrregularItemReader choicePostalCodeIrregularItemReader;

    /** 郵便番号ItemWriter */
    @Autowired
    private ChoicePostalCodeIrregularItemWriter choicePostalCodeIrregularItemWriter;

    /* 一地域のみデータは呼び出し住所を更新する */
    /** 郵便番号CsvItemReader */
    @Autowired
    private SelectPostalCodeSingleAddressItemReader selectPostalCodeSingleAddressItemReader;

    /** 郵便番号ItemWriter */
    @Autowired
    private SelectPostalCodeSingleAddressItemWriter selectPostalCodeSingleAddressItemWriter;

    /* 作業にためた内容を不規則に反映(最後から2番目) */

    /** 郵便番号作業ItemReader */
    @Autowired
    private WorksPostalItemReader worksPostalItemReader;

    /** 郵便番号作業不規則EntityProcessor */
    @Autowired
    private AddressPostalWorksIrregularProcessor addressPostalWorksIrregularProcessor;

    /** 郵便番号不規則ItemWriter */
    @Autowired
    private IrregularPostalItemWriter irregularPostalItemWriter;

    /** 正規郵便番号ItemWriter */
    @Autowired
    private NormalPostalItemWriter normalPostalItemWriter;

    /** 郵便番号読点分割ItemReader */
    @Autowired
    private SplitToutenOrgItemReader splitToutenOrgItemReader;

    /** 郵便番号読点分割ItemWriter */
    @Autowired
    private SplitToutenOrgItemWriter splitToutenOrgItemWriter;

    /** 郵便番号作業ItemReader */
    @Autowired
    private JigyoushAddressSplitItemReader jigyoushAddressSplitItemReader;

    /** 郵便番号作業不規則EntityProcessor */
    @Autowired
    private JigyoushAddressSplitProcessor jigyoushAddressSplitProcessor;

    /** 郵便番号不規則ItemWriter */
    @Autowired
    private WkPostalCommonItemWriter wkPostalCommonItemWriter;

    /** 以下に掲載がない場合修復ItemWriter */
    @Autowired
    private FixIkaniKeisaiNashiItemReader fixIkaniKeisaiNashiItemReader;

    /** 以下に掲載がない場合修復ItemWriter */
    @Autowired
    private FixIkaniKeisaiNashiItemWriter fixIkaniKeisaiNashiItemWriter;

    /** 郵便番号ワークテーブルProcessor */
    @Autowired
    private AddressPostalWorksProcessor addressPostalWorksProcessor;

    /** 郵便番号ワークテーブル郵便番号Processor */
    @Autowired
    private AddressPostalWorksPostalProcessor addressPostalWorksPostalProcessor;

    /* 作業を全Clean(最終ステップ) */
    /** 郵便番号作業全削除Tasklet */
    @Autowired
    private CleanWorksAddressPostalTasklet cleanWorksAddressPostalTasklet;

    /** その他でない通常抽出ItemReader */
    @Autowired
    private UpdatePostalCodeNormalItemReader updatePostalCodeNormalItemReader;

    /** その他抽出ItemReader */
    @Autowired
    private UpdatePostalCodeOtherItemReader updatePostalCodeOtherItemReader;

    /** 郵便番号更新ItemWriter */
    @Autowired
    private UpdateRsdtPostalCodeItemWriter updateRsdtPostalCodeItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, //
            @Qualifier(STEP_OTHER) final Step stepOther, //
            @Qualifier(STEP_RANGE) final Step stepRange, //
            @Qualifier(STEP_SINGLE) final Step stepSingle, //
            @Qualifier(STEP_JIGYOUSHO_SPLIT) final Step stepJigyoushoSplit, //
            @Qualifier(STEP_SPLIT_TOUTEN) final Step stepSplitTouten, //
            @Qualifier(STEP_POSTAL_NORMAL) final Step stepPostalNormal, //
            @Qualifier(STEP_POSTAL_OTHER) final Step stepPostalOther, //
            @Qualifier(STEP_KEISAI_NASHI) final Step stepKeisaiNashi, //
            @Qualifier(STEP_HISTORY1) final Step stepHistory1, //
            @Qualifier(STEP_HISTORY2) final Step stepHistory2, //
            @Qualifier(STEP_HISTORY3) final Step stepHistory3, //
            @Qualifier(STEP_HISTORY4) final Step stepHistory4, //
            @Qualifier(STEP_HISTORY5) final Step stepHistory5, //
            @Qualifier(STEP_HISTORY6) final Step stepHistory6, //
            @Qualifier(STEP_CLEAN1) final Step stepClean1, //
            @Qualifier(STEP_CLEAN2) final Step stepClean2, //
            @Qualifier(STEP_CLEAN3) final Step stepClean3, //
            @Qualifier(STEP_CLEAN4) final Step stepClean4, //
            @Qualifier(STEP_CLEAN5) final Step stepClean5, //
            @Qualifier(STEP_CLEAN6) final Step stepClean6, //
            @Qualifier(STEP_CLEAN) final Step stepClean //
    ) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepClean)
                .next(stepSplitTouten).next(stepHistory1).next(stepClean1) // 読点分割処理（A,B）を不規則にだけで（A）（B）別行
                .next(stepOther).next(stepHistory2).next(stepClean2) // その他処理(正規のその他表現を削除)
                .next(stepRange).next(stepHistory3).next(stepClean3) // 範囲処理(正規に範囲展開を追加)
                .next(stepSingle).next(stepHistory4).next(stepClean4) // 単一地域処理
                // .next(stepJigyoushoSplit).next(stepHistory5).next(stepClean5) // 事業所地域処理
                .next(stepPostalNormal).next(stepPostalOther) // 郵便番号更新(通常・その他)
                .next(stepKeisaiNashi).next(stepHistory6).next(stepClean6) // 以下に掲載がない場合修復
                .end().build();
    }

    /**
     * StepOtherを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OTHER)
    protected Step getStepOther(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_OTHER, jobRepository)
                .<AddressPostalIrregularEntity, WkTblPostalCommonEntity>chunk(CHUNK_SIZE)
                .reader(selectPostalCodeOtherItemReader).processor(addressPostalIrregularWorksProcessor)
                .writer(selectPostalCodeOtherItemWriter).build();
    }

    /**
     * StepRangeを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RANGE)
    protected Step getStepRange(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RANGE, jobRepository)
                .<AddressPostalIrregularEntity, WkTblPostalCommonEntity>chunk(CHUNK_SIZE)
                .reader(choicePostalCodeIrregularItemReader).processor(addressPostalIrregularWorksProcessor)
                .writer(choicePostalCodeIrregularItemWriter).build();
    }

    /**
     * StepSingleを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_SINGLE)
    protected Step getStepSingle(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_SINGLE, jobRepository)
                .<AddressPostalIrregularEntity, WkTblPostalCommonEntity>chunk(CHUNK_SIZE)
                .reader(selectPostalCodeSingleAddressItemReader).processor(addressPostalIrregularWorksProcessor)
                .writer(selectPostalCodeSingleAddressItemWriter).build();
    }

    /**
     * StepJigyoushoSplitを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_JIGYOUSHO_SPLIT)
    protected Step getStepJigyoushoSplit(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_JIGYOUSHO_SPLIT, jobRepository)
                .<AddressPostalIrregularEntity, WkTblPostalCommonEntity>chunk(CHUNK_SIZE)
                .reader(jigyoushAddressSplitItemReader).processor(jigyoushAddressSplitProcessor)
                .writer(wkPostalCommonItemWriter).build();
    }

    /**
     * StepSplitToutenを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_SPLIT_TOUTEN)
    protected Step getStepSplitTouten(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_SPLIT_TOUTEN, jobRepository)
                .<AddressPostalIrregularEntity, AddressPostalIrregularEntity>chunk(CHUNK_SIZE)
                .reader(splitToutenOrgItemReader).writer(splitToutenOrgItemWriter).build();
    }

    /**
     * StepPostalNormalを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_POSTAL_NORMAL)
    protected Step getStepPostalNormal(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_POSTAL_NORMAL, jobRepository)
                .<AddressPostalEntity, AddressPostalEntity>chunk(CHUNK_SIZE)
                .reader(updatePostalCodeNormalItemReader).writer(updateRsdtPostalCodeItemWriter).build();
    }

    /**
     * StepPostalOtherを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_POSTAL_OTHER)
    protected Step getStepPostalOther(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_POSTAL_OTHER, jobRepository)
                .<AddressPostalEntity, AddressPostalEntity>chunk(CHUNK_SIZE)
                .reader(updatePostalCodeOtherItemReader).writer(updateRsdtPostalCodeItemWriter).build();
    }

    /**
     * StepFixKeisaiNashiを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_KEISAI_NASHI)
    protected Step getStepFixKeisaiNashi(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_KEISAI_NASHI, jobRepository)
                .<AddressPostalEntity, WkTblPostalCommonEntity>chunk(CHUNK_SIZE)
                .reader(fixIkaniKeisaiNashiItemReader).processor(addressPostalWorksProcessor)
                .writer(fixIkaniKeisaiNashiItemWriter).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY1)
    protected Step getStepHistory1(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY1, jobRepository)
                .<WkTblPostalCommonEntity, AddressPostalIrregularEntity>chunk(CHUNK_SIZE)
                .reader(worksPostalItemReader).processor(addressPostalWorksIrregularProcessor)
                .writer(irregularPostalItemWriter).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY2)
    protected Step getStepHistory2(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY2, jobRepository)
                .<WkTblPostalCommonEntity, AddressPostalIrregularEntity>chunk(CHUNK_SIZE)
                .reader(worksPostalItemReader).processor(addressPostalWorksIrregularProcessor)
                .writer(irregularPostalItemWriter).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY3)
    protected Step getStepHistory3(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY3, jobRepository)
                .<WkTblPostalCommonEntity, AddressPostalIrregularEntity>chunk(CHUNK_SIZE)
                .reader(worksPostalItemReader).processor(addressPostalWorksIrregularProcessor)
                .writer(irregularPostalItemWriter).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY4)
    protected Step getStepHistory4(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY4, jobRepository)
                .<WkTblPostalCommonEntity, AddressPostalIrregularEntity>chunk(CHUNK_SIZE)
                .reader(worksPostalItemReader).processor(addressPostalWorksIrregularProcessor)
                .writer(irregularPostalItemWriter).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY5)
    protected Step getStepHistory5(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY5, jobRepository)
                .<WkTblPostalCommonEntity, AddressPostalEntity>chunk(CHUNK_SIZE)
                .reader(worksPostalItemReader).processor(addressPostalWorksPostalProcessor)
                .writer(normalPostalItemWriter).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY6)
    protected Step getStepHistory6(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY6, jobRepository)
                .<WkTblPostalCommonEntity, AddressPostalIrregularEntity>chunk(CHUNK_SIZE)
                .reader(worksPostalItemReader).processor(addressPostalWorksIrregularProcessor)
                .writer(irregularPostalItemWriter).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN)
    protected Step getStepClean(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN1)
    protected Step getStepClean1(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN1, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN2)
    protected Step getStepClean2(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN2, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN3)
    protected Step getStepClean3(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN3, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN4)
    protected Step getStepClean4(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN4, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN5)
    protected Step getStepClean5(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN5, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN6)
    protected Step getStepClean6(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN6, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

}
