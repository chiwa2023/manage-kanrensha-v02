package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.InsertPartnerApiAccessHistoryY2026Logic;

/**
 * APIパートナー履歴保存年切替Service
 */
@Component
public class SwitchYearSavePartnerApiLoginHistoryService {

    /** 登録対応年(2026) */
    private static final int YEAR_2026 = 2026;
    /** APIパートナー接続履歴保存Logic(2026) */
    @Autowired
    private InsertPartnerApiAccessHistoryY2026Logic insertPartnerApiAccessHistoryY2026Logic;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー接続履歴基本Entity
     * @return 処理結果Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        Integer year = baseEntity.getAttemptTime().getYear();

        switch (year) {
            // case YEAR_2025:
            // return copyLoginStatusHistoryY2025Logic.practice(baseEntity);
            case YEAR_2026:
                return insertPartnerApiAccessHistoryY2026Logic.practice(baseEntity);
            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
