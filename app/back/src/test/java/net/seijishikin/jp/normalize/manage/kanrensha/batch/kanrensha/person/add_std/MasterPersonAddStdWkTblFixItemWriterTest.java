package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterPersonAddStdWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterPersonAddStdWkTblFixItemWriterTest.sql")
class MasterPersonAddStdWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdWkTblFixItemWriter masterPersonAddStdWkTblFixItemWriter;

    /** 関連者個人マスタワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonMasterRepository wkTblKanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        masterPersonAddStdWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonMasterEntity entity1 = createEntity(4001, "個人名A", "正常終了"); // NOPMD
        WkTblKanrenshaPersonMasterEntity entity2 = createEntity(4002, "個人名B", "正常終了");

        List<WkTblKanrenshaPersonMasterEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaPersonMasterEntity> chunk = new Chunk<>(entities);
        masterPersonAddStdWkTblFixItemWriter.write(chunk);

        List<WkTblKanrenshaPersonMasterEntity> savedEntities = wkTblKanrenshaPersonMasterRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(4001, savedEntities.get(0).getWkTblKanrenshaPersonMasterId());
        assertEquals(true, savedEntities.get(0).getIsFinish());
        assertEquals("正常終了", savedEntities.get(0).getJudgeReason());
        assertEquals(4002, savedEntities.get(1).getWkTblKanrenshaPersonMasterId());
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

    private WkTblKanrenshaPersonMasterEntity createEntity(final Integer id, final String name, final String reason) { // NOPMD
        WkTblKanrenshaPersonMasterEntity entity = new WkTblKanrenshaPersonMasterEntity();
        entity.setWkTblKanrenshaPersonMasterId(id);
        entity.setKanrenshaName(name);
        entity.setAllAddress("住所");
        entity.setPersonShokugyou("職業");
        entity.setIsAffected(true);
        entity.setIsFinish(true);
        entity.setJudgeReason(reason);
        entity.setWkTblKanrenshaPersonMasterCode(100);
        return entity;
    }
}
