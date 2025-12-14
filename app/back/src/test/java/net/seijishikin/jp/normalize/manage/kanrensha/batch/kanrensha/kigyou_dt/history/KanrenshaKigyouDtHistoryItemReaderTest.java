package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

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
 * KanrenshaKigyouDtHistoryItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtHistoryItemReaderTest.sql")
class KanrenshaKigyouDtHistoryItemReaderTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtHistoryItemReader kanrenshaKigyouDtHistoryItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat();
        kanrenshaKigyouDtHistoryItemReader.beforeStep(stepExecution);
        kanrenshaKigyouDtHistoryItemReader.open(stepExecution.getExecutionContext());

        KanrenshaKigyouDtHistoryDto dto01 = kanrenshaKigyouDtHistoryItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","12-345-ABCCDEF","8-7654-3"
        assertEquals("ぼったくり企業", dto01.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getKigyouDtDelegate());
        assertEquals("12-345-ABCCDEF", dto01.getKigyouDtKanrenshaCode());
        assertEquals("8-7654-1", dto01.getOrgDelegateCode());

        KanrenshaKigyouDtHistoryDto dto02 = kanrenshaKigyouDtHistoryItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","12-345-ABCCDEF","8-7654-3"
        assertEquals("ぼったくり企業", dto02.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getKigyouDtDelegate());
        assertEquals("12-345-ABCCDEF", dto02.getKigyouDtKanrenshaCode());
        assertEquals("8-7654-2", dto02.getOrgDelegateCode());

        KanrenshaKigyouDtHistoryDto dto03 = kanrenshaKigyouDtHistoryItemReader.read();
        // "ふんだくり企業","宮崎県架空市実在町","代表者 次郎","23-45657-QWERTY","8-7654-3"
        assertEquals("ふんだくり企業", dto03.getKanrenshaName());
        assertEquals("宮崎県架空市実在町", dto03.getAllAddress());
        assertEquals("代表者　次郎", dto03.getKigyouDtDelegate());
        assertEquals("23-45657-QWERTY", dto03.getKigyouDtKanrenshaCode());
        assertEquals("8-7654-3", dto03.getOrgDelegateCode());

        KanrenshaKigyouDtHistoryDto dto04 = kanrenshaKigyouDtHistoryItemReader.read();
        // "職業組合A","山形県架空市実在町","代表者 三郎","12-33-44-55","8-7654-3"
        assertEquals("職業組合A", dto04.getKanrenshaName());
        assertEquals("山形県架空市実在町", dto04.getAllAddress());
        assertEquals("代表者　三郎", dto04.getKigyouDtDelegate());
        assertEquals("12-33-44-55", dto04.getKigyouDtKanrenshaCode());
        assertEquals("8-7654-4", dto04.getOrgDelegateCode());

        KanrenshaKigyouDtHistoryDto dto05 = kanrenshaKigyouDtHistoryItemReader.read();
        // "職業組合B","山梨県架空市実在町","代表者 四郎","99-88-77-66","8-7654-3"
        assertEquals("職業組合B", dto05.getKanrenshaName());
        assertEquals("山梨県架空市実在町", dto05.getAllAddress());
        assertEquals("代表者　四郎", dto05.getKigyouDtDelegate());
        assertEquals("99-88-77-66", dto05.getKigyouDtKanrenshaCode());
        assertEquals("8-7654-5", dto05.getOrgDelegateCode());

        assertNull(kanrenshaKigyouDtHistoryItemReader.read());
    }

    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者企業団体履歴.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
