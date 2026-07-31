package net.seijishikin.jp.normalize.manage.kanrensha.controller.sns;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SnsServiceOptionDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.sns.GetSnsOptionListService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * SNSサービス選択肢項目取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/sns-service")
public class GetSnsOptionListController {

    /** snsサービスselectbox項目Service */
    @Autowired
    private GetSnsOptionListService getSnsOptionListService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @return レスポンス
     */
    @PostMapping("/get-options")
    public ResponseEntity<List<SnsServiceOptionDto>> practice() {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(getSnsOptionListService.practice());

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
