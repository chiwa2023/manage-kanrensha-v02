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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.GetKanrenshaCombineLeastCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.GetKanrenshaCombineLeastResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.combine.GetKigyouDtLeastDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者紐づけ用企業団体最小取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/kanrensha-combine")
public class GetKigyouDtLeastDtoController {

    /** 関連者企業団体最小取得Service */
    @Autowired
    private GetKigyouDtLeastDtoService getKigyouDtLeastDtoService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto ページング含む検索条件格納Dto
     * @return 検索結果
     */
    @PostMapping("/get-kigyou-dt")
    public ResponseEntity<GetKanrenshaCombineLeastResultDto> practice(
            final @RequestBody GetKanrenshaCombineLeastCapsuleDto capsuleDto) {

        GetKanrenshaCombineLeastResultDto resultDto = new GetKanrenshaCombineLeastResultDto();
        try {

            // 本人は許可
            if (!validateAuthoraizeUserDetailLogic.practiceKanrensha(capsuleDto.getUserDto(),
                    capsuleDto.getKanrenshaCode(), UserRoleConstants.KANRENSHA_KIGYOU_DT, UserRoleConstants.MANAGER,
                    UserRoleConstants.PARTNER_API, UserRoleConstants.ADMIN)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            resultDto = getKigyouDtLeastDtoService.practice(capsuleDto.getKanrenshaCode());

            final String BLANK = "";
            if (BLANK.equals(resultDto.getKanrenshaCode())) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("該当するデータが取得できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {

                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
