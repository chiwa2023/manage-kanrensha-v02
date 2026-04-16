package net.seijishikin.jp.normalize.manage.kanrensha.controller.yotei;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.EditTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.DeleteTimerYoteiService;

/**
 * 予定実行削除Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/timer-yotei")
public class DeleteTimerYoteiController {

    /** 予定実行検索Service */
    @Autowired
    private DeleteTimerYoteiService deleteTimerYoteiService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 削除条件Dto
     * @return レスポンス
     */
    @PostMapping("/delete")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody EditTimerYoteiCapsuleDto capsuleDto) {

        try {
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            if (0 == deleteTimerYoteiService.practice(capsuleDto)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("正常に登録できませんでした。");
            } else {
                resultDto.setMessage("正常に登録できました。");
            }

            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();
        }
    }

}
