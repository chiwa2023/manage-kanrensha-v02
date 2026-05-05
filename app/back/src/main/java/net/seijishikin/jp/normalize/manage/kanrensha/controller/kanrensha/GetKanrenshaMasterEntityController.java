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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaMasterCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaMasterResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaMasterEntityService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者マスタ取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-kanrensha")
public class GetKanrenshaMasterEntityController {

    /** 関連者マスタ取得Service */
    @Autowired
    private GetKanrenshaMasterEntityService getKanrenshaMasterEntityService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ページング含む検索条件格納Dto
     * @return 検索結果
     */
    @PostMapping("/get-myself")
    public ResponseEntity<GetKanrenshaMasterResultDto> practice(
            final @RequestBody GetKanrenshaMasterCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(getKanrenshaMasterEntityService.practice(capsuleDto));
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
