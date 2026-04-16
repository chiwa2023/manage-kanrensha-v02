package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterKigyouDtAddStdRecordItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterKigyouDtAddStdRecordItemReaderTest.sql")
class MasterKigyouDtAddStdRecordItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterKigyouDtAddStdRecordItemReader masterKigyouDtAddStdRecordItemReader;

    @Test
    @Tag("TableTruncate")
    void testRead() throws Exception {
        // userCode 190 のユーザーで実行
        masterKigyouDtAddStdRecordItemReader.beforeStep(this.getStepExecution(190));

        // 期待される読み込み順序はID順、かつ条件に合致するもののみ
        // ID: 1001 (userCode=190, isLatest=1, isAffected=1, isFinish=0) -> 読み込まれる
        assertEquals(1001, masterKigyouDtAddStdRecordItemReader.read().getWkTblKanrenshaKigyouDtMasterId());
        // ID: 1002 (userCode=190, isLatest=1, isAffected=1, isFinish=0) -> 読み込まれる
        assertEquals(1002, masterKigyouDtAddStdRecordItemReader.read().getWkTblKanrenshaKigyouDtMasterId());
        // ID: 1003 (userCode=190, isLatest=1, isAffected=0, isFinish=0) -> isAffected=0 なので読み込まれない
        // ID: 1004 (userCode=190, isLatest=0, isAffected=1, isFinish=0) -> isLatest=0 なので読み込まれない
        // ID: 1005 (userCode=190, isLatest=1, isAffected=1, isFinish=1) -> isFinish=1 なので読み込まれない
        // ID: 1006 (userCode=191, isLatest=1, isAffected=1, isFinish=0) -> userCodeが異なるので読み込まれない
        assertNull(masterKigyouDtAddStdRecordItemReader.read());
    }

    private StepExecution getStepExecution(final long userCode) {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", userCode)
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
