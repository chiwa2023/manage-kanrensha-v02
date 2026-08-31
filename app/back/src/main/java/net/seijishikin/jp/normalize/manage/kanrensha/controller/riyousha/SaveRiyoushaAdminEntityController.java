package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SaveRiyoushaAdminEntityService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者SE権限保存Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha")
public class SaveRiyoushaAdminEntityController {

    /** 利用者運営者保存Service */
    @Autowired
    private SaveRiyoushaAdminEntityService saveRiyoushaAdminEntityService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto リクエストボディDto
     * @return レスポンス
     */
    @PostMapping("/save-admin")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveRiyoushaAdminCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            // SE権限者が相互にデータを触れる
            if (!validateAuthoraizeUserDetailLogic.practiceRiyousha(capsuleDto.getUserDto(),
                    capsuleDto.getRiyoushaAdminDto().getRiyoushaAdminMasterCode(), UserRoleConstants.ADMIN,
                    UserRoleConstants.ADMIN)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            final Integer zero = 0;
            if (zero.equals(saveRiyoushaAdminEntityService.practice(capsuleDto))) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_RECORD);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);

            } else {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD AvoidCatchGenericException
            // 例外を保存してエラー発生を伝達
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
