package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
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
 * SuspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SuspendDuplicateWkTblKanrenshaSeijidantaiHistoryTaskletTest.sql")
class SuspendDuplicateWkTblKanrenshaSeijidantaiHistoryTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SuspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet suspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet;
    
    /** 関連者企業・団体登録最小限Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTblKanrenshaSeijidantaiHistoryRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String reason = "アップロードファイル内で重複しているデータです";

        suspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet.beforeStep(this.getStepExecution());
        suspendDuplicateWkTblKanrenshaSeijidantaiHistoryTasklet.execute(null, null);

        WkTblKanrenshaSeijidantaiHistoryEntity entity501 = wkTblKanrenshaSeijidantaiHistoryRepository.findById(5001).get();
        assertEquals(false, entity501.getIsLatest());
        assertEquals(true, entity501.getIsFinish());
        assertEquals(reason, entity501.getJudgeReason());
        WkTblKanrenshaSeijidantaiHistoryEntity entity502 = wkTblKanrenshaSeijidantaiHistoryRepository.findById(5002).get();
        assertEquals(false, entity502.getIsLatest());
        assertEquals(true, entity502.getIsFinish());
        assertEquals(reason, entity502.getJudgeReason());
        WkTblKanrenshaSeijidantaiHistoryEntity entity601 = wkTblKanrenshaSeijidantaiHistoryRepository.findById(6001).get();
        assertEquals(false, entity601.getIsLatest());
        assertEquals(true, entity601.getIsFinish());
        assertEquals(reason, entity601.getJudgeReason());
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
