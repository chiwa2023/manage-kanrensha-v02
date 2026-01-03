package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

//import java.time.LocalDate;
import java.util.Date;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserCapsuleDto;

/**
 * APIユーザ用リフレッシュトークン生成Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT)
public class ReplaceComradeRefleshTokenController {
    // CHECKSTYLE:OFF

//    /** JwtService */
//    @Autowired
//    private JwtService jwtService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザログインDto
     * @return トークン
     */
    @PostMapping("/replace-token")
    public ResponseEntity<JwtTokenDto> practice(final @RequestBody LoginUserCapsuleDto capsuleDto) {

//        LocalDate now = LocalDate.now();
//        LocalDate change = now.plusMonths(6);
//
//        UserDetails userDetails = User.builder().username(capsuleDto.getUserId()).password(capsuleDto.getPassword())
//                .accountExpired(now.plusYears(CustomUserDetailsManager.LIMIT_PASS_CHANGE).isBefore(now)) // x年無活動なのでアカウントロックしたなど
//                .accountLocked(false) // 現状未使用
//                .credentialsExpired(change.plusMonths(CustomUserDetailsManager.LIMIT_ACTIVE).isBefore(now)) // xか月パスワード更新なしなのでアカウントロックしたなど
//                .disabled(false).roles().build(); //

        // 新しいトークンの生成
        // JwtTokenDto jwtToken = jwtService.generateToken(userDetails);
        JwtTokenDto jwtToken = new JwtTokenDto("", "", new Date());

        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }

}
