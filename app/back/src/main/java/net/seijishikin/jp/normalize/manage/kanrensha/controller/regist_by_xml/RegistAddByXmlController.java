package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_by_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml.RegistAddByXmlService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * XMLからマスタ最小登録ワークテーブル編集Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-by-xml")
public class RegistAddByXmlController {

    /** XMLからマスタ最小登録ワークテーブル編集Service */
    @Autowired
    private RegistAddByXmlService registAddByXmlService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集Dto
     * @return 編集結果
     */
    @PostMapping("/update")
    public ResponseEntity<UpdateWkTblAddByXmlResultDto> practice(
            final @RequestBody UpdateWkTblAddByXmlCapsuleDto capsuleDto) {

        WkTblMasterAllByXmlEntity entity = registAddByXmlService.practice(capsuleDto.getWkTblMasterAllByXmlEntity(),
                capsuleDto.getUserDto());
        Integer newId = entity.getWkTblMasterAllByXmlId();

        UpdateWkTblAddByXmlResultDto resultDto = new UpdateWkTblAddByXmlResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.ACCEPTED.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblMasterAllByXmlEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
