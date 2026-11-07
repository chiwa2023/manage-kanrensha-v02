package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.SearchKanrenshaCombineY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.SearchKanrenshaCombineY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.SearchKanrenshaCombineY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.SearchKanrenshaCombineY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.SearchKanrenshaCombineY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.SearchKanrenshaCombineY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.SearchKanrenshaCombineY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.SearchKanrenshaCombineY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.SearchKanrenshaCombineY2027Logic;

/**
 * 年切り替え関連者紐づけ検索Service
 */
@Service
public class SwitchYearSearchKanrenshaCombineService {

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** 関連者紐づけ検索Logic(2019) */
    @Autowired
    private SearchKanrenshaCombineY2019Logic searchKanrenshaCombineY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** 関連者紐づけ検索Logic(2020) */
    @Autowired
    private SearchKanrenshaCombineY2020Logic searchKanrenshaCombineY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** 関連者紐づけ検索Logic(2021) */
    @Autowired
    private SearchKanrenshaCombineY2021Logic searchKanrenshaCombineY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 関連者紐づけ検索Logic(2022) */
    @Autowired
    private SearchKanrenshaCombineY2022Logic searchKanrenshaCombineY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 関連者紐づけ検索Logic(2023) */
    @Autowired
    private SearchKanrenshaCombineY2023Logic searchKanrenshaCombineY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 関連者紐づけ検索Logic(2024) */
    @Autowired
    private SearchKanrenshaCombineY2024Logic searchKanrenshaCombineY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 関連者紐づけ検索Logic(2025) */
    @Autowired
    private SearchKanrenshaCombineY2025Logic searchKanrenshaCombineY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** 関連者紐づけ検索Logic(2026) */
    @Autowired
    private SearchKanrenshaCombineY2026Logic searchKanrenshaCombineY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** 関連者紐づけ検索Logic(2027) */
    @Autowired
    private SearchKanrenshaCombineY2027Logic searchKanrenshaCombineY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者紐づけ検索条件Dto
     * @return 検索結果Dto
     */
    public SearchKanrenshaCombineResultDto practice(final SearchKanrenshaCombineCapsuleDto capsuleDto) {

        Integer year = capsuleDto.getTableYear();

        switch (year) {

            // 2019年
            case YEAR_2019:
                return searchKanrenshaCombineY2019Logic.practice(capsuleDto);

            // 2020年
            case YEAR_2020:
                return searchKanrenshaCombineY2020Logic.practice(capsuleDto);

            // 2021年
            case YEAR_2021:
                return searchKanrenshaCombineY2021Logic.practice(capsuleDto);

            // 2022年
            case YEAR_2022:
                return searchKanrenshaCombineY2022Logic.practice(capsuleDto);

            // 2023年
            case YEAR_2023:
                return searchKanrenshaCombineY2023Logic.practice(capsuleDto);

            // 2024年
            case YEAR_2024:
                return searchKanrenshaCombineY2024Logic.practice(capsuleDto);

            // 2025年
            case YEAR_2025:
                return searchKanrenshaCombineY2025Logic.practice(capsuleDto);

            // 2026年
            case YEAR_2026:
                return searchKanrenshaCombineY2026Logic.practice(capsuleDto);

            // 2027年
            case YEAR_2027:
                return searchKanrenshaCombineY2027Logic.practice(capsuleDto);

            // case次回追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }

}
