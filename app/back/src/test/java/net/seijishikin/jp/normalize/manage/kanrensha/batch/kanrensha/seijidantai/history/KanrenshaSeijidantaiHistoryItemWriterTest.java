package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaSeijidantaiHistoryItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaSeijidantaiHistoryItemWriterTest.sql")
class KanrenshaSeijidantaiHistoryItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiHistoryItemWriter kanrenshaSeijidantaiHistoryItemWriter;

    /** 政治団体履歴ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTbKanrenshaSeijidantaiHistoryRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaSeijidantaiHistoryItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiHistoryEntity entity1 = createEntity("政治団体A", "住所A", "代表A", "S-CODE-A", "D-CODE-A");
        WkTblKanrenshaSeijidantaiHistoryEntity entity2 = createEntity("政治団体B", "住所B", "代表B", "S-CODE-B", "D-CODE-B");

        List<WkTblKanrenshaSeijidantaiHistoryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaSeijidantaiHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaSeijidantaiHistoryItemWriter.write(chunk);

        List<WkTblKanrenshaSeijidantaiHistoryEntity> savedEntities = wkTbKanrenshaSeijidantaiHistoryRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(1, savedEntities.get(0).getWkKanrenshaSeijidantaiHistoryCode());
        assertEquals("政治団体A", savedEntities.get(0).getKanrenshaName());
        assertEquals(2, savedEntities.get(1).getWkKanrenshaSeijidantaiHistoryCode());
        assertEquals("政治団体B", savedEntities.get(1).getKanrenshaName());

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteWithExistingData() throws Exception {
        WkTblKanrenshaSeijidantaiHistoryEntity existingEntity = createEntity("既存団体", "既存住所", "既存代表", "S-CODE-OLD",
                "D-CODE-OLD");
        existingEntity.setWkKanrenshaSeijidantaiHistoryCode(50);
        wkTbKanrenshaSeijidantaiHistoryRepository.save(existingEntity);

        StepExecution stepExecution = getStepExecution();
        kanrenshaSeijidantaiHistoryItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiHistoryEntity entity1 = createEntity("政治団体C", "住所C", "代表C", "S-CODE-C", "D-CODE-C");
        List<WkTblKanrenshaSeijidantaiHistoryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        Chunk<WkTblKanrenshaSeijidantaiHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaSeijidantaiHistoryItemWriter.write(chunk);

        List<WkTblKanrenshaSeijidantaiHistoryEntity> savedEntities = wkTbKanrenshaSeijidantaiHistoryRepository.findAll();
        assertEquals(2, savedEntities.size()); // 1 existing + 1 new
        Optional<WkTblKanrenshaSeijidantaiHistoryEntity> newEntityOptional = savedEntities.stream()
                .filter(e -> "政治団体C".equals(e.getKanrenshaName())).findFirst();
        
        assertTrue(newEntityOptional.isPresent());
        assertEquals(51, newEntityOptional.get().getWkKanrenshaSeijidantaiHistoryCode());

    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaSeijidantaiHistoryEntity createEntity(final String name, final String address,
            final String delegate, final String kanrenshaCode, final String orgDelegateCode) {
        WkTblKanrenshaSeijidantaiHistoryEntity entity = new WkTblKanrenshaSeijidantaiHistoryEntity();
        entity.setKanrenshaName(name);
        entity.setAllAddress(address);
        entity.setSeijidantaiDelegate(delegate);
        entity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        entity.setOrgDelegateCode(orgDelegateCode);
        entity.setIsAffected(true);
        entity.setIsFinish(false);
        entity.setJudgeReason("正)");
        entity.setIsLatest(true);
        return entity;
    }
}