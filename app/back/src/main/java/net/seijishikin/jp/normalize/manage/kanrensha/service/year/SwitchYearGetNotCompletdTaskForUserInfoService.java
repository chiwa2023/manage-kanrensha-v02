package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.GetNotCompletdTaskY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.GetNotCompletdTaskY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.GetNotCompletdTaskY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.GetNotCompletdTaskY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.GetNotCompletdTaskY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.GetNotCompletdTaskY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.GetNotCompletdTaskY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.GetNotCompletdTaskY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.GetNotCompletdTaskY2027Logic;

/**
 * 年切り替え未処理タスク計画取得Service
 */
@Service
public class SwitchYearGetNotCompletdTaskForUserInfoService {

    /** 取得件数 */
    private static final Integer NOT_COMPLETE_TASK_LIMIT = 5;

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画履歴取得Logic(2019) */
    @Autowired
    private GetNotCompletdTaskY2019Logic getNotCompletdTaskY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画履歴取得Logic(2020) */
    @Autowired
    private GetNotCompletdTaskY2020Logic getNotCompletdTaskY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画履歴取得Logic(2021) */
    @Autowired
    private GetNotCompletdTaskY2021Logic getNotCompletdTaskY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画履歴取得Logic(2022) */
    @Autowired
    private GetNotCompletdTaskY2022Logic getNotCompletdTaskY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画履歴取得Logic(2023) */
    @Autowired
    private GetNotCompletdTaskY2023Logic getNotCompletdTaskY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画履歴取得Logic(2024) */
    @Autowired
    private GetNotCompletdTaskY2024Logic getNotCompletdTaskY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画履歴取得Logic(2025) */
    @Autowired
    private GetNotCompletdTaskY2025Logic getNotCompletdTaskY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画履歴取得Logic(2026) */
    @Autowired
    private GetNotCompletdTaskY2026Logic getNotCompletdTaskY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画履歴取得Logic(2027) */
    @Autowired
    private GetNotCompletdTaskY2027Logic getNotCompletdTaskY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param year    実行年
     * @param userDto ユーザ最小限
     * @return 検索結果リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer year, final LeastUserDto userDto) {

        switch (year) {

            // 2019年
            case YEAR_2019:
                return getNotCompletdTaskY2019Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2020年
            case YEAR_2020:
                return getNotCompletdTaskY2020Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2021年
            case YEAR_2021:
                return getNotCompletdTaskY2021Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2022年
            case YEAR_2022:
                return getNotCompletdTaskY2022Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2023年
            case YEAR_2023:
                return getNotCompletdTaskY2023Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2024年
            case YEAR_2024:
                return getNotCompletdTaskY2024Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2025年
            case YEAR_2025:
                return getNotCompletdTaskY2025Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2026年
            case YEAR_2026:
                return getNotCompletdTaskY2026Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2027年
            case YEAR_2027:
                return getNotCompletdTaskY2027Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
