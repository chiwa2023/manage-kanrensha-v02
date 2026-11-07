package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetTempFilePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.SaveFileBase64Logic;

/**
 * 汎用ファイル保存Service
 */
@Service
public class SaveFileTempFolderService {

    /** 一時ファイルパス取得Logic */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    /** ファイル絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** ファイル保存Logic */
    @Autowired
    private SaveFileBase64Logic saveFileBase64Logic;

    /**
     * 処理を行う
     * 
     * @param month         処理月
     * @param uploadFileDto アップロードファイルDto
     * @return 処理結果Dto
     * @throws IOException ファイル保存例外
     */
    public UploadFileResultDto practice(final int month, final UploadFileDto uploadFileDto) throws IOException {

        StorageFileDto storageFileDto = getTempFilePathLogic.practice(month, uploadFileDto.getFileName());

        UploadFileResultDto resultDto = new UploadFileResultDto();
        resultDto.setStorageFileDto(storageFileDto);

        Path path = getAbsolutePathLogic.practice(storageFileDto.getSavedDir(), storageFileDto.getFileName());

        if (saveFileBase64Logic.practice(path, uploadFileDto.getFileContent())) {
            return resultDto;

        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に保存できませんでした");
            return resultDto;

        }
    }

}
