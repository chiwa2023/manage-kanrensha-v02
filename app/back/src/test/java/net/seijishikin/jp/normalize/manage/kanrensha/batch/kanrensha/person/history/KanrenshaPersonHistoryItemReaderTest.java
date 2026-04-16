package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

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
 * KanrenshaPersonHistoryItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaPersonHistoryItemReaderTest.sql")
class KanrenshaPersonHistoryItemReaderTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonHistoryItemReader kanrenshaPersonHistoryItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat();
        kanrenshaPersonHistoryItemReader.beforeStep(stepExecution);
        kanrenshaPersonHistoryItemReader.open(stepExecution.getExecutionContext());

        KanrenshaPersonHistoryDto dto01 = kanrenshaPersonHistoryItemReader.read();
        // "迂回献金 太郎","和歌山県架空市実在町","経営者","1-2345-ABCCDEF"
        assertEquals("迂回献金　太郎", dto01.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("経営者", dto01.getPersonShokugyou());
        assertEquals("1-2345-ABCCDEF", dto01.getPersonKanrenshaCode());

        KanrenshaPersonHistoryDto dto02 = kanrenshaPersonHistoryItemReader.read();
        // "迂回献金 太郎","和歌山県架空市実在町","経営者","1-2345-ABCCDEF"
        assertEquals("迂回献金　太郎", dto02.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("経営者", dto02.getPersonShokugyou());
        assertEquals("1-2345-ABCCDEF", dto02.getPersonKanrenshaCode());

        KanrenshaPersonHistoryDto dto03 = kanrenshaPersonHistoryItemReader.read();
        // "迂回献金 次郎","宮崎県架空市実在町","教師","2-345657-QWERTY"
        assertEquals("迂回献金　次郎", dto03.getKanrenshaName());
        assertEquals("宮崎県架空市実在町", dto03.getAllAddress());
        assertEquals("教師", dto03.getPersonShokugyou());
        assertEquals("2-345657-QWERTY", dto03.getPersonKanrenshaCode());

        KanrenshaPersonHistoryDto dto04 = kanrenshaPersonHistoryItemReader.read();
        // "寄付上限 花子","山形県架空市実在町","医師","1-233-44-55"
        assertEquals("寄付上限　花子", dto04.getKanrenshaName());
        assertEquals("山形県架空市実在町", dto04.getAllAddress());
        assertEquals("医師", dto04.getPersonShokugyou());
        assertEquals("1-233-44-55", dto04.getPersonKanrenshaCode());

        KanrenshaPersonHistoryDto dto05 = kanrenshaPersonHistoryItemReader.read();
        // "寄付上限 直子","山梨県架空市実在町","弁護士","9-988-77-66"
        assertEquals("寄付上限　直子", dto05.getKanrenshaName());
        assertEquals("山梨県架空市実在町", dto05.getAllAddress());
        assertEquals("弁護士", dto05.getPersonShokugyou());
        assertEquals("9-988-77-66", dto05.getPersonKanrenshaCode());

        assertNull(kanrenshaPersonHistoryItemReader.read());
    }

    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者個人履歴.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
