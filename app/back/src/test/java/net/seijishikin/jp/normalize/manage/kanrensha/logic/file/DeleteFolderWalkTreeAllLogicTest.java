package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * DeleteFolderWalkTreeAllLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteFolderWalkTreeAllLogicTest {

    /** テスト対象 */
    @Autowired
    private DeleteFolderWalkTreeAllLogic deleteFolderWalkTreeAllLogic;

    /** 一時ファイルパス指定Logic */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    /** ストレージ内絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // テスト前に最低限の削除できるファイルは作成
        StorageFileDto fileDto = getTempFilePathLogic.practice(1, "aaa.txt");

        String savedDir = fileDto.getSavedDir(); // ファイル名は指定なし
        Path path = getAbsolutePathLogic.practice(savedDir, "");
        Path pathFile = Paths.get(path.toString(), fileDto.getFileName());

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        if (!Files.exists(pathFile)) {
            Files.createFile(pathFile);
        }

        Path pathResult = deleteFolderWalkTreeAllLogic.practice(path);

        // 入出力が一緒
        assertEquals(path.toAbsolutePath(), pathResult.toAbsolutePath());
        // 指定したディレクトリは存在する
        assertTrue(Files.exists(pathResult));
        // ディレクトリ内のファイルは存在しない
        assertFalse(Files.exists(pathFile));
    }

}
