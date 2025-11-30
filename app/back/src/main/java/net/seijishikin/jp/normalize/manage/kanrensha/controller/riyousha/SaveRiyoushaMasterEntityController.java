package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SaveRiyoushaManagerEntityService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者運営者保存Controller
 */
@RestController
@RequestMapping("/riyousha")
public class SaveRiyoushaMasterEntityController {

    /** 利用者運営者保存Service */
    @Autowired
    private SaveRiyoushaManagerEntityService saveRiyoushaManagerEntityService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto リクエストボディDto
     * @return レスポンス
     */
    @PostMapping("/save-manager")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveRiyoushaManagerCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {

            final Integer zero = 0;
            if (zero.equals(saveRiyoushaManagerEntityService.practice(capsuleDto))) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_RECORD);
                return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);

            } else {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD AvoidCatchGenericException
            // 例外を保存してエラー発生を伝達
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }
    }
}
