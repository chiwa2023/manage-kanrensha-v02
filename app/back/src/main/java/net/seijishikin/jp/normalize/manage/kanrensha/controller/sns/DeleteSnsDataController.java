package net.seijishikin.jp.normalize.manage.kanrensha.controller.sns;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.EditSnsServiceCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.sns.DeleteSnsDataService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * SNSサービス削除Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/sns-service")
public class DeleteSnsDataController {

    /** snsサービス削除Service */
    @Autowired
    private DeleteSnsDataService deleteSnsDataService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 削除条件Dto
     * @return レスポンス
     */
    @PostMapping("/delete")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final EditSnsServiceCapsuleDto capsuleDto) {

        try {
            Integer deleteId = deleteSnsDataService.practice(capsuleDto);

            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            if (0 == deleteId) {
                return ResponseEntity.status(HttpResponseStatus.ACCEPTED.code()).body(resultDto);

            } else {
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();

        }

    }

}
