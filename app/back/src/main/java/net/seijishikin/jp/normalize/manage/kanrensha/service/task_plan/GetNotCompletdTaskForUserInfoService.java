package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskListForUserInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetNotCompletdTaskForUserInfoService;

/**
 * 未処理タスク取得Service
 */
@Service
public class GetNotCompletdTaskForUserInfoService {

    /** 年り替未処理タスク取得Service */
    @Autowired
    private SwitchYearGetNotCompletdTaskForUserInfoService switchYearGetNotCompletdTaskForUserInfoService;

    /**
     * 処理を行う
     * 
     * @param year       検索年
     * @param capsuleDto ユーザ最小限Dto
     * @return 検索結果Dto
     */
    public TaskListForUserInfoResultDto practice(final Integer year, final FrameworkCapsuleDto capsuleDto) {

        TaskListForUserInfoResultDto resultDto = new TaskListForUserInfoResultDto();

        LeastUserDto userDto = capsuleDto.getUserDto();
        // 本年度
        resultDto.setListThisYear(switchYearGetNotCompletdTaskForUserInfoService.practice(year, userDto));
        // 前年度
        resultDto.setListLastYear(switchYearGetNotCompletdTaskForUserInfoService.practice(year - 1, userDto));

        // 取得状態であることを更新
        resultDto.setIsRefreshed(true);

        return resultDto;
    }

}
