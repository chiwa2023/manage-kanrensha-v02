package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertTaskPlanY2025Logic;

/**
 * タスク計画挿入(年管理)Service
 */
@Service
public class SwitchYearInsertTaskPlanService {

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    /**
     * 処理を行う
     *
     * @param year     処理年
     * @param userDto  ユーザ最低限Dto
     * @param taskCode タスクコード
     * @return 処理後Id
     */
    public Integer practice(final int year, final LeastUserDto userDto, final Integer taskCode) {
        switch (year) {
            // 2025年
            case YEAR_2025:
                return insertTaskPlanY2025Logic.practice(userDto, taskCode);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
