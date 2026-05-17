package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;

/**
 * タスク計画の開始と終了を記録する(バッチでない短時間処理タスク用)
 */
@Component
public class UpdateTaskStartAndEndY2026Logic {

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

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

        Optional<TaskPlan2026Entity> optional = taskPlan2026Repository.findById(taskPlanId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("更新対象のEnitytを抽出できませんでした", 1);
        }

        TaskPlan2026Entity entitySrc = optional.get();
        TaskPlan2026Entity entityNew = new TaskPlan2026Entity();
        BeanUtils.copyProperties(entitySrc, entityNew);

        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        taskPlan2026Repository.save(entitySrc);

        entityNew.setIsStart(true);
        // entityNew.setStartDatetime(endTime);
        entityNew.setIsFinished(true);
        entityNew.setEndDateimte(endTime);
        setTableDataHistoryUtil.practiceInsert(userDto, entityNew);
        entityNew.setTaskPlanId(0); // auto increment 明記

        return taskPlan2026Repository.save(entityNew).getTaskPlanId();
    }

}
