package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.SearchWkTblMinPersonPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min.SearchBulkMasterMinPersonService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体最小検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-master-min")
public class SearchBulkMasterMinPersonController {

    /** ワークテーブルマスタ企業／団体最小検索Service */
    @Autowired
    private SearchBulkMasterMinPersonService searchBulkMasterMinPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/search-person")
    public ResponseEntity<SearchWkTblMinPersonPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {

        return ResponseEntity.ok(searchBulkMasterMinPersonService.practice(capsuleDto));
    }

}
