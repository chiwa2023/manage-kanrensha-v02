package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.SearchTaskHistoryY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.SearchTaskHistoryY2026Logic;

/**
 * タスク計画履歴取得年展開Service
 */
@Service
public class SwitchYearSearchTaskHistoryService {

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画履歴取得Logic(2025) */
    @Autowired
    private SearchTaskHistoryY2025Logic searchTaskHistoryY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画履歴取得Logic(2026) */
    @Autowired
    private SearchTaskHistoryY2026Logic searchTaskHistoryY2026Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果リスト
     */
    public List<TaskPlanBaseEntity> practice(final SearchTaskHistoryCapsuleDto capsuleDto) {

        int year = capsuleDto.getTaskYear();
        int planCode = capsuleDto.getTaskPlanCode();
        switch (year) {
            // 2025年
            case YEAR_2025:
                return searchTaskHistoryY2025Logic.practice(planCode);

            // 2025年
            case YEAR_2026:
                return searchTaskHistoryY2026Logic.practice(planCode);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
