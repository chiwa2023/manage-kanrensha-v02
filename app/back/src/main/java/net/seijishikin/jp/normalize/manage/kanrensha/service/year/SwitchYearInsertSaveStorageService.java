package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertSaveStorageY2025Logic;

/**
 * 書証保存(年管理)Service
 */
@Service
public class SwitchYearInsertSaveStorageService {

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertSaveStorageY2025Logic insertSaveStorageY2025Logic;

    /**
     * 処理を行う
     *
     * @param year       処理年
     * @param userDto    ユーザ最低限 Dto
     * @param path       ファイル保存フルパス
     * @param shoshouKbn 書証区分
     * @return テーブルId
     */
    public Integer practice(final int year, final LeastUserDto userDto, final Path path, final Short shoshouKbn) {

        switch (year) {
            case YEAR_2025:
                return insertSaveStorageY2025Logic.practice(userDto, path, shoshouKbn);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
