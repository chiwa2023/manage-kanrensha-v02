package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.AcceptUserAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.AcceptUserAdminService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * SE権限追加推薦承諾Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-role")
public class AcceptUserAdminController {

    /** SE権限追加推薦承諾Service */
    @Autowired
    private AcceptUserAdminService acceptUserAdminService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService stackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/accept")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody AcceptUserAdminCapsuleDto capsuleDto) {
        LocalDateTime now = LocalDateTime.now();
        try {
            Integer savedCount = acceptUserAdminService.practice(capsuleDto, now);

            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            if (0 == savedCount) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("保存できませんでした");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
            } else {
                resultDto.setMessage("登録できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            stackTraceService.practice(exception, now.getYear(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
