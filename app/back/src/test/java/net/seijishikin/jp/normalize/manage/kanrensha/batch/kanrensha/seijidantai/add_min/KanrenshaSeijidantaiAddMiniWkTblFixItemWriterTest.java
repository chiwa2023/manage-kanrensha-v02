package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaSeijidantaiAddMiniWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaSeijidantaiAddMiniWkTblFixItemWriterTest.sql")
class KanrenshaSeijidantaiAddMiniWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiAddMiniWkTblFixItemWriter kanrenshaSeijidantaiAddMiniWkTblFixItemWriter;

    /** 政治団体最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaSeijidantaiAddMiniWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiAddMinEntity entity1 = createEntity(3001, "政治団体A", "正常終了"); // NOPMD
        WkTblKanrenshaSeijidantaiAddMinEntity entity2 = createEntity(3002, "政治団体B", "正常終了");

        List<WkTblKanrenshaSeijidantaiAddMinEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaSeijidantaiAddMinEntity> chunk = new Chunk<>(entities);
        kanrenshaSeijidantaiAddMiniWkTblFixItemWriter.write(chunk);

        List<WkTblKanrenshaSeijidantaiAddMinEntity> savedEntities = wkTblKanrenshaSeijidantaiAddMinRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(3001, savedEntities.get(0).getWkTblKanrenshaSeijidantaiAddMinId());
        assertEquals(true, savedEntities.get(0).getIsFinish());
        assertEquals("正常終了", savedEntities.get(0).getJudgeReason());
        assertEquals(3002, savedEntities.get(1).getWkTblKanrenshaSeijidantaiAddMinId());
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

    private WkTblKanrenshaSeijidantaiAddMinEntity createEntity(final Integer id, final String name, // NOPMD
            final String reason) {
        WkTblKanrenshaSeijidantaiAddMinEntity entity = new WkTblKanrenshaSeijidantaiAddMinEntity();
        entity.setWkTblKanrenshaSeijidantaiAddMinId(id);
        entity.setKanrenshaName(name);
        entity.setAllAddress("住所");
        entity.setSeijidantaiDelegate("代表者");
        entity.setDantaiKbn("01");
        entity.setPoliOrgNo("12345");
        entity.setIsAffected(true);
        entity.setIsFinish(true);
        entity.setJudgeReason(reason);
        entity.setWkTblKanrenshaSeijidantaiAddMinCode(100);
        return entity;
    }
}