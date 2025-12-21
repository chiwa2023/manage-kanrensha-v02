package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaByXmlMinWkTblFixItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaByXmlMinWkTblFixItemReaderTest.sql")
class KanrenshaByXmlMinWkTblFixItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaByXmlMinWkTblFixItemReader kanrenshaByXmlMinWkTblFixItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        kanrenshaByXmlMinWkTblFixItemReader.beforeStep(this.getStepExecution());

        assertEquals(653, kanrenshaByXmlMinWkTblFixItemReader.read().getWkTblMasterAllByXmlResultId());
        assertEquals(654, kanrenshaByXmlMinWkTblFixItemReader.read().getWkTblMasterAllByXmlResultId());
        assertEquals(655, kanrenshaByXmlMinWkTblFixItemReader.read().getWkTblMasterAllByXmlResultId());
        assertNull(kanrenshaByXmlMinWkTblFixItemReader.read());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
