package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.DeleteRiyoushaMasterCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.DeleteRiyoushaManagerService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.DeleteRiyoushaPartnerApiService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者削除処理Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha")
public class DeleteRiyoushaPersonController {

    /** 利用者運営者削除Service */
    @Autowired
    private DeleteRiyoushaManagerService deleteRiyoushaManagerService;

    /** 利用者APIパートナー削除Service */
    @Autowired
    private DeleteRiyoushaPartnerApiService deleteRiyoushaPartnerApiService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 利用者削除条件Dto
     * @return レスポンス
     */
    @PostMapping("/delete")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final DeleteRiyoushaMasterCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer updateSize;
            if (UserRoleConstants.MANAGER.equals(capsuleDto.getRiyoushaRole())) {
                // 運営者削除処理
                updateSize = deleteRiyoushaManagerService.practice(capsuleDto.getRiyoushaCode(),
                        capsuleDto.getUserDto());
            } else {
                // 運営者削除処理
                updateSize = deleteRiyoushaPartnerApiService.practice(capsuleDto.getRiyoushaCode(),
                        capsuleDto.getUserDto());
            }
            if (0 == updateSize) {
                resultDto.setIsFailure(true);
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_RECORD);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
