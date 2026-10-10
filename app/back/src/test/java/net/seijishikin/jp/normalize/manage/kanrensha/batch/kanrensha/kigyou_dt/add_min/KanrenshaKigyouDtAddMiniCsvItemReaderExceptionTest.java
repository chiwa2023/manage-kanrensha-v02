package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.ItemStreamException;
import org.springframework.batch.infrastructure.item.file.FlatFileParseException;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * KanrenshaKigyouDtAddMiniCsvItemReader単体テスト例外編
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class KanrenshaKigyouDtAddMiniCsvItemReaderExceptionTest {

    @AfterEach
    void tearDown() {
        kanrenshaKigyouDtAddMiniCsvItemReader.close();
    }

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddMiniCsvItemReader kanrenshaKigyouDtAddMiniCsvItemReader;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testOnlyHeader() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat("ヘッダのみ存在.csv");
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        // 例外発生
        assertThrows(FlatFileParseException.class, () -> kanrenshaKigyouDtAddMiniCsvItemReader.read());
    }

    @Test
    @Tag("TableTruncate")
    void testNotQuote() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat("引用符がない.csv");
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        // ArrayIndexOutOfBoundsExceptionが変換されている
        assertThrows(FlatFileParseException.class, () -> kanrenshaKigyouDtAddMiniCsvItemReader.read());

    }

    @Test
    @Tag("TableTruncate")
    void testQuoteWrongPair() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat("引用符が不適切.csv");
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);

        kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext());
        assertThrows(FlatFileParseException.class, () -> kanrenshaKigyouDtAddMiniCsvItemReader.read());
    }

    @Test
    @Tag("TableTruncate")
    void testCommaData() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat("データにカンマが存在.csv");
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        // 特に問題なく処理される
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
    }

    @Test
    @Tag("TableTruncate")
    void testCarriageReturn() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat("データに改行が存在.csv");
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        // 特に問題なく処理される
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
    }

    @Test
    @Tag("TableTruncate")
    void testCharCode() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat("文字コード違い.csv");
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        // 落ちはしないが文字化けしている
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNotNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
        assertNull(kanrenshaKigyouDtAddMiniCsvItemReader.read());
    }
    
    
    @Test
    @Tag("TableTruncate")
    void testNotHaveFile() throws Exception {

        // 存在しないファイルを指定
        StepExecution stepExecution = this.getStepExecutionFormat("aaaa.csv");
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);

        // readの前段階で、引数例外が、対象がstreamできない例外として変換されて出てくる
        assertThrows(ItemStreamException.class,
                () -> kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext()));
    }

    private StepExecution getStepExecutionFormat(final String fileName) {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha/exception",
                fileName);

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
