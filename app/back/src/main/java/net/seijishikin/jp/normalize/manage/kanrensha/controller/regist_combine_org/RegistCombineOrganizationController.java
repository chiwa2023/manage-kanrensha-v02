package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_combine_org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org.RegistCombineOrganizationService;
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

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集EntityDto
     * @return 編集結果
     */
    @PostMapping("/update")
    public ResponseEntity<UpdateWkTblCombineOrgResultDto> practice(
            final @RequestBody UpdateWkTblCombineOrgCapsuleDto capsuleDto) {

        WkTblKanrenshaCombineOrgEntity entity = registCombineOrganizationService.practice(capsuleDto);
        Integer newId = entity.getWkTblKanrenshaCombineOrgId();

        UpdateWkTblCombineOrgResultDto resultDto = new UpdateWkTblCombineOrgResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.ACCEPTED.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblKanrenshaCombineOrgEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
