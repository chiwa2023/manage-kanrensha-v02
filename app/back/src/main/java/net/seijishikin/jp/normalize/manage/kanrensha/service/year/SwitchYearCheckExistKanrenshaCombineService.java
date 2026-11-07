package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.year_option.YearOptionResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.CheckExistKanrenshaCombineY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.CheckExistKanrenshaCombineY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.CheckExistKanrenshaCombineY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.CheckExistKanrenshaCombineY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.CheckExistKanrenshaCombineY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.CheckExistKanrenshaCombineY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.CheckExistKanrenshaCombineY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.CheckExistKanrenshaCombineY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.CheckExistKanrenshaCombineY2027Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.YearOptionRepository;

/**
 * 年切り替え関連者紐づけ年存在確認Service
 */
@Service
public class SwitchYearCheckExistKanrenshaCombineService {

    /** 年選択肢Repository */
    @Autowired
    private YearOptionRepository yearOptionRepository;

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** 関連者紐づけ年存在確認Logic(2019) */
    @Autowired
    private CheckExistKanrenshaCombineY2019Logic checkExistKanrenshaCombineY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** 関連者紐づけ年存在確認Logic(2020) */
    @Autowired
    private CheckExistKanrenshaCombineY2020Logic checkExistKanrenshaCombineY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** 関連者紐づけ年存在確認Logic(2021) */
    @Autowired
    private CheckExistKanrenshaCombineY2021Logic checkExistKanrenshaCombineY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 関連者紐づけ年存在確認Logic(2022) */
    @Autowired
    private CheckExistKanrenshaCombineY2022Logic checkExistKanrenshaCombineY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 関連者紐づけ年存在確認Logic(2023) */
    @Autowired
    private CheckExistKanrenshaCombineY2023Logic checkExistKanrenshaCombineY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 関連者紐づけ年存在確認Logic(2024) */
    @Autowired
    private CheckExistKanrenshaCombineY2024Logic checkExistKanrenshaCombineY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 関連者紐づけ年存在確認Logic(2025) */
    @Autowired
    private CheckExistKanrenshaCombineY2025Logic checkExistKanrenshaCombineY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** 関連者紐づけ年存在確認Logic(2026) */
    @Autowired
    private CheckExistKanrenshaCombineY2026Logic checkExistKanrenshaCombineY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** 関連者紐づけ年存在確認Logic(2027) */
    @Autowired
    private CheckExistKanrenshaCombineY2027Logic checkExistKanrenshaCombineY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者紐づけ検索条件Dto
     * @return 年選択肢結果Dto
     */
    public YearOptionResultDto practice(final SearchKanrenshaCombineCapsuleDto capsuleDto) {

        final String personCode = capsuleDto.getPersonCode();
        final String orgCode = capsuleDto.getOrgCode();
        final Short kanrenshaKbn = capsuleDto.getKanrenshaKbn();

        List<YearOptionEntity> list = new ArrayList<>();
        for (YearOptionEntity entity : yearOptionRepository.findAll()) {
            int year = entity.getSelectedYear();
            switch (year) {

                // 2019年
                case YEAR_2019:
                    list.add(checkExistKanrenshaCombineY2019Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2020年
                case YEAR_2020:
                    list.add(checkExistKanrenshaCombineY2020Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2021年
                case YEAR_2021:
                    list.add(checkExistKanrenshaCombineY2021Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2022年
                case YEAR_2022:
                    list.add(checkExistKanrenshaCombineY2022Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2023年
                case YEAR_2023:
                    list.add(checkExistKanrenshaCombineY2023Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2024年
                case YEAR_2024:
                    list.add(checkExistKanrenshaCombineY2024Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2025年
                case YEAR_2025:
                    list.add(checkExistKanrenshaCombineY2025Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2026年
                case YEAR_2026:
                    list.add(checkExistKanrenshaCombineY2026Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // 2027年
                case YEAR_2027:
                    list.add(checkExistKanrenshaCombineY2027Logic.practice(personCode, orgCode, kanrenshaKbn));
                    break;

                // case次回追加位置

                default:
                    throw new IllegalArgumentException("Unexpected value: " + year);
            }
        }

        YearOptionResultDto resultDto = new YearOptionResultDto();
        resultDto.setListEntity(list);

        return resultDto;
    }

}
