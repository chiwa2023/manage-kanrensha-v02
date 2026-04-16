package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaOrgCombinOptionsService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者紐づけ組織選択肢取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class GetRiyoushaOrgCombinOptionsController {

    /** 利用者紐づけ組織選択肢取得Service */
    @Autowired
    private GetRiyoushaOrgCombinOptionsService getRiyoushaOrgCombinOptionsService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/get-org-options")
    public ResponseEntity<List<SelectOptionIntegerDto>> practice(@RequestBody final FrameworkCapsuleDto capsuleDto) {
        try {
            // 必ずサイズ1以上のリストが返ってくる
            return ResponseEntity.status(HttpResponseStatus.OK.code())
                    .body(getRiyoushaOrgCombinOptionsService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();
        }
    }

}
