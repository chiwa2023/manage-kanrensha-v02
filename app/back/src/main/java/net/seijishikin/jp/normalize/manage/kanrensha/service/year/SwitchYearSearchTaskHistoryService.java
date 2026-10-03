package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.SearchTaskHistoryY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.SearchTaskHistoryY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.SearchTaskHistoryY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.SearchTaskHistoryY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.SearchTaskHistoryY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.SearchTaskHistoryY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.SearchTaskHistoryY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.SearchTaskHistoryY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.SearchTaskHistoryY2027Logic;

/**
 * タスク計画履歴取得年展開Service
 */
@Service
public class SwitchYearSearchTaskHistoryService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画履歴取得Logic(2019) */
    @Autowired
    private SearchTaskHistoryY2019Logic searchTaskHistoryY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画履歴取得Logic(2020) */
    @Autowired
    private SearchTaskHistoryY2020Logic searchTaskHistoryY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画履歴取得Logic(2021) */
    @Autowired
    private SearchTaskHistoryY2021Logic searchTaskHistoryY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画履歴取得Logic(2022) */
    @Autowired
    private SearchTaskHistoryY2022Logic searchTaskHistoryY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画履歴取得Logic(2023) */
    @Autowired
    private SearchTaskHistoryY2023Logic searchTaskHistoryY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画履歴取得Logic(2024) */
    @Autowired
    private SearchTaskHistoryY2024Logic searchTaskHistoryY2024Logic;

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

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画履歴取得Logic(2027) */
    @Autowired
    private SearchTaskHistoryY2027Logic searchTaskHistoryY2027Logic;

    // field次回追加位置

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

            // 2019年
            case YEAR_2019:
                return searchTaskHistoryY2019Logic.practice(planCode);

            // 2020年
            case YEAR_2020:
                return searchTaskHistoryY2020Logic.practice(planCode);

            // 2021年
            case YEAR_2021:
                return searchTaskHistoryY2021Logic.practice(planCode);

            // 2022年
            case YEAR_2022:
                return searchTaskHistoryY2022Logic.practice(planCode);

            // 2023年
            case YEAR_2023:
                return searchTaskHistoryY2023Logic.practice(planCode);

            // 2024年
            case YEAR_2024:
                return searchTaskHistoryY2024Logic.practice(planCode);

            // 2025年
            case YEAR_2025:
                return searchTaskHistoryY2025Logic.practice(planCode);

            // 2026年
            case YEAR_2026:
                return searchTaskHistoryY2026Logic.practice(planCode);

            // 2027年
            case YEAR_2027:
                return searchTaskHistoryY2027Logic.practice(planCode);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
