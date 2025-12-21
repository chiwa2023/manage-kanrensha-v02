package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Tag;
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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CombineOrgCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CombineOrgCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private CombineOrgCsvItemReader combineOrgCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();
        combineOrgCsvItemReader.beforeStep(stepExecution);
        combineOrgCsvItemReader.open(stepExecution.getExecutionContext());

        KanrenshaCombineOrgDto dto01 = combineOrgCsvItemReader.read();
        // "12-34567-8901-2345-67890","迂回献金
        // 太郎","1-ABCD-67-890123-4567890","ふんだくり企業","2021","abcde"
        assertEquals("12-34567-8901-2345-67890", dto01.getPersonKanrenshaCode());
        assertEquals("迂回献金　太郎", dto01.getPersonName());
        assertEquals("1-ABCD-67-890123-4567890", dto01.getOrgKanrenshaCode());
        assertEquals("ふんだくり企業", dto01.getOrgName());
        assertEquals(Short.valueOf("2021"), dto01.getStartYear());
        assertEquals(Short.valueOf("-1"), dto01.getEndYear()); // 数字変換できないときは-1

        KanrenshaCombineOrgDto dto02 = combineOrgCsvItemReader.read();
        // "12-34567-8901-2345-67891","迂回献金
        // 花子","1-ABCD-67-890123-4567890","ふんだくり企業","999999999","2024"
        assertEquals("12-34567-8901-2345-67891", dto02.getPersonKanrenshaCode());
        assertEquals("迂回献金　花子", dto02.getPersonName());
        assertEquals("1-ABCD-67-890123-4567890", dto02.getOrgKanrenshaCode());
        assertEquals("ふんだくり企業", dto02.getOrgName());
        assertEquals(Short.valueOf("-1"), dto02.getStartYear());
        assertEquals(Short.valueOf("2024"), dto02.getEndYear());

        assertNull(combineOrgCsvItemReader.read());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha/combine_org",
                "sample_combine_corp.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).addString("readFilePath", path.toString())
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
