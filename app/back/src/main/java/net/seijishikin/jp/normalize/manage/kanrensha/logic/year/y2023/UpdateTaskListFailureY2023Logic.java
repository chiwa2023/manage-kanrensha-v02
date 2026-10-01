package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.TaskPlan2023Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023.TaskPlan2023Repository;

/**
 * タスク計画の開始と終了を記録する(バッチでない短時間処理タスク用)
 */
@Component
public class UpdateTaskListFailureY2023Logic {

    /** タスク計画Repository(2023) */
    @Autowired
    private TaskPlan2023Repository taskPlan2023Repository;

    /** タスク履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 0 */
    private static final Integer ZERO = 0;

    /**
     * 処理を行う
     * 
     * @param userDto    ユーザ最小限Dto
     * @param taskPlanId タスクId
     * @param endTime    終了時間
     * @return 最終更新Id
     */
    public Integer practice(final LeastUserDto userDto, final Integer taskPlanId, final Integer taskPlanCode,
            final LocalDateTime endTime) {

        // 既存の同コードデータは、この失敗を最新にするため、(複数ないと思うけど)すべての最新を履歴にする
        List<TaskPlan2023Entity> listOld = taskPlan2023Repository.findByTaskPlanCode(taskPlanCode);

        TaskPlan2023Entity entitySrc = new TaskPlan2023Entity();
        List<TaskPlan2023Entity> listHistory = new ArrayList<>();
        for (TaskPlan2023Entity entity : listOld) {
            // 作成時のデータ(Idが同じ)が存在した時点で元データと認定
            if (taskPlanId.equals(entity.getTaskPlanId())) {
                BeanUtils.copyProperties(entity, entitySrc);
            }
            if (entity.getIsLatest()) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity);
                listHistory.add(entity);
            }
        }
        if (ZERO.equals(entitySrc.getTaskPlanId())) {
            throw new EmptyResultDataAccessException("idでデータが呼びだせませんでした", 1);
        }
        if(!taskPlanCode.equals(entitySrc.getTaskPlanCode())) {
            throw new IllegalArgumentException("idとコードが同一のコードのものではありません");
        }
        taskPlan2023Repository.saveAll(listHistory);

        TaskPlan2023Entity entityNew = new TaskPlan2023Entity();
        BeanUtils.copyProperties(entitySrc, entityNew);

        entityNew.setIsStart(true); // 変わらない
        entityNew.setIsFinished(false);
        entityNew.setIsSuspended(true);
        entityNew.setEndDateimte(endTime);
        setTableDataHistoryUtil.practiceInsert(userDto, entityNew); // 終了が最新に変更
        entityNew.setTaskPlanId(0); // auto increment 明記

        return taskPlan2023Repository.save(entityNew).getTaskPlanId();
    }

}
