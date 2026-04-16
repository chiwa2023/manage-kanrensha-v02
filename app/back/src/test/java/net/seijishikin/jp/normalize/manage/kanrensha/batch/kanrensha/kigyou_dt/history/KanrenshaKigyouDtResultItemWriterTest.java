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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaKigyouDtResultItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtResultItemWriterTest.sql")
class KanrenshaKigyouDtResultItemWriterTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtResultItemWriter kanrenshaKigyouDtResultItemWriter;

    /** 企業団体履歴ワークテーブル結果Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryResultRepository wkTblKanrenshaKigyouDtHistoryResultRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaKigyouDtResultItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaKigyouDtHistoryResultEntity entity1 = createEntity(1, "理由1");
        WkTblKanrenshaKigyouDtHistoryResultEntity entity2 = createEntity(2, "理由2");

        List<WkTblKanrenshaKigyouDtHistoryResultEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaKigyouDtHistoryResultEntity> chunk = new Chunk<>(entities);
        kanrenshaKigyouDtResultItemWriter.write(chunk);

        List<WkTblKanrenshaKigyouDtHistoryResultEntity> savedEntities = wkTblKanrenshaKigyouDtHistoryResultRepository
                .findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(1, savedEntities.get(0).getWkKanrenshaKigyouDtHistoryId());
        assertEquals("理由1", savedEntities.get(0).getJudgeReason());
        assertEquals(2, savedEntities.get(1).getWkKanrenshaKigyouDtHistoryId());
        assertEquals("理由2", savedEntities.get(1).getJudgeReason());

    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaKigyouDtHistoryResultEntity createEntity(final Integer historyId,final String reason) {
        WkTblKanrenshaKigyouDtHistoryResultEntity entity = new WkTblKanrenshaKigyouDtHistoryResultEntity();
        entity.setWkKanrenshaKigyouDtHistoryId(historyId);
        entity.setJudgeReason(reason);
        entity.setIsAffected(true);
        entity.setIsLatest(true);
        return entity;
    }
}
