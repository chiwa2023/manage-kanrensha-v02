package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * KanrenshaSeijidantaiAddMiniCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaSeijidantaiAddMiniCsvItemReaderTest.sql")
class KanrenshaSeijidantaiAddMiniCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiAddMiniCsvItemReader kanrenshaSeijidantaiAddMiniCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat();
        kanrenshaSeijidantaiAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaSeijidantaiAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        KanrenshaSeijidantaiAddMiniDto dto01 = kanrenshaSeijidantaiAddMiniCsvItemReader.read();
        // "ちゃらんぽらん政治団体","和歌山県架空市実在町","代表者 太郎","05"
        assertEquals("ちゃらんぽらん政治団体", dto01.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getSeijidantaiDelegate());
        assertEquals("05", dto01.getDantaiKbn());

        KanrenshaSeijidantaiAddMiniDto dto02 = kanrenshaSeijidantaiAddMiniCsvItemReader.read();
        // "ちゃらんぽらん政治団体","和歌山県架空市実在町","代表者 太郎","05"
        assertEquals("ちゃらんぽらん政治団体", dto02.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getSeijidantaiDelegate());
        assertEquals("05", dto02.getDantaiKbn());

        KanrenshaSeijidantaiAddMiniDto dto03 = kanrenshaSeijidantaiAddMiniCsvItemReader.read();
        // "いいかげん政治団体","宮崎県架空市実在町","代表者 次郎","04"
        assertEquals("いいかげん政治団体", dto03.getKanrenshaName());
        assertEquals("宮崎県架空市実在町", dto03.getAllAddress());
        assertEquals("代表者　次郎", dto03.getSeijidantaiDelegate());
        assertEquals("04", dto03.getDantaiKbn());

        KanrenshaSeijidantaiAddMiniDto dto04 = kanrenshaSeijidantaiAddMiniCsvItemReader.read();
        // "政治団体A","山形県架空市実在町","代表者 三郎","03"
        assertEquals("政治団体A", dto04.getKanrenshaName());
        assertEquals("山形県架空市実在町", dto04.getAllAddress());
        assertEquals("代表者　三郎", dto04.getSeijidantaiDelegate());
        assertEquals("03", dto04.getDantaiKbn());

        KanrenshaSeijidantaiAddMiniDto dto05 = kanrenshaSeijidantaiAddMiniCsvItemReader.read();
        // "政治団体B","山梨県架空市実在町","代表者 四郎","03"
        assertEquals("政治団体B", dto05.getKanrenshaName());
        assertEquals("山梨県架空市実在町", dto05.getAllAddress());
        assertEquals("代表者　四郎", dto05.getSeijidantaiDelegate());
        assertEquals("03", dto05.getDantaiKbn());

        assertNull(kanrenshaSeijidantaiAddMiniCsvItemReader.read());
    }

    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者政治団体最小登録.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
