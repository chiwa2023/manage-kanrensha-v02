package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record.InsertDupmRunRecordService;

/**
 * 履歴差分ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpHistorySabunService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceSabunDumpHistoryKigyouDtService forceSabunDumpHistoryKigyouDtService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceSabunDumpHistoryPersonService forceSabunDumpHistoryPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceSabunDumpHistorySeijidantaiService forceSabunDumpHistorySeijidantaiService;

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

        LocalDate startDate = capsuleDto.getDateStart();
        LocalDate endDate = capsuleDto.getDateEnd();

        List<Integer> listTask = new ArrayList<>();
        if (capsuleDto.getIsExecuteKigyouDt()) {
            forceSabunDumpHistoryKigyouDtService.practice(year, planDto1, startDate, endDate, capsuleDto.getUserDto());
            listTask.add(TaskInfoConstants.DUMP_HISTORY_SABUN_PERSON);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceSabunDumpHistoryPersonService.practice(year, planDto2, startDate, endDate, capsuleDto.getUserDto());
            listTask.add(TaskInfoConstants.DUMP_HISTORY_SABUN_KIGYOU);
        }
        if (capsuleDto.getIsExecuteSeijidantai()) {
            forceSabunDumpHistorySeijidantaiService.practice(year, planDto3, startDate, endDate,
                    capsuleDto.getUserDto());
            listTask.add(TaskInfoConstants.DUMP_HISTORY_SABUN_SEIJIDANTAI);
        }

        // 実行記録
        LocalDateTime startDatetime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endDatetime = LocalDateTime.of(endDate, LocalTime.MAX);

        insertDupmRunRecordService.practice(listTask, startDatetime, endDatetime, capsuleDto.getUserDto());
    }

}
