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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaAdminDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaAdminMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaAdminDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者SE権限取得Controller
 */
@RestController
@RequestMapping("/riyousha")
public class GetRiyoushaAdminDtoController {

    /** 利用者運営者取得Service */
    @Autowired
    private GetRiyoushaAdminDtoService getRiyoushaAdminDtoService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param masterEntity マスタEntity
     * @return レスポンス
     */
    @PostMapping("/get-admin")
    public ResponseEntity<RiyoushaAdminDto> practice(@RequestBody final RiyoushaAdminMasterEntity masterEntity) {
        try {
            RiyoushaAdminDto managerDto = getRiyoushaAdminDtoService.practice(masterEntity);
            final Integer zero = 0;
            if (zero.equals(managerDto.getRiyoushaAdminMasterId())) {
                managerDto.setIsFailure(true);
                managerDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
                return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(managerDto);
            } else {
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(managerDto);
            }
        } catch (Exception exception) { // NOPMD AvoidCatchGenericException
            // 例外を保存してエラー発生を伝達
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            RiyoushaAdminDto managerDto = new RiyoushaAdminDto();
            managerDto.setIsFailure(true);
            managerDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(managerDto);
        }
    }

}
