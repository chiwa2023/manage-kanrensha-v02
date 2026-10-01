package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.InsertPartnerApiAccessHistoryY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.InsertPartnerApiAccessHistoryY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.InsertPartnerApiAccessHistoryY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.InsertPartnerApiAccessHistoryY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.InsertPartnerApiAccessHistoryY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.InsertPartnerApiAccessHistoryY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertPartnerApiAccessHistoryY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.InsertPartnerApiAccessHistoryY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.InsertPartnerApiAccessHistoryY2027Logic;

/**
 * APIパートナー履歴保存年切替Service
 */
@Component
public class SwitchYearSavePartnerApiLoginHistoryService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** APIパートナー接続履歴保存Logic(2019) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2019Logic insertPartnerApiAccessHistoryY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** APIパートナー接続履歴保存Logic(2020) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2020Logic insertPartnerApiAccessHistoryY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** APIパートナー接続履歴保存Logic(2021) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2021Logic insertPartnerApiAccessHistoryY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** APIパートナー接続履歴保存Logic(2022) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2022Logic insertPartnerApiAccessHistoryY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** APIパートナー接続履歴保存Logic(2023) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2023Logic insertPartnerApiAccessHistoryY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** APIパートナー接続履歴保存Logic(2024) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2024Logic insertPartnerApiAccessHistoryY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** APIパートナー接続履歴保存Logic(2025) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2025Logic insertPartnerApiAccessHistoryY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** APIパートナー接続履歴保存Logic(2026) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2026Logic insertPartnerApiAccessHistoryY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** APIパートナー接続履歴保存Logic(2027) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2027Logic insertPartnerApiAccessHistoryY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー接続履歴基本Entity
     * @return 処理結果Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        Integer year = baseEntity.getAttemptTime().getYear();

        switch (year) {

            // 2019年
            case YEAR_2019:
                return insertPartnerApiAccessHistoryY2019Logic.practice(baseEntity);

            // 2020年
            case YEAR_2020:
                return insertPartnerApiAccessHistoryY2020Logic.practice(baseEntity);

            // 2021年
            case YEAR_2021:
                return insertPartnerApiAccessHistoryY2021Logic.practice(baseEntity);

            // 2022年
            case YEAR_2022:
                return insertPartnerApiAccessHistoryY2022Logic.practice(baseEntity);

            // 2023年
            case YEAR_2023:
                return insertPartnerApiAccessHistoryY2023Logic.practice(baseEntity);

            // 2024年
            case YEAR_2024:
                return insertPartnerApiAccessHistoryY2024Logic.practice(baseEntity);

            // 2025年
            case YEAR_2025:
                return insertPartnerApiAccessHistoryY2025Logic.practice(baseEntity);

            // 2026年
            case YEAR_2026:
                return insertPartnerApiAccessHistoryY2026Logic.practice(baseEntity);

            // 2027年
            case YEAR_2027:
                return insertPartnerApiAccessHistoryY2027Logic.practice(baseEntity);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
