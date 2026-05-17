package net.seijishikin.jp.normalize.manage.kanrensha.controller.file;

import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.LookAheadCsvResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.LookAheadCsvFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * アップロードされたCSVファイルを10行先読みController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/csv")
public class LookAheadCsvFileController {

    /** アップロードされたCSVファイルを10行先読みService */
    @Autowired
    private LookAheadCsvFileService lookAheadCsvFileService;

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
    public ResponseEntity<LookAheadCsvResultDto> practice(final @RequestBody UploadContentCapsuleDto capsuleDto) {

        LocalDate now = LocalDate.now();
        Integer year = now.getYear();
        LookAheadCsvResultDto resultDto = new LookAheadCsvResultDto();
        try {
            resultDto = lookAheadCsvFileService.practice(now.getMonthValue(), capsuleDto.getUploadFileDto());
            
            if(resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
                
            }else {
                // 正常取得できたらそのまま返却
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (IOException exception) {
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に保存できませんでした");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (NoSuchElementException exception) {
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("csv解析が正常にできませんでした");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, year, 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("予測できない例外発生しました");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
