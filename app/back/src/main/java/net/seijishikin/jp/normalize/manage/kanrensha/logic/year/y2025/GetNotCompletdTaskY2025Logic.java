package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;

/**
 * 未終了タスク取得Logic(2025)
 */
@Component
public class GetNotCompletdTaskY2025Logic {

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /**
     * 処理を行う
     * 
     * @param userDto ユーザ最小限Sto
     * @param limit   取得件数
     * @return 検索結果
     */
    public List<TaskPlanBaseEntity> practice(final LeastUserDto userDto, final Integer limit) {

        // 最初から指定件数の未取得
        Pageable pageable = Pageable.ofSize(limit).withPage(0);
        List<TaskPlan2025Entity> listYear = taskPlan2025Repository
                .findByInsertUserCodeAndIsLatestTrueAndIsFinishedFalseOrderByInsertTimestampDesc(
                        userDto.getUserPersonCode(), pageable);

        return this.convertList(listYear);
    }

    private List<TaskPlanBaseEntity> convertList(final List<TaskPlan2025Entity> listYear) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        for (TaskPlan2025Entity planEntity : listYear) {

            list.add(this.convertEntity(planEntity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertEntity(final TaskPlan2025Entity planEntity) {

        TaskPlanBaseEntity entityBase = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(planEntity, entityBase);

        return entityBase;
    }

}
