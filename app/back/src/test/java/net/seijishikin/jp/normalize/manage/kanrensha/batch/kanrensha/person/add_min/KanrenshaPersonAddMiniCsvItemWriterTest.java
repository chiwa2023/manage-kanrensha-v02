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
 * KanrenshaPersonAddMiniCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaPersonAddMiniCsvItemWriterTest.sql")
class KanrenshaPersonAddMiniCsvItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonAddMiniCsvItemWriter kanrenshaPersonAddMiniCsvItemWriter;

    /** 企業団体最小登録ワークテーブルRespoitory */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        kanrenshaPersonAddMiniCsvItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonAddMinEntity entity1 = createEntity("山田太郎", "東京都", "会社員"); // NOPMD
        WkTblKanrenshaPersonAddMinEntity entity2 = createEntity("鈴木花子", "大阪府", "公務員");

        List<WkTblKanrenshaPersonAddMinEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaPersonAddMinEntity> chunk = new Chunk<>(entities);
        kanrenshaPersonAddMiniCsvItemWriter.write(chunk);

        List<WkTblKanrenshaPersonAddMinEntity> savedEntities = wkTblKanrenshaPersonAddMinRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(1, savedEntities.get(0).getWkTblKanrenshaPersonAddMinCode());
        assertEquals("山田太郎", savedEntities.get(0).getKanrenshaName());
        assertEquals(2, savedEntities.get(1).getWkTblKanrenshaPersonAddMinCode());
        assertEquals("鈴木花子", savedEntities.get(1).getKanrenshaName());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteWithExistingData() throws Exception {
        WkTblKanrenshaPersonAddMinEntity existingEntity = createEntity("既存太郎", "福岡県", "自営業");
        existingEntity.setWkTblKanrenshaPersonAddMinCode(50);
        wkTblKanrenshaPersonAddMinRepository.save(existingEntity);

        StepExecution stepExecution = getStepExecution();
        kanrenshaPersonAddMiniCsvItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaPersonAddMinEntity entity1 = createEntity("山田太郎", "東京都", "会社員");
        List<WkTblKanrenshaPersonAddMinEntity> entities = new ArrayList<>();
        entities.add(entity1);
        Chunk<WkTblKanrenshaPersonAddMinEntity> chunk = new Chunk<>(entities);
        kanrenshaPersonAddMiniCsvItemWriter.write(chunk);

        List<WkTblKanrenshaPersonAddMinEntity> savedEntities = wkTblKanrenshaPersonAddMinRepository.findAll();
        assertEquals(2, savedEntities.size());
        WkTblKanrenshaPersonAddMinEntity newEntity = savedEntities.stream()
                .filter(e -> e.getWkTblKanrenshaPersonAddMinCode() == 51).findFirst().get();
        assertEquals("山田太郎", newEntity.getKanrenshaName());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaPersonAddMinEntity createEntity(String name, String address, String shokugyou) { // NOPMD
        WkTblKanrenshaPersonAddMinEntity entity = new WkTblKanrenshaPersonAddMinEntity();
        entity.setKanrenshaName(name);
        entity.setAllAddress(address);
        entity.setPersonShokugyou(shokugyou);
        entity.setIsAffected(true);
        entity.setIsFinish(false);
        entity.setJudgeReason("正)");
        return entity;
    }
}