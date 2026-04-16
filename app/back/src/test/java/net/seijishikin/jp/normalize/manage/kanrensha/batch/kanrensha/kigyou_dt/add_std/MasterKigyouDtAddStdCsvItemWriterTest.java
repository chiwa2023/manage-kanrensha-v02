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
 * MasterKigyouDtAddStdCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MasterKigyouDtAddStdCsvItemWriterTest.sql")
class MasterKigyouDtAddStdCsvItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterKigyouDtAddStdCsvItemWriter masterKigyouDtAddStdCsvItemWriter;

    /** 関連者企業・団体マスタワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterRepository wkTblKanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWrite() throws Exception {

        // Call beforeStep to set userDto
        StepExecution stepExecution = getStepExecution();
        masterKigyouDtAddStdCsvItemWriter.beforeStep(stepExecution);

        // Prepare test data
        WkTblKanrenshaKigyouDtMasterEntity entity01 = createTestEntity("団体名A", "住所A", "代表者A", "1111111111111");
        WkTblKanrenshaKigyouDtMasterEntity entity02 = createTestEntity("団体名B", "住所B", "代表者B", "2222222222222");

        List<WkTblKanrenshaKigyouDtMasterEntity> list = new ArrayList<>();
        list.add(entity01);
        list.add(entity02);

        Chunk<? extends WkTblKanrenshaKigyouDtMasterEntity> items = new Chunk<>(list);

        masterKigyouDtAddStdCsvItemWriter.write(items);

        // Verify data saved
        List<WkTblKanrenshaKigyouDtMasterEntity> savedEntities = wkTblKanrenshaKigyouDtMasterRepository.findAll();
        assertEquals(2, savedEntities.size());

        // Verify content and generated code
        WkTblKanrenshaKigyouDtMasterEntity savedEntity01 = savedEntities.get(0);
        assertEquals("団体名A", savedEntity01.getKanrenshaName());
        assertEquals(1, savedEntity01.getWkTblKanrenshaKigyouDtMasterCode());

        WkTblKanrenshaKigyouDtMasterEntity savedEntity02 = savedEntities.get(1);
        assertEquals("団体名B", savedEntity02.getKanrenshaName());
        assertEquals(2, savedEntity02.getWkTblKanrenshaKigyouDtMasterCode());

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWriteWithExistingData() throws Exception {
        // Insert some initial data to test code increment
        WkTblKanrenshaKigyouDtMasterEntity existingEntity = createTestEntity("既存団体", "既存住所", "既存代表", "9999999999999");
        existingEntity.setWkTblKanrenshaKigyouDtMasterCode(100);
        wkTblKanrenshaKigyouDtMasterRepository.save(existingEntity);

        // Call beforeStep to set userDto
        StepExecution stepExecution = getStepExecution();
        masterKigyouDtAddStdCsvItemWriter.beforeStep(stepExecution);

        // Prepare test data
        WkTblKanrenshaKigyouDtMasterEntity entity01 = createTestEntity("新規団体C", "新規住所C", "新規代表C", "3333333333333");
        WkTblKanrenshaKigyouDtMasterEntity entity02 = createTestEntity("新規団体D", "新規住所D", "新規代表D", "4444444444444");

        List<WkTblKanrenshaKigyouDtMasterEntity> list = new ArrayList<>();
        list.add(entity01);
        list.add(entity02);

        Chunk<? extends WkTblKanrenshaKigyouDtMasterEntity> items = new Chunk<>(list);

        masterKigyouDtAddStdCsvItemWriter.write(items);

        // Verify data saved
        List<WkTblKanrenshaKigyouDtMasterEntity> savedEntities = wkTblKanrenshaKigyouDtMasterRepository.findAll();
        assertEquals(3, savedEntities.size()); // 1 existing + 2 new

        // Verify content and generated code
        // The first new entity should have code 101
        WkTblKanrenshaKigyouDtMasterEntity savedEntityNew01 = savedEntities.stream()
                .filter(e -> "新規団体C".equals(e.getKanrenshaName())).findFirst().get();
        assertEquals(101, savedEntityNew01.getWkTblKanrenshaKigyouDtMasterCode());

        // The second new entity should have code 102
        WkTblKanrenshaKigyouDtMasterEntity savedEntityNew02 = savedEntities.stream()
                .filter(e -> "新規団体D".equals(e.getKanrenshaName())).findFirst().get();
        assertEquals(102, savedEntityNew02.getWkTblKanrenshaKigyouDtMasterCode());

    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private WkTblKanrenshaKigyouDtMasterEntity createTestEntity( //
            String name, String address, String delegate, String houjinNo) { // NOPMD
        WkTblKanrenshaKigyouDtMasterEntity entity = new WkTblKanrenshaKigyouDtMasterEntity();
        entity.setKanrenshaName(name);
        entity.setAllAddress(address);
        entity.setKigyouDtDelegate(delegate);
        entity.setHoujinNo(houjinNo);
        entity.setIsAffected(true);
        entity.setIsFinish(false);
        entity.setJudgeReason("正)");
        // 他のフィールドも必要に応じて設定
        entity.setAddressPostal("住所郵便");
        entity.setAddressBlock("住所番地");
        entity.setAddressBuilding("ビル名");
        entity.setPostalcode1("123");
        entity.setPostalcode2("4567");
        entity.setPhon1("01");
        entity.setPhon2("2345");
        entity.setPhon3("6789");
        entity.setEmail("test@example.com");
        entity.setMyPortalUrl("http://example.com");
        entity.setIsForeign(false);
        entity.setHoujinSbts("株式会社");
        entity.setOrgNameKana("カブシキガイシャ");
        entity.setIsShiten(false);
        entity.setOrgDelegateCode("D-CODE");
        entity.setSnsServiceName("Twitter");
        entity.setSnsAccount("@test");
        entity.setLgCode("131012");
        entity.setMachiazaId("machiaza");
        entity.setBlkId("blk");
        entity.setPrcId("prc");
        entity.setRsdtId("rsdt");
        entity.setRsdt2Id("rsdt2");
        return entity;
    }
}
