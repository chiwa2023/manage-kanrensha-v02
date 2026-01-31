package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;

/**
 * 未終了タスク取得Logic(2026)
 */
@Component
public class GetNotCompletdTaskY2026Logic {

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

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
        List<TaskPlan2026Entity> listYear = taskPlan2026Repository
                .findByInsertUserCodeAndIsLatestTrueAndIsFinishedFalseOrderByInsertTimestampDesc(
                        userDto.getUserPersonCode(), pageable);

        return this.convertList(listYear);
    }

    private List<TaskPlanBaseEntity> convertList(final List<TaskPlan2026Entity> listYear) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        for (TaskPlan2026Entity planEntity : listYear) {

            list.add(this.convertEntity(planEntity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertEntity(final TaskPlan2026Entity planEntity) {

        TaskPlanBaseEntity entityBase = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(planEntity, entityBase);

        return entityBase;
    }

}
