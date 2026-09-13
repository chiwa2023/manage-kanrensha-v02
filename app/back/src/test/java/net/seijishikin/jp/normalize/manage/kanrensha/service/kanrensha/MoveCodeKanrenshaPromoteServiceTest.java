package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Comparator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodePromoteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveCodeKanrenshaPromoteServic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("MoveCodeKanrenshaPromoteServiceTest.sql")
class MoveCodeKanrenshaPromoteServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeKanrenshaPromoteService moveCodeKanrenshaPromoteService;

    /** 関連者コード移動Repository */
    @Autowired
    private KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        LocalDateTime createDateTime = LocalDateTime.of(2025, 11, 13, 12, 34, 56);

        final String finleName = "utf_del_2603-05.csv";
        StorageFileDto fileDto = this.createStorageFile(finleName);

        MoveKanrenshaCodePromoteCapsuleDto capsuleDto = new MoveKanrenshaCodePromoteCapsuleDto();
        capsuleDto.setStorageFileDto(fileDto);

        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        capsuleDto.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);
        capsuleDto.setAbolishKanrenshaCode("12345");
        capsuleDto.setAbolishKanrenshaName("廃止名称");
        capsuleDto.setOriginKanrenshaCode("9786");
        capsuleDto.setOriginName("残す名称");
        capsuleDto.setMoveReason("移動理由");
        capsuleDto.setIsAbolishLast(true);

        Integer savedId = moveCodeKanrenshaPromoteService.practice(createDateTime, capsuleDto);

        // 確かに保存されている
        assertNotEquals(0, savedId);

        // 詳細を確認
        KanrenshaCodeMoveEntity moveEntity = kanrenshaCodeMoveRepository.findById(savedId).get();

        assertEquals(capsuleDto.getKanrenshaKbn(), moveEntity.getKanrenshaKbn());
        assertEquals(capsuleDto.getAbolishKanrenshaCode(), moveEntity.getAbolishKanrenshaCode());
        assertEquals(capsuleDto.getAbolishKanrenshaName(), moveEntity.getAbolishKanrenshaName());
        assertEquals(capsuleDto.getOriginKanrenshaCode(), moveEntity.getOriginKanrenshaCode());
        assertEquals(capsuleDto.getOriginName(), moveEntity.getOriginName());
        assertEquals(capsuleDto.getMoveReason(), moveEntity.getMoveReason());
        assertEquals(createDateTime.getYear(), moveEntity.getTaskYear());
        assertEquals(true, moveEntity.getIsAbolishLast());
        assertEquals(ShinseiStatusConstants.PROMOTE, moveEntity.getMoveStatus());
    }

    private StorageFileDto createStorageFile(final String fileName) throws IOException {

        Path readFilePath = Paths.get("190/test/", fileName);

        Path readFilePathAbs = Paths.get(storageFolder, readFilePath.toString());

        // 中身を削除
        if (Files.exists(readFilePathAbs.getParent())) {
            Files.walk(readFilePathAbs.getParent()).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .forEach(java.io.File::delete);
        }

        Files.createDirectories(readFilePathAbs.getParent());

        // サンプルファイルが存在しないときは複写
        if (!Files.exists(readFilePathAbs)) {
            Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode",
                    fileName);
            Files.copy(pathSrc, readFilePathAbs);
        }
        assertTrue(Files.exists(readFilePathAbs));

        StorageFileDto fileDto = new StorageFileDto();
        fileDto.setSavedDir("190/test/");
        fileDto.setFileName(fileName);

        return fileDto;
    }

}
