package net.seijishikin.jp.normalize.manage.kanrensha.controller.works_approval;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SaveWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval.SaveApprovalShokugyouService;

/**
 * 作業内容承認検索Controller(初回のみ)
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/works-approval")
public class SaveApprovalShokugyouControler {

    /** 関連者住所承認作業検索Service */
    @Autowired
    private SaveApprovalShokugyouService saveApprovalShokugyouService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService stackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/save-shokugyou")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SaveWorksApprovalCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            if (0 == saveApprovalShokugyouService.practice(capsuleDto.getListShokugyou(), capsuleDto.getUserDto())) {
                resultDto.setMessage("データ更新が0件でした");
                resultDto.setIsFailure(true);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage("登録できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            stackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

}
