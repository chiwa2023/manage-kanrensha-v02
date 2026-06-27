package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RsdtWkTblAddressFileLgCodeItemReader単体テスト
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RsdtWkTblAddressFileLgCodeItemReaderTest.sql")
class RsdtWkTblAddressFileLgCodeItemReaderTest {
    // CHECKSTYLE:OFF MagicNumbber

    /** テスト対象 */
    @Autowired
    private RsdtWkTblAddressFileLgCodeItemReader rsdtWkTblAddressFileLgCodeItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        rsdtWkTblAddressFileLgCodeItemReader.beforeStep(this.getStepExecution());

        assertEquals(245, rsdtWkTblAddressFileLgCodeItemReader.read().getWkTblAddressRsdtFileId());
        assertEquals(248, rsdtWkTblAddressFileLgCodeItemReader.read().getWkTblAddressRsdtFileId());
        assertNull(rsdtWkTblAddressFileLgCodeItemReader.read());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
