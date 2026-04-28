package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_combine_org;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org.RegistCombineOrganizationService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ワークテーブル個人団体紐づけ編集Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-combine")
public class RegistCombineOrganizationController {

    /** ワークテーブル個人団体紐づけ編集Service */
    @Autowired
    private RegistCombineOrganizationService registCombineOrganizationService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集EntityDto
     * @return 編集結果
     */
    @PostMapping("/update")
    public ResponseEntity<UpdateWkTblCombineOrgResultDto> practice(
            final @RequestBody UpdateWkTblCombineOrgCapsuleDto capsuleDto) {

        
        System.out.print("======更新" + capsuleDto.getWkTblKanrenshaCombineOrgEntity().getYearArrayText());
        
        UpdateWkTblCombineOrgResultDto resultDto = new UpdateWkTblCombineOrgResultDto();
        try {
            WkTblKanrenshaCombineOrgEntity entity = registCombineOrganizationService.practice(capsuleDto);
            Integer newId = entity.getWkTblKanrenshaCombineOrgId();

            if (0 == newId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("更新できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage("正常に登録できました");
                resultDto.setWkTblKanrenshaCombineOrgEntity(entity);
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
