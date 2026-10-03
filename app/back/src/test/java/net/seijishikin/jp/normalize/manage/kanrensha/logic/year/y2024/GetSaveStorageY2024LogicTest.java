package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.SaveFileStorage2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.SaveFileStorage2024Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.FileUploadServcie;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetSaveStorageY2024Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetSaveStorageY2024LogicTest.sql")
class GetSaveStorageY2024LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetSaveStorageY2024Logic getSaveStorageY2024Logic;

    /** ファイルアップロードService */
    @Autowired
    private FileUploadServcie fileUploadServcie;

    /** 保存ファイルRepository(2024) */
    @Autowired
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

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

        LocalDateTime dateTimeStart = LocalDateTime.of(2024, 7, 26, 12, 22, 56);

        Path pathSaved = fileUploadServcie.practice(dateTimeStart, capsuleDto, CreateQueryParamDummyUtil.practice());

        // コピー成功
        assertTrue(Files.exists(pathSaved));

        List<SaveFileStorage2024Entity> listSave = saveFileStorage2024Repository.findAll();
        assertEquals(1, listSave.size()); // 空に対して1件追加
        final Integer storageId = listSave.get(0).getSaveFileStorageId();
        assertEquals(111, storageId); // テスト前にストレージ情報をクリアしたときに auto incrementを110まで使用している設定

        // 存在しないIdを指定すると例外
        assertThrows(EmptyResultDataAccessException.class, () -> getSaveStorageY2024Logic.practice(24));

        OneFileBlobResultDto resultDto = getSaveStorageY2024Logic.practice(storageId);
        assertNotEquals("", resultDto.getFileName());
        // アップロードファイルとダウンロードファイルのbase64バイナリ変換が同一
        assertEquals(uploadFileDto.getFileContent(), resultDto.getFileContentBase64()); 
    }

}
