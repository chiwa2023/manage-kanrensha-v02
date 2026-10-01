package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.TaskPlan2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.TaskPlan2024Repository;

/**
 * タスク計画更新Logic(2024)
 */
@Component
public class UpdateTaskPlanY2024Logic {

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    /** テービル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param userDto     ユーザ最小限Dto
     * @param taskId      タスクId
     * @param endDatetime 終了日時
     * @param isFinished  終了状態
     * @return 保存Id(taskIdと同じはず)
     */
    public Integer practice(final LeastUserDto userDto, final Integer taskId, final LocalDateTime endDatetime,
            final boolean isFinished) {

        Optional<TaskPlan2024Entity> optional = taskPlan2024Repository.findById(taskId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("必要なタスク計画が呼び出せませんでした:" + taskId, 1);
        }

        TaskPlan2024Entity deleteEntity = new TaskPlan2024Entity();
        BeanUtils.copyProperties(optional.get(), deleteEntity);

        // すでに削除状態であれば作業中断
        if (!deleteEntity.getIsLatest()) {
            return 0;
        }

        setTableDataHistoryUtil.practiceDelete(userDto, deleteEntity);
        taskPlan2024Repository.save(deleteEntity);

        TaskPlan2024Entity updateEntity = new TaskPlan2024Entity();
        BeanUtils.copyProperties(optional.get(), updateEntity);
        updateEntity.setTaskPlanId(0); // auto increment

        // 終了時間と終了フラグをセットする
        updateEntity.setEndDateimte(endDatetime);
        updateEntity.setIsFinished(isFinished);
        updateEntity.setIsSuspended(!isFinished);

        setTableDataHistoryUtil.practiceInsert(userDto, updateEntity);
        return taskPlan2024Repository.save(updateEntity).getTaskPlanId();
    }

}
