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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SuspendDuplicateWkTblCombineOrgTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SuspendDuplicateWkTblCombineOrgTaskletTest.sql")
class SuspendDuplicateWkTblCombineOrgTaskletTest {
    
    /** テスト対象 */
    @Autowired
    private SuspendDuplicateWkTblCombineOrgTasklet suspendDuplicateWkTblCombineOrgTasklet;

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository    ;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        // CHECKSTYLE:OFF

        final String reason = "アップロードファイル内で重複しているデータです";

        suspendDuplicateWkTblCombineOrgTasklet.beforeStep(this.getStepExecution());
        suspendDuplicateWkTblCombineOrgTasklet.execute(null, null);

        WkTblKanrenshaCombineOrgEntity entity501 = wkTblKanrenshaCombineOrgRepository.findById(501).get();
        assertEquals(false, entity501.getIsLatest());
        assertEquals(true, entity501.getIsFinish());
        assertEquals(reason, entity501.getJudgeReason());
        WkTblKanrenshaCombineOrgEntity entity502 = wkTblKanrenshaCombineOrgRepository.findById(502).get();
        assertEquals(false, entity502.getIsLatest());
        assertEquals(true, entity502.getIsFinish());
        assertEquals(reason, entity502.getJudgeReason());
        WkTblKanrenshaCombineOrgEntity entity601 = wkTblKanrenshaCombineOrgRepository.findById(601).get();
        assertEquals(false, entity601.getIsLatest());
        assertEquals(true, entity601.getIsFinish());
        assertEquals(reason, entity601.getJudgeReason());
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
