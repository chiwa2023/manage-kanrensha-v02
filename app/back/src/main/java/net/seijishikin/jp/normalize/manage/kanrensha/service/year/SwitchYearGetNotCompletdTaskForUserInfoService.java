package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.GetNotCompletdTaskY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.GetNotCompletdTaskY2026Logic;

/**
 * 年切り替え未処理タスク計画取得Service
 */
@Service
public class SwitchYearGetNotCompletdTaskForUserInfoService {

    /** 取得件数 */
    private static final Integer NOT_COMPLETE_TASK_LIMIT = 5;

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

    /**
     * 処理を行う
     * 
     * @param year    実行年
     * @param userDto ユーザ最小限
     * @return 検索結果リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer year, final LeastUserDto userDto) {

        switch (year) {
            // 2025年
            case YEAR_2025:
                return getNotCompletdTaskY2025Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            // 2026年
            case YEAR_2026:
                return getNotCompletdTaskY2026Logic.practice(userDto, NOT_COMPLETE_TASK_LIMIT);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
