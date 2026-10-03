package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2019;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2019.SaveFileStorage2019Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2019.SaveFileStorage2019Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.FileUploadServcie;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetStorageFielByIdService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearGetStorageFielByIdService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearGetStorageFielByIdY2019ServiceTest.sql")
class SwitchYearGetStorageFielByIdY2019ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetStorageFielByIdService switchYearGetStorageFielByIdService;

    /** ファイルアップロードService */
    @Autowired
    private FileUploadServcie fileUploadServcie;

    /** 保存ファイルRepository(2019) */
    @Autowired
    private SaveFileStorage2019Repository saveFileStorage2019Repository;

    @Test
    @Tag("TableTruncate")
    void test2019() throws Exception {

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

        LocalDateTime dateTimeStart = LocalDateTime.of(2019, 7, 26, 12, 22, 56);

        Path pathSaved = fileUploadServcie.practice(dateTimeStart, capsuleDto, CreateQueryParamDummyUtil.practice());

        // コピー成功
        assertTrue(Files.exists(pathSaved));

        List<SaveFileStorage2019Entity> listSave = saveFileStorage2019Repository.findAll();
        assertEquals(1, listSave.size()); // 空に対して1件追加
        final Integer storageId = listSave.get(0).getSaveFileStorageId();
        assertEquals(111, storageId); // テスト前にストレージ情報をクリアしたときに auto incrementを110まで使用している設定

        // 2019年で登録したので2019年で取得できる
        OneFileBlobResultDto resultDto = switchYearGetStorageFielByIdService.practice(2019, storageId);
        assertNotEquals("", resultDto.getFileName());
        // アップロードファイルとダウンロードファイルのbase64バイナリ変換が同一
        assertEquals(uploadFileDto.getFileContent(), resultDto.getFileContentBase64());
    }

}
