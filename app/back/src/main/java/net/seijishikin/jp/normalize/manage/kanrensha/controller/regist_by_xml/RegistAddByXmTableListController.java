package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_by_xml;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlTableListCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml.RegistAddByXmlService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
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

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集Dto
     * @return 編集結果
     */
    @PostMapping("/update-list")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody UpdateWkTblAddByXmlTableListCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            LeastUserDto userDto = capsuleDto.getUserDto();

            for (WkTblMasterAllByXmlEntity entityEdit : capsuleDto.getListWkTblByXml()) {
                if (entityEdit.getIsAffected()) { // 編集する、と宣言していないデータは除外
                    WkTblMasterAllByXmlEntity entityAns = registAddByXmlService.practice(entityEdit, userDto);
                    if (0 == entityAns.getWkTblMasterAllByXmlId()) {
                        resultDto.setIsFailure(true);
                        resultDto.setMessage("途中で処理が中断されました");
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
                    }
                }
            }

            resultDto.setMessage("正常に登録できました");
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

}
