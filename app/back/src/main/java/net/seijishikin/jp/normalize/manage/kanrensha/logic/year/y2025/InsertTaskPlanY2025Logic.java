package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;

/**
 * タスク計画挿入Logic(2025)
 */
@Component
public class InsertTaskPlanY2025Logic {

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param userDto   ユーザ最小限Dto
     * @param startDatetime  タスク開始時間
     * @param taskInfoEntity タスク情報Entity
     * @return 追加Id
     */
    public InsertTaskPlanResultDto practice(final LeastUserDto userDto, final LocalDateTime startDatetime,
            final TaskInfoEntity taskInfoEntity) {

        TaskPlan2025Entity planEntity = new TaskPlan2025Entity();

        planEntity.setTableYear(startDatetime.getYear());
        planEntity.setTaskInfoCode(taskInfoEntity.getTaskInfoCode());
        planEntity.setTaskPlanName(taskInfoEntity.getTaskInfoName());

        planEntity.setStartDatetime(startDatetime);
        planEntity.setEndDateimte(AllTabeDataHistoryInterface.INIT_TIMESTAMP);
        planEntity.setIsFinished(AllTabeDataHistoryInterface.INIT_BOOLEAN);
        planEntity.setIsStart(AllTabeDataHistoryInterface.INIT_BOOLEAN);
        planEntity.setIsSuspended(AllTabeDataHistoryInterface.INIT_BOOLEAN);
        planEntity.setRoleList(taskInfoEntity.getRoleList());

        setTableDataHistoryUtil.practiceInsert(userDto, planEntity);

        // コードを取得
        Integer code = 1;
        Optional<TaskPlan2025Entity> optional = taskPlan2025Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getTaskPlanCode();
        }
        planEntity.setTaskPlanCode(code);

        planEntity.setTaskPlanId(0); // auto increment明記

        TaskPlan2025Entity savedEntity = taskPlan2025Repository.save(planEntity);

        InsertTaskPlanResultDto resultDto = new InsertTaskPlanResultDto();
        resultDto.setTaskYear(savedEntity.getTableYear());
        resultDto.setTaskPlanId(savedEntity.getTaskPlanId());
        
        return resultDto;
    }

}
