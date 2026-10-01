package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.year;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * RefleshYearSourceTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearSourceTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearSourceTasklet refleshYearSourceTasklet;

    /** プロジェクトディレクトリ */
    private static final String DIR_ROOT = "main/java/net/seijishikin/jp/normalize/manage/kanrensha";

    /** テストディレクトリ */
    private static final String DIR_TEST_ROOT = "test/java/net/seijishikin/jp/normalize/manage/kanrensha";

    /** テストリソースディレクトリ */
    private static final String DIR_TEST_SOURCE_ROOT = "test/resources/net/seijishikin/jp/normalize/manage/kanrensha";

    /** Entityディレクトリ */
    private static final String DIR_ENTITY = "/entity/year";

    /** Repositoryディレクトリ */
    private static final String DIR_REPOSITORY = "/repository/year";

    /** Logicディレクトリ */
    private static final String DIR_LOGIC = "/logic/year";

    /** 年切り替えServiceディレクトリ */
    private static final String DIR_SWITCH_SERVICE = "/service/year";

    @Test
    void test() throws Exception {

        final Long srcYear = 2025L;
        final Long copyYear = 2024L;

        refleshYearSourceTasklet.beforeStep(this.getStepExecution(srcYear, copyYear));
        refleshYearSourceTasklet.execute(null, null);

        // 超いいかげんテスト・新規にディレクトリができていることを確認

        // entity,repository,logicの複写を行う
        String yearText = "/y" + copyYear;

        // this.copy(DIR_ROOT + DIR_ENTITY);
        assertTrue(Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_ROOT + DIR_ENTITY + yearText))));

        // this.copy(DIR_ROOT + DIR_REPOSITORY);
        assertTrue(
                Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_ROOT + DIR_REPOSITORY + yearText))));

        // this.copy(DIR_ROOT + DIR_LOGIC);
        assertTrue(Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_ROOT + DIR_LOGIC + yearText))));

        // テスト
        // this.copy(DIR_TEST_ROOT + DIR_LOGIC);
        assertTrue(
                Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_TEST_ROOT + DIR_LOGIC + yearText))));

        // this.copy(DIR_TEST_ROOT + DIR_SWITCH_SERVICE);
        assertTrue(Files.exists(
                Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_TEST_ROOT + DIR_SWITCH_SERVICE + yearText))));

        // テストリソース
        // this.copy(DIR_TEST_SOURCE_ROOT + DIR_LOGIC);
        assertTrue(Files
                .exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_TEST_SOURCE_ROOT + DIR_LOGIC + yearText))));

        // this.copy(DIR_TEST_SOURCE_ROOT + DIR_SWITCH_SERVICE);
        assertTrue(Files.exists(Paths
                .get(GetCurrentResourcePath.getBackSrcPath(DIR_TEST_SOURCE_ROOT + DIR_SWITCH_SERVICE + yearText))));
    }

    private StepExecution getStepExecution(final Long srcYear, final Long copyYear) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("exe_datetitme", LocalDateTime.now()) //
                .addLong(RefleshYearSourceTasklet.KEY_SRC_YEAR, srcYear) //
                .addLong(RefleshYearSourceTasklet.KEY_COPY_YEAR, copyYear).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
