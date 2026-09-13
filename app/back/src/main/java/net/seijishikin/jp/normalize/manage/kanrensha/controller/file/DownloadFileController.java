package net.seijishikin.jp.normalize.manage.kanrensha.controller.file;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.DownloadFileCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetStorageFielByIdService;

/**
 * ファイルダウンロードController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/file")
public class DownloadFileController {

    /** 年切替Idダウンロードファイル取得Service */
    @Autowired
    private SwitchYearGetStorageFielByIdService switchYearGetStorageFielByIdService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto アップロードファイル内容Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/download")
    public ResponseEntity<OneFileBlobResultDto> practice(final @RequestBody DownloadFileCapsuleDto capsuleDto) {

        OneFileBlobResultDto resultDto = new OneFileBlobResultDto();
        Integer year = capsuleDto.getStorageYear();
        try {
            // ユーザチェック
            // 利用者は許可
            if (!validateAuthoraizeUserDetailLogic.practiceKanrensha(capsuleDto.getUserDto(),
                    capsuleDto.getKanrenshaCode(), this.getKanrenshaRole(capsuleDto.getKanrenshaKbn()),
                    UserRoleConstants.MANAGER, UserRoleConstants.PARTNER_API, UserRoleConstants.ADMIN)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("所持している権限では本人の編集しかできません");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            }

            resultDto = switchYearGetStorageFielByIdService.practice(year, capsuleDto.getStorageId());

            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                // 正常取得できたらそのまま返却
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (IOException exception) {
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に保存できませんでした");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (EmptyResultDataAccessException exception) {
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("予測できない例外発生しました");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

    private String getKanrenshaRole(final Short kanrenshaKbn) {

        final short PERSON = KanrenshaKbnConstants.PERSON;
        final short KIGYOU_DT = KanrenshaKbnConstants.KIGYOU_DT;
        final short SEIJIDANTAI = KanrenshaKbnConstants.SEIJIDANTAI;

        switch ((short) kanrenshaKbn) { // NOPMD
            case PERSON:
                return UserRoleConstants.KANRENSHA_PERSON;
            case KIGYOU_DT:
                return UserRoleConstants.KANRENSHA_KIGYOU_DT;
            case SEIJIDANTAI:
                return UserRoleConstants.KANRENSHA_SEIJIDANTAI;
            default:
                throw new IllegalArgumentException("関連者区分が定数値でありません");
        }
    }
}
