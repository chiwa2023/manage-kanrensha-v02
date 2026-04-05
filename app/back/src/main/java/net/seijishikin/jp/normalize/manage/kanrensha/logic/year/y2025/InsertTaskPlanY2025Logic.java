package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;

/**
 * タスク情報を基にタスク計画挿入Logic
 */
@Component
public class InsertTaskPlanY2025Logic {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /** テービル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** このLogicの登録年 */
    private static final Integer THIS_YEAR = 2025;
    
    /**
     * 処理を行う
     *
     * @param userDto      ユーザ最低限Dto
     * @param taskInfoCode タスク情報コード
     * @return 挿入後Id
     */
    public TaskPlanInfoDto practice(final LeastUserDto userDto, final Integer taskInfoCode) {

        List<TaskInfoEntity> list = taskInfoRepository.findByTaskInfoCodeAndIsLatest(taskInfoCode,
                SetTableDataHistoryUtil.INSERT_STATE);
        if (list.isEmpty()) {
            throw new EmptyResultDataAccessException("指定されたタスク情報が存在しません(" + taskInfoCode + ")", 0);
        }

        // DB的に1件しか存在しない想定
        TaskInfoEntity taskInfoEntity = list.get(0);

        TaskPlan2025Entity entityPlan = new TaskPlan2025Entity();

        setTableDataHistoryUtil.practiceInsert(userDto, entityPlan);
        entityPlan.setTaskInfoCode(taskInfoCode);
        entityPlan.setRoleList(taskInfoEntity.getRoleList());
        entityPlan.setTaskPlanName(taskInfoEntity.getTaskInfoName());
        entityPlan.setTransferPass(taskInfoEntity.getTransferPass());
        entityPlan.setStartDatetime(LocalDateTime.now());
        entityPlan.setIsStart(true);
        entityPlan.setTableYear(THIS_YEAR);

        // 新規タスクは新しいコード番号を振る
        int code = 1;
        Optional<TaskPlan2025Entity> optional = taskPlan2025Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getTaskPlanCode();
        }
        entityPlan.setTaskPlanCode(code);

        TaskPlan2025Entity savedEntity = taskPlan2025Repository.save(entityPlan);
        TaskPlanInfoDto dto = new TaskPlanInfoDto();
        BeanUtils.copyProperties(savedEntity, dto);
        return dto;
    }

}
