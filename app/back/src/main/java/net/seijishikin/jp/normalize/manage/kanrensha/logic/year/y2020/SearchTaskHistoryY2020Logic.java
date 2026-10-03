package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2020.TaskPlan2020Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2020.TaskPlan2020Repository;

/**
 * タスク計画履歴取得Logic(2020)
 */
@Component
public class SearchTaskHistoryY2020Logic {

    /** タスク計画Repository(2020) */
    @Autowired
    private TaskPlan2020Repository taskPlan2020Repository;

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer taskPlanCode) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        List<TaskPlan2020Entity> listByCode = taskPlan2020Repository
                .findByTaskPlanCodeOrderByInsertTimestampAsc(taskPlanCode);

        for (TaskPlan2020Entity entity : listByCode) {
            list.add(this.convertBaseEntity(entity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertBaseEntity(final TaskPlan2020Entity entity) {

        TaskPlanBaseEntity entityCopy = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(entity, entityCopy);

        return entityCopy;
    }
}
