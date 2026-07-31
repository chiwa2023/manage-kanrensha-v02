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

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaMasterCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaMasterResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaMasterEntityService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者マスタ取得Controllrt
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha")
public class GetRiyoushaMasterEntityController {

    /** 利用者マスタ取得Service */
    @Autowired
    private GetRiyoushaMasterEntityService getRiyoushaMasterEntityService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 利用者取得条件
     * @return レスポンス
     */
    @PostMapping("/get-myself")
    public ResponseEntity<GetRiyoushaMasterResultDto> practice(
            @RequestBody final GetRiyoushaMasterCapsuleDto capsuleDto) {

        GetRiyoushaMasterResultDto resultDto = new GetRiyoushaMasterResultDto();
        try {

            // 利用者全体
            if (!validateAuthoraizeUserDetailLogic.practiceRiyousha(capsuleDto.getUserDto(),
                    capsuleDto.getRiyoushaCode(), capsuleDto.getRiyoushaRole())) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            return ResponseEntity.status(HttpStatus.OK).body(getRiyoushaMasterEntityService.practice(capsuleDto));
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
