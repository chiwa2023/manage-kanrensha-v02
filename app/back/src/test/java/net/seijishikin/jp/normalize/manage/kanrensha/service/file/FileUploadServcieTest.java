package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.SaveFileStorage2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * FileUploadServcie単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CopyTempToUseSavedFileServiceTest.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class FileUploadServcieTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private FileUploadServcie fileUploadServcie;

    /** 書証保存Repository */
    @Autowired
    private SaveFileStorage2025Repository saveFileStorage2025Repository;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // TODO 書証区分、タスク情報区分の処理は確定後テスト構築する

        String fileName = "mt_city_all.csv";

        UploadContentCapsuleDto capsuleDto = new UploadContentCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        UploadFileDto uploadFileDto = new UploadFileDto();
        uploadFileDto.setFileName(fileName);
        // ファイルをバイナリで呼び出し
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
        byte[] bytes = Files.readAllBytes(path);
        uploadFileDto.setFileContent(Base64.getEncoder().encodeToString(bytes));
        capsuleDto.setUploadFileDto(uploadFileDto);

        Path pathSaved = fileUploadServcie.practice(2025, capsuleDto);

        // コピー成功
        assertTrue(Files.exists(pathSaved));

        // 空の予定テーブル、空のタスクテーブルに何か行が追加されたことだけをチェック
        // 内容は各Serviceで確認する
        assertEquals(1L, saveFileStorage2025Repository.count());
        assertEquals(1L, taskPlan2025Repository.count());
    }

}
