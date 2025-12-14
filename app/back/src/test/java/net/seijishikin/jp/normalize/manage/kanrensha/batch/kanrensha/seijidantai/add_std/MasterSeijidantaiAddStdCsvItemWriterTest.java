package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterSeijidantaiAddStdCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterSeijidantaiAddStdCsvItemWriterTest.sql")
class MasterSeijidantaiAddStdCsvItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterSeijidantaiAddStdCsvItemWriter masterSeijidantaiAddStdCsvItemWriter;

    /** 政治団体登録マスタテーブル */
    @Autowired
    private WkTblKanrenshaSeijidantaiMasterRepository wkTblKanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        masterSeijidantaiAddStdCsvItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiMasterEntity entity1 = createEntity("政治団体A", "住所A", "代表A", "01", "111");
        WkTblKanrenshaSeijidantaiMasterEntity entity2 = createEntity("政治団体B", "住所B", "代表B", "03", "222");

        List<WkTblKanrenshaSeijidantaiMasterEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaSeijidantaiMasterEntity> chunk = new Chunk<>(entities);
        masterSeijidantaiAddStdCsvItemWriter.write(chunk);

        List<WkTblKanrenshaSeijidantaiMasterEntity> savedEntities = wkTblKanrenshaSeijidantaiMasterRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(1, savedEntities.get(0).getWkTblKanrenshaSeijidantaiMasterCode());
        assertEquals("政治団体A", savedEntities.get(0).getKanrenshaName());
        assertEquals(2, savedEntities.get(1).getWkTblKanrenshaSeijidantaiMasterCode());
        assertEquals("政治団体B", savedEntities.get(1).getKanrenshaName());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteWithExistingData() throws Exception {
        WkTblKanrenshaSeijidantaiMasterEntity existingEntity = createEntity("既存政治団体", "既存住所", "既存代表", "01", "999");
        existingEntity.setWkTblKanrenshaSeijidantaiMasterCode(300);
        wkTblKanrenshaSeijidantaiMasterRepository.save(existingEntity);

        StepExecution stepExecution = getStepExecution();
        masterSeijidantaiAddStdCsvItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaSeijidantaiMasterEntity entity1 = createEntity("政治団体C", "住所C", "代表C", "04", "333");
        List<WkTblKanrenshaSeijidantaiMasterEntity> entities = new ArrayList<>();
        entities.add(entity1);
        Chunk<WkTblKanrenshaSeijidantaiMasterEntity> chunk = new Chunk<>(entities);
        masterSeijidantaiAddStdCsvItemWriter.write(chunk);

        List<WkTblKanrenshaSeijidantaiMasterEntity> savedEntities = wkTblKanrenshaSeijidantaiMasterRepository.findAll();
        assertEquals(2, savedEntities.size());
        WkTblKanrenshaSeijidantaiMasterEntity newEntity = savedEntities.stream()
                .filter(e -> e.getWkTblKanrenshaSeijidantaiMasterCode() == 301).findFirst().get();
        assertEquals("政治団体C", newEntity.getKanrenshaName());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaSeijidantaiMasterEntity createEntity(final String name, final String address,
            final String delegate, final String dantaiKbn, final String poliOrgNo) {
        WkTblKanrenshaSeijidantaiMasterEntity entity = new WkTblKanrenshaSeijidantaiMasterEntity();
        entity.setKanrenshaName(name);
        entity.setAllAddress(address);
        entity.setSeijidantaiDelegate(delegate);
        entity.setDantaiKbn(dantaiKbn);
        entity.setPoliOrgNo(poliOrgNo);
        entity.setIsAffected(true);
        entity.setIsFinish(false);
        entity.setJudgeReason("正)");
        return entity;
    }
}
