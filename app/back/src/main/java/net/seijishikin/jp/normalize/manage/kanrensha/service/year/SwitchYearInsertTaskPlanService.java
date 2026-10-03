package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.InsertTaskPlanY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.InsertTaskPlanY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.InsertTaskPlanY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.InsertTaskPlanY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.InsertTaskPlanY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.InsertTaskPlanY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertTaskPlanY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.InsertTaskPlanY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.InsertTaskPlanY2027Logic;

/**
 * タスク計画挿入(年管理)Service develop_security
 */
@Service
public class SwitchYearInsertTaskPlanService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画挿入Logic(2019) */
    @Autowired
    private InsertTaskPlanY2019Logic insertTaskPlanY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画挿入Logic(2020) */
    @Autowired
    private InsertTaskPlanY2020Logic insertTaskPlanY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画挿入Logic(2021) */
    @Autowired
    private InsertTaskPlanY2021Logic insertTaskPlanY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画挿入Logic(2022) */
    @Autowired
    private InsertTaskPlanY2022Logic insertTaskPlanY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画挿入Logic(2023) */
    @Autowired
    private InsertTaskPlanY2023Logic insertTaskPlanY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画挿入Logic(2024) */
    @Autowired
    private InsertTaskPlanY2024Logic insertTaskPlanY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画追加Logic(2026) */
    @Autowired
    private InsertTaskPlanY2026Logic insertTaskPlanY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画挿入Logic(2027) */
    @Autowired
    private InsertTaskPlanY2027Logic insertTaskPlanY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     *
     * @param userDtoWork   作業者ユーザDto
     * @param userDtoInsert 操作者ユーザDto
     * @param startDatetime タスク開始時間
     * @param taskPlanCode  タスク情報コード
     * @return 追加Id
     */
    @Transactional
    public InsertTaskPlanResultDto practice(final LeastUserDto userDtoWork, final LeastUserDto userDtoInsert,
            final LocalDateTime startDatetime, final Integer taskPlanCode, final Map<String, String> mapParam) {

        Integer year = startDatetime.getYear();
        switch (year) {

            // 2019年
            case YEAR_2019:
                return insertTaskPlanY2019Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // 2020年
            case YEAR_2020:
                return insertTaskPlanY2020Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // 2021年
            case YEAR_2021:
                return insertTaskPlanY2021Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // 2022年
            case YEAR_2022:
                return insertTaskPlanY2022Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // 2023年
            case YEAR_2023:
                return insertTaskPlanY2023Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // 2024年
            case YEAR_2024:
                return insertTaskPlanY2024Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // 2025年
            case YEAR_2025:
                return insertTaskPlanY2025Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);
            // 2026年
            case YEAR_2026:
                return insertTaskPlanY2026Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // 2027年
            case YEAR_2027:
                return insertTaskPlanY2027Logic.practice(userDtoWork, userDtoInsert, startDatetime, taskPlanCode,
                        mapParam);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
