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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodePromoteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.MoveCodeKanrenshaPromoteSendMessageService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.MoveCodeKanrenshaPromoteService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者コード移動申請Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/kanrensha-code-move")
public class MoveCodeKanrenshaPromoteController {

    /** 関連者コード移動申請Service */
    @Autowired
    private MoveCodeKanrenshaPromoteService moveCodeKanrenshaPromoteService;

    /** 関連者コード移動申請該当者メッセージ送信Service */
    @Autowired
    private MoveCodeKanrenshaPromoteSendMessageService moveCodeKanrenshaPromoteSendMessageService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/promote")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final MoveKanrenshaCodePromoteCapsuleDto capsuleDto) {
        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            LocalDateTime createDatetime = LocalDateTime.now();

            InsertTaskPlanResultDto planResultDto = moveCodeKanrenshaPromoteService.practice(createDatetime,
                    capsuleDto);

            Integer newId = planResultDto.getSavedId();
            if (0 == newId) {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_RECORD);
                resultDto.setIsFailure(true);

            } else {
                // 関係者にメール送信
                moveCodeKanrenshaPromoteSendMessageService.practice(capsuleDto, planResultDto);
                
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
