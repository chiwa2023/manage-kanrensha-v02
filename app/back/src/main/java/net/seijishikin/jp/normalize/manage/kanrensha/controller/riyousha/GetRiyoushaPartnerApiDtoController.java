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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaPartnerApiByEntityCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaPartnerApiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaPartnerApiDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者運営者取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha")
public class GetRiyoushaPartnerApiDtoController {

    /** 利用者運営者取得Service */
    @Autowired
    private GetRiyoushaPartnerApiDtoService getRiyoushaPartnerApiDtoService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto マスタEntityから利用者取得Dto
     * @return レスポンス
     */
    @PostMapping("/get-partner-api")
    public ResponseEntity<RiyoushaPartnerApiDto> practice(
            @RequestBody final GetRiyoushaPartnerApiByEntityCapsuleDto capsuleDto) {

        RiyoushaPartnerApiDto resultDto = new RiyoushaPartnerApiDto();
        try {
            // ユーザチェック
            // APIパートナとSE権限者
            if (!validateAuthoraizeUserDetailLogic.practiceRiyousha(capsuleDto.getUserDto(),
                    capsuleDto.getMasterEntity().getRiyoushaPartnerApiMasterCode(), UserRoleConstants.PARTNER_API,
                    UserRoleConstants.ADMIN)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            resultDto = getRiyoushaPartnerApiDtoService.practice(capsuleDto.getMasterEntity());
            final Integer zero = 0;
            if (zero.equals(resultDto.getRiyoushaPartnerApiMasterId())) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
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
