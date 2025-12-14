package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

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
 * KanrenshaPersonAddMiniCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaPersonAddMiniCsvItemReaderTest.sql")
class KanrenshaPersonAddMiniCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonAddMiniCsvItemReader kanrenshaPersonAddMiniCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat();
        kanrenshaPersonAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaPersonAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        KanrenshaPersonAddMiniDto dto01 = kanrenshaPersonAddMiniCsvItemReader.read();
        // "迂回献金 太郎","和歌山県架空市実在町","経営者"
        assertEquals("迂回献金　太郎", dto01.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("経営者", dto01.getPersonShokugyou());

        KanrenshaPersonAddMiniDto dto02 = kanrenshaPersonAddMiniCsvItemReader.read();
        // "迂回献金 太郎","和歌山県架空市実在町","経営者"
        assertEquals("迂回献金　太郎", dto02.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("経営者", dto02.getPersonShokugyou());

        KanrenshaPersonAddMiniDto dto03 = kanrenshaPersonAddMiniCsvItemReader.read();
        // "迂回献金 次郎","宮崎県架空市実在町","教師"
        assertEquals("迂回献金　次郎", dto03.getKanrenshaName());
        assertEquals("宮崎県架空市実在町", dto03.getAllAddress());
        assertEquals("教師", dto03.getPersonShokugyou());

        KanrenshaPersonAddMiniDto dto04 = kanrenshaPersonAddMiniCsvItemReader.read();
        // "寄付上限 花子","山形県架空市実在町","医師"
        assertEquals("寄付上限　花子", dto04.getKanrenshaName());
        assertEquals("山形県架空市実在町", dto04.getAllAddress());
        assertEquals("医師", dto04.getPersonShokugyou());

        KanrenshaPersonAddMiniDto dto05 = kanrenshaPersonAddMiniCsvItemReader.read();
        // "寄付上限 直子","山梨県架空市実在町","弁護士"
        assertEquals("寄付上限　直子", dto05.getKanrenshaName());
        assertEquals("山梨県架空市実在町", dto05.getAllAddress());
        assertEquals("弁護士", dto05.getPersonShokugyou());

        assertNull(kanrenshaPersonAddMiniCsvItemReader.read());
    }

    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者個人最小登録.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
