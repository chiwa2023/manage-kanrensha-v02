package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;

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

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final Integer year, final TaskPlanInfoDto planDto1, final TaskPlanInfoDto planDto2,
            final TaskPlanInfoDto planDto3, final ForceDumpCapsuleDto capsuleDto) {

        LocalDate startDate = capsuleDto.getDateStart();
        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteKigyouDt()) {
            forceSabunDumpHistoryKigyouDtService.practice(year, planDto1, startDate, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceSabunDumpHistoryPersonService.practice(year, planDto2, startDate, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecuteSeijidantai()) {
            forceSabunDumpHistorySeijidantaiService.practice(year, planDto3, startDate, endDate,
                    capsuleDto.getUserDto());
        }
    }

}
