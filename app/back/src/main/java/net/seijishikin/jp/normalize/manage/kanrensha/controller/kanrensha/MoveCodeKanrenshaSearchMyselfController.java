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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeSearchResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.MoveCodeKanrenshaSearchMyselfService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者コード移動申請自分自身検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/kanrensha-code-move")
public class MoveCodeKanrenshaSearchMyselfController {

    /** 関連者コード移動申請自分自身検索Service */
    @Autowired
    private MoveCodeKanrenshaSearchMyselfService moveCodeKanrenshaSearchMyselfService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/search-myself")
    public ResponseEntity<MoveKanrenshaCodeSearchResultDto> practice(
            @RequestBody final MoveKanrenshaCodeHistoryCapsuleDto capsuleDto) {

        MoveKanrenshaCodeSearchResultDto resultDto = new MoveKanrenshaCodeSearchResultDto();
        try {
            resultDto.setListEntity(
                    moveCodeKanrenshaSearchMyselfService.practice(capsuleDto.getUserDto().getKanrenshaCode()));

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
    }
}
