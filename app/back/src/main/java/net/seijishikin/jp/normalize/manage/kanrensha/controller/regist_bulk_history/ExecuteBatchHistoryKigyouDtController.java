package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_history;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.RegistDataByCsvFileCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history.ExecuteBatchHistoryKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 企業団体履歴Csv登録Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-history")
public class ExecuteBatchHistoryKigyouDtController {

    /** 非同期処理登録専用Service */
    @Autowired
    private ExecuteBatchHistoryKigyouDtService executeBatchHistoryKigyouDtService;

    //    /** 仮ファイル本登録Service */
    //    @Autowired
    //    private CopyTempToUseSavedFileService copyTempToUseSavedFileService;

    /**
     * 処理を行う
     *
     * @param capsuleDto Csv登録バッチ起動条件Dto
     * @return 処理受付レスポンス
     */
    @PostMapping("/execute-kigyouDt")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RegistDataByCsvFileCapsuleDto capsuleDto) {

        // TODO ファイルタイプとタスク種類は決定次第修正する
        // int year = LocalDate.now().getYear();
        // Short fileType = Short.valueOf("205");
        // int taskConstants = 1;
        try {
            StorageFileDto fileDto = capsuleDto.getStorageFileDto();
            LeastUserDto userDto = capsuleDto.getUserDto();

            // copyTempToUseSavedFileService.practice(year, fileDto, userDto, fileType,
            // taskConstants);

            Path path = Paths.get(fileDto.getSavedDir(), fileDto.getFileName());
            executeBatchHistoryKigyouDtService.practice(path.toString(), userDto);
        } catch (Exception exception) { // NOPMD

            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に登録できませんでした。");

            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        }

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
