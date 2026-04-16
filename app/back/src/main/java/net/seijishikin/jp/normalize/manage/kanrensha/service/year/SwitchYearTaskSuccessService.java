package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateTaskPlanY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.UpdateTaskPlanY2026Logic;

/**
 * タスク計画成功記録Service
 */
@Component
public class SwitchYearTaskSuccessService {

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
            // 2025年
            case YEAR_2025:
                return updateTaskPlanY2025Logic.practice(userDto, taskId, endDatetime, true);
            // 2026年
            case YEAR_2026:
                return updateTaskPlanY2026Logic.practice(userDto, taskId, endDatetime, true);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
