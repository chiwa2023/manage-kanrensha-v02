package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.CombineOrgCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.UpdateCombineOrgY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.UpdateCombineOrgY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.UpdateCombineOrgY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.UpdateCombineOrgY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.UpdateCombineOrgY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.UpdateCombineOrgY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.UpdateCombineOrgY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.UpdateCombineOrgY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.UpdateCombineOrgY2027Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.YearOptionRepository;

/**
 * 年ごとで個人団体紐づけに挿入Service
 */
@Component
public class SwitchYearUpdateCombineOrgService {

    /** 登録年区切り文字 */
    public static final String YEAR_SPLITER = CombineOrgCsvProcessor.YEAR_SPLITER;

    /** 年処理選択肢Repository */
    @Autowired
    private YearOptionRepository yearOptionRepository;

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** 関連者紐づけ更新Logic(2019) */
    @Autowired
    private UpdateCombineOrgY2019Logic updateCombineOrgY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** 関連者紐づけ更新Logic(2020) */
    @Autowired
    private UpdateCombineOrgY2020Logic updateCombineOrgY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** 関連者紐づけ更新Logic(2021) */
    @Autowired
    private UpdateCombineOrgY2021Logic updateCombineOrgY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 関連者紐づけ更新Logic(2022) */
    @Autowired
    private UpdateCombineOrgY2022Logic updateCombineOrgY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 関連者紐づけ更新Logic(2023) */
    @Autowired
    private UpdateCombineOrgY2023Logic updateCombineOrgY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 関連者紐づけ更新Logic(2024) */
    @Autowired
    private UpdateCombineOrgY2024Logic updateCombineOrgY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 関連者紐づけ更新Logic(2025) */
    @Autowired
    private UpdateCombineOrgY2025Logic updateCombineOrgY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** 関連者紐づけ更新Logic(2026) */
    @Autowired
    private UpdateCombineOrgY2026Logic updateCombineOrgY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** 関連者紐づけ更新Logic(2027) */
    @Autowired
    private UpdateCombineOrgY2027Logic updateCombineOrgY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     *
     * @param entity  個人団体紐づけワークテーブルEntity
     * @param userDto ユーザ最小限Dto
     * @return 処理結果(すべて登録できたらtrue)
     */
    @Transactional
    public Integer practice(final WkTblKanrenshaCombineOrgEntity entity, final LeastUserDto userDto) {

        List<String> listRecord = Arrays.asList(entity.getYearArrayText().split(YEAR_SPLITER));
        Integer updateCount = 0;
        for (YearOptionEntity optionEntity : yearOptionRepository.findAll()) {

            Integer year = optionEntity.getSelectedYear();

            switch (year) {
                // 2019年
                case YEAR_2019:
                    updateCount += updateCombineOrgY2019Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2020年
                case YEAR_2020:
                    updateCount += updateCombineOrgY2020Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2021年
                case YEAR_2021:
                    updateCount += updateCombineOrgY2021Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2022年
                case YEAR_2022:
                    updateCount += updateCombineOrgY2022Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2023年
                case YEAR_2023:
                    updateCount += updateCombineOrgY2023Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2024年
                case YEAR_2024:
                    updateCount += updateCombineOrgY2024Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2025年
                case YEAR_2025:
                    updateCount += updateCombineOrgY2025Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2026年
                case YEAR_2026:
                    updateCount += updateCombineOrgY2026Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // 2027年
                case YEAR_2027:
                    updateCount += updateCombineOrgY2027Logic.practice(entity,
                            listRecord.contains(String.valueOf(year)), userDto);
                    break;

                // case次回追加位置

                default:
                    throw new IllegalArgumentException("Unexpected value: " + year);
            }
        }

        return updateCount;
    }

}
