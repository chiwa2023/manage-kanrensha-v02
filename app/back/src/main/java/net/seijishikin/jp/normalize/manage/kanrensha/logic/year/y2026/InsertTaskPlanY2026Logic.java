package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.ConvertQueryParamLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;

/**
 * タスク計画挿入Logic(2026)
 */
@Component
public class InsertTaskPlanY2026Logic {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    /** queryパラメータ作成Logic */
    @Autowired
    private ConvertQueryParamLogic convertQueryParamLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** このLogicの登録年 */
    private static final Integer THIS_YEAR = 2026;

    /**
     * 処理を行う
     * 
     * @param userDto        ユーザ最小限Dto
     * @param startDatetime  タスク開始時間
     * @param taskInfoCode タスク情報Entity
     * @param mapParam       queryパラメータMap
     * @return 追加Id
     */
    public InsertTaskPlanResultDto practice(final LeastUserDto userDto, final LocalDateTime startDatetime,
            final Integer taskInfoCode, final Map<String, String> mapParam) {

        List<TaskInfoEntity> list = taskInfoRepository.findByTaskInfoCodeAndIsLatestTrue(taskInfoCode);
        if (list.isEmpty()) {
            throw new EmptyResultDataAccessException("指定されたタスク情報が存在しません(" + taskInfoCode + ")", 0);
        }

        // DB的に1件しか存在しない想定
        TaskInfoEntity taskInfoEntity = list.get(0);

        TaskPlan2026Entity planEntity = new TaskPlan2026Entity();

        planEntity.setTableYear(THIS_YEAR);
        planEntity.setTaskInfoCode(taskInfoEntity.getTaskInfoCode());
        planEntity.setTaskPlanName(taskInfoEntity.getTaskInfoName());

        planEntity.setStartDatetime(startDatetime);
        planEntity.setIsStart(true);

        planEntity.setEndDateimte(AllTabeDataHistoryInterface.INIT_TIMESTAMP);
        planEntity.setIsFinished(AllTabeDataHistoryInterface.INIT_BOOLEAN);
        planEntity.setIsSuspended(AllTabeDataHistoryInterface.INIT_BOOLEAN);
        planEntity.setRoleList(taskInfoEntity.getRoleList());
        planEntity.setTransferPass(taskInfoEntity.getTransferPass()
                + convertQueryParamLogic.practice(taskInfoEntity.getParamQuery(), mapParam));

        setTableDataHistoryUtil.practiceInsert(userDto, planEntity);

        // コードを取得
        Integer code = 1;
        Optional<TaskPlan2026Entity> optional = taskPlan2026Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getTaskPlanCode();
        }
        planEntity.setTaskPlanCode(code);

        planEntity.setTaskPlanId(0); // auto increment明記

        TaskPlan2026Entity savedEntity = taskPlan2026Repository.save(planEntity);

        InsertTaskPlanResultDto resultDto = new InsertTaskPlanResultDto();
        resultDto.setTaskYear(savedEntity.getTableYear());
        resultDto.setTaskPlanId(savedEntity.getTaskPlanId());
        resultDto.setTaskInfoCode(savedEntity.getTaskInfoCode());
        resultDto.setTaskPlanName(savedEntity.getTaskPlanName());
        resultDto.setTransferPass(savedEntity.getTransferPass());
        resultDto.setParamQuery(taskInfoEntity.getParamQuery());
        resultDto.setMessageTemplate(taskInfoEntity.getMessageTemplate());

        return resultDto;
    }

}
