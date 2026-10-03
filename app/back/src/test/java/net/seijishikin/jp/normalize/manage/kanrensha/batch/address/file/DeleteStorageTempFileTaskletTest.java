package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.file;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.DeleteFolderWalkTreeAllLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetTempFilePathLogic;

/**
 * DeleteStorageTempFileTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
class DeleteStorageTempFileTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteStorageTempFileTasklet deleteStorageTempFileTasklet;

    /** 一時ファイルパス指定Logic */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    /** ストレージ内絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** 再帰ファイル削除Logic */
    @Autowired
    private DeleteFolderWalkTreeAllLogic deleteFolderWalkTreeAllLogic;

    /** 削除月 */
    private static final Integer monthDelete = 3;

    @Test
    @Tag("TableTruncate")
    void testCreate() throws Exception {

        // 対象となるディレクトリを確定
        String savedDir = getTempFilePathLogic.practice(monthDelete, "").getSavedDir(); // ファイル名は指定なし
        Path path = getAbsolutePathLogic.practice(savedDir, "");

        // 指定ディレクトリを残して削除
        deleteFolderWalkTreeAllLogic.practice(path);
        // この検証ではルートも削除
        Files.deleteIfExists(path);

        deleteStorageTempFileTasklet.beforeStep(this.getStepExecution());
        deleteStorageTempFileTasklet.execute(null, null);

        // 次回のために指定した元ディレクトリだけは残っている
        assertTrue(Files.exists(path));
    }

    @Test
    @Tag("TableTruncate")
    void testDelete() throws Exception {

        // 対象となるディレクトリを確定
        String savedDir = getTempFilePathLogic.practice(monthDelete, "").getSavedDir(); // ファイル名は指定なし
        Path path = getAbsolutePathLogic.practice(savedDir, "");

        // 必ず削除すべきファイルを1件は作成
        Path pathFile = Paths.get(path.toString(), "aaa.txt");
        if (!Files.exists(pathFile)) {
            Files.createFile(pathFile);
        }

        deleteStorageTempFileTasklet.beforeStep(this.getStepExecution());
        deleteStorageTempFileTasklet.execute(null, null);

        // 元ディレクトリは残って配下のファイルは削除されている
        assertTrue(Files.exists(path));
        assertFalse(Files.exists(pathFile));
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("month", (long) monthDelete).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
