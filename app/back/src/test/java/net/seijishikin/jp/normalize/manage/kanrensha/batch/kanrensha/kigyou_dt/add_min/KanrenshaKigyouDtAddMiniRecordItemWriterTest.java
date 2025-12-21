package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory45Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaKigyouDtAddMiniRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("KanrenshaKigyouDtAddMiniRecordItemWriterTest.sql")
class KanrenshaKigyouDtAddMiniRecordItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddMiniRecordItemWriter kanrenshaKigyouDtAddMiniRecordItemWriter;

    /** ワークテーブル関連者企業団体最小登録Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinResultRepository wkTblKanrenshaKigyouDtAddMinResultRepository;

    /** 関連者企業団体履歴登録Repository */
    @Autowired
    private KanrenshaKigyouDtHistory45Repository kanrenshaKigyouDtHistory45Repository;

    /** 関連者企業団体履歴登録Repository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaKigyouDtAddMinEntity entity00 = new WkTblKanrenshaKigyouDtAddMinEntity();
        entity00.setWkTblKanrenshaKigyouDtAddMinId(323);
        entity00.setKanrenshaName("超元素製造組合");
        entity00.setAllAddress("宮崎県架空市湖畔町");
        entity00.setKigyouDtDelegate("組合長　花子");
        entity00.setHoujinNo("1234ABCD");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("");

        List<WkTblKanrenshaKigyouDtAddMinEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaKigyouDtAddMinEntity> items = new Chunk<>(listLoad);

        kanrenshaKigyouDtAddMiniRecordItemWriter.beforeStep(this.getStepExecution());
        kanrenshaKigyouDtAddMiniRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<KanrenshaKigyouDtHistory45Entity> listHistory = kanrenshaKigyouDtHistory45Repository.findAll();
        assertEquals(1, listHistory.size());

        final String key = "1-234A-BC-D";

        KanrenshaKigyouDtHistory45Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getKanrenshaName(), entity10.getAllName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getKigyouDtDelegate(), entity10.getOrgDelegateName());
        assertTrue(entity10.getKigyouDtKanrenshaCode().startsWith(key));
        assertEquals("超元素製造組合宮崎県架空市湖畔町組合長花子", entity10.getSearchText());

        // マスタ本体に正常登録
        List<KanrenshaKigyouDtMasterEntity> listMaster = kanrenshaKigyouDtMasterRepository.findAll();
        assertEquals(1, listMaster.size());

        KanrenshaKigyouDtMasterEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getKanrenshaName(), entity11.getKanrenshaName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getKigyouDtDelegate(), entity11.getKigyouDtDelegate());
        assertTrue(entity11.getKigyouDtKanrenshaCode().startsWith(key));
        assertEquals("超元素製造組合", entity11.getCompareNameText());

        // 処理結果に正常登録
        List<WkTblKanrenshaKigyouDtAddMinResultEntity> listResult = wkTblKanrenshaKigyouDtAddMinResultRepository
                .findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblKanrenshaKigyouDtAddMinId(),
                listResult.get(0).getWkTblKanrenshaKigyouDtAddMinId());
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
