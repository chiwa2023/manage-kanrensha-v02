package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history.RegistBulkHistoryKigyouDtService;
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

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-kigyouDt")
    public ResponseEntity<UpdateWkTblHistoryKigyouDtResultDto> practice(
            final @RequestBody UpdateWkTblHistoryKigyouDtCapsuleDto capsuleDto) {

        WkTblKanrenshaKigyouDtHistoryEntity entity = registBulkHistoryKigyouDtService.practice(capsuleDto);
        Integer newId = entity.getWkKanrenshaKigyouDtHistoryId();

        UpdateWkTblHistoryKigyouDtResultDto resultDto = new UpdateWkTblHistoryKigyouDtResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NOT_FOUND.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblKanrenshaKigyouDtHistoryEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
