package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.CombineOrgCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.InsertCombineOrgY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.InsertCombineOrgY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.InsertCombineOrgY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.InsertCombineOrgY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.InsertCombineOrgY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertCombineOrgY2025Logic;

/**
 * 年ごとで個人団体紐づけに挿入Service
 */
@Component
public class SwitchYearInsertCombineOrgService {

    /** 空文字 */
    private static final String BLANK = "";

    /** 登録年区切り文字 */
    public static final String YEAR_SPLITER = CombineOrgCsvProcessor.YEAR_SPLITER;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画挿入Logic(2020) */
    @Autowired
    private InsertCombineOrgY2020Logic insertCombineOrgY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画挿入Logic(2021) */
    @Autowired
    private InsertCombineOrgY2021Logic insertCombineOrgY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画挿入Logic(2022) */
    @Autowired
    private InsertCombineOrgY2022Logic insertCombineOrgY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画挿入Logic(2023) */
    @Autowired
    private InsertCombineOrgY2023Logic insertCombineOrgY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画挿入Logic(2024) */
    @Autowired
    private InsertCombineOrgY2024Logic insertCombineOrgY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertCombineOrgY2025Logic insertCombineOrgY2025Logic;

    /**
     * 処理を行う
     *
     * @param entity  個人団体紐づけワークテーブルEntity
     * @param userDto ユーザ最小限Dto
     * @return 処理結果(すべて登録できたらtrue)
     */
    @Transactional
    public Boolean practice(final WkTblKanrenshaCombineOrgEntity entity, final LeastUserDto userDto) {

        final Integer noRecord = 0;

        if (!BLANK.equals(entity.getYearArrayText())) {
            // String conditon = this.createCondition(entity);
            for (String year : entity.getYearArrayText().split(YEAR_SPLITER)) {

                switch (Integer.parseInt(year)) {
                    // 2020年
                    case YEAR_2020:
                        if (noRecord.equals(insertCombineOrgY2020Logic.practice(entity, userDto))) {
                            return false;
                        }
                        break;
                    // 2021年
                    case YEAR_2021:
                        if (noRecord.equals(insertCombineOrgY2021Logic.practice(entity, userDto))) {
                            return false;
                        }
                        break;
                    // 2022年
                    case YEAR_2022:
                        if (noRecord.equals(insertCombineOrgY2022Logic.practice(entity, userDto))) {
                            return false;
                        }
                        break;
                    // 2023年
                    case YEAR_2023:
                        if (noRecord.equals(insertCombineOrgY2023Logic.practice(entity, userDto))) {
                            return false;
                        }
                        break;
                    // 2024年
                    case YEAR_2024:
                        if (noRecord.equals(insertCombineOrgY2024Logic.practice(entity, userDto))) {
                            return false;
                        }
                        break;
                    // 2025年
                    case YEAR_2025:
                        if (noRecord.equals(insertCombineOrgY2025Logic.practice(entity, userDto))) {
                            return false;
                        }
                        break;

                    default:
                        throw new IllegalArgumentException("Unexpected value: " + year);
                }
            }
        }

        return true;
    }

}
