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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SaveRiyoushaOrgEntityService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者組織更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class SaveRiyoushaOrgEntityController {

    /** 利用者組織更新Service */
    @Autowired
    private SaveRiyoushaOrgEntityService saveRiyoushaOrgEntityService;

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
     * @return レスポンス
     */
    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveRiyoushaOrgCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            // 組織の場合はコードチェックなし
            if (!validateAuthoraizeUserDetailLogic.practiceRiyousha(capsuleDto.getUserDto(),
                    capsuleDto.getRiyoushaOrgDto().getRiyoushaOrgMasterCode(), UserRoleConstants.MANAGER,
                    UserRoleConstants.MANAGER, UserRoleConstants.ADMIN)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            Integer savedId = saveRiyoushaOrgEntityService.practice(capsuleDto);

            if (savedId == 0) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("保存できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);

            } else {
                resultDto.setMessage("正常に保存できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);

        }

    }

}
