package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.ReplacePartnerApiRefreshTokenService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * APIユーザ用リフレッシュトークン更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/partner-api")
public class ReplacePartnerApiRefreshTokenController {

    /** APIユーザ用リフレッシュトークン更新 */
    @Autowired
    private ReplacePartnerApiRefreshTokenService replacePartnerApiRefreshTokenService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザログインDto
     * @return トークン
     */
    @PostMapping("/replace-token")
    public ResponseEntity<PartnerApiTokenResultDto> practice(final @RequestBody PartnerApiTokenCapsuleDto capsuleDto) {
        
        try {
            PartnerApiTokenResultDto resultDto = replacePartnerApiRefreshTokenService.practice(capsuleDto,
                    LocalDateTime.now());

            if (Objects.isNull(resultDto)) {
                resultDto = new PartnerApiTokenResultDto();
                resultDto.setIsFailure(true);
                resultDto.setMessage("トークンが発行できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {

                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD
            
            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);

            PartnerApiTokenResultDto resultDto = new PartnerApiTokenResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("トークンが発行できませんでした");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
