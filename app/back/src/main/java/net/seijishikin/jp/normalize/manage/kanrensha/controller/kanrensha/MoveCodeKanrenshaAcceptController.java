package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.MoveCodeKanrenshaAcceptService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearTaskSuccessService;

/**
 * 関連者コード移動承認Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/kanrensha-code-move")
public class MoveCodeKanrenshaAcceptController {

    /**
     * 関連者コード移動承認Service
     */
    @Autowired
    private MoveCodeKanrenshaAcceptService moveCodeKanrenshaAcceptService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /** タスク計画成功登録Service */
    @Autowired
    private SwitchYearTaskSuccessService switchYearTaskSuccessService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/accept")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final MoveKanrenshaCodeAcceptCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();

        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            Integer newId = moveCodeKanrenshaAcceptService.practice(capsuleDto, LocalDateTime.now());

            if (0 == newId) {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_RECORD);
                resultDto.setIsFailure(true);
            } else {

                // 正常に更新できた時はタスク終了処理が必要になることもある。 失敗検知はしない
                if (0 != capsuleDto.getTaskYear() && 0 != capsuleDto.getTaskPlanId()) {
                    try {
                        switchYearTaskSuccessService.practice(capsuleDto.getTaskYear(), capsuleDto.getUserDto(),
                                capsuleDto.getTaskPlanId(), LocalDateTime.now());
                    } catch (Exception exception) { // NOPMD 業務上の理由から積極的許容
                        saveStackTraceService.practice(exception, Year.now().getValue(), 0);
                    }
                }

                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            resultDto.setIsFailure(true);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
    }
}