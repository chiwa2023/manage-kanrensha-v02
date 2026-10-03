package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.SaveLoginStatusHistoryY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.SaveLoginStatusHistoryY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.SaveLoginStatusHistoryY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.SaveLoginStatusHistoryY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.SaveLoginStatusHistoryY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.SaveLoginStatusHistoryY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.SaveLoginStatusHistoryY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.SaveLoginStatusHistoryY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.SaveLoginStatusHistoryY2027Logic;

/**
 * ログイン履歴記録Service
 */
@Service
public class SwitchYearLoginHistoryService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** ログイン履歴複写Logic(2019) */
    @Autowired
    private SaveLoginStatusHistoryY2019Logic saveLoginStatusHistoryY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** ログイン履歴複写Logic(2020) */
    @Autowired
    private SaveLoginStatusHistoryY2020Logic saveLoginStatusHistoryY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** ログイン履歴複写Logic(2021) */
    @Autowired
    private SaveLoginStatusHistoryY2021Logic saveLoginStatusHistoryY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** ログイン履歴複写Logic(2022) */
    @Autowired
    private SaveLoginStatusHistoryY2022Logic saveLoginStatusHistoryY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** ログイン履歴複写Logic(2023) */
    @Autowired
    private SaveLoginStatusHistoryY2023Logic saveLoginStatusHistoryY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** ログイン履歴複写Logic(2024) */
    @Autowired
    private SaveLoginStatusHistoryY2024Logic saveLoginStatusHistoryY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** ログイン履歴複写Logic(2025) */
    @Autowired
    private SaveLoginStatusHistoryY2025Logic saveLoginStatusHistoryY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** ログイン履歴複写Logic(2026) */
    @Autowired
    private SaveLoginStatusHistoryY2026Logic saveLoginStatusHistoryY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** ログイン履歴複写Logic(2027) */
    @Autowired
    private SaveLoginStatusHistoryY2027Logic saveLoginStatusHistoryY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param username  ユーザ名
     * @param ipAddress IPアドレス
     * @param userAgent ユーザエージェント
     * @param success   ログイン成功フラグ
     */
    @Transactional
    public Integer practice(final String username, final String ipAddress, final String userAgent,
            final boolean success, final LocalDateTime createDateTime) {
        LoginHistoryBaseEntity baseEntity = new LoginHistoryBaseEntity();
        baseEntity.setEmail(username);
        baseEntity.setIpAddress(ipAddress);
        baseEntity.setUserAgent(userAgent);
        baseEntity.setIsSuccess(success);
        baseEntity.setAttemptTime(createDateTime);

        switch (createDateTime.getYear()) {

            // 2019年
            case YEAR_2019:
                return saveLoginStatusHistoryY2019Logic.practice(baseEntity);

            // 2020年
            case YEAR_2020:
                return saveLoginStatusHistoryY2020Logic.practice(baseEntity);

            // 2021年
            case YEAR_2021:
                return saveLoginStatusHistoryY2021Logic.practice(baseEntity);

            // 2022年
            case YEAR_2022:
                return saveLoginStatusHistoryY2022Logic.practice(baseEntity);

            // 2023年
            case YEAR_2023:
                return saveLoginStatusHistoryY2023Logic.practice(baseEntity);

            // 2024年
            case YEAR_2024:
                return saveLoginStatusHistoryY2024Logic.practice(baseEntity);

            // 2025年
            case YEAR_2025:
                return saveLoginStatusHistoryY2025Logic.practice(baseEntity);

            // 2026年
            case YEAR_2026:
                return saveLoginStatusHistoryY2026Logic.practice(baseEntity);

            // 2027年
            case YEAR_2027:
                return saveLoginStatusHistoryY2027Logic.practice(baseEntity);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + createDateTime.getYear());
        }

    }
}
