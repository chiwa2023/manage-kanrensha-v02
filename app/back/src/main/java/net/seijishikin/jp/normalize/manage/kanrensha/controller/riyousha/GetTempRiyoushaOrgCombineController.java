package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetTempRiyoushaOrgCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetTempRiyoushaOrgCombineResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetTempRiyoushaOrgCombineService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者組織招待取得Controoler
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class GetTempRiyoushaOrgCombineController {

    /** 利用者組織招待取得Service */
    @Autowired
    private GetTempRiyoushaOrgCombineService getTempRiyoushaOrgCombineService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 仮情報取得Dto
     * @return レスポンス
     */
    @PostMapping("/get-temp-combine")
    public ResponseEntity<GetTempRiyoushaOrgCombineResultDto> practice(
            @RequestBody final GetTempRiyoushaOrgCombineCapsuleDto capsuleDto) {

        GetTempRiyoushaOrgCombineResultDto resultDto = new GetTempRiyoushaOrgCombineResultDto();
        try {
            // ユーザチェック
            // 利用者組織はconfigのSEと運営者のみ
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            resultDto = getTempRiyoushaOrgCombineService.practice(capsuleDto);
            if (resultDto.getIsFailure()) {
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
