package net.seijishikin.jp.normalize.manage.kanrensha.controller.works_approval;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval.SearchApprovalAddressKigyouDtService;

/**
 * 未承認変更データ企業団体検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/works-approval")
public class SearchApprovalAddressKigyouDtController {

    /** 企業団体住所未承認検索Service */
    @Autowired
    private SearchApprovalAddressKigyouDtService searchApprovalAddressKigyouDtService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService stackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search-kigyou-dt")
    public ResponseEntity<SearchWorksApprovalResultDto> practice(
            final @RequestBody SearchWorksApprovalCapsuleDto capsuleDto) {
        try {
            SearchWorksApprovalResultDto resultDto = new SearchWorksApprovalResultDto();
            resultDto.setResultDtoAddress(searchApprovalAddressKigyouDtService.practice(capsuleDto));

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);
        } catch (Exception exception) {
            stackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
