package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskListForUserInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetRoleSomeoneTaskService;

/**
 * 権限指定タスク取得Service
 */
@Service
public class GetRoleSomeoneTaskService {

    
    /** 年切り替え権限指定タスク */
    @Autowired
    private SwitchYearGetRoleSomeoneTaskService switchYearGetRoleSomeoneTaskService;
    
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
        // 本年
        resultDto.setListThisYear(switchYearGetRoleSomeoneTaskService.practice(year, userDto));
        // 前年
        resultDto.setListLastYear(switchYearGetRoleSomeoneTaskService.practice(year - 1, userDto));

        // 取得状態であることを更新
        resultDto.setIsRefreshed(true);

        return resultDto;
    }


}
