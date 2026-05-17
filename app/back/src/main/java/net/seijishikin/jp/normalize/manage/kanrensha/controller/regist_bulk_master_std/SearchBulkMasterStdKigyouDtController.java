package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_std;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.SearchWkTblStdKigyouDtPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std.SearchBulkMasterStdKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体標準検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-master-std")
public class SearchBulkMasterStdKigyouDtController {

    /** ワークテーブルマスタ企業／団体標準検索Service */
    @Autowired
    private SearchBulkMasterStdKigyouDtService searchBulkMasterStdKigyouDtService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/search-kigyou-dt")
    public ResponseEntity<SearchWkTblStdKigyouDtPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {

        return ResponseEntity.ok(searchBulkMasterStdKigyouDtService.practice(capsuleDto));
    }
}
