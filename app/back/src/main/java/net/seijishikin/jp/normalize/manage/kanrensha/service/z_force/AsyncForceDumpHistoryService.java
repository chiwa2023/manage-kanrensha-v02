package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;

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

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final Integer year, final TaskPlanInfoDto planDto1, final TaskPlanInfoDto planDto2,
            final TaskPlanInfoDto planDto3, final ForceDumpCapsuleDto capsuleDto) {

        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteKigyouDt()) {
            forceDumpHistoryKigyouDtService.practice(year, planDto1, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpHistoryPersonService.practice(year, planDto2, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecuteSeijidantai()) {
            forceDumpHistorySeijidantaiService.practice(year, planDto3, endDate, capsuleDto.getUserDto());
        }
    }

}
