package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.sabun.history; // NOPMD

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

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory01ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory02ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory03ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory04ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory05ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory06ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory07ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory08ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory09ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory10ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory11ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory12ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory13ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory14ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory15ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory16ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory17ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory18ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory19ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory20ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory21ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory22ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory23ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory24ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory25ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory26ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory27ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory28ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory29ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory30ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory31ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory32ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory33ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory34ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory35ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory36ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory37ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory38ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory39ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory40ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory41ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory42ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory43ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory44ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory45ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory46ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory47ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history.DumpKanrenshaSeijidantaiHistory99ItemWriter;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.zip.CompressZipFileTasklet;
//import net.seijishikin.jp.normalize.manage.kanrensha.batch.zip.CompressZipFileTasklet;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory02Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory03Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory04Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory05Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory06Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory07Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory08Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory09Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory10Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory11Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory12Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory14Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory15Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory16Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory17Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory18Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory19Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory20Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory21Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory22Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory23Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory24Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory25Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory26Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory27Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory28Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory29Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory30Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory31Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory32Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory33Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory34Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory35Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory36Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory37Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory38Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory39Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory40Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory41Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory42Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory43Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory44Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory46Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory47Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory99Entity;

/**
 * 関連者政治団体履歴差分csv作成BatchConfiguration
 */
