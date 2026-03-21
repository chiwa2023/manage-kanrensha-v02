package net.seijishikin.jp.normalize.manage.kanrensha.controller.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.NaturalTextSearchPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SearchSnsServiceResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.sns.SearchSnsDataService;

/**
 * SNSサービス検索Service
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/sns-service")
public class SearchSnsDataController {

    /** snsサービス検索Service */
    @Autowired
    private SearchSnsDataService searchSnsDataService;

    /**
     * 処理をお来ぬ
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchSnsServiceResultDto> practice(
            @RequestBody final NaturalTextSearchPagingCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(searchSnsDataService.practice(capsuleDto));
    }

}
