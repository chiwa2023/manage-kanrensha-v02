package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.TaskPlan2021Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021.TaskPlan2021Repository;

/**
 * タスク計画履歴取得Logic(2021)
 */
@Component
public class SearchTaskHistoryY2021Logic {

    /** タスク計画Repository(2021) */
    @Autowired
    private TaskPlan2021Repository taskPlan2021Repository;

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> practice(final Integer taskPlanCode) {

        List<TaskPlanBaseEntity> list = new ArrayList<>();
        List<TaskPlan2021Entity> listByCode = taskPlan2021Repository
                .findByTaskPlanCodeOrderByInsertTimestampAsc(taskPlanCode);

        for (TaskPlan2021Entity entity : listByCode) {
            list.add(this.convertBaseEntity(entity));
        }

        return list;
    }

    private TaskPlanBaseEntity convertBaseEntity(final TaskPlan2021Entity entity) {

        TaskPlanBaseEntity entityCopy = new TaskPlanBaseEntity();
        BeanUtils.copyProperties(entity, entityCopy);

        return entityCopy;
    }
}
