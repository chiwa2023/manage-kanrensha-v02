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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory02Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory03Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory04Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory05Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory06Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory07Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory08Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory09Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory10Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory11Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory12Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory14Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory15Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory16Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory17Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory18Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory19Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory20Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory21Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory22Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory23Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory24Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory25Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory26Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory27Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory28Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory29Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory30Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory31Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory32Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory33Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory34Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory35Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory36Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory37Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory38Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory39Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory40Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory41Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory42Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory43Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory44Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory46Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory47Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory99Entity;

/**
 * 関連者個人履歴csv作成BatchConfiguration
 */
@Configuration
public class DumpKanrenshaPersonHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "dumpKanrenshaPersonHistory";

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
    private DumpKanrenshaPersonHistory01ItemReader dumpKanrenshaPersonHistory01ItemReader;
    /** 関連者企業・団体履歴(02)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory02ItemReader dumpKanrenshaPersonHistory02ItemReader;
    /** 関連者企業・団体履歴(03)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory03ItemReader dumpKanrenshaPersonHistory03ItemReader;
    /** 関連者企業・団体履歴(04)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory04ItemReader dumpKanrenshaPersonHistory04ItemReader;
    /** 関連者企業・団体履歴(05)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory05ItemReader dumpKanrenshaPersonHistory05ItemReader;
    /** 関連者企業・団体履歴(06)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory06ItemReader dumpKanrenshaPersonHistory06ItemReader;
    /** 関連者企業・団体履歴(07)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory07ItemReader dumpKanrenshaPersonHistory07ItemReader;
    /** 関連者企業・団体履歴(08)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory08ItemReader dumpKanrenshaPersonHistory08ItemReader;
    /** 関連者企業・団体履歴(09)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory09ItemReader dumpKanrenshaPersonHistory09ItemReader;
    /** 関連者企業・団体履歴(10)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory10ItemReader dumpKanrenshaPersonHistory10ItemReader;
    /** 関連者企業・団体履歴(11)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory11ItemReader dumpKanrenshaPersonHistory11ItemReader;
    /** 関連者企業・団体履歴(12)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory12ItemReader dumpKanrenshaPersonHistory12ItemReader;
    /** 関連者企業・団体履歴(13)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory13ItemReader dumpKanrenshaPersonHistory13ItemReader;
    /** 関連者企業・団体履歴(14)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory14ItemReader dumpKanrenshaPersonHistory14ItemReader;
    /** 関連者企業・団体履歴(15)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory15ItemReader dumpKanrenshaPersonHistory15ItemReader;
    /** 関連者企業・団体履歴(16)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory16ItemReader dumpKanrenshaPersonHistory16ItemReader;
    /** 関連者企業・団体履歴(17)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory17ItemReader dumpKanrenshaPersonHistory17ItemReader;
    /** 関連者企業・団体履歴(18)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory18ItemReader dumpKanrenshaPersonHistory18ItemReader;
    /** 関連者企業・団体履歴(19)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory19ItemReader dumpKanrenshaPersonHistory19ItemReader;
    /** 関連者企業・団体履歴(20)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory20ItemReader dumpKanrenshaPersonHistory20ItemReader;
    /** 関連者企業・団体履歴(21)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory21ItemReader dumpKanrenshaPersonHistory21ItemReader;
    /** 関連者企業・団体履歴(22)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory22ItemReader dumpKanrenshaPersonHistory22ItemReader;
    /** 関連者企業・団体履歴(23)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory23ItemReader dumpKanrenshaPersonHistory23ItemReader;
    /** 関連者企業・団体履歴(24)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory24ItemReader dumpKanrenshaPersonHistory24ItemReader;
    /** 関連者企業・団体履歴(25)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory25ItemReader dumpKanrenshaPersonHistory25ItemReader;
    /** 関連者企業・団体履歴(26)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory26ItemReader dumpKanrenshaPersonHistory26ItemReader;
    /** 関連者企業・団体履歴(27)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory27ItemReader dumpKanrenshaPersonHistory27ItemReader;
    /** 関連者企業・団体履歴(28)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory28ItemReader dumpKanrenshaPersonHistory28ItemReader;
    /** 関連者企業・団体履歴(29)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory29ItemReader dumpKanrenshaPersonHistory29ItemReader;
    /** 関連者企業・団体履歴(30)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory30ItemReader dumpKanrenshaPersonHistory30ItemReader;
    /** 関連者企業・団体履歴(31)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory31ItemReader dumpKanrenshaPersonHistory31ItemReader;
    /** 関連者企業・団体履歴(32)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory32ItemReader dumpKanrenshaPersonHistory32ItemReader;
    /** 関連者企業・団体履歴(33)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory33ItemReader dumpKanrenshaPersonHistory33ItemReader;
    /** 関連者企業・団体履歴(34)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory34ItemReader dumpKanrenshaPersonHistory34ItemReader;
    /** 関連者企業・団体履歴(35)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory35ItemReader dumpKanrenshaPersonHistory35ItemReader;
    /** 関連者企業・団体履歴(36)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory36ItemReader dumpKanrenshaPersonHistory36ItemReader;
    /** 関連者企業・団体履歴(37)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory37ItemReader dumpKanrenshaPersonHistory37ItemReader;
    /** 関連者企業・団体履歴(38)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory38ItemReader dumpKanrenshaPersonHistory38ItemReader;
    /** 関連者企業・団体履歴(39)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory39ItemReader dumpKanrenshaPersonHistory39ItemReader;
    /** 関連者企業・団体履歴(40)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory40ItemReader dumpKanrenshaPersonHistory40ItemReader;
    /** 関連者企業・団体履歴(41)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory41ItemReader dumpKanrenshaPersonHistory41ItemReader;
    /** 関連者企業・団体履歴(42)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory42ItemReader dumpKanrenshaPersonHistory42ItemReader;
    /** 関連者企業・団体履歴(43)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory43ItemReader dumpKanrenshaPersonHistory43ItemReader;
    /** 関連者企業・団体履歴(44)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory44ItemReader dumpKanrenshaPersonHistory44ItemReader;
    /** 関連者企業・団体履歴(45)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory45ItemReader dumpKanrenshaPersonHistory45ItemReader;
    /** 関連者企業・団体履歴(46)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory46ItemReader dumpKanrenshaPersonHistory46ItemReader;
    /** 関連者企業・団体履歴(47)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory47ItemReader dumpKanrenshaPersonHistory47ItemReader;
    /** 関連者企業・団体履歴(99)CsvItemReader */
    @Autowired
    private DumpKanrenshaPersonHistory99ItemReader dumpKanrenshaPersonHistory99ItemReader;
    /** 関連者企業・団体履歴(01)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory01ItemWriter dumpKanrenshaPersonHistory01ItemWriter;
    /** 関連者企業・団体履歴(02)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory02ItemWriter dumpKanrenshaPersonHistory02ItemWriter;
    /** 関連者企業・団体履歴(03)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory03ItemWriter dumpKanrenshaPersonHistory03ItemWriter;
    /** 関連者企業・団体履歴(04)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory04ItemWriter dumpKanrenshaPersonHistory04ItemWriter;
    /** 関連者企業・団体履歴(05)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory05ItemWriter dumpKanrenshaPersonHistory05ItemWriter;
    /** 関連者企業・団体履歴(06)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory06ItemWriter dumpKanrenshaPersonHistory06ItemWriter;
    /** 関連者企業・団体履歴(07)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory07ItemWriter dumpKanrenshaPersonHistory07ItemWriter;
    /** 関連者企業・団体履歴(08)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory08ItemWriter dumpKanrenshaPersonHistory08ItemWriter;
    /** 関連者企業・団体履歴(09)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory09ItemWriter dumpKanrenshaPersonHistory09ItemWriter;
    /** 関連者企業・団体履歴(10)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory10ItemWriter dumpKanrenshaPersonHistory10ItemWriter;
    /** 関連者企業・団体履歴(11)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory11ItemWriter dumpKanrenshaPersonHistory11ItemWriter;
    /** 関連者企業・団体履歴(12)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory12ItemWriter dumpKanrenshaPersonHistory12ItemWriter;
    /** 関連者企業・団体履歴(13)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory13ItemWriter dumpKanrenshaPersonHistory13ItemWriter;
    /** 関連者企業・団体履歴(14)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory14ItemWriter dumpKanrenshaPersonHistory14ItemWriter;
    /** 関連者企業・団体履歴(15)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory15ItemWriter dumpKanrenshaPersonHistory15ItemWriter;
    /** 関連者企業・団体履歴(16)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory16ItemWriter dumpKanrenshaPersonHistory16ItemWriter;
    /** 関連者企業・団体履歴(17)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory17ItemWriter dumpKanrenshaPersonHistory17ItemWriter;
    /** 関連者企業・団体履歴(18)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory18ItemWriter dumpKanrenshaPersonHistory18ItemWriter;
    /** 関連者企業・団体履歴(19)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory19ItemWriter dumpKanrenshaPersonHistory19ItemWriter;
    /** 関連者企業・団体履歴(20)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory20ItemWriter dumpKanrenshaPersonHistory20ItemWriter;
    /** 関連者企業・団体履歴(21)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory21ItemWriter dumpKanrenshaPersonHistory21ItemWriter;
    /** 関連者企業・団体履歴(22)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory22ItemWriter dumpKanrenshaPersonHistory22ItemWriter;
    /** 関連者企業・団体履歴(23)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory23ItemWriter dumpKanrenshaPersonHistory23ItemWriter;
    /** 関連者企業・団体履歴(24)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory24ItemWriter dumpKanrenshaPersonHistory24ItemWriter;
    /** 関連者企業・団体履歴(25)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory25ItemWriter dumpKanrenshaPersonHistory25ItemWriter;
    /** 関連者企業・団体履歴(26)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory26ItemWriter dumpKanrenshaPersonHistory26ItemWriter;
    /** 関連者企業・団体履歴(27)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory27ItemWriter dumpKanrenshaPersonHistory27ItemWriter;
    /** 関連者企業・団体履歴(28)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory28ItemWriter dumpKanrenshaPersonHistory28ItemWriter;
    /** 関連者企業・団体履歴(29)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory29ItemWriter dumpKanrenshaPersonHistory29ItemWriter;
    /** 関連者企業・団体履歴(30)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory30ItemWriter dumpKanrenshaPersonHistory30ItemWriter;
    /** 関連者企業・団体履歴(31)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory31ItemWriter dumpKanrenshaPersonHistory31ItemWriter;
    /** 関連者企業・団体履歴(32)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory32ItemWriter dumpKanrenshaPersonHistory32ItemWriter;
    /** 関連者企業・団体履歴(33)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory33ItemWriter dumpKanrenshaPersonHistory33ItemWriter;
    /** 関連者企業・団体履歴(34)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory34ItemWriter dumpKanrenshaPersonHistory34ItemWriter;
    /** 関連者企業・団体履歴(35)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory35ItemWriter dumpKanrenshaPersonHistory35ItemWriter;
    /** 関連者企業・団体履歴(36)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory36ItemWriter dumpKanrenshaPersonHistory36ItemWriter;
    /** 関連者企業・団体履歴(37)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory37ItemWriter dumpKanrenshaPersonHistory37ItemWriter;
    /** 関連者企業・団体履歴(38)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory38ItemWriter dumpKanrenshaPersonHistory38ItemWriter;
    /** 関連者企業・団体履歴(39)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory39ItemWriter dumpKanrenshaPersonHistory39ItemWriter;
    /** 関連者企業・団体履歴(40)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory40ItemWriter dumpKanrenshaPersonHistory40ItemWriter;
    /** 関連者企業・団体履歴(41)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory41ItemWriter dumpKanrenshaPersonHistory41ItemWriter;
    /** 関連者企業・団体履歴(42)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory42ItemWriter dumpKanrenshaPersonHistory42ItemWriter;
    /** 関連者企業・団体履歴(43)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory43ItemWriter dumpKanrenshaPersonHistory43ItemWriter;
    /** 関連者企業・団体履歴(44)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory44ItemWriter dumpKanrenshaPersonHistory44ItemWriter;
    /** 関連者企業・団体履歴(45)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory45ItemWriter dumpKanrenshaPersonHistory45ItemWriter;
    /** 関連者企業・団体履歴(46)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory46ItemWriter dumpKanrenshaPersonHistory46ItemWriter;
    /** 関連者企業・団体履歴(47)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory47ItemWriter dumpKanrenshaPersonHistory47ItemWriter;
    /** 関連者企業・団体履歴(99)ItemWriter */
    @Autowired
    private DumpKanrenshaPersonHistory99ItemWriter dumpKanrenshaPersonHistory99ItemWriter;

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
                .<KanrenshaPersonHistory01Entity, KanrenshaPersonHistory01Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory01ItemReader).writer(dumpKanrenshaPersonHistory01ItemWriter).build();
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
                .<KanrenshaPersonHistory02Entity, KanrenshaPersonHistory02Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory02ItemReader).writer(dumpKanrenshaPersonHistory02ItemWriter).build();
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
                .<KanrenshaPersonHistory03Entity, KanrenshaPersonHistory03Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory03ItemReader).writer(dumpKanrenshaPersonHistory03ItemWriter).build();
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
                .<KanrenshaPersonHistory04Entity, KanrenshaPersonHistory04Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory04ItemReader).writer(dumpKanrenshaPersonHistory04ItemWriter).build();
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
                .<KanrenshaPersonHistory05Entity, KanrenshaPersonHistory05Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory05ItemReader).writer(dumpKanrenshaPersonHistory05ItemWriter).build();
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
                .<KanrenshaPersonHistory06Entity, KanrenshaPersonHistory06Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory06ItemReader).writer(dumpKanrenshaPersonHistory06ItemWriter).build();
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
                .<KanrenshaPersonHistory07Entity, KanrenshaPersonHistory07Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory07ItemReader).writer(dumpKanrenshaPersonHistory07ItemWriter).build();
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
                .<KanrenshaPersonHistory08Entity, KanrenshaPersonHistory08Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory08ItemReader).writer(dumpKanrenshaPersonHistory08ItemWriter).build();
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
                .<KanrenshaPersonHistory09Entity, KanrenshaPersonHistory09Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory09ItemReader).writer(dumpKanrenshaPersonHistory09ItemWriter).build();
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
                .<KanrenshaPersonHistory10Entity, KanrenshaPersonHistory10Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory10ItemReader).writer(dumpKanrenshaPersonHistory10ItemWriter).build();
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
                .<KanrenshaPersonHistory11Entity, KanrenshaPersonHistory11Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory11ItemReader).writer(dumpKanrenshaPersonHistory11ItemWriter).build();
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
                .<KanrenshaPersonHistory12Entity, KanrenshaPersonHistory12Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory12ItemReader).writer(dumpKanrenshaPersonHistory12ItemWriter).build();
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
                .<KanrenshaPersonHistory13Entity, KanrenshaPersonHistory13Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory13ItemReader).writer(dumpKanrenshaPersonHistory13ItemWriter).build();
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
                .<KanrenshaPersonHistory14Entity, KanrenshaPersonHistory14Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory14ItemReader).writer(dumpKanrenshaPersonHistory14ItemWriter).build();
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
                .<KanrenshaPersonHistory15Entity, KanrenshaPersonHistory15Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory15ItemReader).writer(dumpKanrenshaPersonHistory15ItemWriter).build();
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
                .<KanrenshaPersonHistory16Entity, KanrenshaPersonHistory16Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory16ItemReader).writer(dumpKanrenshaPersonHistory16ItemWriter).build();
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
                .<KanrenshaPersonHistory17Entity, KanrenshaPersonHistory17Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory17ItemReader).writer(dumpKanrenshaPersonHistory17ItemWriter).build();
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
                .<KanrenshaPersonHistory18Entity, KanrenshaPersonHistory18Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory18ItemReader).writer(dumpKanrenshaPersonHistory18ItemWriter).build();
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
                .<KanrenshaPersonHistory19Entity, KanrenshaPersonHistory19Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory19ItemReader).writer(dumpKanrenshaPersonHistory19ItemWriter).build();
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
                .<KanrenshaPersonHistory20Entity, KanrenshaPersonHistory20Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory20ItemReader).writer(dumpKanrenshaPersonHistory20ItemWriter).build();
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
                .<KanrenshaPersonHistory21Entity, KanrenshaPersonHistory21Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory21ItemReader).writer(dumpKanrenshaPersonHistory21ItemWriter).build();
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
                .<KanrenshaPersonHistory22Entity, KanrenshaPersonHistory22Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory22ItemReader).writer(dumpKanrenshaPersonHistory22ItemWriter).build();
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
                .<KanrenshaPersonHistory23Entity, KanrenshaPersonHistory23Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory23ItemReader).writer(dumpKanrenshaPersonHistory23ItemWriter).build();
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
                .<KanrenshaPersonHistory24Entity, KanrenshaPersonHistory24Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory24ItemReader).writer(dumpKanrenshaPersonHistory24ItemWriter).build();
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
                .<KanrenshaPersonHistory25Entity, KanrenshaPersonHistory25Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory25ItemReader).writer(dumpKanrenshaPersonHistory25ItemWriter).build();
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
                .<KanrenshaPersonHistory26Entity, KanrenshaPersonHistory26Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory26ItemReader).writer(dumpKanrenshaPersonHistory26ItemWriter).build();
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
                .<KanrenshaPersonHistory27Entity, KanrenshaPersonHistory27Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory27ItemReader).writer(dumpKanrenshaPersonHistory27ItemWriter).build();
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
                .<KanrenshaPersonHistory28Entity, KanrenshaPersonHistory28Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory28ItemReader).writer(dumpKanrenshaPersonHistory28ItemWriter).build();
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
                .<KanrenshaPersonHistory29Entity, KanrenshaPersonHistory29Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory29ItemReader).writer(dumpKanrenshaPersonHistory29ItemWriter).build();
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
                .<KanrenshaPersonHistory30Entity, KanrenshaPersonHistory30Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory30ItemReader).writer(dumpKanrenshaPersonHistory30ItemWriter).build();
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
                .<KanrenshaPersonHistory31Entity, KanrenshaPersonHistory31Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory31ItemReader).writer(dumpKanrenshaPersonHistory31ItemWriter).build();
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
                .<KanrenshaPersonHistory32Entity, KanrenshaPersonHistory32Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory32ItemReader).writer(dumpKanrenshaPersonHistory32ItemWriter).build();
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
                .<KanrenshaPersonHistory33Entity, KanrenshaPersonHistory33Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory33ItemReader).writer(dumpKanrenshaPersonHistory33ItemWriter).build();
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
                .<KanrenshaPersonHistory34Entity, KanrenshaPersonHistory34Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory34ItemReader).writer(dumpKanrenshaPersonHistory34ItemWriter).build();
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
                .<KanrenshaPersonHistory35Entity, KanrenshaPersonHistory35Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory35ItemReader).writer(dumpKanrenshaPersonHistory35ItemWriter).build();
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
                .<KanrenshaPersonHistory36Entity, KanrenshaPersonHistory36Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory36ItemReader).writer(dumpKanrenshaPersonHistory36ItemWriter).build();
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
                .<KanrenshaPersonHistory37Entity, KanrenshaPersonHistory37Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory37ItemReader).writer(dumpKanrenshaPersonHistory37ItemWriter).build();
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
                .<KanrenshaPersonHistory38Entity, KanrenshaPersonHistory38Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory38ItemReader).writer(dumpKanrenshaPersonHistory38ItemWriter).build();
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
                .<KanrenshaPersonHistory39Entity, KanrenshaPersonHistory39Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory39ItemReader).writer(dumpKanrenshaPersonHistory39ItemWriter).build();
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
                .<KanrenshaPersonHistory40Entity, KanrenshaPersonHistory40Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory40ItemReader).writer(dumpKanrenshaPersonHistory40ItemWriter).build();
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
                .<KanrenshaPersonHistory41Entity, KanrenshaPersonHistory41Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory41ItemReader).writer(dumpKanrenshaPersonHistory41ItemWriter).build();
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
                .<KanrenshaPersonHistory42Entity, KanrenshaPersonHistory42Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory42ItemReader).writer(dumpKanrenshaPersonHistory42ItemWriter).build();
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
                .<KanrenshaPersonHistory43Entity, KanrenshaPersonHistory43Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory43ItemReader).writer(dumpKanrenshaPersonHistory43ItemWriter).build();
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
                .<KanrenshaPersonHistory44Entity, KanrenshaPersonHistory44Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory44ItemReader).writer(dumpKanrenshaPersonHistory44ItemWriter).build();
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
                .<KanrenshaPersonHistory45Entity, KanrenshaPersonHistory45Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory45ItemReader).writer(dumpKanrenshaPersonHistory45ItemWriter).build();
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
                .<KanrenshaPersonHistory46Entity, KanrenshaPersonHistory46Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory46ItemReader).writer(dumpKanrenshaPersonHistory46ItemWriter).build();
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
                .<KanrenshaPersonHistory47Entity, KanrenshaPersonHistory47Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory47ItemReader).writer(dumpKanrenshaPersonHistory47ItemWriter).build();
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
                .<KanrenshaPersonHistory99Entity, KanrenshaPersonHistory99Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpKanrenshaPersonHistory99ItemReader).writer(dumpKanrenshaPersonHistory99ItemWriter).build();
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
