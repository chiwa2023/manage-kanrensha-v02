package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaOrgResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SearchRiyoushaOrgService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者組織検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class SearchRiyoushaOrgController {

    /** 利用者組織検索Service */
    @Autowired
    private SearchRiyoushaOrgService searchRiyoushaOrgService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchRiyoushaOrgResultDto> practice(
            @RequestBody final SearchRiyoushaOrgCapsuleDto capsuleDto) {

        SearchRiyoushaOrgResultDto resultDto;
        try {
            resultDto = searchRiyoushaOrgService.practice(capsuleDto);

            if (resultDto.getAllCount() == 0) {
                resultDto.setMessage("検索結果が取得できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);

            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto = new SearchRiyoushaOrgResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);

        }

    }

}
