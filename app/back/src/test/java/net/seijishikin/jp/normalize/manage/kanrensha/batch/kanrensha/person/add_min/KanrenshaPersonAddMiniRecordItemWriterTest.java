package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory13Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaPersonAddMiniRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("KanrenshaPersonAddMiniRecordItemWriterTest.sql")
class KanrenshaPersonAddMiniRecordItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonAddMiniRecordItemWriter kanrenshaPersonAddMiniRecordItemWriter;

    /** ワークテーブル関連者個人最小登録Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinResultRepository wkTblKanrenshaPersonAddMinResultRepository;

    /** 関連者個人履歴登録Repository */
    @Autowired
    private KanrenshaPersonHistory13Repository kanrenshaPersonHistory13Repository;

    /** 関連者個人マスタ登録Repository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaPersonAddMinEntity entity00 = new WkTblKanrenshaPersonAddMinEntity();
        entity00.setWkTblKanrenshaPersonAddMinId(101);
        entity00.setKanrenshaName("テスト　太郎");
        entity00.setAllAddress("東京都千代田区");
        entity00.setPersonShokugyou("会社員");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("");

        List<WkTblKanrenshaPersonAddMinEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaPersonAddMinEntity> items = new Chunk<>(listLoad);

        kanrenshaPersonAddMiniRecordItemWriter.beforeStep(this.getStepExecution());
        kanrenshaPersonAddMiniRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<KanrenshaPersonHistory13Entity> listHistory = kanrenshaPersonHistory13Repository.findAll();
        assertEquals(1, listHistory.size());

        KanrenshaPersonHistory13Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getKanrenshaName(), entity10.getAllName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity10.getPersonShokugyou());
        assertNotEquals("", entity10.getPersonKanrenshaCode(),"初期値のままでない");
        assertEquals("テスト太郎東京都千代田区会社員", entity10.getSearchText());
       

        // マスタ本体に正常登録
        List<KanrenshaPersonMasterEntity> listMaster = kanrenshaPersonMasterRepository.findAll();
        assertEquals(1, listMaster.size());

        KanrenshaPersonMasterEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getKanrenshaName(), entity11.getKanrenshaName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity11.getPersonShokugyou());
        assertNotEquals("", entity11.getPersonKanrenshaCode(),"初期値のままでない");
       assertEquals("テスト太郎", entity11.getCompareNameText());
 
        // 処理結果に正常登録
        List<WkTblKanrenshaPersonAddMinResultEntity> listResult = wkTblKanrenshaPersonAddMinResultRepository
                .findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblKanrenshaPersonAddMinId(),
                listResult.get(0).getWkTblKanrenshaPersonAddMinId());
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
