package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_min;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min.RegistBulkMasterMinKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
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
    public ResponseEntity<UpdateWkTblMinKigyouDtResultDto> practice(
            final @RequestBody UpdateWkTblMinKigyouDtCapsuleDto capsuleDto) {

        UpdateWkTblMinKigyouDtResultDto resultDto = new UpdateWkTblMinKigyouDtResultDto();
        try {
            WkTblKanrenshaKigyouDtAddMinEntity entity = registBulkMasterMinKigyouDtService.practice(capsuleDto);
            Integer newId = entity.getWkTblKanrenshaKigyouDtAddMinId();

            if (0 == newId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("更新できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage("正常に登録できました");
                resultDto.setWkTblKanrenshaKigyouDtAddMinEntity(entity);
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
