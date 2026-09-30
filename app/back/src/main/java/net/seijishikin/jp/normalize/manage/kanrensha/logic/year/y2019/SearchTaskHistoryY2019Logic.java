package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2019.TaskPlan2019Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2019.TaskPlan2019Repository;

/**
 * タスク計画履歴取得Logic(2019)
 */
@Component
public class SearchTaskHistoryY2019Logic {

    /** タスク計画Repository(2019) */
    @Autowired
    private TaskPlan2019Repository taskPlan2019Repository;

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer taskPlanCode) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        List<TaskPlan2019Entity> listByCode = taskPlan2019Repository
                .findByTaskPlanCodeOrderByInsertTimestampAsc(taskPlanCode);

        for (TaskPlan2019Entity entity : listByCode) {
            list.add(this.convertBaseEntity(entity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertBaseEntity(final TaskPlan2019Entity entity) {

        TaskPlanBaseEntity entityCopy = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(entity, entityCopy);

        return entityCopy;
    }
}
