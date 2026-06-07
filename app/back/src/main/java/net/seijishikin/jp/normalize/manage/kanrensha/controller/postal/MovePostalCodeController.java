package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.MovePostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.WrapMovePostalcodeRsdtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 郵便番号移動Controller(郵便番号、住所ワンセット)
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-code")
public class MovePostalCodeController {

    /** 郵便番号移動Service */
    @Autowired
    private WrapMovePostalcodeRsdtService wrapMovePostalcodeRsdtService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 住居編集対象Dto
     * @return レスポンス
     */
    @PostMapping("/move")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody MovePostalCodeCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            int deleteCount = wrapMovePostalcodeRsdtService.practice(capsuleDto);

            if (0 == deleteCount) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_RECORD);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
