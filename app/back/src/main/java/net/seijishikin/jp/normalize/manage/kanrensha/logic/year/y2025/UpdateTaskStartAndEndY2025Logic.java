package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;

/**
 * タスク計画の開始と終了を記録する(バッチでない短時間処理タスク用)
 */
@Component
public class UpdateTaskStartAndEndY2025Logic {

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

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

        Optional<TaskPlan2025Entity> optional = taskPlan2025Repository.findById(taskPlanId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("更新対象のEnitytを抽出できませんでした", 1);
        }

        TaskPlan2025Entity entitySrc = optional.get();
        TaskPlan2025Entity entityNew = new TaskPlan2025Entity();
        BeanUtils.copyProperties(entitySrc, entityNew);

        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        taskPlan2025Repository.save(entitySrc);

        entityNew.setIsStart(true);
        entityNew.setStartDatetime(endTime);
        entityNew.setIsFinished(true);
        entityNew.setEndDateimte(endTime);
        setTableDataHistoryUtil.practiceInsert(userDto, entityNew); // タスク自体が終了が最新に変更
        entityNew.setTaskPlanId(0); // auto increment 明記

        return taskPlan2025Repository.save(entityNew).getTaskPlanId();
    }

}
