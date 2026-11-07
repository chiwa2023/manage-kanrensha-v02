package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha.combine;

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
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.UpdateKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearUpdateCombineOrgService;

/**
 * 関連者紐づけ用企業団体最小取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/kanrensha-combine")
public class UpdateKanrenshaCombineOrgController {

    /** 関連者紐づけ更新Service */
    @Autowired
    private SwitchYearUpdateCombineOrgService switchYearUpdateCombineOrgService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者紐づけ更新Dto
     * @return レスポンス
     */
    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody UpdateKanrenshaCombineCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            WkTblKanrenshaCombineOrgEntity entity = capsuleDto.getCombineOrgEntity();
            LeastUserDto userDto = capsuleDto.getUserDto();

            // チェックする関連者コードは、操作している関連者によって異なる
            String checkCode;
            if (UserRoleConstants.KANRENSHA_PERSON.equals(userDto.getKanrenshaRole())) {
                checkCode = entity.getPersonKanrenshaCode();
            } else {
                checkCode = entity.getOrgKanrenshaCode();
            }

            // 本人は許可
            if (!validateAuthoraizeUserDetailLogic.practiceKanrensha(userDto, checkCode, userDto.getKanrenshaRole(),
                    UserRoleConstants.MANAGER, UserRoleConstants.PARTNER_API, UserRoleConstants.ADMIN)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            Integer updateId = switchYearUpdateCombineOrgService.practice(entity, userDto);

            if (0 == updateId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("更新したデータが存在しませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
