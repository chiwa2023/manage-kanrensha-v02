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

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.year_option.YearOptionResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearCheckExistKanrenshaCombineService;

/**
 * 関連者紐づけ年存在確認Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/kanrensha-combine")
public class CheckExistKanrenshaCombineController {

    /** 関連者紐づけ年存在確認Service */
    @Autowired
    private SwitchYearCheckExistKanrenshaCombineService switchYearCheckExistKanrenshaCombineService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者紐づけ年存在確認条件Dto
     * @return レスポンス
     */
    @PostMapping("/check-exist")
    public ResponseEntity<YearOptionResultDto> practice(
            final @RequestBody SearchKanrenshaCombineCapsuleDto capsuleDto) {

        YearOptionResultDto resultDto = new YearOptionResultDto();
        try {

            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            resultDto = switchYearCheckExistKanrenshaCombineService.practice(capsuleDto);

            if (resultDto.getListEntity().isEmpty()) {
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
