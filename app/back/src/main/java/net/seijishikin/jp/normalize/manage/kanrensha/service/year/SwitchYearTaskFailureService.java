package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.UpdateTaskListFailureY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.UpdateTaskListFailureY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.UpdateTaskListFailureY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.UpdateTaskListFailureY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.UpdateTaskListFailureY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.UpdateTaskListFailureY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateTaskListFailureY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.UpdateTaskListFailureY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.UpdateTaskListFailureY2027Logic;

/**
 * タスク計画失敗記録Service
 */
@Component
public class SwitchYearTaskFailureService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画挿入Logic(2019) */
    @Autowired
    private UpdateTaskListFailureY2019Logic updateTaskListFailureY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画挿入Logic(2020) */
    @Autowired
    private UpdateTaskListFailureY2020Logic updateTaskListFailureY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画挿入Logic(2021) */
    @Autowired
    private UpdateTaskListFailureY2021Logic updateTaskListFailureY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画挿入Logic(2022) */
    @Autowired
    private UpdateTaskListFailureY2022Logic updateTaskListFailureY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画挿入Logic(2023) */
    @Autowired
    private UpdateTaskListFailureY2023Logic updateTaskListFailureY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画挿入Logic(2024) */
    @Autowired
    private UpdateTaskListFailureY2024Logic updateTaskListFailureY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private UpdateTaskListFailureY2025Logic updateTaskListFailureY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private UpdateTaskListFailureY2026Logic updateTaskListFailureY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画挿入Logic(2027) */
    @Autowired
    private UpdateTaskListFailureY2027Logic updateTaskListFailureY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param year        登録年
     * @param userDto     ユーザ最小限Dto
     * @param taskId      タスク計画Id
     * @param taskCode    タスク計画コード
     * @param endDatetime 終了日時
     * @return 新規作成Id
     */
    @Transactional
    public Integer practice(final Integer year, final LeastUserDto userDto, final Integer taskId,
            final Integer taskCode, final LocalDateTime endDatetime) {
        switch (year) {

            // 2019年
            case YEAR_2019:
                return updateTaskListFailureY2019Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2020年
            case YEAR_2020:
                return updateTaskListFailureY2020Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2021年
            case YEAR_2021:
                return updateTaskListFailureY2021Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2022年
            case YEAR_2022:
                return updateTaskListFailureY2022Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2023年
            case YEAR_2023:
                return updateTaskListFailureY2023Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2024年
            case YEAR_2024:
                return updateTaskListFailureY2024Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2025年
            case YEAR_2025:
                return updateTaskListFailureY2025Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2026年
            case YEAR_2026:
                return updateTaskListFailureY2026Logic.practice(userDto, taskId, taskCode, endDatetime);

            // 2027年
            case YEAR_2027:
                return updateTaskListFailureY2027Logic.practice(userDto, taskId, taskCode, endDatetime);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
