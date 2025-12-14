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
 * KanrenshaKigyouDtHistoryItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtHistoryItemWriterTest.sql")
class KanrenshaKigyouDtHistoryItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtHistoryItemWriter kanrenshaKigyouDtHistoryItemWriter;

    /** 企業団体履歴Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTbKanrenshaKigyouDtHistoryRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaKigyouDtHistoryItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaKigyouDtHistoryEntity entity1 = createEntity("団体名A", "住所A", "代表A", "K-CODE-A", "D-CODE-A");
        WkTblKanrenshaKigyouDtHistoryEntity entity2 = createEntity("団体名B", "住所B", "代表B", "K-CODE-B", "D-CODE-B");

        List<WkTblKanrenshaKigyouDtHistoryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaKigyouDtHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaKigyouDtHistoryItemWriter.write(chunk);

        List<WkTblKanrenshaKigyouDtHistoryEntity> savedEntities = wkTbKanrenshaKigyouDtHistoryRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(1, savedEntities.get(0).getWkKanrenshaKigyouDtHistoryCode());
        assertEquals("団体名A", savedEntities.get(0).getKanrenshaName());
        assertEquals(2, savedEntities.get(1).getWkKanrenshaKigyouDtHistoryCode());
        assertEquals("団体名B", savedEntities.get(1).getKanrenshaName());

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteWithExistingData() throws Exception {
        WkTblKanrenshaKigyouDtHistoryEntity existingEntity = createEntity("既存団体", "既存住所", "既存代表", "K-CODE-OLD",
                "D-CODE-OLD");
        existingEntity.setWkKanrenshaKigyouDtHistoryCode(50);
        wkTbKanrenshaKigyouDtHistoryRepository.save(existingEntity);

        StepExecution stepExecution = getStepExecution();
        kanrenshaKigyouDtHistoryItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaKigyouDtHistoryEntity entity1 = createEntity("団体名C", "住所C", "代表C", "K-CODE-C", "D-CODE-C");
        List<WkTblKanrenshaKigyouDtHistoryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        Chunk<WkTblKanrenshaKigyouDtHistoryEntity> chunk = new Chunk<>(entities);
        kanrenshaKigyouDtHistoryItemWriter.write(chunk);

        List<WkTblKanrenshaKigyouDtHistoryEntity> savedEntities = wkTbKanrenshaKigyouDtHistoryRepository.findAll();
        assertEquals(2, savedEntities.size()); // 1 existing + 1 new
        WkTblKanrenshaKigyouDtHistoryEntity newEntity = savedEntities.stream()
                .filter(e -> "団体名C".equals(e.getKanrenshaName())).findFirst().get();
        assertEquals(51, newEntity.getWkKanrenshaKigyouDtHistoryCode());

    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaKigyouDtHistoryEntity createEntity(final String name, final String address,
            final String delegate, final String kanrenshaCode, final String orgDelegateCode) {
        WkTblKanrenshaKigyouDtHistoryEntity entity = new WkTblKanrenshaKigyouDtHistoryEntity();
        entity.setKanrenshaName(name);
        entity.setAllAddress(address);
        entity.setKigyouDtDelegate(delegate);
        entity.setKigyouDtKanrenshaCode(kanrenshaCode);
        entity.setOrgDelegateCode(orgDelegateCode);
        entity.setIsAffected(true);
        entity.setIsFinish(false);
        entity.setJudgeReason("正)");
        entity.setIsLatest(true);
        return entity;
    }
}
