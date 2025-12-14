package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

import static org.junit.jupiter.api.Assertions.*;

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
 * MasterKigyouDtAddStdCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterKigyouDtAddStdCsvItemReaderTest.sql")
class MasterKigyouDtAddStdCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private MasterKigyouDtAddStdCsvItemReader masterKigyouDtAddStdCsvItemReader;
    
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        StepExecution stepExecution = this.getStepExecutionFormat();
        masterKigyouDtAddStdCsvItemReader.beforeStep(stepExecution);
        masterKigyouDtAddStdCsvItemReader.open(stepExecution.getExecutionContext());
        
        KanrenshaKigyouDtAddStdDto dto01 = masterKigyouDtAddStdCsvItemReader.read();

        assertEquals("超元素製造組合", dto01.getKanrenshaName());
        assertEquals("和歌山県架空市山麓町", dto01.getAllAddress());
        assertEquals("組合長　花子", dto01.getKigyouDtDelegate());
        assertEquals("12334444", dto01.getHoujinNo());
        assertEquals("和歌山県実在市山麓町", dto01.getAddressPostal());
        assertEquals("3番地の4", dto01.getAddressBlock());
        assertEquals("四角ビル202号", dto01.getAddressBuilding());
        assertEquals("012", dto01.getPostalcode1());
        assertEquals("3456", dto01.getPostalcode2());
        assertEquals("023", dto01.getPhon1());
        assertEquals("4567", dto01.getPhon2());
        assertEquals("8901", dto01.getPhon3());
        assertEquals("aaa@bbb.net", dto01.getEmail());
        assertEquals("https://bbb.net/aaa", dto01.getMyPortalUrl());
        assertEquals(true, dto01.getIsForeign());
        assertEquals("401", dto01.getHoujinSbts());
        assertEquals("ちょうげんそせいぞうくみあい", dto01.getOrgNameKana());
        assertEquals(false, dto01.getIsShiten());
        assertEquals("98-777", dto01.getOrgDelegateCode());
        assertEquals("弱小SNS", dto01.getSnsServiceName());
        assertEquals("@bbb_aaa", dto01.getSnsAccount());
        assertEquals("098", dto01.getLgCode());
        assertEquals("765", dto01.getMachiazaId());
        assertEquals("432", dto01.getBlkId());
        assertEquals("543", dto01.getPrcId());
        assertEquals("210", dto01.getRsdtId());
        assertEquals("777", dto01.getRsdt2Id());

        assertNull(masterKigyouDtAddStdCsvItemReader.read());
        
    }


    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者企業団体標準.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
