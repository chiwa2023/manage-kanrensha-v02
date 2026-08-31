package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetStoragePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertSaveStorageService;

/**
 * 一時ファイル正式ファイルService(タスク登録なし)
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class ModifyTempToStorageFileService {

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

    /** ファイル保存(年管理)Service */
    @Autowired
    private SwitchYearInsertSaveStorageService switchYearInsertSaveStorageService;

    /** 保存フォルダ作成Logic */
    @Autowired
    private GetStoragePathLogic getStoragePathLogic;

    /**
     * 処理を行う
     * 
     * @param year    発生年
     * @param userDto ユーザ最小限
     * @param fileDto 仮ファイルDto
     * @return 保存Path
     * @throws IOException ファイル書き込み例外
     */
    public String practice(final Integer year, final LeastUserDto userDto, final StorageFileDto fileDto,
            final Short fileType) throws IOException {

        // 仮ファイルから本ファイルに複写
        Path pathTempFull = Paths.get(storageFolder, fileDto.getSavedDir(), fileDto.getFileName());
        Path pathSavedFull = Paths.get(storageFolder, getStoragePathLogic.practice(userDto).toString(),
                fileDto.getFileName());
        final Path path = Files.copy(pathTempFull, pathSavedFull);

        // ファイルが保存出来たら保存場所を記録
        switchYearInsertSaveStorageService.practice(year, userDto, pathSavedFull, fileType);

        return path.toString();
    }
}
