package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * 権限限定タスク計画追加Service
 */
@Service
public class InsertTaskRoleOnlyService {

    /** 年切替タスク計画挿入Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanInsertService;

    /**
     * 処理を行う
     * 
     * @param userDto        ユーザ最小限
     * @param createDatetime 作成日時
     * @param taskInfoCode   タスク情報コード
     * @param mapParam       呼び出しパラメータ
     * @return 処理結果Dto
     */
    public InsertTaskPlanResultDto practice(final LeastUserDto userDto, final LocalDateTime createDatetime,
            final Integer taskInfoCode, final Map<String, String> mapParam) {

        // タスク計画が挿入できなかったとしても本筋の処理ができていれば、処理としてまぁOKなので処理継続
        InsertTaskPlanResultDto resultDto = new InsertTaskPlanResultDto();

        Integer savedId;
        try {
            // 空ユーザを作業者とすると本人には限定しない、権限だけをタスク作業者とするタスクができる、
            resultDto = switchYearInsertTaskPlanInsertService.practice(new LeastUserDto(), userDto, createDatetime,
                    taskInfoCode, mapParam);

            savedId = resultDto.getTaskPlanId();
            if (0 == savedId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("タスク計画が登録できませんでした");
                return resultDto;
            }
        } catch (EmptyResultDataAccessException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return resultDto;

        } catch (IllegalArgumentException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("タスク計画が登録できませんでした");
            return resultDto;
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            resultDto.setIsFailure(true);
            resultDto.setMessage("タスク計画が登録できませんでした");
            return resultDto;
        }

        return resultDto;
    }

}
