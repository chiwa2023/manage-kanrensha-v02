package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * MasterSeijidantaiAddStdCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterSeijidantaiAddStdCsvItemReaderTest.sql")
class MasterSeijidantaiAddStdCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private MasterSeijidantaiAddStdCsvItemReader masterSeijidantaiAddStdCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat();
        masterSeijidantaiAddStdCsvItemReader.beforeStep(stepExecution);
        masterSeijidantaiAddStdCsvItemReader.open(stepExecution.getExecutionContext());

        KanrenshaSeijidantaiAddStdDto dto = masterSeijidantaiAddStdCsvItemReader.read();

        assertEquals("名称", dto.getKanrenshaName());
        assertEquals("全住所", dto.getAllAddress());
        assertEquals("政治団体代表者", dto.getSeijidantaiDelegate());
        assertEquals("02", dto.getDantaiKbn());
        assertEquals("政治団体番号", dto.getPoliOrgNo());
        assertEquals("住所郵便番号まで", dto.getAddressPostal());
        assertEquals("住所番地まで", dto.getAddressBlock());
        assertEquals("住所建物まで", dto.getAddressBuilding());
        assertEquals("234", dto.getPostalcode1());
        assertEquals("5678", dto.getPostalcode2());
        assertEquals("023", dto.getPhon1());
        assertEquals("4567", dto.getPhon2());
        assertEquals("8901", dto.getPhon3());
        assertEquals("メールアドレス", dto.getEmail());
        assertEquals("自分の公式サイト", dto.getMyPortalUrl());
        assertEquals("SNS名称", dto.getSnsServiceName());
        assertEquals("SNSアカウント", dto.getSnsAccount());
        assertEquals("関連者団体名称かな", dto.getOrgNameKana());
        assertEquals("団体代表者関連者コード", dto.getOrgDelegateCode());
        assertEquals("会計責任者関連者個人コード", dto.getAccountMgrCode());
        assertEquals("会計責任者関連者個人氏名", dto.getAccountMgrName());
        assertEquals("123456", dto.getLgCode());
        assertEquals("345", dto.getMachiazaId());
        assertEquals("456", dto.getBlkId());
        assertEquals("567", dto.getPrcId());
        assertEquals("678", dto.getRsdtId());
        assertEquals("789", dto.getRsdt2Id());

        assertNull(masterSeijidantaiAddStdCsvItemReader.read());
    }

    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者政治団体標準.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
