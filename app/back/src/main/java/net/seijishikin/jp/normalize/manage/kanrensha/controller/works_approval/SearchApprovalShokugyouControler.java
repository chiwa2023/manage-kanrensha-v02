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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchApprovalShokugyouResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval.SearchApprovalShokugyouService;

/**
 * 作業承認職業検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/works-approval")
public class SearchApprovalShokugyouControler {

    /** 関連者住所承認作業検索Service */
    @Autowired
    private SearchApprovalShokugyouService searchApprovalShokugyouService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService stackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search-shokugyou")
    public ResponseEntity<SearchApprovalShokugyouResultDto> practice(
            final @RequestBody SearchWorksApprovalCapsuleDto capsuleDto) {
        
        try {
            return ResponseEntity.status(HttpStatus.OK).body(searchApprovalShokugyouService.practice(capsuleDto));
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            stackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
