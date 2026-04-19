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
import net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval.SaveApprovalAddressService;

/**
 * 作業承認保存Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/works-approval")
public class SaveApprovalAddressControler {

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService stackTraceService;

    /** 住所承認作業保存Service */
    @Autowired
    private SaveApprovalAddressService saveApprovalAddressService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理リストDto
     * @return 処理結果レスポンス
     */
    @PostMapping("/save-address")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SaveWorksApprovalCapsuleDto capsuleDto) {

        try {
            Integer addressCount = saveApprovalAddressService.practice(capsuleDto.getListAddress(),
                    capsuleDto.getUserDto());

            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            if (0 == addressCount) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("更新件数が0件でした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setMessage("正常に更新できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) {
            stackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
