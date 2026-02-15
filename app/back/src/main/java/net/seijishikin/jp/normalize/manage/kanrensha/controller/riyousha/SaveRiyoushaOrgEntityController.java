package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SaveRiyoushaOrgEntityService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 利用者組織更新Controller
 */
@RestController
@RequestMapping("/riyousha-org")
public class SaveRiyoushaOrgEntityController {

    /** 利用者組織更新Service */
    @Autowired
    private SaveRiyoushaOrgEntityService saveRiyoushaOrgEntityService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveRiyoushaOrgCapsuleDto capsuleDto) {

        try {
            Integer savedId = saveRiyoushaOrgEntityService.practice(capsuleDto);

            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            if (savedId == 0) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("保存できませんでした");
                return ResponseEntity.status(HttpResponseStatus.ACCEPTED.code()).body(resultDto);

            } else {
                resultDto.setMessage("正常に保存できました");
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();

        }

    }

}
