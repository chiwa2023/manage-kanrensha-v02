package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_combine_org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RetryWktblBatchCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org.RetryBatchCombineOrgService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブル編集後再試行XML最小マスタController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-combine")
public class RetryBatchCombineOrgController {

    /** ワークテーブル編集後再試行XML最小マスタServce */
    @Autowired
    private RetryBatchCombineOrgService retryBatchCombineKigyouDtService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集後再試行条件Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/retry")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RetryWktblBatchCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        retryBatchCombineKigyouDtService.practice(capsuleDto.getUserDto());
        
        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
