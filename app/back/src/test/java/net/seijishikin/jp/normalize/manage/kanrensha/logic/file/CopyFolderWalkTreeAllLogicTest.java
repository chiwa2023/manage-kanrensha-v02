package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;


/**
 * CopyFolderWalkTreeAllLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CopyFolderWalkTreeAllLogicTest {

    /** テスト対象 */
    @Autowired
    private CopyFolderWalkTreeAllLogic copyFolderWalkTreeAllLogic;

    /** ファイル置き場絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file", "stack_trace", "task_plan");

        Path pathCopy = Paths.get(getAbsolutePathLogic.getStorageFolder(), "test", "task_plan");

        // 複写先が存在していれば削除
        if(Files.exists(pathCopy)) {
            Files.walk(pathCopy).sorted(Comparator.reverseOrder()).filter(p -> !p.equals(pathCopy)).map(Path::toFile)
            .forEach(File::delete);
        }

        copyFolderWalkTreeAllLogic.practice(pathSrc, pathCopy);

        // 複写したすべてのディレクトリ、フォルダについて存在を確認
        String copyDirString = pathCopy.toString();
        assertTrue(Files.exists(Paths.get(copyDirString, "2025")));
        assertTrue(Files.exists(Paths.get(copyDirString, "2025", "329")));
        assertTrue(Files.exists(
                Paths.get(copyDirString, "2025", "329", "20251012105750866993400_IndexOutOfBoundsException_.log")));
    }

}
