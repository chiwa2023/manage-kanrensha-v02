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
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;


/**
 * アップロード仮保存ファイルから正式保存記録を残すService
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class CopyTempToUseSavedFileService {

    /** ファイル保存(年管理)Service */
    @Autowired
    private SwitchYearInsertSaveStorageService switchYearInsertSaveStorageService;

    /** タスク計画(年管理)Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** 保存フォルダ作成Logic */
    @Autowired
    private GetStoragePathLogic getStoragePathLogic;

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

    /**
     * 処理を行う
     *
     * @param year          登録年
     * @param fileDto       tempファイル
     * @param userDto       ユーザ最小限Dto
     * @param fileType      ファイルタイプ
     * @param taskConstants タスク種類定数
     * @return 正規登録ファイルパス
     * @throws IOException ファイル保存例外
     */
    public Path practice(final int year, final StorageFileDto fileDto, final LeastUserDto userDto,
            final Short fileType, final int taskConstants) throws IOException {
        
        // 仮ファイルから本ファイルに複写
        Path pathTempFull = Paths.get(storageFolder, fileDto.getSavedDir(), fileDto.getFileName());
        Path pathSavedFull = Paths.get(storageFolder, getStoragePathLogic.practice(userDto).toString(),
                fileDto.getFileName());
        Path path = Files.copy(pathTempFull, pathSavedFull);

        // ファイルが保存出来たら保存場所を記録しスケジュールに登録
        switchYearInsertSaveStorageService.practice(year, userDto, pathSavedFull, fileType);
        switchYearInsertTaskPlanService.practice(year, userDto, taskConstants);

        return path;
    }

}
