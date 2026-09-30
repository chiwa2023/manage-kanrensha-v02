package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.InsertPartnerApiAccessHistoryY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertPartnerApiAccessHistoryY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.InsertPartnerApiAccessHistoryY2026Logic;

/**
 * APIパートナー履歴保存年切替Service
 */
@Component
public class SwitchYearSavePartnerApiLoginHistoryService {

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

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** APIパートナー接続履歴保存Logic(2019) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2019Logic insertPartnerApiAccessHistoryY2019Logic;

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

            // 2025年
            case YEAR_2025:
                return insertPartnerApiAccessHistoryY2025Logic.practice(baseEntity);

            // 2026年
            case YEAR_2026:
                return insertPartnerApiAccessHistoryY2026Logic.practice(baseEntity);

            // 2019年
            case YEAR_2019:
                return insertPartnerApiAccessHistoryY2019Logic.practice(baseEntity);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
