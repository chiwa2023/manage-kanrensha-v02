package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory13Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaSeijidantaiWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaSeijidantaiWkTblFixItemWriterTest.sql")
class KanrenshaSeijidantaiWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiWkTblFixItemWriter kanrenshaSeijidantaiWkTblFixItemWriter;

    /** 政治団体履歴ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTbKanrenshaSeijidantaiHistoryRepository;

    /** 東京都政治団体履歴Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory13Repository kanrenshaSeijidantaiHistory13Repository;

    @Test
    @Tag("TableTruncate")
    void testWriteAffectedEntity() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaSeijidantaiWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiHistoryEntity entity = createEntity(6001, true);

        List<WkTblKanrenshaSeijidantaiHistoryEntity> entities = new ArrayList<>();
        entities.add(entity);

        Chunk<WkTblKanrenshaSeijidantaiHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaSeijidantaiWkTblFixItemWriter.write(chunk);

        WkTblKanrenshaSeijidantaiHistoryEntity savedEntity = wkTbKanrenshaSeijidantaiHistoryRepository.findById(6001)
                .get();
        assertTrue(savedEntity.getIsFinish());
        assertEquals("正常保存", savedEntity.getJudgeReason());

        List<KanrenshaSeijidantaiHistory13Entity> listHistory = kanrenshaSeijidantaiHistory13Repository.findAll();
        assertEquals(1, listHistory.size());

        // 履歴に登録されているか確認
        assertEquals(1, listHistory.size());
        KanrenshaSeijidantaiHistory13Entity historyEntity = listHistory.get(0);
        assertEquals("テスト政治団体", historyEntity.getAllName());
        assertEquals("東京都千代田区", historyEntity.getAllAddress());
        assertEquals("代表テスト", historyEntity.getOrgDelegateName());
        assertEquals("D-CODE-TEST", historyEntity.getOrgDelegateCode());
        assertEquals("S-CODE-TEST", historyEntity.getSeijidantaiKanrenshaCode());
        assertEquals("テスト政治団体東京都千代田区代表テスト", historyEntity.getSearchText());

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteNotAffectedEntity() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaSeijidantaiWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiHistoryEntity entity = createEntity(6001, false);

        List<WkTblKanrenshaSeijidantaiHistoryEntity> entities = new ArrayList<>();
        entities.add(entity);

        Chunk<WkTblKanrenshaSeijidantaiHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaSeijidantaiWkTblFixItemWriter.write(chunk);

        WkTblKanrenshaSeijidantaiHistoryEntity savedEntity = wkTbKanrenshaSeijidantaiHistoryRepository.findById(6001)
                .get();
        assertFalse(savedEntity.getIsFinish());
        assertEquals("既存理由", savedEntity.getJudgeReason()); // 既存の理由は変更されない
        assertEquals(0, kanrenshaSeijidantaiHistory13Repository.count());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaSeijidantaiHistoryEntity createEntity(final Integer id, final boolean isAffected) { // NOPMD
        WkTblKanrenshaSeijidantaiHistoryEntity entity = wkTbKanrenshaSeijidantaiHistoryRepository.findById(id).get();
        entity.setIsAffected(isAffected);
        return entity;
    }
}