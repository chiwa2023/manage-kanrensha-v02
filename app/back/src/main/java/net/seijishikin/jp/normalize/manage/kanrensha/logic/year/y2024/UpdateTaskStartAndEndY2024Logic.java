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
 * タスク計画の開始と終了を記録する(バッチでない短時間処理タスク用)
 */
@Component
public class UpdateTaskStartAndEndY2024Logic {

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

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

        Optional<TaskPlan2024Entity> optional = taskPlan2024Repository.findById(taskPlanId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("更新対象のEnitytを抽出できませんでした", 1);
        }

        TaskPlan2024Entity entitySrc = optional.get();
        TaskPlan2024Entity entityNew = new TaskPlan2024Entity();
        BeanUtils.copyProperties(entitySrc, entityNew);

        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        taskPlan2024Repository.save(entitySrc);

        entityNew.setIsStart(true);
        entityNew.setIsFinished(true);
        entityNew.setEndDateimte(endTime);
        setTableDataHistoryUtil.practiceInsert(userDto, entityNew); // タスク自体が終了が最新に変更
        entityNew.setTaskPlanId(0); // auto increment 明記

        return taskPlan2024Repository.save(entityNew).getTaskPlanId();
    }

}
