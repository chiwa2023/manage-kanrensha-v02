package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterKigyouDtAddStdWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterKigyouDtAddStdWkTblFixItemWriterTest.sql")
class MasterKigyouDtAddStdWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterKigyouDtAddStdWkTblFixItemWriter masterKigyouDtAddStdWkTblFixItemWriter;

    /** 関連者企業団体マスタワークテーブルRepsoitory */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterRepository wkTblKanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void testWrite() throws Exception {

        StepExecution stepExecution = getStepExecution();
        masterKigyouDtAddStdWkTblFixItemWriter.beforeStep(stepExecution);

        WkTblKanrenshaKigyouDtMasterEntity entity1 = createEntity(1001, "団体名A", "正常終了"); // NOPMD
        WkTblKanrenshaKigyouDtMasterEntity entity2 = createEntity(1002, "団体名B", "正常終了");

        List<WkTblKanrenshaKigyouDtMasterEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        Chunk<WkTblKanrenshaKigyouDtMasterEntity> chunk = new Chunk<>(entities);
        masterKigyouDtAddStdWkTblFixItemWriter.write(chunk);

        List<WkTblKanrenshaKigyouDtMasterEntity> savedEntities = wkTblKanrenshaKigyouDtMasterRepository.findAll();
        assertEquals(2, savedEntities.size());
        assertEquals(1001, savedEntities.get(0).getWkTblKanrenshaKigyouDtMasterId());
        assertEquals(true, savedEntities.get(0).getIsFinish());
        assertEquals("正常終了", savedEntities.get(0).getJudgeReason());
        assertEquals(1002, savedEntities.get(1).getWkTblKanrenshaKigyouDtMasterId());
        assertEquals(true, savedEntities.get(1).getIsFinish());
        assertEquals("正常終了", savedEntities.get(1).getJudgeReason());

    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaKigyouDtMasterEntity createEntity(Integer id, String name, String reason) { // NOPMD
        WkTblKanrenshaKigyouDtMasterEntity entity = new WkTblKanrenshaKigyouDtMasterEntity();
        entity.setWkTblKanrenshaKigyouDtMasterId(id);
        entity.setKanrenshaName(name);
        entity.setAllAddress("住所");
        entity.setKigyouDtDelegate("代表者");
        entity.setHoujinNo("1234567890123");
        entity.setIsAffected(true);
        entity.setIsFinish(true);
        entity.setJudgeReason(reason);
        entity.setWkTblKanrenshaKigyouDtMasterCode(100);
        return entity;
    }
}
