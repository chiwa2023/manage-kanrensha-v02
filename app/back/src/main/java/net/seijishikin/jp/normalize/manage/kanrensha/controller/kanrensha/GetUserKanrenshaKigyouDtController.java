package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者個人Dto取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-kanrensha")
public class GetUserKanrenshaKigyouDtController {

    /** 関連者個人Dto取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

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
    @PostMapping("/get-kigyou-dt")
    public ResponseEntity<GetKanrenshaKigyouDtResultDto> practice(
            @RequestBody final GetKanrenshaKigyouDtCapsuleDto capsuleDto) {

        GetKanrenshaKigyouDtResultDto resultDto = new GetKanrenshaKigyouDtResultDto();

        try {
            // ユーザチェック
            // 利用者は許可
            if (!validateAuthoraizeUserDetailLogic.practiceKanrensha(capsuleDto.getUserDto(),
                    capsuleDto.getMasterKigyouDtEntity().getKigyouDtKanrenshaCode(),
                    UserRoleConstants.KANRENSHA_KIGYOU_DT, UserRoleConstants.MANAGER, UserRoleConstants.PARTNER_API,
                    UserRoleConstants.ADMIN)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            KanrenshaKigyouDtDto kanrenshaKigyouDtDto = getKanrenshaKigyouDtDtoService
                    .practice(capsuleDto.getMasterKigyouDtEntity());
            resultDto.setKanrenshaKigyouDtDto(kanrenshaKigyouDtDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (DataRetrievalFailureException exception) {
            // 最新が2件以上
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("データにエラーがあります。システム運営者にお問い合わせください");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
