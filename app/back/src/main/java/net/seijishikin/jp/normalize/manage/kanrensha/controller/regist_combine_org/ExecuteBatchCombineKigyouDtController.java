package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_combine_org;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.RegistDataByCsvFileCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org.ExecuteBatchCombineKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 個人団体紐づけ政治団体登録Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-combine")
public class ExecuteBatchCombineKigyouDtController {

    /** 個人団体紐づけ政治団体登録Service */
    @Autowired
    private ExecuteBatchCombineKigyouDtService executeBatchCombineKigyouDtService;

    /**
     * 処理を行う
     *
     * @param capsuleDto Csv登録バッチ起動条件Dto
     * @return 処理受付レスポンス
     */
    @PostMapping("/execute-kigyouDt")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RegistDataByCsvFileCapsuleDto capsuleDto) {
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        StorageFileDto fileDto = capsuleDto.getStorageFileDto();
        executeBatchCombineKigyouDtService.practice(Paths.get(fileDto.getSavedDir(), fileDto.getFileName()).toString(),
                capsuleDto.getUserDto());

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }
}
