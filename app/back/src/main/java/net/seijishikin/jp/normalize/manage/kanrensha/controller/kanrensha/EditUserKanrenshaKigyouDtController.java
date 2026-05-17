package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.EditKanrenshaKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者企業・団体追加Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-kanrensha")
public class EditUserKanrenshaKigyouDtController {

    /** 関連者企業団体編集Service */
    @Autowired
    private EditKanrenshaKigyouDtService editKanrenshaKigyouDtService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/edit-kigyou-dt")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveKanrenshaKigyouDtCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer newId = editKanrenshaKigyouDtService.practice(capsuleDto);
            if (0 == newId) {
                resultDto.setMessage("前データと元データに変更がありませんでした。");
                resultDto.setIsFailure(true);
            } else {
                resultDto.setMessage("登録できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (EmptyResultDataAccessException exception) {
            resultDto.setMessage("必要なデータが呼び出せませんでした。システム運営者に連絡してください。("
                    + capsuleDto.getKanrenshaKigyouDtDto().getKigyouDtKanrenshaCode() + ")");
            resultDto.setIsFailure(true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);

        } catch (ConcurrencyFailureException exception) {
            resultDto.setMessage("他のユーザが修正したようです。大変お手数をおかけしますが変更された後のデータを確認して修正作業をしなおしてください("
                    + capsuleDto.getKanrenshaKigyouDtDto().getKigyouDtKanrenshaCode() + ")");
            resultDto.setIsFailure(true);
        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setMessage("例外が発生しました。システム運営者に連絡してください。");
            resultDto.setIsFailure(true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);

    }

}
