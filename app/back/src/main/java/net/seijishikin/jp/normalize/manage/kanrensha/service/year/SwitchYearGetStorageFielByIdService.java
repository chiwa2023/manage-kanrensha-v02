package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.GetSaveStorageY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.GetSaveStorageY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.GetSaveStorageY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.GetSaveStorageY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.GetSaveStorageY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.GetSaveStorageY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.GetSaveStorageY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.GetSaveStorageY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.GetSaveStorageY2027Logic;

/**
 * 年切り替え保存ファイル取得Service
 */
@Service
public class SwitchYearGetStorageFielByIdService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画挿入Logic(2019) */
    @Autowired
    private GetSaveStorageY2019Logic getSaveStorageY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画挿入Logic(2020) */
    @Autowired
    private GetSaveStorageY2020Logic getSaveStorageY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画挿入Logic(2021) */
    @Autowired
    private GetSaveStorageY2021Logic getSaveStorageY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画挿入Logic(2022) */
    @Autowired
    private GetSaveStorageY2022Logic getSaveStorageY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画挿入Logic(2023) */
    @Autowired
    private GetSaveStorageY2023Logic getSaveStorageY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画挿入Logic(2024) */
    @Autowired
    private GetSaveStorageY2024Logic getSaveStorageY2024Logic;

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
    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画挿入Logic(2027) */
    @Autowired
    private GetSaveStorageY2027Logic getSaveStorageY2027Logic;

    // field次回追加位置

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

            // 2019年
            case YEAR_2019:
                return getSaveStorageY2019Logic.practice(taskId);

            // 2020年
            case YEAR_2020:
                return getSaveStorageY2020Logic.practice(taskId);

            // 2021年
            case YEAR_2021:
                return getSaveStorageY2021Logic.practice(taskId);

            // 2022年
            case YEAR_2022:
                return getSaveStorageY2022Logic.practice(taskId);

            // 2023年
            case YEAR_2023:
                return getSaveStorageY2023Logic.practice(taskId);

            // 2024年
            case YEAR_2024:
                return getSaveStorageY2024Logic.practice(taskId);

            // 2025年
            case YEAR_2025:
                return getSaveStorageY2025Logic.practice(taskId);

            // 2026年
            case YEAR_2026:
                return getSaveStorageY2026Logic.practice(taskId);

            // 2027年
            case YEAR_2027:
                return getSaveStorageY2027Logic.practice(taskId);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
