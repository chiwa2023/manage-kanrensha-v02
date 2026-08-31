package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.GetRoleSomeoneTaskY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.GetRoleSomeoneTaskY2026Logic;

/**
 * 年切り替え指定権限タスク計画取得Service
 */
@Service
public class SwitchYearGetRoleSomeoneTaskService {

    /** 実施年(2026) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画履歴取得Logic(2025) */
    @Autowired
    private GetRoleSomeoneTaskY2025Logic getRoleSomeoneTaskY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画履歴取得Logic(2026) */
    @Autowired
    private GetRoleSomeoneTaskY2026Logic getRoleSomeoneTaskY2026Logic;

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
                return getRoleSomeoneTaskY2025Logic.practice(userDto);

            // 2026年
            case YEAR_2026:
                return getRoleSomeoneTaskY2026Logic.practice(userDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

    
}
