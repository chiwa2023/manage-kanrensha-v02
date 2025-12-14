package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaKigyouDtWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtWkTblFixItemWriterTest.sql")
class KanrenshaKigyouDtWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtWkTblFixItemWriter kanrenshaKigyouDtWkTblFixItemWriter;

    /** 企業団体履歴Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTbKanrenshaKigyouDtHistoryRepository;

    @Test
    @Tag("TableTruncate")
    void testWriteAffectedEntity() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaKigyouDtWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaKigyouDtHistoryEntity entity = createEntity(1001, true);

        List<WkTblKanrenshaKigyouDtHistoryEntity> entities = new ArrayList<>();
        entities.add(entity);

        Chunk<WkTblKanrenshaKigyouDtHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaKigyouDtWkTblFixItemWriter.write(chunk);

        List<WkTblKanrenshaKigyouDtHistoryEntity> savedEntities = wkTbKanrenshaKigyouDtHistoryRepository.findAll();
        assertEquals(1, savedEntities.size());
        assertEquals(1001, savedEntities.get(0).getWkKanrenshaKigyouDtHistoryId());
        assertEquals(true, savedEntities.get(0).getIsFinish());
        assertEquals("正常保存", savedEntities.get(0).getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteNotAffectedEntity() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaKigyouDtWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaKigyouDtHistoryEntity entity = createEntity(1001, false);

        List<WkTblKanrenshaKigyouDtHistoryEntity> entities = new ArrayList<>();
        entities.add(entity);

        Chunk<WkTblKanrenshaKigyouDtHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaKigyouDtWkTblFixItemWriter.write(chunk);

        List<WkTblKanrenshaKigyouDtHistoryEntity> savedEntities = wkTbKanrenshaKigyouDtHistoryRepository.findAll();
        assertEquals(1, savedEntities.size());
        assertEquals(1001, savedEntities.get(0).getWkKanrenshaKigyouDtHistoryId());
        assertEquals(false, savedEntities.get(0).getIsFinish());
        assertEquals("既存理由", savedEntities.get(0).getJudgeReason()); // 既存の理由は変更されない
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaKigyouDtHistoryEntity createEntity(final Integer id,final boolean isAffected) { // NOPMD
        WkTblKanrenshaKigyouDtHistoryEntity entity = new WkTblKanrenshaKigyouDtHistoryEntity();
        entity.setWkKanrenshaKigyouDtHistoryId(id);
        entity.setKanrenshaName("テスト団体");
        entity.setAllAddress("テスト住所");
        entity.setKigyouDtDelegate("テスト代表");
        entity.setKigyouDtKanrenshaCode("TEST-CODE");
        entity.setOrgDelegateCode("ORG-TEST-CODE");
        entity.setIsAffected(isAffected);
        entity.setIsFinish(false);
        entity.setJudgeReason("既存理由");
        entity.setIsLatest(true);
        return entity;
    }
}
