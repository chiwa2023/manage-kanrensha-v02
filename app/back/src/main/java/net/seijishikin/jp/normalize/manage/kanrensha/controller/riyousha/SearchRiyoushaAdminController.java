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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAdminResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SearchRiyoushaAdminService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者SE権限者検索Controller
 */
@RestController
@RequestMapping("/riyousha")
public class SearchRiyoushaAdminController {

    /** 利用者運営者検索Service */
    @Autowired
    private SearchRiyoushaAdminService searchRiyoushaAdminService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto リクエストボディDto
     * @return レスポンス
     */
    @PostMapping("/search-admin")
    public ResponseEntity<SearchRiyoushaAdminResultDto> practice(
            @RequestBody final SearchRiyoushaAdminCapsuleDto capsuleDto) {

        try {
            SearchRiyoushaAdminResultDto resultDto = searchRiyoushaAdminService.practice(capsuleDto);

            final Integer zero = 0;
            if (zero.equals(resultDto.getAllCount())) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
                return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
            } else {
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD AvoidCatchGenericException
            // 例外を保存してエラー発生を伝達
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            SearchRiyoushaAdminResultDto resultDto = new SearchRiyoushaAdminResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }

    }
}
