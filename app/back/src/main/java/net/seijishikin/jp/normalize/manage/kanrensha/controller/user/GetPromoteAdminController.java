package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetPromoteAdminResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.GetPromoteAdminService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * SE権限推薦取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-role")
public class GetPromoteAdminController {

    /** SE権限推薦取得Service */
    @Autowired
    private GetPromoteAdminService getPromoteAdminService;

    /** SE権限推薦取得Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 最小限ユーザ格納Dto
     * @return レスポンス
     */
    @PostMapping("/get")
    public ResponseEntity<GetPromoteAdminResultDto> practice(final @RequestBody FrameworkCapsuleDto capsuleDto) {

        try {
            GetPromoteAdminResultDto resultDto = getPromoteAdminService.practice(capsuleDto.getUserDto());
            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);

            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
