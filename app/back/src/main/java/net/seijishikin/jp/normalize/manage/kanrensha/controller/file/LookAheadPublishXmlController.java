package net.seijishikin.jp.normalize.manage.kanrensha.controller.file;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DatabindException;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.LookAheadPublishXmlResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.LookAheadPublishXmlService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * アップロードされたXMLファイルを文書種類先読みService
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/xml")
public class LookAheadPublishXmlController {

    /** 文書種類先読みService */
    @Autowired
    private LookAheadPublishXmlService lookAheadPublishXmlService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto アップロードファイル内容Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/look-ahead")
    public ResponseEntity<LookAheadPublishXmlResultDto> practice(
            final @RequestBody UploadContentCapsuleDto capsuleDto) {

        LocalDate now = LocalDate.now();
        Integer year = now.getYear();

        LookAheadPublishXmlResultDto resultDto = new LookAheadPublishXmlResultDto();
        try {
            resultDto = lookAheadPublishXmlService.practice(now.getMonthValue(), capsuleDto.getUploadFileDto());
            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                // 正常取得できたらそのまま返却
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (DatabindException exception) {
            saveStackTraceService.practice(exception, year, 0);

            resultDto.setIsFailure(true);
            resultDto.setMessage("形式が異なるXMLを読み込むことができませんでした");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (IOException exception) {
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に保存できませんでした");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (Exception exception) {  // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("なにがしかの例外が発生しました");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
