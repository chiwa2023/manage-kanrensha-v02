package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateTaskPlanY2025Logic;

/**
 * タスク計画挿入(年管理)Service
 */
@Service
public class SwitchYearUpdateTaskPlanService {

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private UpdateTaskPlanY2025Logic updateTaskPlanY2025Logic;

    /**
     * 処理を行う
     *
     * @param year       処理年
     * @param userDto    ユーザ最低限Dto
     * @param taskId     タスクコード
     * @param isFinished タスク終了フラグ
     * @return 処理後Id
     */
    public Integer practice(final int year, final LeastUserDto userDto, final Integer taskId,
            final Boolean isFinished) {

        LocalDateTime now = LocalDateTime.now();
        switch (year) {
            // 2025年
            case YEAR_2025:
                return updateTaskPlanY2025Logic.practice(userDto, taskId, now, isFinished);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
