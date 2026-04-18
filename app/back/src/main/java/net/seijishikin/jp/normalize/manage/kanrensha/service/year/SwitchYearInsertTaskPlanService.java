package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertTaskPlanY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.InsertTaskPlanY2026Logic;

/**
 * タスク計画挿入(年管理)Service develop_security
 */
@Service
public class SwitchYearInsertTaskPlanService {

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** タスク計画追加Logic(2026) */
    @Autowired
    private InsertTaskPlanY2026Logic insertTaskPlanY2026Logic;

    /**
     * 処理を行う
     *
     * @param userDto       ユーザ最小限Dto
     * @param startDatetime タスク開始時間
     * @param taskPlanCode  タスク情報コード
     * @return 追加Id
     */
    @Transactional
    public InsertTaskPlanResultDto practice(final LeastUserDto userDto, final LocalDateTime startDatetime,
            final Integer taskPlanCode, final Map<String, String> mapParam) {

        Integer year = startDatetime.getYear();
        switch (year) {
            case YEAR_2025:
                return insertTaskPlanY2025Logic.practice(userDto, startDatetime, taskPlanCode, mapParam);
            case YEAR_2026:
                return insertTaskPlanY2026Logic.practice(userDto, startDatetime, taskPlanCode, mapParam);

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
    }
}
