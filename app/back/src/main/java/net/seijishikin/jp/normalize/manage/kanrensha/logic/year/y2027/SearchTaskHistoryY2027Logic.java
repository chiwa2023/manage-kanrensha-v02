package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2027.TaskPlan2027Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.TaskPlan2027Repository;

/**
 * タスク計画履歴取得Logic(2027)
 */
@Component
public class SearchTaskHistoryY2027Logic {

    /** タスク計画Repository(2027) */
    @Autowired
    private TaskPlan2027Repository taskPlan2027Repository;

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer taskPlanCode) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        List<TaskPlan2027Entity> listByCode = taskPlan2027Repository
                .findByTaskPlanCodeOrderByInsertTimestampAsc(taskPlanCode);

        for (TaskPlan2027Entity entity : listByCode) {
            list.add(this.convertBaseEntity(entity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertBaseEntity(final TaskPlan2027Entity entity) {

        TaskPlanBaseEntity entityCopy = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(entity, entityCopy);

        return entityCopy;
    }
}
