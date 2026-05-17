package net.seijishikin.jp.normalize.manage.kanrensha.controller.yotei;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.EditTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.UpdateTimerYoteiService;

/**
 * 予定実行更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/timer-yotei")
public class UpdateTimerYoteiController {

    /** StackTrace保存Service */
    @Autowired
    private UpdateTimerYoteiService updateTimerYoteiService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 編集条件Dto
     * @return レスポンス
     */
    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody EditTimerYoteiCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {

            if (0 == updateTimerYoteiService.practice(capsuleDto)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("正常に登録できませんでした。");
            } else {
                resultDto.setMessage("正常に登録できました。");
            }

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
