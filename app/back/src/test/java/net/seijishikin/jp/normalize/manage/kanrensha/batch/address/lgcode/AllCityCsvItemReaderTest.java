package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * AllCityCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AllCityCsvItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private AllCityCsvItemReader allCityCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        allCityCsvItemReader.beforeStep(execution);
        allCityCsvItemReader.open(execution.getExecutionContext());

        AllCityCsvDto csvDto0 = allCityCsvItemReader.read();
        assertEquals("011002", csvDto0.getLgCode());
        assertEquals("北海道", csvDto0.getPref()); // NOPMD
        assertEquals("", csvDto0.getCounty());
        assertEquals("札幌市", csvDto0.getCity());
        assertEquals("", csvDto0.getWard());
        assertEquals("ホッカイドウサッポロシ", csvDto0.getAddressNameKana());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto0.getEffectDate());
        assertNull(csvDto0.getAbolishDate());

        AllCityCsvDto csvDto1 = allCityCsvItemReader.read();
        assertEquals("011011", csvDto1.getLgCode());
        assertEquals("北海道", csvDto1.getPref());
        assertEquals("", csvDto1.getCounty());
        assertEquals("札幌市", csvDto1.getCity());
        assertEquals("中央区", csvDto1.getWard());
        assertEquals("ホッカイドウサッポロシチュウオウク", csvDto1.getAddressNameKana());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto1.getEffectDate());
        assertNull(csvDto1.getAbolishDate());

        AllCityCsvDto csvDto2 = allCityCsvItemReader.read();
        assertEquals("011029", csvDto2.getLgCode());
        assertEquals("北海道", csvDto2.getPref());
        assertEquals("", csvDto2.getCounty());
        assertEquals("札幌市", csvDto2.getCity());
        assertEquals("北区", csvDto2.getWard());
        assertEquals("ホッカイドウサッポロシキタク", csvDto2.getAddressNameKana());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto2.getEffectDate());
        assertEquals(LocalDate.of(2044, 12, 31), csvDto2.getAbolishDate());

        AllCityCsvDto csvDto3 = allCityCsvItemReader.read();
        assertEquals("012076", csvDto3.getLgCode());
        assertEquals("北海道", csvDto3.getPref());
        assertEquals("", csvDto3.getCounty());
        assertEquals("帯広市", csvDto3.getCity());
        assertEquals("", csvDto3.getWard());
        assertEquals("ホッカイドウオビヒロシ", csvDto3.getAddressNameKana());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto3.getEffectDate());
        assertNull(csvDto3.getAbolishDate());

        AllCityCsvDto csvDto4 = allCityCsvItemReader.read();
        assertEquals("012084", csvDto4.getLgCode());
        assertEquals("北海道", csvDto4.getPref());
        assertEquals("", csvDto4.getCounty());
        assertEquals("北見市", csvDto4.getCity());
        assertEquals("", csvDto4.getWard());
        assertEquals("ホッカイドウキタミシ", csvDto4.getAddressNameKana());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto4.getEffectDate());
        assertNull(csvDto4.getAbolishDate());

        AllCityCsvDto csvDto5 = allCityCsvItemReader.read();
        assertEquals("013374", csvDto5.getLgCode());
        assertEquals("北海道", csvDto5.getPref());
        assertEquals("亀田郡", csvDto5.getCounty());
        assertEquals("七飯町", csvDto5.getCity());
        assertEquals("", csvDto5.getWard());
        assertEquals("ホッカイドウカメダグンナナエチョウ", csvDto5.getAddressNameKana());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto5.getEffectDate());
        assertNull(csvDto5.getAbolishDate());

        assertNull(allCityCsvItemReader.read());
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/address_base/",
                "mt_city_all_sample.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFilePath", path.toString())
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
