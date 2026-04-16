package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_std;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdSeijidantaiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std.RegistBulkMasterStdSeijidantaiService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体標準編集Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-master-std")
public class RegistBulkMasterStdSeijidantaiController {

    /** ワークテーブルマスタ企業／団体標準編集Service */
    @Autowired
    private RegistBulkMasterStdSeijidantaiService registBulkMasterStdSeijidantaiService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-poli-org")
    public ResponseEntity<UpdateWkTblStdSeijidantaiResultDto> practice(
            final @RequestBody UpdateWkTblStdSeijidantaiCapsuleDto capsuleDto) {

        WkTblKanrenshaSeijidantaiMasterEntity entity = registBulkMasterStdSeijidantaiService.practice(capsuleDto);
        Integer newId = entity.getWkTblKanrenshaSeijidantaiMasterId();

        UpdateWkTblStdSeijidantaiResultDto resultDto = new UpdateWkTblStdSeijidantaiResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NOT_FOUND.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblKanrenshaSeijidantaiMasterEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
