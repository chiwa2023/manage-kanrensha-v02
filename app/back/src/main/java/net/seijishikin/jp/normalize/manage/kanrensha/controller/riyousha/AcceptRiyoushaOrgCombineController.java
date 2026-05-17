package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.AcceptRiyoushaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.AcceptRiyoushaOrgCombineService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者組織招待承諾登録Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class AcceptRiyoushaOrgCombineController {

    /** 利用者組織招待承諾登録Service */
    @Autowired
    private AcceptRiyoushaOrgCombineService acceptRiyoushaOrgCombineService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 仮情報取得Dto
     * @return レスポンス
     */
    @PostMapping("/accept-combine")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final AcceptRiyoushaCombineCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            LocalDateTime endDatetime = LocalDateTime.now();
            resultDto = acceptRiyoushaOrgCombineService.practice(capsuleDto, endDatetime);
            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // 例外を保存してエラー発生を伝達
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
