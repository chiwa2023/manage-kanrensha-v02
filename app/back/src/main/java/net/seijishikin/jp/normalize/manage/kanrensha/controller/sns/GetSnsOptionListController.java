package net.seijishikin.jp.normalize.manage.kanrensha.controller.sns;

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

/**
 * SNSサービス選択肢項目取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/sns-service")
public class GetSnsOptionListController {

    /** snsサービスselectbox項目Service */
    @Autowired
    private GetSnsOptionListService getSnsOptionListService;

    /**
     * 処理を行う
     * 
     * @return レスポンス
     */
    @PostMapping("/get-options")
    public ResponseEntity<List<SnsServiceOptionDto>> practice() {

        return ResponseEntity.status(HttpStatus.OK).body(getSnsOptionListService.practice());
    }
}
