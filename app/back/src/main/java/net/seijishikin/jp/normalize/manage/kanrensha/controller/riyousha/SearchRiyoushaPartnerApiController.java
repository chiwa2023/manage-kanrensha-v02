package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SearchRiyoushaPartnerAppiService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者API接続者検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha")
public class SearchRiyoushaPartnerApiController {

    /** 利用者運営者検索Service */
    @Autowired
    private SearchRiyoushaPartnerAppiService searchRiyoushaPartnerAppiService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto リクエストボディDto
     * @return レスポンス
     */
    @PostMapping("/search-partner-api")
    public ResponseEntity<SearchRiyoushaPartnerApiResultDto> practice(
            @RequestBody final SearchRiyoushaPartnerApiCapsuleDto capsuleDto) {

        SearchRiyoushaPartnerApiResultDto resultDto;
        try {
            resultDto = searchRiyoushaPartnerAppiService.practice(capsuleDto);

            final Integer zero = 0;
            if (zero.equals(resultDto.getAllCount())) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD AvoidCatchGenericException
            // 例外を保存してエラー発生を伝達
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto = new SearchRiyoushaPartnerApiResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

}
