package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.util.UUID;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * (一定期間後に削除する)Tempファイル取得Dto
 */
@Component
public class GetTempFilePathLogic {

    /**
     * 処理を行う
     *
     * @param month    保存するディレクトリ(実施月)
     * @param fileName ファイル名
     * @return 保存ファイル情報
     */
    public StorageFileDto practice(final int month, final String fileName) {

        StorageFileDto storageFileDto = new StorageFileDto();

        // 一定期間後に内容を削除するファイルを一時ディレクトリに保存
        storageFileDto.setSavedDir("temp/" + this.getMonthText(month));
        storageFileDto.setFileName(UUID.randomUUID().toString() + "_" + fileName);

        return storageFileDto;
    }

    private String getMonthText(final int month) {

        final int digit2 = 10;
        if (digit2 > month) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }

}
