package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * MasterPersonAddStdCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterPersonAddStdCsvItemReaderTest.sql")
class MasterPersonAddStdCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdCsvItemReader masterPersonAddStdCsvItemReader;
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        StepExecution stepExecution = this.getStepExecutionFormat();
        masterPersonAddStdCsvItemReader.beforeStep(stepExecution);
        masterPersonAddStdCsvItemReader.open(stepExecution.getExecutionContext());
        
        KanrenshaPersonAddStdDto dto = masterPersonAddStdCsvItemReader.read();

        assertEquals("名称", dto.getKanrenshaName());
        assertEquals("全住所", dto.getAllAddress());
        assertEquals("個人職業", dto.getPersonShokugyou());
        assertEquals("住所郵便番号まで", dto.getAddressPostal());
        assertEquals("住所番地まで", dto.getAddressBlock());
        assertEquals("住所建物まで", dto.getAddressBuilding());
        assertEquals("郵便番号1", dto.getPostalcode1());
        assertEquals("郵便番号2", dto.getPostalcode2());
        assertEquals("電話番号市外局番", dto.getPhon1());
        assertEquals("電話番号局番", dto.getPhon2());
        assertEquals("電話番号番号", dto.getPhon3());
        assertEquals("メールアドレス", dto.getEmail());
        assertEquals("自分の公式サイト", dto.getMyPortalUrl());
        assertEquals("SNSサービス名称", dto.getSnsServiceName());
        assertEquals("SNSサービスアカウント", dto.getSnsAccount());
        assertTrue(dto.getIsForeign());
        assertEquals("姓名の姓", dto.getLastName());
        assertEquals("姓名の名", dto.getFirstName());
        assertEquals("姓名のミドルネーム", dto.getMiddleName());
        assertEquals("姓名の姓のかな", dto.getLastNameKana());
        assertEquals("姓名の名のかな", dto.getFirstNameKana());
        assertEquals("姓名のミドルネームのかな", dto.getMiddleNameKana());
        assertEquals("職業の業種", dto.getGyoushu());
        assertEquals("職業の役職", dto.getYakushoku());
        assertEquals("職業のユーザ記載", dto.getShokugyouUserWrite());
        assertEquals("職業法人番号", dto.getKigyouDtNo());
        assertEquals("職業法人住所", dto.getKigyouDtAddress());
        assertEquals("職業法人名", dto.getKigyouDtName());
        assertEquals("地方公共団体コード", dto.getLgCode());
        assertEquals("町字Id", dto.getMachiazaId());
        assertEquals("街区Id", dto.getBlkId());
        assertEquals("地番Id", dto.getPrcId());
        assertEquals("住居Id", dto.getRsdtId());
        assertEquals("住居2Id", dto.getRsdt2Id());

        assertNull(masterPersonAddStdCsvItemReader.read());
    }


    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者個人標準.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
