package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.lgcode;

import static org.junit.jupiter.api.Assertions.fail;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;

/**
 * CopyFileByPrefectureTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CopyFileByPrefectureTaskletTest {

    /** テスト対象 */
    @Autowired
    private CopyFileByPrefectureTasklet copyFileByPrefectureTasklet;

    /** テスト対象 */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        copyFileByPrefectureTasklet.beforeStep(this.getStepExecution());
        copyFileByPrefectureTasklet.execute(null, null);

        fail("Not yet implemented");
    }

    private StepExecution getStepExecution() {

        /* 元ソース */
        String srcPath = GetCurrentResourcePath.getBackSrcPath("");
        // エンティティ
        Path path = Paths.get(srcPath,
                 "/main/java/net/seijishikin/jp/normalize/manage/kanrensha/entity/lgcode/KanrenshaKigyouDtHistory99Entity.java");
        
        // レポジトリ
        // Path path = Paths.get(srcPath,
        //         "/main/java/net/seijishikin/jp/normalize/manage/kanrensha/repository/lgcode/KanrenshaKigyouDtHistory99Repository.java");

        // DDL
        // Path pathBackRoot = Paths.get(srcPath);
        //  Path pathApp = pathBackRoot.getParent().getParent().getParent();
        // Path pathDdl = Paths.get(pathApp.toString(), "config/database/DDL");
        // Path path = Paths.get(pathDdl.toString(), "kanrensha_seijidantai_history_99.sql");

        /* テスト */
        // String srcPath = GetCurrentResourcePath.getBackTestFilePath();
        // Path path = Paths.get(srcPath,
        // "/mitei/mitei/political/balancesheet/manage/kanrensha/batch/dump/history/DumpPartnerCorpHistory99ItemWriterTest.java");

        // Path path =
        // Paths.get("C:/workspace/git/pg/manage-kanrensha-v01/back/src/main/resources/DDL/partner_poli_org_history_99.sql");

        // 出力フォルダ
        Path pathOutput = getAbsolutePathLogic.practice("out_source", "");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("outputPath", pathOutput.toString()) //
                .addString("readPath", path.toString()) //
                .addString("key", "99") //
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
