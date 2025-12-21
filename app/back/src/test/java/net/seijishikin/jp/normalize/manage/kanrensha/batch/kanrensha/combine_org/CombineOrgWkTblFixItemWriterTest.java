package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Tag;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
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
 * CombineOrgWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CombineOrgWkTblFixItemWriterTest.sql")
class CombineOrgWkTblFixItemWriterTest {

    /** テスト対象 */
    @Autowired
    private CombineOrgWkTblFixItemWriter combineOrgWkTblFixItemWriter;

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    @Test
    @Tag("TableTruncate")
    void test() {

        final int executeId = 211;
        WkTblKanrenshaCombineOrgEntity entityCall = wkTblKanrenshaCombineOrgRepository.findById(executeId).get();
        entityCall.setIsFinish(true);
        entityCall.setJudgeReason("正常終了");

        List<WkTblKanrenshaCombineOrgEntity> list = new ArrayList<>();
        list.add(entityCall);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaCombineOrgEntity> items = new Chunk<>(list);

        combineOrgWkTblFixItemWriter.beforeStep(this.getStepExecution());
        combineOrgWkTblFixItemWriter.write(items);

        WkTblKanrenshaCombineOrgEntity entityAns = wkTblKanrenshaCombineOrgRepository.findById(executeId).get();
        assertEquals(true, entityAns.getIsFinish());
        assertEquals("正常終了", entityAns.getJudgeReason());
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
