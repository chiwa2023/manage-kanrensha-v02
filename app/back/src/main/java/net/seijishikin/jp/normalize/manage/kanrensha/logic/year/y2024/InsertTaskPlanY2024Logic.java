package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.TaskPlan2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.ConvertQueryParamLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.TaskPlan2024Repository;

/**
 * タスク計画挿入Logic(2024)
 */
@Component
public class InsertTaskPlanY2024Logic {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    /** queryパラメータ作成Logic */
    @Autowired
    private ConvertQueryParamLogic convertQueryParamLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** このLogicの登録年 */
    private static final Integer THIS_YEAR = 2024;

    /**
     * 処理を行う
     * 
     * @param userDtoWork   作業者ユーザDto
     * @param userDtoInsert 操作者ユーザDto
     * @param startDatetime タスク開始時間
     * @param taskInfoCode  タスク情報Entity
     * @param mapParam      queryパラメータMap
     * @return 追加Id
     */
    public InsertTaskPlanResultDto practice(final LeastUserDto userDtoWork, final LeastUserDto userDtoInsert,
            final LocalDateTime startDatetime, final Integer taskInfoCode, final Map<String, String> mapParam) {

        // タスク対象のユーザを設定する
        LeastUserDto taskUser;
        if (Objects.isNull(userDtoWork)) {
            taskUser = userDtoInsert;
        } else {
            taskUser = userDtoWork;
        }

        List<TaskInfoEntity> list = taskInfoRepository.findByTaskInfoCodeAndIsLatestTrue(taskInfoCode);
        if (list.isEmpty()) {
            throw new EmptyResultDataAccessException("指定されたタスク情報が存在しません(" + taskInfoCode + ")", 0);
        }

        // DB的に1件しか存在しない想定
        TaskInfoEntity taskInfoEntity = list.get(0);

        TaskPlan2024Entity planEntity = new TaskPlan2024Entity();

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

        planEntity.setTaskUserCode(taskUser.getUserPersonCode());
        planEntity.setTaskUserName(taskUser.getUserPersonName());

        setTableDataHistoryUtil.practiceInsert(userDtoInsert, planEntity);

        // コードを取得
        Integer code = 1;
        Optional<TaskPlan2024Entity> optional = taskPlan2024Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getTaskPlanCode();
        }
        planEntity.setTaskPlanCode(code);

        planEntity.setTaskPlanId(0); // auto increment明記

        TaskPlan2024Entity savedEntity = taskPlan2024Repository.save(planEntity);

        InsertTaskPlanResultDto resultDto = new InsertTaskPlanResultDto();
        resultDto.setTaskYear(savedEntity.getTableYear());
        resultDto.setTaskPlanId(savedEntity.getTaskPlanId());
        resultDto.setTaskInfoCode(savedEntity.getTaskInfoCode());
        resultDto.setTaskPlanCode(savedEntity.getTaskPlanCode());
        resultDto.setTaskPlanName(savedEntity.getTaskPlanName());
        resultDto.setTransferPass(savedEntity.getTransferPass());
        resultDto.setParamQuery(taskInfoEntity.getParamQuery());
        resultDto.setMessageTemplate(taskInfoEntity.getMessageStart());

        return resultDto;
    }

}
