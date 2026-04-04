package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.SearchWkTblHistoryPersonPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history.SearchBulkHistoryPersonService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体履歴検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-history")
public class SearchBulkHistoryPersonController {


    /** ワークテーブルマスタ企業／団体履歴検索Service */
    @Autowired
    private SearchBulkHistoryPersonService searchBulkHistoryPersonService;


    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/search-person")
    public ResponseEntity<SearchWkTblHistoryPersonPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {
        
        return ResponseEntity.ok(searchBulkHistoryPersonService.practice(capsuleDto));
    }

}
