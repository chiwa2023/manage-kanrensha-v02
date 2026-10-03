package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.TaskPlan2023Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023.TaskPlan2023Repository;

/**
 * タスク計画履歴取得Logic(2023)
 */
@Component
public class SearchTaskHistoryY2023Logic {

    /** タスク計画Repository(2023) */
    @Autowired
    private TaskPlan2023Repository taskPlan2023Repository;

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer taskPlanCode) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        List<TaskPlan2023Entity> listByCode = taskPlan2023Repository
                .findByTaskPlanCodeOrderByInsertTimestampAsc(taskPlanCode);

        for (TaskPlan2023Entity entity : listByCode) {
            list.add(this.convertBaseEntity(entity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertBaseEntity(final TaskPlan2023Entity entity) {

        TaskPlanBaseEntity entityCopy = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(entity, entityCopy);

        return entityCopy;
    }
}
