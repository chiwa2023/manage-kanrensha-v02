package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.GetLoginHistoryFailureY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.GetLoginHistoryFailureY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.GetLoginHistoryFailureY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.GetLoginHistoryFailureY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.GetLoginHistoryFailureY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.GetLoginHistoryFailureY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.GetLoginHistoryFailureY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.GetLoginHistoryFailureY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.GetLoginHistoryFailureY2027Logic;

/**
 * 年切替え失敗ログイン履歴取得Service
 */
@Service
public class SwitchYearGetLoginHistoryFailureService {

    /** 試行回数限界 */
    private static final int LIMIT = 5;

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画検索Logic(2019) */
    @Autowired
    private GetLoginHistoryFailureY2019Logic getLoginHistoryFailureY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画検索Logic(2020) */
    @Autowired
    private GetLoginHistoryFailureY2020Logic getLoginHistoryFailureY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画検索Logic(2021) */
    @Autowired
    private GetLoginHistoryFailureY2021Logic getLoginHistoryFailureY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画検索Logic(2022) */
    @Autowired
    private GetLoginHistoryFailureY2022Logic getLoginHistoryFailureY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画検索Logic(2023) */
    @Autowired
    private GetLoginHistoryFailureY2023Logic getLoginHistoryFailureY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画検索Logic(2025) */
    @Autowired
    private GetLoginHistoryFailureY2024Logic getLoginHistoryFailureY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画検索Logic(2025) */
    @Autowired
    private GetLoginHistoryFailureY2025Logic getLoginHistoryFailureY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画検索Logic(2026) */
    @Autowired
    private GetLoginHistoryFailureY2026Logic getLoginHistoryFailureY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画検索Logic(2027) */
    @Autowired
    private GetLoginHistoryFailureY2027Logic getLoginHistoryFailureY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param email   メールアドレス
     * @param nowDate 試行日
     * @return ログインが試行回数以下該非
     */
    public boolean practice(final String email, final LocalDate nowDate) {

        LocalDateTime endDateTime = LocalDateTime.of(nowDate, LocalTime.MAX);
        LocalDateTime starDateTime = LocalDateTime.of(nowDate.minusDays(1L), LocalTime.MIN);

        int count = 0;
        List<Integer> listYear = getYearList(starDateTime, endDateTime);
        for (Integer year : listYear) {
            
            switch (year) {

                // 2019年
                case YEAR_2019:
                    count += getLoginHistoryFailureY2019Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2020年
                case YEAR_2020:
                    count += getLoginHistoryFailureY2020Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2021年
                case YEAR_2021:
                    count += getLoginHistoryFailureY2021Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2022年
                case YEAR_2022:
                    count += getLoginHistoryFailureY2022Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2023年
                case YEAR_2023:
                    count += getLoginHistoryFailureY2023Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2024年
                case YEAR_2024:
                    count += getLoginHistoryFailureY2024Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2025年
                case YEAR_2025:
                    count += getLoginHistoryFailureY2025Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2026年
                case YEAR_2026:
                    count += getLoginHistoryFailureY2026Logic.practice(email, starDateTime, endDateTime);
                    break;

                // 2027年
                case YEAR_2027:
                    count += getLoginHistoryFailureY2027Logic.practice(email, starDateTime, endDateTime);
                    break;

                // case次回追加位置

                default:
                    throw new IllegalArgumentException("Unexpected value: " + year);
            }
        }

        return count < LIMIT;
    }

    private List<Integer> getYearList(final LocalDateTime starDateTime, final LocalDateTime endDateTime) {

        List<Integer> list = new ArrayList<>();
        list.add(starDateTime.getYear());
        int year = endDateTime.getYear();
        if (!list.contains(year)) {
            list.add(year);
        }

        return list;
    }

}
