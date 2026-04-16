package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaOrgDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaOrgDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者組織取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class GetRiyoushaOrgDtoController {

    /** 利用者組織取得Service */
    @Autowired
    private GetRiyoushaOrgDtoService getRiyoushaOrgDtoService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param masterEntity 利用者組織Entity
     * @return レスポンス
     */
    @PostMapping("/get")
    public ResponseEntity<RiyoushaOrgDto> practice(@RequestBody final RiyoushaOrgMasterEntity masterEntity) {

        try {
            RiyoushaOrgDto dto = getRiyoushaOrgDtoService.practice(masterEntity);
            final Integer zero = 0;
            if (zero.equals(dto.getRiyoushaOrgMasterId())) {
                dto.setIsFailure(true);
                dto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
                return ResponseEntity.status(HttpResponseStatus.ACCEPTED.code()).body(dto);
            } else {
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(dto);
            }
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();
        }
    }

}
