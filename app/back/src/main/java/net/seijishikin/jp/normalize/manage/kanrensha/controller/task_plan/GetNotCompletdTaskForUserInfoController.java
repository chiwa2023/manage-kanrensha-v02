package net.seijishikin.jp.normalize.manage.kanrensha.controller.task_plan;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskListForUserInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan.GetNotCompletdTaskForUserInfoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 未処理タスク取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/task-plan")
public class GetNotCompletdTaskForUserInfoController {

    /** 未処理タスク取得Service */
    @Autowired
    private GetNotCompletdTaskForUserInfoService getNotCompletdTaskForUserInfoService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 起動条件Dto
     * @return レスポンス
     */
    @PostMapping("/get-not-finished")
    public ResponseEntity<TaskListForUserInfoResultDto> practice(final @RequestBody FrameworkCapsuleDto capsuleDto) {

        Integer year = LocalDate.now().getYear();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(getNotCompletdTaskForUserInfoService.practice(year, capsuleDto));

        } catch (UsernameNotFoundException exception) {
            // resultDto.setIsFailure(true);
            // resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, year, 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TaskListForUserInfoResultDto());
        }
    }

}
