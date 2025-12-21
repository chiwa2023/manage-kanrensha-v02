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
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CombineOrgCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CombineOrgCsvItemWriterTest.sql")
class CombineOrgCsvItemWriterTest {

    /** テスト対象 */
    @Autowired
    private CombineOrgCsvItemWriter combineOrgCsvItemWriter;
    

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    @Test
    @Tag("TableTruncate")
    void test() {

        WkTblKanrenshaCombineOrgEntity entity00 = new WkTblKanrenshaCombineOrgEntity();

        entity00.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);
        entity00.setPersonKanrenshaCode("個人コード");
        entity00.setPersonName("個人名称");
        entity00.setOrgKanrenshaCode("団体コード");
        entity00.setOrgName("団体名称");
        entity00.setStartYear(Short.valueOf("2021"));
        entity00.setEndYear(Short.valueOf("2022"));
        entity00.setYearArrayText("12345");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        List<WkTblKanrenshaCombineOrgEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaCombineOrgEntity> items = new Chunk<>(list);

        combineOrgCsvItemWriter.beforeStep(this.getStepExecution());
        combineOrgCsvItemWriter.write(items);

        List<WkTblKanrenshaCombineOrgEntity> listAns = wkTblKanrenshaCombineOrgRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblKanrenshaCombineOrgEntity entityAns = listAns.get(0);

        assertEquals(entity00.getPersonKanrenshaCode(), entityAns.getPersonKanrenshaCode());
        assertEquals(entity00.getPersonName(), entityAns.getPersonName());
        assertEquals(entity00.getOrgKanrenshaCode(), entityAns.getOrgKanrenshaCode());
        assertEquals(entity00.getOrgName(), entityAns.getOrgName());
        assertEquals(entity00.getStartYear(), entityAns.getStartYear());
        assertEquals(entity00.getEndYear(), entityAns.getEndYear());
        assertEquals(entity00.getYearArrayText(), entityAns.getYearArrayText());
        assertEquals(entity00.getIsAffected(), entityAns.getIsAffected());
        assertEquals(entity00.getIsFinish(), entityAns.getIsFinish());
        assertEquals(entity00.getJudgeReason(), entityAns.getJudgeReason());
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