@Configuration
public class DumpSabunKanrenshaSeijidantaiHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "dumpSabunKanrenshaSeijidantaiHistory";

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

    /** 関連者政治団体履歴(01)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory01ItemReader dumpSabunKanrenshaSeijidantaiHistory01ItemReader;
    /** 関連者政治団体履歴(02)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory02ItemReader dumpSabunKanrenshaSeijidantaiHistory02ItemReader;
    /** 関連者政治団体履歴(03)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory03ItemReader dumpSabunKanrenshaSeijidantaiHistory03ItemReader;
    /** 関連者政治団体履歴(04)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory04ItemReader dumpSabunKanrenshaSeijidantaiHistory04ItemReader;
    /** 関連者政治団体履歴(05)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory05ItemReader dumpSabunKanrenshaSeijidantaiHistory05ItemReader;
    /** 関連者政治団体履歴(06)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory06ItemReader dumpSabunKanrenshaSeijidantaiHistory06ItemReader;
    /** 関連者政治団体履歴(07)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory07ItemReader dumpSabunKanrenshaSeijidantaiHistory07ItemReader;
    /** 関連者政治団体履歴(08)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory08ItemReader dumpSabunKanrenshaSeijidantaiHistory08ItemReader;
    /** 関連者政治団体履歴(09)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory09ItemReader dumpSabunKanrenshaSeijidantaiHistory09ItemReader;
    /** 関連者政治団体履歴(10)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory10ItemReader dumpSabunKanrenshaSeijidantaiHistory10ItemReader;
    /** 関連者政治団体履歴(11)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory11ItemReader dumpSabunKanrenshaSeijidantaiHistory11ItemReader;
    /** 関連者政治団体履歴(12)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory12ItemReader dumpSabunKanrenshaSeijidantaiHistory12ItemReader;
    /** 関連者政治団体履歴(13)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory13ItemReader dumpSabunKanrenshaSeijidantaiHistory13ItemReader;
    /** 関連者政治団体履歴(14)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory14ItemReader dumpSabunKanrenshaSeijidantaiHistory14ItemReader;
    /** 関連者政治団体履歴(15)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory15ItemReader dumpSabunKanrenshaSeijidantaiHistory15ItemReader;
    /** 関連者政治団体履歴(16)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory16ItemReader dumpSabunKanrenshaSeijidantaiHistory16ItemReader;
    /** 関連者政治団体履歴(17)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory17ItemReader dumpSabunKanrenshaSeijidantaiHistory17ItemReader;
    /** 関連者政治団体履歴(18)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory18ItemReader dumpSabunKanrenshaSeijidantaiHistory18ItemReader;
    /** 関連者政治団体履歴(19)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory19ItemReader dumpSabunKanrenshaSeijidantaiHistory19ItemReader;
    /** 関連者政治団体履歴(20)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory20ItemReader dumpSabunKanrenshaSeijidantaiHistory20ItemReader;
    /** 関連者政治団体履歴(21)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory21ItemReader dumpSabunKanrenshaSeijidantaiHistory21ItemReader;
    /** 関連者政治団体履歴(22)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory22ItemReader dumpSabunKanrenshaSeijidantaiHistory22ItemReader;
    /** 関連者政治団体履歴(23)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory23ItemReader dumpSabunKanrenshaSeijidantaiHistory23ItemReader;
    /** 関連者政治団体履歴(24)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory24ItemReader dumpSabunKanrenshaSeijidantaiHistory24ItemReader;
    /** 関連者政治団体履歴(25)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory25ItemReader dumpSabunKanrenshaSeijidantaiHistory25ItemReader;
    /** 関連者政治団体履歴(26)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory26ItemReader dumpSabunKanrenshaSeijidantaiHistory26ItemReader;
    /** 関連者政治団体履歴(27)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory27ItemReader dumpSabunKanrenshaSeijidantaiHistory27ItemReader;
    /** 関連者政治団体履歴(28)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory28ItemReader dumpSabunKanrenshaSeijidantaiHistory28ItemReader;
    /** 関連者政治団体履歴(29)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory29ItemReader dumpSabunKanrenshaSeijidantaiHistory29ItemReader;
    /** 関連者政治団体履歴(30)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory30ItemReader dumpSabunKanrenshaSeijidantaiHistory30ItemReader;
    /** 関連者政治団体履歴(31)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory31ItemReader dumpSabunKanrenshaSeijidantaiHistory31ItemReader;
    /** 関連者政治団体履歴(32)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory32ItemReader dumpSabunKanrenshaSeijidantaiHistory32ItemReader;
    /** 関連者政治団体履歴(33)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory33ItemReader dumpSabunKanrenshaSeijidantaiHistory33ItemReader;
    /** 関連者政治団体履歴(34)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory34ItemReader dumpSabunKanrenshaSeijidantaiHistory34ItemReader;
    /** 関連者政治団体履歴(35)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory35ItemReader dumpSabunKanrenshaSeijidantaiHistory35ItemReader;
    /** 関連者政治団体履歴(36)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory36ItemReader dumpSabunKanrenshaSeijidantaiHistory36ItemReader;
    /** 関連者政治団体履歴(37)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory37ItemReader dumpSabunKanrenshaSeijidantaiHistory37ItemReader;
    /** 関連者政治団体履歴(38)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory38ItemReader dumpSabunKanrenshaSeijidantaiHistory38ItemReader;
    /** 関連者政治団体履歴(39)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory39ItemReader dumpSabunKanrenshaSeijidantaiHistory39ItemReader;
    /** 関連者政治団体履歴(40)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory40ItemReader dumpSabunKanrenshaSeijidantaiHistory40ItemReader;
    /** 関連者政治団体履歴(41)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory41ItemReader dumpSabunKanrenshaSeijidantaiHistory41ItemReader;
    /** 関連者政治団体履歴(42)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory42ItemReader dumpSabunKanrenshaSeijidantaiHistory42ItemReader;
    /** 関連者政治団体履歴(43)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory43ItemReader dumpSabunKanrenshaSeijidantaiHistory43ItemReader;
    /** 関連者政治団体履歴(44)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory44ItemReader dumpSabunKanrenshaSeijidantaiHistory44ItemReader;
    /** 関連者政治団体履歴(45)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory45ItemReader dumpSabunKanrenshaSeijidantaiHistory45ItemReader;
    /** 関連者政治団体履歴(46)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory46ItemReader dumpSabunKanrenshaSeijidantaiHistory46ItemReader;
    /** 関連者政治団体履歴(47)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory47ItemReader dumpSabunKanrenshaSeijidantaiHistory47ItemReader;
    /** 関連者政治団体履歴(99)CsvItemReader */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory99ItemReader dumpSabunKanrenshaSeijidantaiHistory99ItemReader;
    /** 関連者政治団体履歴(01)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory01ItemWriter dumpKanrenshaSeijidantaiHistory01ItemWriter;
    /** 関連者政治団体履歴(02)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory02ItemWriter dumpKanrenshaSeijidantaiHistory02ItemWriter;
    /** 関連者政治団体履歴(03)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory03ItemWriter dumpKanrenshaSeijidantaiHistory03ItemWriter;
    /** 関連者政治団体履歴(04)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory04ItemWriter dumpKanrenshaSeijidantaiHistory04ItemWriter;
    /** 関連者政治団体履歴(05)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory05ItemWriter dumpKanrenshaSeijidantaiHistory05ItemWriter;
    /** 関連者政治団体履歴(06)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory06ItemWriter dumpKanrenshaSeijidantaiHistory06ItemWriter;
    /** 関連者政治団体履歴(07)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory07ItemWriter dumpKanrenshaSeijidantaiHistory07ItemWriter;
    /** 関連者政治団体履歴(08)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory08ItemWriter dumpKanrenshaSeijidantaiHistory08ItemWriter;
    /** 関連者政治団体履歴(09)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory09ItemWriter dumpKanrenshaSeijidantaiHistory09ItemWriter;
    /** 関連者政治団体履歴(10)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory10ItemWriter dumpKanrenshaSeijidantaiHistory10ItemWriter;
    /** 関連者政治団体履歴(11)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory11ItemWriter dumpKanrenshaSeijidantaiHistory11ItemWriter;
    /** 関連者政治団体履歴(12)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory12ItemWriter dumpKanrenshaSeijidantaiHistory12ItemWriter;
    /** 関連者政治団体履歴(13)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory13ItemWriter dumpKanrenshaSeijidantaiHistory13ItemWriter;
    /** 関連者政治団体履歴(14)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory14ItemWriter dumpKanrenshaSeijidantaiHistory14ItemWriter;
    /** 関連者政治団体履歴(15)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory15ItemWriter dumpKanrenshaSeijidantaiHistory15ItemWriter;
    /** 関連者政治団体履歴(16)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory16ItemWriter dumpKanrenshaSeijidantaiHistory16ItemWriter;
    /** 関連者政治団体履歴(17)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory17ItemWriter dumpKanrenshaSeijidantaiHistory17ItemWriter;
    /** 関連者政治団体履歴(18)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory18ItemWriter dumpKanrenshaSeijidantaiHistory18ItemWriter;
    /** 関連者政治団体履歴(19)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory19ItemWriter dumpKanrenshaSeijidantaiHistory19ItemWriter;
    /** 関連者政治団体履歴(20)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory20ItemWriter dumpKanrenshaSeijidantaiHistory20ItemWriter;
    /** 関連者政治団体履歴(21)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory21ItemWriter dumpKanrenshaSeijidantaiHistory21ItemWriter;
    /** 関連者政治団体履歴(22)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory22ItemWriter dumpKanrenshaSeijidantaiHistory22ItemWriter;
    /** 関連者政治団体履歴(23)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory23ItemWriter dumpKanrenshaSeijidantaiHistory23ItemWriter;
    /** 関連者政治団体履歴(24)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory24ItemWriter dumpKanrenshaSeijidantaiHistory24ItemWriter;
    /** 関連者政治団体履歴(25)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory25ItemWriter dumpKanrenshaSeijidantaiHistory25ItemWriter;
    /** 関連者政治団体履歴(26)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory26ItemWriter dumpKanrenshaSeijidantaiHistory26ItemWriter;
    /** 関連者政治団体履歴(27)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory27ItemWriter dumpKanrenshaSeijidantaiHistory27ItemWriter;
    /** 関連者政治団体履歴(28)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory28ItemWriter dumpKanrenshaSeijidantaiHistory28ItemWriter;
    /** 関連者政治団体履歴(29)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory29ItemWriter dumpKanrenshaSeijidantaiHistory29ItemWriter;
    /** 関連者政治団体履歴(30)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory30ItemWriter dumpKanrenshaSeijidantaiHistory30ItemWriter;
    /** 関連者政治団体履歴(31)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory31ItemWriter dumpKanrenshaSeijidantaiHistory31ItemWriter;
    /** 関連者政治団体履歴(32)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory32ItemWriter dumpKanrenshaSeijidantaiHistory32ItemWriter;
    /** 関連者政治団体履歴(33)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory33ItemWriter dumpKanrenshaSeijidantaiHistory33ItemWriter;
    /** 関連者政治団体履歴(34)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory34ItemWriter dumpKanrenshaSeijidantaiHistory34ItemWriter;
    /** 関連者政治団体履歴(35)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory35ItemWriter dumpKanrenshaSeijidantaiHistory35ItemWriter;
    /** 関連者政治団体履歴(36)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory36ItemWriter dumpKanrenshaSeijidantaiHistory36ItemWriter;
    /** 関連者政治団体履歴(37)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory37ItemWriter dumpKanrenshaSeijidantaiHistory37ItemWriter;
    /** 関連者政治団体履歴(38)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory38ItemWriter dumpKanrenshaSeijidantaiHistory38ItemWriter;
    /** 関連者政治団体履歴(39)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory39ItemWriter dumpKanrenshaSeijidantaiHistory39ItemWriter;
    /** 関連者政治団体履歴(40)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory40ItemWriter dumpKanrenshaSeijidantaiHistory40ItemWriter;
    /** 関連者政治団体履歴(41)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory41ItemWriter dumpKanrenshaSeijidantaiHistory41ItemWriter;
    /** 関連者政治団体履歴(42)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory42ItemWriter dumpKanrenshaSeijidantaiHistory42ItemWriter;
    /** 関連者政治団体履歴(43)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory43ItemWriter dumpKanrenshaSeijidantaiHistory43ItemWriter;
    /** 関連者政治団体履歴(44)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory44ItemWriter dumpKanrenshaSeijidantaiHistory44ItemWriter;
    /** 関連者政治団体履歴(45)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory45ItemWriter dumpKanrenshaSeijidantaiHistory45ItemWriter;
    /** 関連者政治団体履歴(46)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory46ItemWriter dumpKanrenshaSeijidantaiHistory46ItemWriter;
    /** 関連者政治団体履歴(47)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory47ItemWriter dumpKanrenshaSeijidantaiHistory47ItemWriter;
    /** 関連者政治団体履歴(99)ItemWriter */
    @Autowired
    private DumpKanrenshaSeijidantaiHistory99ItemWriter dumpKanrenshaSeijidantaiHistory99ItemWriter;

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
                .<KanrenshaSeijidantaiHistory01Entity, KanrenshaSeijidantaiHistory01Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory01ItemReader).writer(dumpKanrenshaSeijidantaiHistory01ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory02Entity, KanrenshaSeijidantaiHistory02Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory02ItemReader).writer(dumpKanrenshaSeijidantaiHistory02ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory03Entity, KanrenshaSeijidantaiHistory03Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory03ItemReader).writer(dumpKanrenshaSeijidantaiHistory03ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory04Entity, KanrenshaSeijidantaiHistory04Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory04ItemReader).writer(dumpKanrenshaSeijidantaiHistory04ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory05Entity, KanrenshaSeijidantaiHistory05Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory05ItemReader).writer(dumpKanrenshaSeijidantaiHistory05ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory06Entity, KanrenshaSeijidantaiHistory06Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory06ItemReader).writer(dumpKanrenshaSeijidantaiHistory06ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory07Entity, KanrenshaSeijidantaiHistory07Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory07ItemReader).writer(dumpKanrenshaSeijidantaiHistory07ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory08Entity, KanrenshaSeijidantaiHistory08Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory08ItemReader).writer(dumpKanrenshaSeijidantaiHistory08ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory09Entity, KanrenshaSeijidantaiHistory09Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory09ItemReader).writer(dumpKanrenshaSeijidantaiHistory09ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory10Entity, KanrenshaSeijidantaiHistory10Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory10ItemReader).writer(dumpKanrenshaSeijidantaiHistory10ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory11Entity, KanrenshaSeijidantaiHistory11Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory11ItemReader).writer(dumpKanrenshaSeijidantaiHistory11ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory12Entity, KanrenshaSeijidantaiHistory12Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory12ItemReader).writer(dumpKanrenshaSeijidantaiHistory12ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory13Entity, KanrenshaSeijidantaiHistory13Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory13ItemReader).writer(dumpKanrenshaSeijidantaiHistory13ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory14Entity, KanrenshaSeijidantaiHistory14Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory14ItemReader).writer(dumpKanrenshaSeijidantaiHistory14ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory15Entity, KanrenshaSeijidantaiHistory15Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory15ItemReader).writer(dumpKanrenshaSeijidantaiHistory15ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory16Entity, KanrenshaSeijidantaiHistory16Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory16ItemReader).writer(dumpKanrenshaSeijidantaiHistory16ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory17Entity, KanrenshaSeijidantaiHistory17Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory17ItemReader).writer(dumpKanrenshaSeijidantaiHistory17ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory18Entity, KanrenshaSeijidantaiHistory18Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory18ItemReader).writer(dumpKanrenshaSeijidantaiHistory18ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory19Entity, KanrenshaSeijidantaiHistory19Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory19ItemReader).writer(dumpKanrenshaSeijidantaiHistory19ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory20Entity, KanrenshaSeijidantaiHistory20Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory20ItemReader).writer(dumpKanrenshaSeijidantaiHistory20ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory21Entity, KanrenshaSeijidantaiHistory21Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory21ItemReader).writer(dumpKanrenshaSeijidantaiHistory21ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory22Entity, KanrenshaSeijidantaiHistory22Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory22ItemReader).writer(dumpKanrenshaSeijidantaiHistory22ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory23Entity, KanrenshaSeijidantaiHistory23Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory23ItemReader).writer(dumpKanrenshaSeijidantaiHistory23ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory24Entity, KanrenshaSeijidantaiHistory24Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory24ItemReader).writer(dumpKanrenshaSeijidantaiHistory24ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory25Entity, KanrenshaSeijidantaiHistory25Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory25ItemReader).writer(dumpKanrenshaSeijidantaiHistory25ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory26Entity, KanrenshaSeijidantaiHistory26Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory26ItemReader).writer(dumpKanrenshaSeijidantaiHistory26ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory27Entity, KanrenshaSeijidantaiHistory27Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory27ItemReader).writer(dumpKanrenshaSeijidantaiHistory27ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory28Entity, KanrenshaSeijidantaiHistory28Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory28ItemReader).writer(dumpKanrenshaSeijidantaiHistory28ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory29Entity, KanrenshaSeijidantaiHistory29Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory29ItemReader).writer(dumpKanrenshaSeijidantaiHistory29ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory30Entity, KanrenshaSeijidantaiHistory30Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory30ItemReader).writer(dumpKanrenshaSeijidantaiHistory30ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory31Entity, KanrenshaSeijidantaiHistory31Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory31ItemReader).writer(dumpKanrenshaSeijidantaiHistory31ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory32Entity, KanrenshaSeijidantaiHistory32Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory32ItemReader).writer(dumpKanrenshaSeijidantaiHistory32ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory33Entity, KanrenshaSeijidantaiHistory33Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory33ItemReader).writer(dumpKanrenshaSeijidantaiHistory33ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory34Entity, KanrenshaSeijidantaiHistory34Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory34ItemReader).writer(dumpKanrenshaSeijidantaiHistory34ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory35Entity, KanrenshaSeijidantaiHistory35Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory35ItemReader).writer(dumpKanrenshaSeijidantaiHistory35ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory36Entity, KanrenshaSeijidantaiHistory36Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory36ItemReader).writer(dumpKanrenshaSeijidantaiHistory36ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory37Entity, KanrenshaSeijidantaiHistory37Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory37ItemReader).writer(dumpKanrenshaSeijidantaiHistory37ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory38Entity, KanrenshaSeijidantaiHistory38Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory38ItemReader).writer(dumpKanrenshaSeijidantaiHistory38ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory39Entity, KanrenshaSeijidantaiHistory39Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory39ItemReader).writer(dumpKanrenshaSeijidantaiHistory39ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory40Entity, KanrenshaSeijidantaiHistory40Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory40ItemReader).writer(dumpKanrenshaSeijidantaiHistory40ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory41Entity, KanrenshaSeijidantaiHistory41Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory41ItemReader).writer(dumpKanrenshaSeijidantaiHistory41ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory42Entity, KanrenshaSeijidantaiHistory42Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory42ItemReader).writer(dumpKanrenshaSeijidantaiHistory42ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory43Entity, KanrenshaSeijidantaiHistory43Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory43ItemReader).writer(dumpKanrenshaSeijidantaiHistory43ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory44Entity, KanrenshaSeijidantaiHistory44Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory44ItemReader).writer(dumpKanrenshaSeijidantaiHistory44ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory45Entity, KanrenshaSeijidantaiHistory45Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory45ItemReader).writer(dumpKanrenshaSeijidantaiHistory45ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory46Entity, KanrenshaSeijidantaiHistory46Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory46ItemReader).writer(dumpKanrenshaSeijidantaiHistory46ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory47Entity, KanrenshaSeijidantaiHistory47Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory47ItemReader).writer(dumpKanrenshaSeijidantaiHistory47ItemWriter).build();
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
                .<KanrenshaSeijidantaiHistory99Entity, KanrenshaSeijidantaiHistory99Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunKanrenshaSeijidantaiHistory99ItemReader).writer(dumpKanrenshaSeijidantaiHistory99ItemWriter).build();
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
