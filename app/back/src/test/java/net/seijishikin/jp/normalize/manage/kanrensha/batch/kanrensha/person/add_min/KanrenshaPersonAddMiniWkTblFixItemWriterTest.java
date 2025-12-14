package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaPersonAddMiniWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaPersonAddMiniWkTblFixItemWriterTest.sql")
class KanrenshaPersonAddMiniWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonAddMiniWkTblFixItemWriter kanrenshaPersonAddMiniWkTblFixItemWriter;

    /** 関連者個人最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaPersonAddMiniWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonAddMinEntity entity1 = createEntity(2001, "個人名A", "正常終了"); // NOPMD
        WkTblKanrenshaPersonAddMinEntity entity2 = createEntity(2002, "個人名B", "正常終了");

        List<WkTblKanrenshaPersonAddMinEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaPersonAddMinEntity> chunk = new Chunk<>(entities);
        kanrenshaPersonAddMiniWkTblFixItemWriter.write(chunk);

        List<WkTblKanrenshaPersonAddMinEntity> savedEntities = wkTblKanrenshaPersonAddMinRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(2001, savedEntities.get(0).getWkTblKanrenshaPersonAddMinId());
        assertEquals(true, savedEntities.get(0).getIsFinish());
        assertEquals("正常終了", savedEntities.get(0).getJudgeReason());
        assertEquals(2002, savedEntities.get(1).getWkTblKanrenshaPersonAddMinId());
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

    private WkTblKanrenshaPersonAddMinEntity createEntity(final Integer id, final String name, final String reason) { // NOPMD
        WkTblKanrenshaPersonAddMinEntity entity = new WkTblKanrenshaPersonAddMinEntity();
        entity.setWkTblKanrenshaPersonAddMinId(id);
        entity.setKanrenshaName(name);
        entity.setAllAddress("住所");
        entity.setPersonShokugyou("職業");
        entity.setIsAffected(true);
        entity.setIsFinish(true);
        entity.setJudgeReason(reason);
        entity.setWkTblKanrenshaPersonAddMinCode(100);
        return entity;
    }
}