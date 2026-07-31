package net.seijishikin.jp.normalize.manage.kanrensha.controller.sns;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.NaturalTextSearchPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SearchSnsServiceResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.sns.SearchSnsDataService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * SNSサービス検索Service
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/sns-service")
public class SearchSnsDataController {

    /** snsサービス検索Service */
    @Autowired
    private SearchSnsDataService searchSnsDataService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理をお来ぬ
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchSnsServiceResultDto> practice(
            @RequestBody final NaturalTextSearchPagingCapsuleDto capsuleDto) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(searchSnsDataService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
