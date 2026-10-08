package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.TaskPlan2021Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021.TaskPlan2021Repository;

/**
 * タスク計画の開始と終了を記録する(バッチでない短時間処理タスク用)
 */
@Component
public class UpdateTaskStartAndEndY2021Logic {

    /** タスク計画Repository(2021) */
    @Autowired
    private TaskPlan2021Repository taskPlan2021Repository;

    /** タスク履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param userDto    ユーザ最小限Dto
     * @param taskPlanId タスクId
     * @param endTime    終了時間
     * @return 最終更新Id
     */
    public Integer practice(final LeastUserDto userDto, final Integer taskPlanId, final LocalDateTime endTime) {

        Optional<TaskPlan2021Entity> optional = taskPlan2021Repository.findById(taskPlanId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("更新対象のEnitytを抽出できませんでした", 1);
        }

        TaskPlan2021Entity entitySrc = optional.get();
        TaskPlan2021Entity entityNew = new TaskPlan2021Entity();
        BeanUtils.copyProperties(entitySrc, entityNew);

        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        taskPlan2021Repository.save(entitySrc);

        entityNew.setIsStart(true);
        entityNew.setIsFinished(true);
        entityNew.setEndDateimte(endTime);
        setTableDataHistoryUtil.practiceInsert(userDto, entityNew);
        entityNew.setTaskPlanId(0); // auto increment 明記

        return taskPlan2021Repository.save(entityNew).getTaskPlanId();
    }

}
