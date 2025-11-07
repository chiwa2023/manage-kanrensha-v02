package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * 保存ディレクトリを取得する
 */
@Component
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class GetStoragePathLogic {

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
     * @param userPersonLeastDto ユーザ最低限Dto
     * @return 保存ディレクトリ
     * @throws IOException ファイル書き込み時例外
     */
    public Path practice(final LeastUserDto userDto) throws IOException {

        // TODO クラス名変更(User情報存在を明確に)
        
        // 冗長だが毎回ディレクトリの有無を確認する
        Path parentPath = Paths.get(storageFolder);
        if (!Files.exists(parentPath) || !Files.isDirectory(parentPath)) {
            Files.createDirectories(parentPath);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(LocalDateTime.now());

        // 子ディレクトリも作成
        Path childPath = Paths.get(String.valueOf(userDto.getUserPersonCode()), unixTime,
                UUID.randomUUID().toString());
        String childDir = childPath.toString().replaceAll(File.pathSeparator, "/");
        Path fullPath = Paths.get(storageFolder, childDir);
        Files.createDirectories(fullPath);

        return childPath;
    }

}
