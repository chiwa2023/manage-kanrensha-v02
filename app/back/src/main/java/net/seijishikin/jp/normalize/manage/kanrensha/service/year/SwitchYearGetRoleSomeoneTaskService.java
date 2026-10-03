package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.GetRoleSomeoneTaskY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.GetRoleSomeoneTaskY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.GetRoleSomeoneTaskY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.GetRoleSomeoneTaskY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.GetRoleSomeoneTaskY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.GetRoleSomeoneTaskY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.GetRoleSomeoneTaskY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.GetRoleSomeoneTaskY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.GetRoleSomeoneTaskY2027Logic;

/**
 * 年切り替え指定権限タスク計画取得Service
 */
@Service
public class SwitchYearGetRoleSomeoneTaskService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画履歴取得Logic(2019) */
    @Autowired
    private GetRoleSomeoneTaskY2019Logic getRoleSomeoneTaskY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画履歴取得Logic(2020) */
    @Autowired
    private GetRoleSomeoneTaskY2020Logic getRoleSomeoneTaskY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画履歴取得Logic(2021) */
    @Autowired
    private GetRoleSomeoneTaskY2021Logic getRoleSomeoneTaskY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画履歴取得Logic(2022) */
    @Autowired
    private GetRoleSomeoneTaskY2022Logic getRoleSomeoneTaskY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画履歴取得Logic(2023) */
    @Autowired
    private GetRoleSomeoneTaskY2023Logic getRoleSomeoneTaskY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画履歴取得Logic(2024) */
    @Autowired
    private GetRoleSomeoneTaskY2024Logic getRoleSomeoneTaskY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画履歴取得Logic(2025) */
    @Autowired
    private GetRoleSomeoneTaskY2025Logic getRoleSomeoneTaskY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画履歴取得Logic(2026) */
    @Autowired
    private GetRoleSomeoneTaskY2026Logic getRoleSomeoneTaskY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画履歴取得Logic(2027) */
    @Autowired
    private GetRoleSomeoneTaskY2027Logic getRoleSomeoneTaskY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param year    実行年
     * @param userDto ユーザ最小限
     * @return 検索結果リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer year, final LeastUserDto userDto) {

        switch (year) {

            // 2019年
            case YEAR_2019:
                return getRoleSomeoneTaskY2019Logic.practice(userDto);

            // 2020年
            case YEAR_2020:
                return getRoleSomeoneTaskY2020Logic.practice(userDto);

            // 2021年
            case YEAR_2021:
                return getRoleSomeoneTaskY2021Logic.practice(userDto);

            // 2022年
            case YEAR_2022:
                return getRoleSomeoneTaskY2022Logic.practice(userDto);

            // 2023年
            case YEAR_2023:
                return getRoleSomeoneTaskY2023Logic.practice(userDto);

            // 2024年
            case YEAR_2024:
                return getRoleSomeoneTaskY2024Logic.practice(userDto);

            // 2025年
            case YEAR_2025:
                return getRoleSomeoneTaskY2025Logic.practice(userDto);

            // 2026年
            case YEAR_2026:
                return getRoleSomeoneTaskY2026Logic.practice(userDto);

            // 2027年
            case YEAR_2027:
                return getRoleSomeoneTaskY2027Logic.practice(userDto);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
