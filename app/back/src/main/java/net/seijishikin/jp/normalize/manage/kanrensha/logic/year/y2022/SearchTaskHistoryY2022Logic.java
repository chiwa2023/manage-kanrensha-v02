package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.TaskPlan2022Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022.TaskPlan2022Repository;

/**
 * タスク計画履歴取得Logic(2022)
 */
@Component
public class SearchTaskHistoryY2022Logic {

    /** タスク計画Repository(2022) */
    @Autowired
    private TaskPlan2022Repository taskPlan2022Repository;

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer taskPlanCode) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        List<TaskPlan2022Entity> listByCode = taskPlan2022Repository
                .findByTaskPlanCodeOrderByInsertTimestampAsc(taskPlanCode);

        for (TaskPlan2022Entity entity : listByCode) {
            list.add(this.convertBaseEntity(entity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertBaseEntity(final TaskPlan2022Entity entity) {

        TaskPlanBaseEntity entityCopy = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(entity, entityCopy);

        return entityCopy;
    }
}
