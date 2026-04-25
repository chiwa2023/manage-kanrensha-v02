package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaManagerDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者運営者取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha")
public class GetRiyoushaManagerDtoController {

    /** 利用者運営者取得Service */
    @Autowired
    private GetRiyoushaManagerDtoService getRiyoushaManagerDtoService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param masterEntity マスタEntity
     * @return レスポンス
     */
    @PostMapping("/get-manager")
    public ResponseEntity<RiyoushaManagerDto> practice(@RequestBody final RiyoushaManagerMasterEntity masterEntity) {

        RiyoushaManagerDto managerDto;
        try {
            managerDto = getRiyoushaManagerDtoService.practice(masterEntity);
            final Integer zero = 0;
            if (zero.equals(managerDto.getRiyoushaManagerMasterId())) {
                managerDto.setIsFailure(true);
                managerDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(managerDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(managerDto);
            }
        } catch (Exception exception) { // NOPMD AvoidCatchGenericException
            // 例外を保存してエラー発生を伝達
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            managerDto = new RiyoushaManagerDto();
            managerDto.setIsFailure(true);
            managerDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(managerDto);
        }
    }
}
