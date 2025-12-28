package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history; // NOPMD

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.zip.CompressZipFileTasklet;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory02Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory03Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory04Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory05Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory06Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory07Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory08Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory09Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory10Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory11Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory12Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory14Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory15Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory16Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory17Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory18Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory19Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory20Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory21Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory22Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory23Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory24Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory25Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory26Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory27Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory28Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory29Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory30Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory31Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory32Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory33Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory34Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory35Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory36Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory37Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory38Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory39Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory40Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory41Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory42Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory43Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory44Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory46Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory47Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory99Entity;

/**
 * 関連者企業・団体履歴csv作成BatchConfiguration
 */
@Configuration
public class DumpKanrenshaKigyouDtHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "dumpKanrenshaKigyouDtHistory";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 100;

    /** Step名zip圧縮 */
    public static final String STEP_COMPRESS = FUNCTION_NAME + "Compress" + STEP;

    /** Step名(01) */
    public static final String STEP_OUTPUT01 = FUNCTION_NAME + "Output01" + STEP;
    /** Step名(02) */
    public static final String STEP_OUTPUT02 = FUNCTION_NAME + "Output02" + STEP;
    /** Step名(03) */
    public static final String STEP_OUTPUT03 = FUNCTION_NAME + "Output03" + STEP;
    /** Step名(04) */
    public static final String STEP_OUTPUT04 = FUNCTION_NAME + "Output04" + STEP;
    /** Step名(05) */
    public static final String STEP_OUTPUT05 = FUNCTION_NAME + "Output05" + STEP;
    /** Step名(06) */
    public static final String STEP_OUTPUT06 = FUNCTION_NAME + "Output06" + STEP;
    /** Step名(07) */
    public static final String STEP_OUTPUT07 = FUNCTION_NAME + "Output07" + STEP;
    /** Step名(08) */
    public static final String STEP_OUTPUT08 = FUNCTION_NAME + "Output08" + STEP;
    /** Step名(09) */
    public static final String STEP_OUTPUT09 = FUNCTION_NAME + "Output09" + STEP;
    /** Step名(10) */
    public static final String STEP_OUTPUT10 = FUNCTION_NAME + "Output10" + STEP;
    /** Step名(11) */
    public static final String STEP_OUTPUT11 = FUNCTION_NAME + "Output11" + STEP;
    /** Step名(12) */
    public static final String STEP_OUTPUT12 = FUNCTION_NAME + "Output12" + STEP;
    /** Step名(13) */
    public static final String STEP_OUTPUT13 = FUNCTION_NAME + "Output13" + STEP;
    /** Step名(14) */
    public static final String STEP_OUTPUT14 = FUNCTION_NAME + "Output14" + STEP;
    /** Step名(15) */
    public static final String STEP_OUTPUT15 = FUNCTION_NAME + "Output15" + STEP;
    /** Step名(16) */
    public static final String STEP_OUTPUT16 = FUNCTION_NAME + "Output16" + STEP;
    /** Step名(17) */
    public static final String STEP_OUTPUT17 = FUNCTION_NAME + "Output17" + STEP;
    /** Step名(18) */
    public static final String STEP_OUTPUT18 = FUNCTION_NAME + "Output18" + STEP;
    /** Step名(19) */
    public static final String STEP_OUTPUT19 = FUNCTION_NAME + "Output19" + STEP;
    /** Step名(20) */
    public static final String STEP_OUTPUT20 = FUNCTION_NAME + "Output20" + STEP;
    /** Step名(21) */
    public static final String STEP_OUTPUT21 = FUNCTION_NAME + "Output21" + STEP;
    /** Step名(22) */
    public static final String STEP_OUTPUT22 = FUNCTION_NAME + "Output22" + STEP;
    /** Step名(23) */
    public static final String STEP_OUTPUT23 = FUNCTION_NAME + "Output23" + STEP;
    /** Step名(24) */
    public static final String STEP_OUTPUT24 = FUNCTION_NAME + "Output24" + STEP;
    /** Step名(25) */
    public static final String STEP_OUTPUT25 = FUNCTION_NAME + "Output25" + STEP;
    /** Step名(26) */
    public static final String STEP_OUTPUT26 = FUNCTION_NAME + "Output26" + STEP;
    /** Step名(27) */
    public static final String STEP_OUTPUT27 = FUNCTION_NAME + "Output27" + STEP;
    /** Step名(28) */
    public static final String STEP_OUTPUT28 = FUNCTION_NAME + "Output28" + STEP;
    /** Step名(29) */
    public static final String STEP_OUTPUT29 = FUNCTION_NAME + "Output29" + STEP;
    /** Step名(30) */
    public static final String STEP_OUTPUT30 = FUNCTION_NAME + "Output30" + STEP;
    /** Step名(31) */
    public static final String STEP_OUTPUT31 = FUNCTION_NAME + "Output31" + STEP;
    /** Step名(32) */
    public static final String STEP_OUTPUT32 = FUNCTION_NAME + "Output32" + STEP;
    /** Step名(33) */
    public static final String STEP_OUTPUT33 = FUNCTION_NAME + "Output33" + STEP;
    /** Step名(34) */
    public static final String STEP_OUTPUT34 = FUNCTION_NAME + "Output34" + STEP;
    /** Step名(35) */
    public static final String STEP_OUTPUT35 = FUNCTION_NAME + "Output35" + STEP;
    /** Step名(36) */
    public static final String STEP_OUTPUT36 = FUNCTION_NAME + "Output36" + STEP;
    /** Step名(37) */
    public static final String STEP_OUTPUT37 = FUNCTION_NAME + "Output37" + STEP;
    /** Step名(38) */
    public static final String STEP_OUTPUT38 = FUNCTION_NAME + "Output38" + STEP;
    /** Step名(39) */
    public static final String STEP_OUTPUT39 = FUNCTION_NAME + "Output39" + STEP;
    /** Step名(40) */
    public static final String STEP_OUTPUT40 = FUNCTION_NAME + "Output40" + STEP;
    /** Step名(41) */
    public static final String STEP_OUTPUT41 = FUNCTION_NAME + "Output41" + STEP;
    /** Step名(42) */
    public static final String STEP_OUTPUT42 = FUNCTION_NAME + "Output42" + STEP;
    /** Step名(43) */
    public static final String STEP_OUTPUT43 = FUNCTION_NAME + "Output43" + STEP;
    /** Step名(44) */
    public static final String STEP_OUTPUT44 = FUNCTION_NAME + "Output44" + STEP;
    /** Step名(45) */
    public static final String STEP_OUTPUT45 = FUNCTION_NAME + "Output45" + STEP;
    /** Step名(46) */
    public static final String STEP_OUTPUT46 = FUNCTION_NAME + "Output46" + STEP;
    /** Step名(47) */
    public static final String STEP_OUTPUT47 = FUNCTION_NAME + "Output47" + STEP;
    /** Step名(48) */
    public static final String STEP_OUTPUT99 = FUNCTION_NAME + "Output99" + STEP;

    /** フォルダZip圧縮Tasklet */
    @Autowired
    private CompressZipFileTasklet compressZipFileTasklet;

    /** 関連者企業・団体履歴(01)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory01ItemReader dumpKanrenshaKigyouDtHistory01ItemReader;
    /** 関連者企業・団体履歴(02)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory02ItemReader dumpKanrenshaKigyouDtHistory02ItemReader;
    /** 関連者企業・団体履歴(03)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory03ItemReader dumpKanrenshaKigyouDtHistory03ItemReader;
    /** 関連者企業・団体履歴(04)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory04ItemReader dumpKanrenshaKigyouDtHistory04ItemReader;
    /** 関連者企業・団体履歴(05)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory05ItemReader dumpKanrenshaKigyouDtHistory05ItemReader;
    /** 関連者企業・団体履歴(06)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory06ItemReader dumpKanrenshaKigyouDtHistory06ItemReader;
    /** 関連者企業・団体履歴(07)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory07ItemReader dumpKanrenshaKigyouDtHistory07ItemReader;
    /** 関連者企業・団体履歴(08)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory08ItemReader dumpKanrenshaKigyouDtHistory08ItemReader;
    /** 関連者企業・団体履歴(09)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory09ItemReader dumpKanrenshaKigyouDtHistory09ItemReader;
    /** 関連者企業・団体履歴(10)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory10ItemReader dumpKanrenshaKigyouDtHistory10ItemReader;
    /** 関連者企業・団体履歴(11)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory11ItemReader dumpKanrenshaKigyouDtHistory11ItemReader;
    /** 関連者企業・団体履歴(12)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory12ItemReader dumpKanrenshaKigyouDtHistory12ItemReader;
    /** 関連者企業・団体履歴(13)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory13ItemReader dumpKanrenshaKigyouDtHistory13ItemReader;
    /** 関連者企業・団体履歴(14)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory14ItemReader dumpKanrenshaKigyouDtHistory14ItemReader;
    /** 関連者企業・団体履歴(15)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory15ItemReader dumpKanrenshaKigyouDtHistory15ItemReader;
    /** 関連者企業・団体履歴(16)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory16ItemReader dumpKanrenshaKigyouDtHistory16ItemReader;
    /** 関連者企業・団体履歴(17)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory17ItemReader dumpKanrenshaKigyouDtHistory17ItemReader;
    /** 関連者企業・団体履歴(18)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory18ItemReader dumpKanrenshaKigyouDtHistory18ItemReader;
    /** 関連者企業・団体履歴(19)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory19ItemReader dumpKanrenshaKigyouDtHistory19ItemReader;
    /** 関連者企業・団体履歴(20)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory20ItemReader dumpKanrenshaKigyouDtHistory20ItemReader;
    /** 関連者企業・団体履歴(21)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory21ItemReader dumpKanrenshaKigyouDtHistory21ItemReader;
    /** 関連者企業・団体履歴(22)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory22ItemReader dumpKanrenshaKigyouDtHistory22ItemReader;
    /** 関連者企業・団体履歴(23)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory23ItemReader dumpKanrenshaKigyouDtHistory23ItemReader;
    /** 関連者企業・団体履歴(24)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory24ItemReader dumpKanrenshaKigyouDtHistory24ItemReader;
    /** 関連者企業・団体履歴(25)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory25ItemReader dumpKanrenshaKigyouDtHistory25ItemReader;
    /** 関連者企業・団体履歴(26)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory26ItemReader dumpKanrenshaKigyouDtHistory26ItemReader;
    /** 関連者企業・団体履歴(27)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory27ItemReader dumpKanrenshaKigyouDtHistory27ItemReader;
    /** 関連者企業・団体履歴(28)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory28ItemReader dumpKanrenshaKigyouDtHistory28ItemReader;
    /** 関連者企業・団体履歴(29)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory29ItemReader dumpKanrenshaKigyouDtHistory29ItemReader;
    /** 関連者企業・団体履歴(30)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory30ItemReader dumpKanrenshaKigyouDtHistory30ItemReader;
    /** 関連者企業・団体履歴(31)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory31ItemReader dumpKanrenshaKigyouDtHistory31ItemReader;
    /** 関連者企業・団体履歴(32)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory32ItemReader dumpKanrenshaKigyouDtHistory32ItemReader;
    /** 関連者企業・団体履歴(33)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory33ItemReader dumpKanrenshaKigyouDtHistory33ItemReader;
    /** 関連者企業・団体履歴(34)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory34ItemReader dumpKanrenshaKigyouDtHistory34ItemReader;
    /** 関連者企業・団体履歴(35)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory35ItemReader dumpKanrenshaKigyouDtHistory35ItemReader;
    /** 関連者企業・団体履歴(36)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory36ItemReader dumpKanrenshaKigyouDtHistory36ItemReader;
    /** 関連者企業・団体履歴(37)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory37ItemReader dumpKanrenshaKigyouDtHistory37ItemReader;
    /** 関連者企業・団体履歴(38)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory38ItemReader dumpKanrenshaKigyouDtHistory38ItemReader;
    /** 関連者企業・団体履歴(39)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory39ItemReader dumpKanrenshaKigyouDtHistory39ItemReader;
    /** 関連者企業・団体履歴(40)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory40ItemReader dumpKanrenshaKigyouDtHistory40ItemReader;
    /** 関連者企業・団体履歴(41)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory41ItemReader dumpKanrenshaKigyouDtHistory41ItemReader;
    /** 関連者企業・団体履歴(42)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory42ItemReader dumpKanrenshaKigyouDtHistory42ItemReader;
    /** 関連者企業・団体履歴(43)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory43ItemReader dumpKanrenshaKigyouDtHistory43ItemReader;
    /** 関連者企業・団体履歴(44)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory44ItemReader dumpKanrenshaKigyouDtHistory44ItemReader;
    /** 関連者企業・団体履歴(45)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory45ItemReader dumpKanrenshaKigyouDtHistory45ItemReader;
    /** 関連者企業・団体履歴(46)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory46ItemReader dumpKanrenshaKigyouDtHistory46ItemReader;
    /** 関連者企業・団体履歴(47)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory47ItemReader dumpKanrenshaKigyouDtHistory47ItemReader;
    /** 関連者企業・団体履歴(99)CsvItemReader */
    @Autowired
    private DumpKanrenshaKigyouDtHistory99ItemReader dumpKanrenshaKigyouDtHistory99ItemReader;
    /** 関連者企業・団体履歴(01)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory01ItemWriter dumpKanrenshaKigyouDtHistory01ItemWriter;
    /** 関連者企業・団体履歴(02)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory02ItemWriter dumpKanrenshaKigyouDtHistory02ItemWriter;
    /** 関連者企業・団体履歴(03)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory03ItemWriter dumpKanrenshaKigyouDtHistory03ItemWriter;
    /** 関連者企業・団体履歴(04)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory04ItemWriter dumpKanrenshaKigyouDtHistory04ItemWriter;
    /** 関連者企業・団体履歴(05)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory05ItemWriter dumpKanrenshaKigyouDtHistory05ItemWriter;
    /** 関連者企業・団体履歴(06)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory06ItemWriter dumpKanrenshaKigyouDtHistory06ItemWriter;
    /** 関連者企業・団体履歴(07)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory07ItemWriter dumpKanrenshaKigyouDtHistory07ItemWriter;
    /** 関連者企業・団体履歴(08)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory08ItemWriter dumpKanrenshaKigyouDtHistory08ItemWriter;
    /** 関連者企業・団体履歴(09)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory09ItemWriter dumpKanrenshaKigyouDtHistory09ItemWriter;
    /** 関連者企業・団体履歴(10)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory10ItemWriter dumpKanrenshaKigyouDtHistory10ItemWriter;
    /** 関連者企業・団体履歴(11)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory11ItemWriter dumpKanrenshaKigyouDtHistory11ItemWriter;
    /** 関連者企業・団体履歴(12)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory12ItemWriter dumpKanrenshaKigyouDtHistory12ItemWriter;
    /** 関連者企業・団体履歴(13)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory13ItemWriter dumpKanrenshaKigyouDtHistory13ItemWriter;
    /** 関連者企業・団体履歴(14)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory14ItemWriter dumpKanrenshaKigyouDtHistory14ItemWriter;
    /** 関連者企業・団体履歴(15)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory15ItemWriter dumpKanrenshaKigyouDtHistory15ItemWriter;
    /** 関連者企業・団体履歴(16)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory16ItemWriter dumpKanrenshaKigyouDtHistory16ItemWriter;
    /** 関連者企業・団体履歴(17)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory17ItemWriter dumpKanrenshaKigyouDtHistory17ItemWriter;
    /** 関連者企業・団体履歴(18)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory18ItemWriter dumpKanrenshaKigyouDtHistory18ItemWriter;
    /** 関連者企業・団体履歴(19)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory19ItemWriter dumpKanrenshaKigyouDtHistory19ItemWriter;
    /** 関連者企業・団体履歴(20)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory20ItemWriter dumpKanrenshaKigyouDtHistory20ItemWriter;
    /** 関連者企業・団体履歴(21)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory21ItemWriter dumpKanrenshaKigyouDtHistory21ItemWriter;
    /** 関連者企業・団体履歴(22)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory22ItemWriter dumpKanrenshaKigyouDtHistory22ItemWriter;
    /** 関連者企業・団体履歴(23)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory23ItemWriter dumpKanrenshaKigyouDtHistory23ItemWriter;
    /** 関連者企業・団体履歴(24)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory24ItemWriter dumpKanrenshaKigyouDtHistory24ItemWriter;
    /** 関連者企業・団体履歴(25)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory25ItemWriter dumpKanrenshaKigyouDtHistory25ItemWriter;
    /** 関連者企業・団体履歴(26)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory26ItemWriter dumpKanrenshaKigyouDtHistory26ItemWriter;
    /** 関連者企業・団体履歴(27)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory27ItemWriter dumpKanrenshaKigyouDtHistory27ItemWriter;
    /** 関連者企業・団体履歴(28)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory28ItemWriter dumpKanrenshaKigyouDtHistory28ItemWriter;
    /** 関連者企業・団体履歴(29)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory29ItemWriter dumpKanrenshaKigyouDtHistory29ItemWriter;
    /** 関連者企業・団体履歴(30)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory30ItemWriter dumpKanrenshaKigyouDtHistory30ItemWriter;
    /** 関連者企業・団体履歴(31)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory31ItemWriter dumpKanrenshaKigyouDtHistory31ItemWriter;
    /** 関連者企業・団体履歴(32)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory32ItemWriter dumpKanrenshaKigyouDtHistory32ItemWriter;
    /** 関連者企業・団体履歴(33)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory33ItemWriter dumpKanrenshaKigyouDtHistory33ItemWriter;
    /** 関連者企業・団体履歴(34)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory34ItemWriter dumpKanrenshaKigyouDtHistory34ItemWriter;
    /** 関連者企業・団体履歴(35)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory35ItemWriter dumpKanrenshaKigyouDtHistory35ItemWriter;
    /** 関連者企業・団体履歴(36)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory36ItemWriter dumpKanrenshaKigyouDtHistory36ItemWriter;
    /** 関連者企業・団体履歴(37)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory37ItemWriter dumpKanrenshaKigyouDtHistory37ItemWriter;
    /** 関連者企業・団体履歴(38)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory38ItemWriter dumpKanrenshaKigyouDtHistory38ItemWriter;
    /** 関連者企業・団体履歴(39)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory39ItemWriter dumpKanrenshaKigyouDtHistory39ItemWriter;
    /** 関連者企業・団体履歴(40)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory40ItemWriter dumpKanrenshaKigyouDtHistory40ItemWriter;
    /** 関連者企業・団体履歴(41)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory41ItemWriter dumpKanrenshaKigyouDtHistory41ItemWriter;
    /** 関連者企業・団体履歴(42)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory42ItemWriter dumpKanrenshaKigyouDtHistory42ItemWriter;
    /** 関連者企業・団体履歴(43)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory43ItemWriter dumpKanrenshaKigyouDtHistory43ItemWriter;
    /** 関連者企業・団体履歴(44)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory44ItemWriter dumpKanrenshaKigyouDtHistory44ItemWriter;
    /** 関連者企業・団体履歴(45)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory45ItemWriter dumpKanrenshaKigyouDtHistory45ItemWriter;
    /** 関連者企業・団体履歴(46)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory46ItemWriter dumpKanrenshaKigyouDtHistory46ItemWriter;
    /** 関連者企業・団体履歴(47)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory47ItemWriter dumpKanrenshaKigyouDtHistory47ItemWriter;
    /** 関連者企業・団体履歴(99)ItemWriter */
    @Autowired
    private DumpKanrenshaKigyouDtHistory99ItemWriter dumpKanrenshaKigyouDtHistory99ItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_OUTPUT01) final Step stepOutput01,
            @Qualifier(STEP_OUTPUT02) final Step stepOutput02, @Qualifier(STEP_OUTPUT03) final Step stepOutput03,
            @Qualifier(STEP_OUTPUT04) final Step stepOutput04, @Qualifier(STEP_OUTPUT05) final Step stepOutput05,
            @Qualifier(STEP_OUTPUT06) final Step stepOutput06, @Qualifier(STEP_OUTPUT07) final Step stepOutput07,
            @Qualifier(STEP_OUTPUT08) final Step stepOutput08, @Qualifier(STEP_OUTPUT09) final Step stepOutput09,
            @Qualifier(STEP_OUTPUT10) final Step stepOutput10, @Qualifier(STEP_OUTPUT11) final Step stepOutput11,
            @Qualifier(STEP_OUTPUT12) final Step stepOutput12, @Qualifier(STEP_OUTPUT13) final Step stepOutput13,
            @Qualifier(STEP_OUTPUT14) final Step stepOutput14, @Qualifier(STEP_OUTPUT15) final Step stepOutput15,
            @Qualifier(STEP_OUTPUT16) final Step stepOutput16, @Qualifier(STEP_OUTPUT17) final Step stepOutput17,
            @Qualifier(STEP_OUTPUT18) final Step stepOutput18, @Qualifier(STEP_OUTPUT19) final Step stepOutput19,
            @Qualifier(STEP_OUTPUT20) final Step stepOutput20, @Qualifier(STEP_OUTPUT21) final Step stepOutput21,
            @Qualifier(STEP_OUTPUT22) final Step stepOutput22, @Qualifier(STEP_OUTPUT23) final Step stepOutput23,
            @Qualifier(STEP_OUTPUT24) final Step stepOutput24, @Qualifier(STEP_OUTPUT25) final Step stepOutput25,
            @Qualifier(STEP_OUTPUT26) final Step stepOutput26, @Qualifier(STEP_OUTPUT27) final Step stepOutput27,
            @Qualifier(STEP_OUTPUT28) final Step stepOutput28, @Qualifier(STEP_OUTPUT29) final Step stepOutput29,
            @Qualifier(STEP_OUTPUT30) final Step stepOutput30, @Qualifier(STEP_OUTPUT31) final Step stepOutput31,
            @Qualifier(STEP_OUTPUT32) final Step stepOutput32, @Qualifier(STEP_OUTPUT33) final Step stepOutput33,
            @Qualifier(STEP_OUTPUT34) final Step stepOutput34, @Qualifier(STEP_OUTPUT35) final Step stepOutput35,
            @Qualifier(STEP_OUTPUT36) final Step stepOutput36, @Qualifier(STEP_OUTPUT37) final Step stepOutput37,
            @Qualifier(STEP_OUTPUT38) final Step stepOutput38, @Qualifier(STEP_OUTPUT39) final Step stepOutput39,
            @Qualifier(STEP_OUTPUT40) final Step stepOutput40, @Qualifier(STEP_OUTPUT41) final Step stepOutput41,
            @Qualifier(STEP_OUTPUT42) final Step stepOutput42, @Qualifier(STEP_OUTPUT43) final Step stepOutput43,
            @Qualifier(STEP_OUTPUT44) final Step stepOutput44, @Qualifier(STEP_OUTPUT45) final Step stepOutput45,
            @Qualifier(STEP_OUTPUT46) final Step stepOutput46, @Qualifier(STEP_OUTPUT47) final Step stepOutput47,
            @Qualifier(STEP_OUTPUT99) final Step stepOutput99, @Qualifier(STEP_COMPRESS) final Step stepCompress) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepOutput01)
                .next(stepOutput02).next(stepOutput03).next(stepOutput04).next(stepOutput05).next(stepOutput06)
                .next(stepOutput07).next(stepOutput08).next(stepOutput09).next(stepOutput10).next(stepOutput11)
                .next(stepOutput12).next(stepOutput13).next(stepOutput14).next(stepOutput15).next(stepOutput16)
                .next(stepOutput17).next(stepOutput18).next(stepOutput19).next(stepOutput20).next(stepOutput21)
                .next(stepOutput22).next(stepOutput23).next(stepOutput24).next(stepOutput25).next(stepOutput26)
                .next(stepOutput27).next(stepOutput28).next(stepOutput29).next(stepOutput30).next(stepOutput31)
                .next(stepOutput32).next(stepOutput33).next(stepOutput34).next(stepOutput35).next(stepOutput36)
                .next(stepOutput37).next(stepOutput38).next(stepOutput39).next(stepOutput40).next(stepOutput41)
                .next(stepOutput42).next(stepOutput43).next(stepOutput44).next(stepOutput45).next(stepOutput46)
                .next(stepOutput47).next(stepOutput99).next(stepCompress).end().build();
    }

    /**
     * Step01を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT01)
    protected Step getStep01(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT01, jobRepository)
                .<KanrenshaKigyouDtHistory01Entity, KanrenshaKigyouDtHistory01Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory01ItemReader).writer(dumpKanrenshaKigyouDtHistory01ItemWriter).build();
    }

    /**
     * Step02を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT02)
    protected Step getStep02(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT02, jobRepository)
                .<KanrenshaKigyouDtHistory02Entity, KanrenshaKigyouDtHistory02Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory02ItemReader).writer(dumpKanrenshaKigyouDtHistory02ItemWriter).build();
    }

    /**
     * Step03を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT03)
    protected Step getStep03(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT03, jobRepository)
                .<KanrenshaKigyouDtHistory03Entity, KanrenshaKigyouDtHistory03Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory03ItemReader).writer(dumpKanrenshaKigyouDtHistory03ItemWriter).build();
    }

    /**
     * Step04を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT04)
    protected Step getStep04(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT04, jobRepository)
                .<KanrenshaKigyouDtHistory04Entity, KanrenshaKigyouDtHistory04Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory04ItemReader).writer(dumpKanrenshaKigyouDtHistory04ItemWriter).build();
    }

    /**
     * Step05を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT05)
    protected Step getStep05(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT05, jobRepository)
                .<KanrenshaKigyouDtHistory05Entity, KanrenshaKigyouDtHistory05Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory05ItemReader).writer(dumpKanrenshaKigyouDtHistory05ItemWriter).build();
    }

    /**
     * Step06を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT06)
    protected Step getStep06(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT06, jobRepository)
                .<KanrenshaKigyouDtHistory06Entity, KanrenshaKigyouDtHistory06Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory06ItemReader).writer(dumpKanrenshaKigyouDtHistory06ItemWriter).build();
    }

    /**
     * Step07を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT07)
    protected Step getStep07(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT07, jobRepository)
                .<KanrenshaKigyouDtHistory07Entity, KanrenshaKigyouDtHistory07Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory07ItemReader).writer(dumpKanrenshaKigyouDtHistory07ItemWriter).build();
    }

    /**
     * Step08を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT08)
    protected Step getStep08(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT01, jobRepository)
                .<KanrenshaKigyouDtHistory08Entity, KanrenshaKigyouDtHistory08Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory08ItemReader).writer(dumpKanrenshaKigyouDtHistory08ItemWriter).build();
    }

    /**
     * Step09を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT09)
    protected Step getStep09(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT09, jobRepository)
                .<KanrenshaKigyouDtHistory09Entity, KanrenshaKigyouDtHistory09Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory09ItemReader).writer(dumpKanrenshaKigyouDtHistory09ItemWriter).build();
    }

    /**
     * Step10を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT10)
    protected Step getStep10(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT10, jobRepository)
                .<KanrenshaKigyouDtHistory10Entity, KanrenshaKigyouDtHistory10Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory10ItemReader).writer(dumpKanrenshaKigyouDtHistory10ItemWriter).build();
    }

    /**
     * Step11を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT11)
    protected Step getStep11(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT11, jobRepository)
                .<KanrenshaKigyouDtHistory11Entity, KanrenshaKigyouDtHistory11Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory11ItemReader).writer(dumpKanrenshaKigyouDtHistory11ItemWriter).build();
    }

    /**
     * Step12を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT12)
    protected Step getStep12(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT12, jobRepository)
                .<KanrenshaKigyouDtHistory12Entity, KanrenshaKigyouDtHistory12Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory12ItemReader).writer(dumpKanrenshaKigyouDtHistory12ItemWriter).build();
    }

    /**
     * Step13を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT13)
    protected Step getStep13(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT13, jobRepository)
                .<KanrenshaKigyouDtHistory13Entity, KanrenshaKigyouDtHistory13Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory13ItemReader).writer(dumpKanrenshaKigyouDtHistory13ItemWriter).build();
    }

    /**
     * Step14を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT14)
    protected Step getStep14(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT14, jobRepository)
                .<KanrenshaKigyouDtHistory14Entity, KanrenshaKigyouDtHistory14Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory14ItemReader).writer(dumpKanrenshaKigyouDtHistory14ItemWriter).build();
    }

    /**
     * Step15を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT15)
    protected Step getStep15(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT15, jobRepository)
                .<KanrenshaKigyouDtHistory15Entity, KanrenshaKigyouDtHistory15Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory15ItemReader).writer(dumpKanrenshaKigyouDtHistory15ItemWriter).build();
    }

    /**
     * Step16を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT16)
    protected Step getStep16(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT16, jobRepository)
                .<KanrenshaKigyouDtHistory16Entity, KanrenshaKigyouDtHistory16Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory16ItemReader).writer(dumpKanrenshaKigyouDtHistory16ItemWriter).build();
    }

    /**
     * Step17を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT17)
    protected Step getStep17(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT17, jobRepository)
                .<KanrenshaKigyouDtHistory17Entity, KanrenshaKigyouDtHistory17Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory17ItemReader).writer(dumpKanrenshaKigyouDtHistory17ItemWriter).build();
    }

    /**
     * Step18を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT18)
    protected Step getStep18(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT18, jobRepository)
                .<KanrenshaKigyouDtHistory18Entity, KanrenshaKigyouDtHistory18Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory18ItemReader).writer(dumpKanrenshaKigyouDtHistory18ItemWriter).build();
    }

    /**
     * Step19を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT19)
    protected Step getStep19(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT19, jobRepository)
                .<KanrenshaKigyouDtHistory19Entity, KanrenshaKigyouDtHistory19Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory19ItemReader).writer(dumpKanrenshaKigyouDtHistory19ItemWriter).build();
    }

    /**
     * Step20を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT20)
    protected Step getStep20(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT20, jobRepository)
                .<KanrenshaKigyouDtHistory20Entity, KanrenshaKigyouDtHistory20Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory20ItemReader).writer(dumpKanrenshaKigyouDtHistory20ItemWriter).build();
    }

    /**
     * Step21を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT21)
    protected Step getStep21(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT21, jobRepository)
                .<KanrenshaKigyouDtHistory21Entity, KanrenshaKigyouDtHistory21Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory21ItemReader).writer(dumpKanrenshaKigyouDtHistory21ItemWriter).build();
    }

    /**
     * Step22を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT22)
    protected Step getStep22(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT22, jobRepository)
                .<KanrenshaKigyouDtHistory22Entity, KanrenshaKigyouDtHistory22Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory22ItemReader).writer(dumpKanrenshaKigyouDtHistory22ItemWriter).build();
    }

    /**
     * Step23を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT23)
    protected Step getStep23(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT23, jobRepository)
                .<KanrenshaKigyouDtHistory23Entity, KanrenshaKigyouDtHistory23Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory23ItemReader).writer(dumpKanrenshaKigyouDtHistory23ItemWriter).build();
    }

    /**
     * Step24を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT24)
    protected Step getStep24(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT24, jobRepository)
                .<KanrenshaKigyouDtHistory24Entity, KanrenshaKigyouDtHistory24Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory24ItemReader).writer(dumpKanrenshaKigyouDtHistory24ItemWriter).build();
    }

    /**
     * Step25を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT25)
    protected Step getStep25(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT25, jobRepository)
                .<KanrenshaKigyouDtHistory25Entity, KanrenshaKigyouDtHistory25Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory25ItemReader).writer(dumpKanrenshaKigyouDtHistory25ItemWriter).build();
    }

    /**
     * Step26を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT26)
    protected Step getStep26(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT26, jobRepository)
                .<KanrenshaKigyouDtHistory26Entity, KanrenshaKigyouDtHistory26Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory26ItemReader).writer(dumpKanrenshaKigyouDtHistory26ItemWriter).build();
    }

    /**
     * Step27を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT27)
    protected Step getStep27(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT27, jobRepository)
                .<KanrenshaKigyouDtHistory27Entity, KanrenshaKigyouDtHistory27Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory27ItemReader).writer(dumpKanrenshaKigyouDtHistory27ItemWriter).build();
    }

    /**
     * Step28を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT28)
    protected Step getStep28(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT28, jobRepository)
                .<KanrenshaKigyouDtHistory28Entity, KanrenshaKigyouDtHistory28Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory28ItemReader).writer(dumpKanrenshaKigyouDtHistory28ItemWriter).build();
    }

    /**
     * Step29を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT29)
    protected Step getStep29(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT29, jobRepository)
                .<KanrenshaKigyouDtHistory29Entity, KanrenshaKigyouDtHistory29Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory29ItemReader).writer(dumpKanrenshaKigyouDtHistory29ItemWriter).build();
    }

    /**
     * Step30を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT30)
    protected Step getStep30(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT30, jobRepository)
                .<KanrenshaKigyouDtHistory30Entity, KanrenshaKigyouDtHistory30Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory30ItemReader).writer(dumpKanrenshaKigyouDtHistory30ItemWriter).build();
    }

    /**
     * Step31を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT31)
    protected Step getStep31(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT31, jobRepository)
                .<KanrenshaKigyouDtHistory31Entity, KanrenshaKigyouDtHistory31Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory31ItemReader).writer(dumpKanrenshaKigyouDtHistory31ItemWriter).build();
    }

    /**
     * Step32を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT32)
    protected Step getStep32(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT32, jobRepository)
                .<KanrenshaKigyouDtHistory32Entity, KanrenshaKigyouDtHistory32Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory32ItemReader).writer(dumpKanrenshaKigyouDtHistory32ItemWriter).build();
    }

    /**
     * Step33を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT33)
    protected Step getStep33(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT33, jobRepository)
                .<KanrenshaKigyouDtHistory33Entity, KanrenshaKigyouDtHistory33Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory33ItemReader).writer(dumpKanrenshaKigyouDtHistory33ItemWriter).build();
    }

    /**
     * Step34を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT34)
    protected Step getStep34(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT34, jobRepository)
                .<KanrenshaKigyouDtHistory34Entity, KanrenshaKigyouDtHistory34Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory34ItemReader).writer(dumpKanrenshaKigyouDtHistory34ItemWriter).build();
    }

    /**
     * Step35を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT35)
    protected Step getStep35(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT35, jobRepository)
                .<KanrenshaKigyouDtHistory35Entity, KanrenshaKigyouDtHistory35Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory35ItemReader).writer(dumpKanrenshaKigyouDtHistory35ItemWriter).build();
    }

    /**
     * Step36を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT36)
    protected Step getStep36(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT36, jobRepository)
                .<KanrenshaKigyouDtHistory36Entity, KanrenshaKigyouDtHistory36Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory36ItemReader).writer(dumpKanrenshaKigyouDtHistory36ItemWriter).build();
    }

    /**
     * Step37を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT37)
    protected Step getStep37(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT37, jobRepository)
                .<KanrenshaKigyouDtHistory37Entity, KanrenshaKigyouDtHistory37Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory37ItemReader).writer(dumpKanrenshaKigyouDtHistory37ItemWriter).build();
    }

    /**
     * Step38を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT38)
    protected Step getStep38(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT38, jobRepository)
                .<KanrenshaKigyouDtHistory38Entity, KanrenshaKigyouDtHistory38Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory38ItemReader).writer(dumpKanrenshaKigyouDtHistory38ItemWriter).build();
    }

    /**
     * Step39を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT39)
    protected Step getStep39(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT39, jobRepository)
                .<KanrenshaKigyouDtHistory39Entity, KanrenshaKigyouDtHistory39Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory39ItemReader).writer(dumpKanrenshaKigyouDtHistory39ItemWriter).build();
    }

    /**
     * Step40を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT40)
    protected Step getStep40(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT40, jobRepository)
                .<KanrenshaKigyouDtHistory40Entity, KanrenshaKigyouDtHistory40Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory40ItemReader).writer(dumpKanrenshaKigyouDtHistory40ItemWriter).build();
    }

    /**
     * Step41を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT41)
    protected Step getStep41(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT41, jobRepository)
                .<KanrenshaKigyouDtHistory41Entity, KanrenshaKigyouDtHistory41Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory41ItemReader).writer(dumpKanrenshaKigyouDtHistory41ItemWriter).build();
    }

    /**
     * Step42を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT42)
    protected Step getStep42(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT42, jobRepository)
                .<KanrenshaKigyouDtHistory42Entity, KanrenshaKigyouDtHistory42Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory42ItemReader).writer(dumpKanrenshaKigyouDtHistory42ItemWriter).build();
    }

    /**
     * Step43を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT43)
    protected Step getStep43(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT43, jobRepository)
                .<KanrenshaKigyouDtHistory43Entity, KanrenshaKigyouDtHistory43Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory43ItemReader).writer(dumpKanrenshaKigyouDtHistory43ItemWriter).build();
    }

    /**
     * Step44を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT44)
    protected Step getStep44(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT44, jobRepository)
                .<KanrenshaKigyouDtHistory44Entity, KanrenshaKigyouDtHistory44Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory44ItemReader).writer(dumpKanrenshaKigyouDtHistory44ItemWriter).build();
    }

    /**
     * Step45を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT45)
    protected Step getStep45(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT45, jobRepository)
                .<KanrenshaKigyouDtHistory45Entity, KanrenshaKigyouDtHistory45Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory45ItemReader).writer(dumpKanrenshaKigyouDtHistory45ItemWriter).build();
    }

    /**
     * Step46を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT46)
    protected Step getStep46(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT46, jobRepository)
                .<KanrenshaKigyouDtHistory46Entity, KanrenshaKigyouDtHistory46Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory46ItemReader).writer(dumpKanrenshaKigyouDtHistory46ItemWriter).build();
    }

    /**
     * Step47を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT47)
    protected Step getStep47(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT47, jobRepository)
                .<KanrenshaKigyouDtHistory47Entity, KanrenshaKigyouDtHistory47Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory47ItemReader).writer(dumpKanrenshaKigyouDtHistory47ItemWriter).build();
    }

    /**
     * Step99を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT99)
    protected Step getStep99(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT99, jobRepository)
                .<KanrenshaKigyouDtHistory99Entity, KanrenshaKigyouDtHistory99Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaKigyouDtHistory99ItemReader).writer(dumpKanrenshaKigyouDtHistory99ItemWriter).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_COMPRESS)
    protected Step getStepCompress(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_COMPRESS, jobRepository).tasklet(compressZipFileTasklet, transactionManager)
                .build();
    }

}
