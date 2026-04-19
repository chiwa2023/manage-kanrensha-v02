package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min.RegistBulkMasterMinKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体最小編集Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-master-min")
public class RegistBulkMasterMinKigyouDtController {

    /** ワークテーブルマスタ企業／団体最小編集Service */
    @Autowired
    private RegistBulkMasterMinKigyouDtService registBulkMasterMinKigyouDtService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-kigyou-dt")
    public ResponseEntity<UpdateWkTblMinKigyouDtResultDto> practice(
            final @RequestBody UpdateWkTblMinKigyouDtCapsuleDto capsuleDto) {

        WkTblKanrenshaKigyouDtAddMinEntity entity = registBulkMasterMinKigyouDtService.practice(capsuleDto);
        Integer newId = entity.getWkTblKanrenshaKigyouDtAddMinId();

        UpdateWkTblMinKigyouDtResultDto resultDto = new UpdateWkTblMinKigyouDtResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NOT_FOUND.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblKanrenshaKigyouDtAddMinEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
