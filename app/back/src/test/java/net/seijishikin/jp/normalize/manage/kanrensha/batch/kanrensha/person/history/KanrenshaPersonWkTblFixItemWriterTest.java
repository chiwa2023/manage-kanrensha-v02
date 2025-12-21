package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory13Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaPersonWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
//@Transactional
@Sql("KanrenshaPersonWkTblFixItemWriterTest.sql")
class KanrenshaPersonWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonWkTblFixItemWriter kanrenshaPersonWkTblFixItemWriter;

    /** 個人履歴ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTbKanrenshaPersonHistoryRepository;

    /** 個人履歴住所東京都用Repository */
    @Autowired
    private KanrenshaPersonHistory13Repository kanrenshaPersonHistory13Repository;

    @Test
    @Tag("TableTruncate")
    void testWriteAffectedEntity() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaPersonWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonHistoryEntity entity = createEntity(3001, true);

        List<WkTblKanrenshaPersonHistoryEntity> entities = new ArrayList<>();
        entities.add(entity);

        Chunk<WkTblKanrenshaPersonHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaPersonWkTblFixItemWriter.write(chunk);

        WkTblKanrenshaPersonHistoryEntity savedEntity = wkTbKanrenshaPersonHistoryRepository.findById(3001).get();
        assertTrue(savedEntity.getIsFinish());
        assertEquals("正常保存", savedEntity.getJudgeReason());

        // 履歴に登録されているか確認
        List<KanrenshaPersonHistory13Entity> listHistory = kanrenshaPersonHistory13Repository.findAll();
        assertEquals(1, listHistory.size());
        KanrenshaPersonHistory13Entity historyEntity = listHistory.get(0);
        assertEquals("テスト個人", historyEntity.getAllName());
        assertEquals("東京都千代田区", historyEntity.getAllAddress());
        assertEquals("テスト職業", historyEntity.getPersonShokugyou());
        assertEquals("TEST-P-CODE", historyEntity.getPersonKanrenshaCode());
        assertEquals("テスト個人東京都千代田区テスト職業", historyEntity.getSearchText());

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteNotAffectedEntity() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaPersonWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonHistoryEntity entity = createEntity(3001, false);

        List<WkTblKanrenshaPersonHistoryEntity> entities = new ArrayList<>();
        entities.add(entity);

        Chunk<WkTblKanrenshaPersonHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaPersonWkTblFixItemWriter.write(chunk);

        WkTblKanrenshaPersonHistoryEntity savedEntity = wkTbKanrenshaPersonHistoryRepository.findById(3001).get();
        assertFalse(savedEntity.getIsFinish());
        assertEquals("既存理由", savedEntity.getJudgeReason()); // 既存の理由は変更されない
        assertEquals(0, kanrenshaPersonHistory13Repository.count());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaPersonHistoryEntity createEntity(final Integer id, final boolean isAffected) { // NOPMD
        WkTblKanrenshaPersonHistoryEntity entity = wkTbKanrenshaPersonHistoryRepository.findById(id).get();
        entity.setIsAffected(isAffected);
        return entity;
    }
}