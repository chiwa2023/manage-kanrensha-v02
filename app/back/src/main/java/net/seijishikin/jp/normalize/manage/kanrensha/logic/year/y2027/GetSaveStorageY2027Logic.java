package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2027.SaveFileStorage2027Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.SaveFileStorage2027Repository;

/**
 * 保存ファイル取得Logic(2027)
 */
@Component
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class GetSaveStorageY2027Logic {

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

    /** 保存ファイルRepository(2027) */
    @Autowired
    private SaveFileStorage2027Repository saveFileStorage2027Repository;

    /**
     * 処理を行う
     * 
     * @param storageId 保存ファイルId
     * @return 1ファイル内容Dto
     * @throws IOException ファイルアクセス時例外
     */
    public OneFileBlobResultDto practice(final Integer storageId) throws IOException {

        // MEMO
        // 他人申請の書証を上位権限者が確認するので、ユーザ一致、権限確認はfront側等からアクセスさせないことで実現する

        Optional<SaveFileStorage2027Entity> optional = saveFileStorage2027Repository.findById(storageId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("ファイルの格納情報が存在しませんでした", 1);
        }

        SaveFileStorage2027Entity entity = optional.get();

        OneFileBlobResultDto blobDto = new OneFileBlobResultDto();

        Path pathFile = Paths.get(storageFolder, entity.getChildDir(), entity.getFileName());
        blobDto.setFileName(entity.getFileName());
        blobDto.setFileContentBase64(new String(Base64.getEncoder().encode(Files.readAllBytes(pathFile))));

        return blobDto;
    }
}
