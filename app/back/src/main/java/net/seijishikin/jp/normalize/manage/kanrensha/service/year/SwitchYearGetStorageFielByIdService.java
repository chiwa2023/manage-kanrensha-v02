package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.GetSaveStorageY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.GetSaveStorageY2026Logic;

/**
 * 年切り替え保存ファイル取得Service
 */
@Service
public class SwitchYearGetStorageFielByIdService {

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private GetSaveStorageY2025Logic getSaveStorageY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画挿入Logic(2026) */
    @Autowired
    private GetSaveStorageY2026Logic getSaveStorageY2026Logic;

    /**
     * 処理を行う
     * 
     * @param year   保存年
     * @param taskId ファイル保存Id
     * @return ファイル内容Dto
     * @throws IOException ファイル取得時例外
     */
    public OneFileBlobResultDto practice(final int year, final int taskId) throws IOException {

        switch (year) {
            case YEAR_2025:
                return getSaveStorageY2025Logic.practice(taskId);
            case YEAR_2026:
                return getSaveStorageY2026Logic.practice(taskId);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
