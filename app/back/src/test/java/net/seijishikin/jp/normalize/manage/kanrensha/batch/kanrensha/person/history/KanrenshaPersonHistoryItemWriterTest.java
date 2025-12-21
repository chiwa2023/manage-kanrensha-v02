package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaPersonHistoryItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaPersonHistoryItemWriterTest.sql")
class KanrenshaPersonHistoryItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonHistoryItemWriter kanrenshaPersonHistoryItemWriter;

    /** 個人履歴ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTbKanrenshaPersonHistoryRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaPersonHistoryItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonHistoryEntity entity1 = createEntity("個人名A", "住所A", "職業A", "P-CODE-A");
        WkTblKanrenshaPersonHistoryEntity entity2 = createEntity("個人名B", "住所B", "職業B", "P-CODE-B");

        List<WkTblKanrenshaPersonHistoryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaPersonHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaPersonHistoryItemWriter.write(chunk);

        List<WkTblKanrenshaPersonHistoryEntity> savedEntities = wkTbKanrenshaPersonHistoryRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(1, savedEntities.get(0).getWkKanrenshaPersonHistoryCode());
        assertEquals("個人名A", savedEntities.get(0).getKanrenshaName());
        assertEquals(2, savedEntities.get(1).getWkKanrenshaPersonHistoryCode());
        assertEquals("個人名B", savedEntities.get(1).getKanrenshaName());

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteWithExistingData() throws Exception {
        WkTblKanrenshaPersonHistoryEntity existingEntity = createEntity("既存個人", "既存住所", "既存職業", "P-CODE-OLD");
        existingEntity.setWkKanrenshaPersonHistoryCode(50);
        wkTbKanrenshaPersonHistoryRepository.save(existingEntity);

        StepExecution stepExecution = getStepExecution();
        kanrenshaPersonHistoryItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonHistoryEntity entity1 = createEntity("個人名C", "住所C", "職業C", "P-CODE-C");
        List<WkTblKanrenshaPersonHistoryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        Chunk<WkTblKanrenshaPersonHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaPersonHistoryItemWriter.write(chunk);

        List<WkTblKanrenshaPersonHistoryEntity> savedEntities = wkTbKanrenshaPersonHistoryRepository.findAll();
        assertEquals(2, savedEntities.size()); // 1 existing + 1 new
        Optional<WkTblKanrenshaPersonHistoryEntity> newEntityOptional = savedEntities.stream()
                .filter(e -> "個人名C".equals(e.getKanrenshaName())).findFirst();
        
        // Assert that the new entity is present and its code is correctly incremented
        assertTrue( newEntityOptional.isPresent());
        assertEquals(51, newEntityOptional.get().getWkKanrenshaPersonHistoryCode());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaPersonHistoryEntity createEntity(final String name, final String address,
            final String shokugyou, final String kanrenshaCode) {
        WkTblKanrenshaPersonHistoryEntity entity = new WkTblKanrenshaPersonHistoryEntity();
        entity.setKanrenshaName(name);
        entity.setAllAddress(address);
        entity.setPersonShokugyou(shokugyou);
        entity.setPersonKanrenshaCode(kanrenshaCode);
        entity.setIsAffected(true);
        entity.setIsFinish(false);
        entity.setJudgeReason("正)");
        entity.setIsLatest(true);
        return entity;
    }
}