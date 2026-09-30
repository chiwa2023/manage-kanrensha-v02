package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.UpdateTaskStartAndEndY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateTaskStartAndEndY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.UpdateTaskStartAndEndY2026Logic;

/**
 * タスク計画終了更新年切替
 */
@Service
public class SwitchYearUpdateTaskStartAndEndService {

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

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画終了更新Logic(2019) */
    @Autowired
    private UpdateTaskStartAndEndY2019Logic updateTaskStartAndEndY2019Logic;    // field次回追加位置

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
            // 2025年
            case YEAR_2025:
                return updateTaskStartAndEndY2025Logic.practice(userDto, taskPlanId, endTime);

            // 2026年
            case YEAR_2026:
                return updateTaskStartAndEndY2026Logic.practice(userDto, taskPlanId, endTime);

            // 2019年
            case YEAR_2019:
                return updateTaskStartAndEndY2019Logic.practice(userDto, taskPlanId, endTime);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
