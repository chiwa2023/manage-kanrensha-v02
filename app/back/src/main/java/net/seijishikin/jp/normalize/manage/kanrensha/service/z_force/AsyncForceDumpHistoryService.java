package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record.InsertDupmRunRecordService;

/**
 * 履歴ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpHistoryService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpHistoryKigyouDtService forceDumpHistoryKigyouDtService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpHistoryPersonService forceDumpHistoryPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpHistorySeijidantaiService forceDumpHistorySeijidantaiService;

    /** ダンプ実行記録Service */
    @Autowired
    private InsertDupmRunRecordService insertDupmRunRecordService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final Integer year, final InsertTaskPlanResultDto planDto1,
            final InsertTaskPlanResultDto planDto2, final InsertTaskPlanResultDto planDto3,
            final ForceDumpCapsuleDto capsuleDto) {

        LocalDate endDate = capsuleDto.getDateEnd();

        List<Integer> listTask = new ArrayList<>();
        if (capsuleDto.getIsExecuteKigyouDt()) {
            forceDumpHistoryKigyouDtService.practice(year, planDto1, endDate, capsuleDto.getUserDto());
            listTask.add(TaskInfoConstants.DUMP_HISTORY_PERSON);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpHistoryPersonService.practice(year, planDto2, endDate, capsuleDto.getUserDto());
            listTask.add(TaskInfoConstants.DUMP_HISTORY_KIGYOU);
        }
        if (capsuleDto.getIsExecuteSeijidantai()) {
            forceDumpHistorySeijidantaiService.practice(year, planDto3, endDate, capsuleDto.getUserDto());
            listTask.add(TaskInfoConstants.DUMP_HISTORY_SEIJIDANTAI);
        }

        // 実行記録
        LocalDateTime startDatetime = DtoEntityInitialValueInterface.INIT_TIMESTAMP;
        LocalDateTime endDatetime = LocalDateTime.of(endDate, LocalTime.MIN);
        insertDupmRunRecordService.practice(listTask, startDatetime, endDatetime, capsuleDto.getUserDto());

    }

}
