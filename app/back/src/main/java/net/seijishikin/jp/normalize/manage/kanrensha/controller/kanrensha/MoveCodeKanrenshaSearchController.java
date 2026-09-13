package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeSearchCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeSearchResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.MoveCodeKanrenshaSearchService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者コード移動申請検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/kanrensha-code-move")
public class MoveCodeKanrenshaSearchController {

    /** 関連者コード移動申請検索Service */
    @Autowired
    private MoveCodeKanrenshaSearchService moveCodeKanrenshaSearchService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<MoveKanrenshaCodeSearchResultDto> practice(
            final @RequestBody MoveKanrenshaCodeSearchCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(moveCodeKanrenshaSearchService.practice(capsuleDto));
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
