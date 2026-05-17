package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PromoteUserAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.PromoteUserAdminService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * SE権限昇格Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-role")
public class PromoteUserAdminController {

    /** SE権限昇格Service */
    @Autowired
    private PromoteUserAdminService promoteUserAdminService;

    /** SE権限昇格Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/promote")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final PromoteUserAdminCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            resultDto = promoteUserAdminService.practice(capsuleDto, LocalDateTime.now());
            // 基本的に例外はだいたいキャッチしているので失敗＝例外
            if(resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
            }else {
                resultDto.setMessage("登録処理が正常に終了しました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容

            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }
}
