package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.PartnerAccessTokenStateDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.GetPartnerApiTokenStateService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * APIユーザトークン状況取得Conttroller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/partner-api")
public class GetPartnerApiTokenStateController {

    /** APIユーザトークン状況取得Service */
    @Autowired
    private GetPartnerApiTokenStateService getPartnerApiTokenStateService;

    /** APIユーザトークン状況取得Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザログインDto
     * @return トークン
     */
    @PostMapping("/get-state")
    public ResponseEntity<PartnerAccessTokenStateDto> practice(final @RequestBody FrameworkCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(getPartnerApiTokenStateService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, LocalDateTime.now().getYear(), 0);

            PartnerAccessTokenStateDto stateDto = new PartnerAccessTokenStateDto();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stateDto);
        }

    }

}
