package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.SearchTaskHistoryY2019Logic;
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

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画履歴取得Logic(2019) */
    @Autowired
    private SearchTaskHistoryY2019Logic searchTaskHistoryY2019Logic;    // field次回追加位置

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

            // 2026年
            case YEAR_2026:
                return searchTaskHistoryY2026Logic.practice(planCode);

            // 2019年
            case YEAR_2019:
                return searchTaskHistoryY2019Logic.practice(planCode);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
