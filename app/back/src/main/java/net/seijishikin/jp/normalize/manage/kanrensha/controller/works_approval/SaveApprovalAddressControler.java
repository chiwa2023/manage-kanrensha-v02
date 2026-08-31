package net.seijishikin.jp.normalize.manage.kanrensha.controller.works_approval;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SaveWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval.SaveApprovalAddressService;

/**
 * 作業承認保存Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/works-approval")
public class SaveApprovalAddressControler {

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService stackTraceService;

    /** 住所承認作業保存Service */
    @Autowired
    private SaveApprovalAddressService saveApprovalAddressService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理リストDto
     * @return 処理結果レスポンス
     */
    @PostMapping("/save-address")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SaveWorksApprovalCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            Integer addressCount = saveApprovalAddressService.practice(capsuleDto.getListAddress(),
                    capsuleDto.getUserDto());

            if (0 == addressCount) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("更新件数が0件でした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage("正常に更新できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            stackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
