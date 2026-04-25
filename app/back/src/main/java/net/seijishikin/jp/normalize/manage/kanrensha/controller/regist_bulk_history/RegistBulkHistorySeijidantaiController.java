package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_history;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistorySeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistorySeijidantaiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history.RegistBulkHistorySeijidantaiService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体履歴編集Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-history")
public class RegistBulkHistorySeijidantaiController {

    /** ワークテーブルマスタ企業／団体履歴編集Service */
    @Autowired
    private RegistBulkHistorySeijidantaiService registBulkHistorySeijidantaiService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-seijidantai")
    public ResponseEntity<UpdateWkTblHistorySeijidantaiResultDto> practice(
            final @RequestBody UpdateWkTblHistorySeijidantaiCapsuleDto capsuleDto) {

        UpdateWkTblHistorySeijidantaiResultDto resultDto = new UpdateWkTblHistorySeijidantaiResultDto();
        try {
            WkTblKanrenshaSeijidantaiHistoryEntity entity = registBulkHistorySeijidantaiService.practice(capsuleDto);
            Integer newId = entity.getWkKanrenshaSeijidantaiHistoryId();

            if (0 == newId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("更新できませんでした");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDto);
            } else {
                resultDto.setMessage("正常に登録できました");
                resultDto.setWkTblKanrenshaSeijidantaiHistoryEntity(entity);
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
