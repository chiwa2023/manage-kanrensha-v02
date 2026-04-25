package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaOrgByCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaOrgDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaOrgDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者組織を組織コードから取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class GetRiyoushaOrgDtoByCodeController {

    /** 利用者組織取得Service */
    @Autowired
    private GetRiyoushaOrgDtoService getRiyoushaOrgDtoService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/get-by-code")
    public ResponseEntity<RiyoushaOrgDto> practice(@RequestBody final GetRiyoushaOrgByCodeCapsuleDto capsuleDto) {

        RiyoushaOrgDto resultDto;
        try {
            resultDto = getRiyoushaOrgDtoService.practiceByCode(capsuleDto);
            final Integer zero = 0;
            if (zero.equals(resultDto.getRiyoushaOrgMasterId())) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto = new RiyoushaOrgDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
