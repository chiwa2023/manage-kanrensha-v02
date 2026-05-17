package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaCombinePersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.DeleteRiyoushaCombinePersonService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 利用者組織紐づけ個人削除Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/riyousha-org")
public class DeleteRiyoushaCombinePersonController {

    /** 利用者組織紐づけ個人削除Service */
    @Autowired
    private DeleteRiyoushaCombinePersonService deleteRiyoushaCombinePersonService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/delete-person")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final RiyoushaCombinePersonCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto;
        try {
            resultDto = deleteRiyoushaCombinePersonService.practice(capsuleDto);

            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);

            } else {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);

        }

    }

}
