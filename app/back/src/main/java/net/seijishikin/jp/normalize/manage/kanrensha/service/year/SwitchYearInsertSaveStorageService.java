package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.InsertSaveStorageY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.InsertSaveStorageY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.InsertSaveStorageY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.InsertSaveStorageY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.InsertSaveStorageY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.InsertSaveStorageY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertSaveStorageY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.InsertSaveStorageY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.InsertSaveStorageY2027Logic;

/**
 * 書証保存(年管理)Service
 */
@Service
public class SwitchYearInsertSaveStorageService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画挿入Logic(2019) */
    @Autowired
    private InsertSaveStorageY2019Logic insertSaveStorageY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画挿入Logic(2020) */
    @Autowired
    private InsertSaveStorageY2020Logic insertSaveStorageY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画挿入Logic(2021) */
    @Autowired
    private InsertSaveStorageY2021Logic insertSaveStorageY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画挿入Logic(2022) */
    @Autowired
    private InsertSaveStorageY2022Logic insertSaveStorageY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画挿入Logic(2023) */
    @Autowired
    private InsertSaveStorageY2023Logic insertSaveStorageY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画挿入Logic(2024) */
    @Autowired
    private InsertSaveStorageY2024Logic insertSaveStorageY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertSaveStorageY2025Logic insertSaveStorageY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画挿入Logic(2026) */
    @Autowired
    private InsertSaveStorageY2026Logic insertSaveStorageY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画挿入Logic(2027) */
    @Autowired
    private InsertSaveStorageY2027Logic insertSaveStorageY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     *
     * @param year       処理年
     * @param userDto    ユーザ最低限 Dto
     * @param path       ファイル保存フルパス
     * @param shoshouKbn 書証区分
     * @return テーブルId
     */
    @Transactional
    public Integer practice(final int year, final LeastUserDto userDto, final Path path, final Short shoshouKbn) {

        switch (year) {

            // 2019年
            case YEAR_2019:
                return insertSaveStorageY2019Logic.practice(userDto, path, shoshouKbn);

            // 2020年
            case YEAR_2020:
                return insertSaveStorageY2020Logic.practice(userDto, path, shoshouKbn);

            // 2021年
            case YEAR_2021:
                return insertSaveStorageY2021Logic.practice(userDto, path, shoshouKbn);

            // 2022年
            case YEAR_2022:
                return insertSaveStorageY2022Logic.practice(userDto, path, shoshouKbn);

            // 2023年
            case YEAR_2023:
                return insertSaveStorageY2023Logic.practice(userDto, path, shoshouKbn);

            // 2024年
            case YEAR_2024:
                return insertSaveStorageY2024Logic.practice(userDto, path, shoshouKbn);

            // 2025年
            case YEAR_2025:
                return insertSaveStorageY2025Logic.practice(userDto, path, shoshouKbn);

            // 2026年
            case YEAR_2026:
                return insertSaveStorageY2026Logic.practice(userDto, path, shoshouKbn);

            // 2027年
            case YEAR_2027:
                return insertSaveStorageY2027Logic.practice(userDto, path, shoshouKbn);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
