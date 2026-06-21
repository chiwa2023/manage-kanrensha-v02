package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.lgcode;


/**
 * 地方自治体コードファイルから更新処理
 */
public class RefreshAllCityLgcodeBatchConfiguration {


    /** 機能名 */
    private static final String FUNCTION_NAME = "refreshAllCityLgcode";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    ///** Step名 */
    //public static final String STEP_DUMP = FUNCTION_NAME + "Dump" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    // stepClean
    // ワークテーブルと作業ワークテーブルを消去
    
    // stepWorkTable
    // 地方自治体コードファイルを読み出す(実装済、AllCityCsvItemReader)
    // 変換
    // ワークテーブルに書き出し
 
    // stepWorkDelete
    // 原本テーブルを呼び出し
    // ワークテーブルと比較し存在しない場合は作業ワークテーブルに削除として登録

    // stepWorkInsert
    // ワークテーブルを呼び出し
    // 原本テーブルと比較し、存在しない場合は作業ワークテーブルに追加として登録

    // stepOrgFix
    // 作業ワークテーブルを呼び出し
    // 原本テーブルを作業内容に合わせて修正する

//    /**
//     * Jobを返却する
//     *
//     * @param jobRepository ジョブレポジトリ
//     * @param step          このConfigureで設定したステップ
//     * @return Job
//     */
//    @Bean(JOB_NAME)
//    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_DUMP) final Step stepDump) {
//
//        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer())
//                .listener(recordTaskPlanJobExecutionListner).flow(stepDump).end().build();
//    }

//    /**
//     * StepOtherを返却する
//     *
//     * @param jobRepository      jobRepository
//     * @param transactionManager transactionManager
//     * @return step
//     */
//    @Bean(STEP_DUMP)
//    protected Step getStepDump(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
//
//        return new StepBuilder(STEP_DUMP, jobRepository)
//                .<KanrenshaKigyouDtMasterEntity, KanrenshaKigyouDtMasterEntity>chunk(CHUNK_SIZE, transactionManager)
//                .reader(dumpSabunMasterKigyouDtItemReader).writer(dumpMinKigyouDtItemWriter).build();
//    }

}
