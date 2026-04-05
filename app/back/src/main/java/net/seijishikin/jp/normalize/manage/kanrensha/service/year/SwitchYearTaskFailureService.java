package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateTaskListFailureY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.UpdateTaskListFailureY2026Logic;

/**
 * タスク計画失敗記録Service
 */
@Component
public class SwitchYearTaskFailureService {

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
            // 2025年
            case YEAR_2025:
                return updateTaskListFailureY2025Logic.practice(userDto,taskId, taskCode, endDatetime);
            // 2026年
            case YEAR_2026:
                return updateTaskListFailureY2026Logic.practice(userDto,taskId, taskCode, endDatetime);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
