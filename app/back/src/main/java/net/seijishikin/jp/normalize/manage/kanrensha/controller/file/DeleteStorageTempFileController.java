package net.seijishikin.jp.normalize.manage.kanrensha.controller.file;

import java.time.LocalDate;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.DeleteStorageTempFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * ストレージ一時ファイル削除Controller
 * 将来front側から動かす可能性も含めて、タイマー処理とも実装形態を合わせる意味でもControllerとしているが
 * pathも設定していないし、返り値はResponseEntityでないので修正は必要(不必要にパスを設定してアクセスできるようにしない)
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/file")
public class DeleteStorageTempFileController {

    /** ストレージ一時ファイル削除Service */
    @Autowired
    private DeleteStorageTempFileService deleteStorageTempFileService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;


    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @return 起動結果
     */
    @PostMapping("/delete-temp-file")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final @RequestBody FrameworkCapsuleDto capsuleDto) {

        LocalDate now = LocalDate.now();

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());
            
            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。(タスク登録はありません)");
            
            deleteStorageTempFileService.practice(now.getMonthValue());
            
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
