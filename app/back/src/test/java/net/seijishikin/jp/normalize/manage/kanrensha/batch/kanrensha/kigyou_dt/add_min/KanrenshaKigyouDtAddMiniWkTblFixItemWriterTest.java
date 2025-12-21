package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaKigyouDtAddMiniWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtAddMiniWkTblFixItemWriterTest.sql")
class KanrenshaKigyouDtAddMiniWkTblFixItemWriterTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddMiniWkTblFixItemWriter kanrenshaKigyouDtAddMiniWkTblFixItemWriter;

    /** ワークテーブル関連者企業団体最小登録Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaKigyouDtAddMinEntity entity00 = new WkTblKanrenshaKigyouDtAddMinEntity();
        entity00.setAllAddress("全住所");
        entity00.setKigyouDtDelegate("代表者");
        entity00.setHoujinNo("123-4556");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");
        entity00.setKanrenshaName("団体名");

        List<WkTblKanrenshaKigyouDtAddMinEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaKigyouDtAddMinEntity> items = new Chunk<>(list);

        kanrenshaKigyouDtAddMiniWkTblFixItemWriter.beforeStep(this.getStepExecution());
        kanrenshaKigyouDtAddMiniWkTblFixItemWriter.write(items);

        List<WkTblKanrenshaKigyouDtAddMinEntity> listAns = wkTblKanrenshaKigyouDtAddMinRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblKanrenshaKigyouDtAddMinEntity entityAns = listAns.get(0);

        assertEquals(entity00.getAllAddress(), entityAns.getAllAddress());
        assertEquals(entity00.getKigyouDtDelegate(), entityAns.getKigyouDtDelegate());
        assertEquals(entity00.getHoujinNo(), entityAns.getHoujinNo());
        assertEquals(entity00.getIsAffected(), entityAns.getIsAffected());
        assertEquals(entity00.getIsFinish(), entityAns.getIsFinish());
        assertEquals(entity00.getJudgeReason(), entityAns.getJudgeReason());
        assertEquals(entity00.getKanrenshaName(), entityAns.getKanrenshaName());
        
        
        
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
