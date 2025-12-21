package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory13Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaSeijidantaiAddMiniRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("KanrenshaSeijidantaiAddMiniRecordItemWriterTest.sql")
class KanrenshaSeijidantaiAddMiniRecordItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiAddMiniRecordItemWriter kanrenshaSeijidantaiAddMiniRecordItemWriter;

    /** ワークテーブル関連者政治団体最小登録Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinResultRepository wkTblKanrenshaSeijidantaiAddMinResultRepository;

    /** 関連者政治団体履歴登録Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory13Repository kanrenshaSeijidantaiHistory13Repository;

    /** 関連者政治団体マスタ登録Repository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaSeijidantaiAddMinEntity entity00 = new WkTblKanrenshaSeijidantaiAddMinEntity();
        entity00.setWkTblKanrenshaSeijidantaiAddMinId(201);
        entity00.setKanrenshaName("テスト政治団体");
        entity00.setAllAddress("東京都千代田区");
        entity00.setSeijidantaiDelegate("テスト代表");
        entity00.setDantaiKbn("01");
        entity00.setPoliOrgNo("12345");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("");

        List<WkTblKanrenshaSeijidantaiAddMinEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaSeijidantaiAddMinEntity> items = new Chunk<>(listLoad);

        kanrenshaSeijidantaiAddMiniRecordItemWriter.beforeStep(this.getStepExecution());
        kanrenshaSeijidantaiAddMiniRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<KanrenshaSeijidantaiHistory13Entity> listHistory = kanrenshaSeijidantaiHistory13Repository.findAll();
        assertEquals(1, listHistory.size());

        KanrenshaSeijidantaiHistory13Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getKanrenshaName(), entity10.getAllName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getSeijidantaiDelegate(), entity10.getOrgDelegateName());
        assertTrue(entity10.getSeijidantaiKanrenshaCode().startsWith("12"));
        assertEquals("テスト政治団体東京都千代田区テスト代表", entity10.getSearchText());

        // マスタ本体に正常登録
        List<KanrenshaSeijidantaiMasterEntity> listMaster = kanrenshaSeijidantaiMasterRepository.findAll();
        assertEquals(1, listMaster.size());

        KanrenshaSeijidantaiMasterEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getKanrenshaName(), entity11.getKanrenshaName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getSeijidantaiDelegate(), entity11.getSeijidantaiDelegate());
        assertTrue(entity11.getSeijidantaiKanrenshaCode().startsWith("12"));
        assertEquals("テスト政治団体", entity11.getCompareNameText());

        // 処理結果に正常登録
        List<WkTblKanrenshaSeijidantaiAddMinResultEntity> listResult = wkTblKanrenshaSeijidantaiAddMinResultRepository
                .findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblKanrenshaSeijidantaiAddMinId(),
                listResult.get(0).getWkTblKanrenshaSeijidantaiAddMinId());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
