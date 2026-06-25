package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * MultiRsdtCsvFileReader単体テスト
 */
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MultiRsdtCsvFileReaderTest {

    /** テスト対象 */
    @Autowired
    private MultiRsdtCsvFileReader multiRsdtCsvFileReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        multiRsdtCsvFileReader.beforeStep(execution);
        multiRsdtCsvFileReader.open(execution.getExecutionContext());

        // データ1行目
        RsdtAddressCsvDto csvDto00 = multiRsdtCsvFileReader.read();
        assertEquals("011011", csvDto00.getLgCode());

        // ファイルに格納されている分だけ読みとばし
        multiRsdtCsvFileReader.read();
        multiRsdtCsvFileReader.read();

        // 次のファイル1行目
        RsdtAddressCsvDto csvDto10 = multiRsdtCsvFileReader.read();
        assertEquals("131016", csvDto10.getLgCode());

        // ファイルに格納されている分だけ読みとばし
        multiRsdtCsvFileReader.read();
        multiRsdtCsvFileReader.read();

        // 読み取り終了
        assertNull(multiRsdtCsvFileReader.read());
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/address_base/multi/rsdt");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readDirectory", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
