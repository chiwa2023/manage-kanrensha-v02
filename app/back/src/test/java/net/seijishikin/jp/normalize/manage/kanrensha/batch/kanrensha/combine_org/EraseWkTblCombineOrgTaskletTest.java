package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EraseWkTblCombineOrgTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EraseWkTblCombineOrgTaskletTest.sql")
class EraseWkTblCombineOrgTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EraseWkTblCombineOrgTasklet eraseWkTblCombineOrgTasklet;

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgResultRepository wkTblKanrenshaCombineOrgResultRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertEquals(7, wkTblKanrenshaCombineOrgRepository.count());
        assertEquals(5, wkTblKanrenshaCombineOrgResultRepository.count());

        eraseWkTblCombineOrgTasklet.beforeStep(this.getStepExecution());
        eraseWkTblCombineOrgTasklet.execute(null, null);

        assertEquals(1, wkTblKanrenshaCombineOrgRepository.count());
        assertEquals(1, wkTblKanrenshaCombineOrgResultRepository.count());
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
