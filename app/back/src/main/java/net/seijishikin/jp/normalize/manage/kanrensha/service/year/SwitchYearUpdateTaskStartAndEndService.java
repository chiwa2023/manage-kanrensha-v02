package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.UpdateTaskStartAndEndY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.UpdateTaskStartAndEndY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.UpdateTaskStartAndEndY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.UpdateTaskStartAndEndY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.UpdateTaskStartAndEndY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.UpdateTaskStartAndEndY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateTaskStartAndEndY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.UpdateTaskStartAndEndY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.UpdateTaskStartAndEndY2027Logic;

/**
 * タスク計画終了更新年切替
 */
@Service
public class SwitchYearUpdateTaskStartAndEndService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画終了更新Logic(2019) */
    @Autowired
    private UpdateTaskStartAndEndY2019Logic updateTaskStartAndEndY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画終了更新Logic(2020) */
    @Autowired
    private UpdateTaskStartAndEndY2020Logic updateTaskStartAndEndY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画終了更新Logic(2021) */
    @Autowired
    private UpdateTaskStartAndEndY2021Logic updateTaskStartAndEndY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画終了更新Logic(2022) */
    @Autowired
    private UpdateTaskStartAndEndY2022Logic updateTaskStartAndEndY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画終了更新Logic(2023) */
    @Autowired
    private UpdateTaskStartAndEndY2023Logic updateTaskStartAndEndY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画終了更新Logic(2024) */
    @Autowired
    private UpdateTaskStartAndEndY2024Logic updateTaskStartAndEndY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画終了更新Logic(2025) */
    @Autowired
    private UpdateTaskStartAndEndY2025Logic updateTaskStartAndEndY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画終了更新Logic(2026) */
    @Autowired
    private UpdateTaskStartAndEndY2026Logic updateTaskStartAndEndY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画終了更新Logic(2027) */
    @Autowired
    private UpdateTaskStartAndEndY2027Logic updateTaskStartAndEndY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param userDto    ユーザ最小限
     * @param year       登録年
     * @param taskPlanId タスク計画Id
     * @param endTime    終了時間
     * @return 処理後最新Id
     */
    @Transactional
    public Integer practice(final LeastUserDto userDto, final Integer year, final Integer taskPlanId,
            final LocalDateTime endTime) {

        switch (year) {

            // 2019年
            case YEAR_2019:
                return updateTaskStartAndEndY2019Logic.practice(userDto, taskPlanId, endTime);

            // 2020年
            case YEAR_2020:
                return updateTaskStartAndEndY2020Logic.practice(userDto, taskPlanId, endTime);

            // 2021年
            case YEAR_2021:
                return updateTaskStartAndEndY2021Logic.practice(userDto, taskPlanId, endTime);

            // 2022年
            case YEAR_2022:
                return updateTaskStartAndEndY2022Logic.practice(userDto, taskPlanId, endTime);

            // 2023年
            case YEAR_2023:
                return updateTaskStartAndEndY2023Logic.practice(userDto, taskPlanId, endTime);

            // 2024年
            case YEAR_2024:
                return updateTaskStartAndEndY2024Logic.practice(userDto, taskPlanId, endTime);

            // 2025年
            case YEAR_2025:
                return updateTaskStartAndEndY2025Logic.practice(userDto, taskPlanId, endTime);

            // 2026年
            case YEAR_2026:
                return updateTaskStartAndEndY2026Logic.practice(userDto, taskPlanId, endTime);

            // 2027年
            case YEAR_2027:
                return updateTaskStartAndEndY2027Logic.practice(userDto, taskPlanId, endTime);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
