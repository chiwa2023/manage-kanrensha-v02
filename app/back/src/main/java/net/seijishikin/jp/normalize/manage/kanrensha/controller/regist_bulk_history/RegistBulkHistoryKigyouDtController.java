package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_history;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history.RegistBulkHistoryKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体履歴編集Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-history")
public class RegistBulkHistoryKigyouDtController {

    /** ワークテーブルマスタ企業／団体履歴編集Service */
    @Autowired
    private RegistBulkHistoryKigyouDtService registBulkHistoryKigyouDtService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-kigyou-dt")
    public ResponseEntity<UpdateWkTblHistoryKigyouDtResultDto> practice(
            final @RequestBody UpdateWkTblHistoryKigyouDtCapsuleDto capsuleDto) {

        UpdateWkTblHistoryKigyouDtResultDto resultDto = new UpdateWkTblHistoryKigyouDtResultDto();
        try {
            WkTblKanrenshaKigyouDtHistoryEntity entity = registBulkHistoryKigyouDtService.practice(capsuleDto);
            Integer newId = entity.getWkKanrenshaKigyouDtHistoryId();

            if (0 == newId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("更新できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage("正常に登録できました");
                resultDto.setWkTblKanrenshaKigyouDtHistoryEntity(entity);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
