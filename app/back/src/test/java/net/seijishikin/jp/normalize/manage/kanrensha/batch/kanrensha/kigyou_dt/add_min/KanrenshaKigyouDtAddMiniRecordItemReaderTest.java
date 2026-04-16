package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaKigyouDtAddMiniRecordItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtAddMiniRecordItemReaderTest.sql")
class KanrenshaKigyouDtAddMiniRecordItemReaderTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddMiniRecordItemReader kanrenshaKigyouDtAddMiniRecordItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        kanrenshaKigyouDtAddMiniRecordItemReader.beforeStep(this.getStepExecution());

        // 5:対象ユーザでない、4:最新でない、3:編集対象でないため読まれない
        assertEquals(1, kanrenshaKigyouDtAddMiniRecordItemReader.read().getWkTblKanrenshaKigyouDtAddMinId());
        assertEquals(2, kanrenshaKigyouDtAddMiniRecordItemReader.read().getWkTblKanrenshaKigyouDtAddMinId());
        assertNull(kanrenshaKigyouDtAddMiniRecordItemReader.read());
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
