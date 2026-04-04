package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_std;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPersonResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std.RegistBulkMasterStdPersonService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブルマスタ企業／団体標準編集Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-master-std")
public class RegistBulkMasterStdPersonController {

    /** ワークテーブルマスタ企業／団体標準編集Service */
    @Autowired
    private RegistBulkMasterStdPersonService registBulkMasterStdPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-person")
    public ResponseEntity<UpdateWkTblStdPersonResultDto> practice(
            final @RequestBody UpdateWkTblStdPersonCapsuleDto capsuleDto) {

        WkTblKanrenshaPersonMasterEntity entity = registBulkMasterStdPersonService.practice(capsuleDto);
        Integer newId = entity.getWkTblKanrenshaPersonMasterId();

        UpdateWkTblStdPersonResultDto resultDto = new UpdateWkTblStdPersonResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NOT_FOUND.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblKanrenshaPersonMasterEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
