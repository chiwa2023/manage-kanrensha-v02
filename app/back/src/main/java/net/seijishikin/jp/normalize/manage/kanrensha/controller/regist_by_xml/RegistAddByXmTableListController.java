package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_by_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlTableListCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml.RegistAddByXmlService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * XMLからマスタ最小登録ワークテーブル編集リストController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-by-xml")
public class RegistAddByXmTableListController {

    /** XMLからマスタ最小登録ワークテーブル編集Service */
    @Autowired
    private RegistAddByXmlService registAddByXmlService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集Dto
     * @return 編集結果
     */
    @PostMapping("/update-list")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody UpdateWkTblAddByXmlTableListCapsuleDto capsuleDto) {

        LeastUserDto userDto = capsuleDto.getUserDto();

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        for (WkTblMasterAllByXmlEntity entityEdit : capsuleDto.getListWkTblByXml()) {
            if (entityEdit.getIsAffected()) { // 編集する、と宣言していないデータは除外
                WkTblMasterAllByXmlEntity entityAns = registAddByXmlService.practice(entityEdit, userDto);
                if (0 == entityAns.getWkTblMasterAllByXmlId()) {
                    resultDto.setIsFailure(true);
                    resultDto.setMessage("途中で処理が中断されました");
                    return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
                }
            }
        }

        resultDto.setMessage("正常に登録できました");
        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
