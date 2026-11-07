package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.paging.SwitchYearPagingIntegerDatetimeDtoInterface;
import net.seijishikin.jp.normalize.common_tool.logic.CreateSearchConditionMapByYearLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019.SearchRoleSomeoneTaskY2019Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020.SearchRoleSomeoneTaskY2020Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021.SearchRoleSomeoneTaskY2021Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022.SearchRoleSomeoneTaskY2022Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023.SearchRoleSomeoneTaskY2023Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024.SearchRoleSomeoneTaskY2024Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.SearchRoleSomeoneTaskY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.SearchRoleSomeoneTaskY2026Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027.SearchRoleSomeoneTaskY2027Logic;

/**
 * 権限対象タスクを検索する
 */
@Service
public class SwitchYearSearchRoleSomeoneTaskService {

    /** 検索条件年展開Logic */
    @Autowired
    private CreateSearchConditionMapByYearLogic createSearchConditionMapByYearLogic;

    /** 実施年(2019) */
    private static final int YEAR_2019 = 2019;
    /** タスク計画検索Logic(2019) */
    @Autowired
    private SearchRoleSomeoneTaskY2019Logic searchRoleSomeoneTaskY2019Logic;

    /** 実施年(2020) */
    private static final int YEAR_2020 = 2020;
    /** タスク計画検索Logic(2020) */
    @Autowired
    private SearchRoleSomeoneTaskY2020Logic searchRoleSomeoneTaskY2020Logic;

    /** 実施年(2021) */
    private static final int YEAR_2021 = 2021;
    /** タスク計画検索Logic(2021) */
    @Autowired
    private SearchRoleSomeoneTaskY2021Logic searchRoleSomeoneTaskY2021Logic;

    /** 実施年(2022) */
    private static final int YEAR_2022 = 2022;
    /** タスク計画検索Logic(2022) */
    @Autowired
    private SearchRoleSomeoneTaskY2022Logic searchRoleSomeoneTaskY2022Logic;

    /** 実施年(2023) */
    private static final int YEAR_2023 = 2023;
    /** タスク計画検索Logic(2023) */
    @Autowired
    private SearchRoleSomeoneTaskY2023Logic searchRoleSomeoneTaskY2023Logic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** タスク計画検索Logic(2024) */
    @Autowired
    private SearchRoleSomeoneTaskY2024Logic searchRoleSomeoneTaskY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画検索Logic(2025) */
    @Autowired
    private SearchRoleSomeoneTaskY2025Logic searchRoleSomeoneTaskY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画検索Logic(2026) */
    @Autowired
    private SearchRoleSomeoneTaskY2026Logic searchRoleSomeoneTaskY2026Logic;

    /** 実施年(2027) */
    private static final int YEAR_2027 = 2027;
    /** タスク計画検索Logic(2027) */
    @Autowired
    private SearchRoleSomeoneTaskY2027Logic searchRoleSomeoneTaskY2027Logic;

    // field次回追加位置

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     * @throws InvocationTargetException reflection例外(検索条件年展開)
     * @throws IllegalAccessException    reflection例外(検索条件年展開)
     * @throws InstantiationException    reflection例外(検索条件年展開)
     * @throws NoSuchMethodException     reflection例外(検索条件年展開)
     */
    public SearchTaskPlanResultDto practice(final SearchTaskPlanCapsuleDto capsuleDto)
            throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        Map<Integer, SwitchYearPagingIntegerDatetimeDtoInterface> map = createSearchConditionMapByYearLogic
                .practice(capsuleDto);

        SearchTaskPlanResultDto resultDto = new SearchTaskPlanResultDto();
        for (Integer year : map.keySet()) {
            switch (year) {

                // 2019年
                case YEAR_2019:
                    SearchTaskPlanResultDto resultDto2019 = searchRoleSomeoneTaskY2019Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2019.getListTaskPlan());
                    resultDto.setLimit(resultDto2019.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2019.getAllCount());
                    resultDto.setPageNumber(resultDto2019.getPageNumber());
                    break;

                // 2020年
                case YEAR_2020:
                    SearchTaskPlanResultDto resultDto2020 = searchRoleSomeoneTaskY2020Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2020.getListTaskPlan());
                    resultDto.setLimit(resultDto2020.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2020.getAllCount());
                    resultDto.setPageNumber(resultDto2020.getPageNumber());
                    break;

                // 2021年
                case YEAR_2021:
                    SearchTaskPlanResultDto resultDto2021 = searchRoleSomeoneTaskY2021Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2021.getListTaskPlan());
                    resultDto.setLimit(resultDto2021.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2021.getAllCount());
                    resultDto.setPageNumber(resultDto2021.getPageNumber());
                    break;

                // 2022年
                case YEAR_2022:
                    SearchTaskPlanResultDto resultDto2022 = searchRoleSomeoneTaskY2022Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2022.getListTaskPlan());
                    resultDto.setLimit(resultDto2022.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2022.getAllCount());
                    resultDto.setPageNumber(resultDto2022.getPageNumber());
                    break;

                // 2023年
                case YEAR_2023:
                    SearchTaskPlanResultDto resultDto2023 = searchRoleSomeoneTaskY2023Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2023.getListTaskPlan());
                    resultDto.setLimit(resultDto2023.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2023.getAllCount());
                    resultDto.setPageNumber(resultDto2023.getPageNumber());
                    break;

                // 2024年
                case YEAR_2024:
                    SearchTaskPlanResultDto resultDto2024 = searchRoleSomeoneTaskY2024Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2024.getListTaskPlan());
                    resultDto.setLimit(resultDto2024.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2024.getAllCount());
                    resultDto.setPageNumber(resultDto2024.getPageNumber());
                    break;

                // 2025年
                case YEAR_2025:
                    SearchTaskPlanResultDto resultDto2025 = searchRoleSomeoneTaskY2025Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2025.getListTaskPlan());
                    resultDto.setLimit(resultDto2025.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2025.getAllCount());
                    resultDto.setPageNumber(resultDto2025.getPageNumber());
                    break;

                // 2026年
                case YEAR_2026:
                    SearchTaskPlanResultDto resultDto2026 = searchRoleSomeoneTaskY2026Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2026.getListTaskPlan());
                    resultDto.setLimit(resultDto2026.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2026.getAllCount());
                    resultDto.setPageNumber(resultDto2026.getPageNumber());
                    break;

                // 2027年
                case YEAR_2027:
                    SearchTaskPlanResultDto resultDto2027 = searchRoleSomeoneTaskY2027Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2027.getListTaskPlan());
                    resultDto.setLimit(resultDto2027.getLimit());
                    resultDto.setAllCount(resultDto.getAllCount() + resultDto2027.getAllCount());
                    resultDto.setPageNumber(resultDto2027.getPageNumber());
                    break;

                // case次回追加位置

                default:
                    throw new IllegalArgumentException("Unexpected value: " + year);
            }
        }

        return resultDto;
    }

}
