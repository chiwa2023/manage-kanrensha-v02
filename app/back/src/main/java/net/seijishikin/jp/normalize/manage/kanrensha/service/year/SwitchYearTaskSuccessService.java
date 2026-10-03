package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.UpdateTaskPlanY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.UpdateTaskPlanY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.UpdateTaskPlanY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.UpdateTaskPlanY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.UpdateTaskPlanY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.UpdateTaskPlanY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateTaskPlanY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.UpdateTaskPlanY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.UpdateTaskPlanY2027Logic;

/**
 * タスク計画成功記録Service
 */
@Component
public class SwitchYearTaskSuccessService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画挿入Logic(2019) */
    @Autowired
    private UpdateTaskPlanY2019Logic updateTaskPlanY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画挿入Logic(2020) */
    @Autowired
    private UpdateTaskPlanY2020Logic updateTaskPlanY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画挿入Logic(2021) */
    @Autowired
    private UpdateTaskPlanY2021Logic updateTaskPlanY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画挿入Logic(2022) */
    @Autowired
    private UpdateTaskPlanY2022Logic updateTaskPlanY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画挿入Logic(2023) */
    @Autowired
    private UpdateTaskPlanY2023Logic updateTaskPlanY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画挿入Logic(2024) */
    @Autowired
    private UpdateTaskPlanY2024Logic updateTaskPlanY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private UpdateTaskPlanY2025Logic updateTaskPlanY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private UpdateTaskPlanY2026Logic updateTaskPlanY2026Logic;
    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画挿入Logic(2027) */
    @Autowired
    private UpdateTaskPlanY2027Logic updateTaskPlanY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param year        登録年
     * @param userDto     ユーザ最小限Dto
     * @param taskId      タスク計画Id
     * @param endDatetime 終了日時
     * @return 追加Id
     */
    @Transactional
    public Integer practice(final Integer year, final LeastUserDto userDto, final Integer taskId,
            final LocalDateTime endDatetime) {
        switch (year) {

            // 2019年
            case YEAR_2019:
                return updateTaskPlanY2019Logic.practice(userDto, taskId, endDatetime, true);

            // 2020年
            case YEAR_2020:
                return updateTaskPlanY2020Logic.practice(userDto, taskId, endDatetime, true);

            // 2021年
            case YEAR_2021:
                return updateTaskPlanY2021Logic.practice(userDto, taskId, endDatetime, true);

            // 2022年
            case YEAR_2022:
                return updateTaskPlanY2022Logic.practice(userDto, taskId, endDatetime, true);

            // 2023年
            case YEAR_2023:
                return updateTaskPlanY2023Logic.practice(userDto, taskId, endDatetime, true);

            // 2024年
            case YEAR_2024:
                return updateTaskPlanY2024Logic.practice(userDto, taskId, endDatetime, true);

            // 2025年
            case YEAR_2025:
                return updateTaskPlanY2025Logic.practice(userDto, taskId, endDatetime, true);

            // 2026年
            case YEAR_2026:
                return updateTaskPlanY2026Logic.practice(userDto, taskId, endDatetime, true);

            // 2027年
            case YEAR_2027:
                return updateTaskPlanY2027Logic.practice(userDto, taskId, endDatetime, true);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
