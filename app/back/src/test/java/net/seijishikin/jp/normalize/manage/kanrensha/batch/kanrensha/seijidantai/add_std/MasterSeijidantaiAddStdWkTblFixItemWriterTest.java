package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterSeijidantaiAddStdWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterSeijidantaiAddStdWkTblFixItemWriterTest.sql")
class MasterSeijidantaiAddStdWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MasterSeijidantaiAddStdWkTblFixItemWriter masterSeijidantaiAddStdWkTblFixItemWriter;

    /** 政治団体マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiMasterRepository wkTblKanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {
        StepExecution stepExecution = getStepExecution();
        masterSeijidantaiAddStdWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiMasterEntity entity1 = createEntity(7001, "政治団体A", "正常終了"); // NOPMD
        WkTblKanrenshaSeijidantaiMasterEntity entity2 = createEntity(7002, "政治団体B", "正常終了");

        List<WkTblKanrenshaSeijidantaiMasterEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaSeijidantaiMasterEntity> chunk = new Chunk<>(entities);
        masterSeijidantaiAddStdWkTblFixItemWriter.write(chunk);

        List<WkTblKanrenshaSeijidantaiMasterEntity> savedEntities = wkTblKanrenshaSeijidantaiMasterRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(7001, savedEntities.get(0).getWkTblKanrenshaSeijidantaiMasterId());
        assertEquals(true, savedEntities.get(0).getIsFinish());
        assertEquals("正常終了", savedEntities.get(0).getJudgeReason());
        assertEquals(7002, savedEntities.get(1).getWkTblKanrenshaSeijidantaiMasterId());
        assertEquals(true, savedEntities.get(1).getIsFinish());
        assertEquals("正常終了", savedEntities.get(1).getJudgeReason());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaSeijidantaiMasterEntity createEntity(final Integer id, final String name, // NOPMD
            final String reason) {
        WkTblKanrenshaSeijidantaiMasterEntity entity = new WkTblKanrenshaSeijidantaiMasterEntity();
        entity.setWkTblKanrenshaSeijidantaiMasterId(id);
        entity.setKanrenshaName(name);
        entity.setAllAddress("住所");
        entity.setSeijidantaiDelegate("代表者");
        entity.setDantaiKbn("1");
        entity.setPoliOrgNo("12345");
        entity.setIsAffected(true);
        entity.setIsFinish(true);
        entity.setJudgeReason(reason);
        entity.setWkTblKanrenshaSeijidantaiMasterCode(100);
        return entity;
    }
}
