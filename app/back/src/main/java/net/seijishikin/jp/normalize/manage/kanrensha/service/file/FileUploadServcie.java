package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetStoragePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.SaveFileLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertSaveStorageService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;


/**
 * ファイルアップロードService
 * アップロードされたファイルを、ユーザが保存したものとして、正式の形で直接保存する。
 * 関連者ではファイルを頭出して、そのあと処理をするのでほとんど使う機会のない想定
 */
@Service
public class FileUploadServcie {

    /** ファイル保存フォルダ取得Logic */
    @Autowired
    private GetStoragePathLogic getStoragePathLogic;

    /** 絶対パス変換Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** ファイル保存Logic */
    @Autowired
    private SaveFileLogic saveFileLogic;

    /** ファイル保存(年管理)Service */
    @Autowired
    private SwitchYearInsertSaveStorageService switchYearInsertSaveStorageService;

    /** タスク計画(年管理)Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /**
     * 処理を行う
     *
     * @param year    処理年
     * @param capsuleDto アップロードファイルDto
     * @throws IOException ファイル書き込み例外
     */
    @Transactional
    public Path practice(final int year, final UploadContentCapsuleDto capsuleDto) throws IOException {

        LeastUserDto userDto = capsuleDto.getUserDto();
        
        Path childPath = getStoragePathLogic.practice(userDto);
        
        Path fullPath = getAbsolutePathLogic.practice(childPath.toString(),capsuleDto.getUploadFileDto().getFileName());
        
        // ファイルを保存する
        saveFileLogic.practice(fullPath, capsuleDto.getUploadFileDto().getFileContent());

        // TODO 書証区分を決定次第指定する
        switchYearInsertSaveStorageService.practice(year, userDto, fullPath, Short.valueOf("205"));
        // TODO タスク情報の入れ方を決定する
        switchYearInsertTaskPlanService.practice(year, userDto, TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV);
        
        return fullPath;
    }

}
