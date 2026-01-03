package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

//import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.RefleshPasswordCapsuleDto;

/**
 * パスワード更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/edit-user")
public class RefleshPasswordController {

//    /** ユーザ詳細Manager */
//    @Autowired
//    private CustomUserDetailsManager customUserDetailsManager;

//    /** PasswordEncoder */
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    /** ユーザ人物Respoitory */
//    @Autowired
//    private UserPersonRepository userPersonRepository;

//    /** 認証プロバイダ */
//    @Autowired
//    private DaoAuthenticationProvider daoAuthenticationProvider;

    /**
     * 処理を行う
     *
     * @param capsuleDto パスワード更新Dto
     * @return 処理結果
     */
    @PostMapping("/reflesh-password")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final RefleshPasswordCapsuleDto capsuleDto) {

//        Optional<UserPersonEntity> optional = userPersonRepository
//                .findById(capsuleDto.getUserPersonLeastDto().getUserPersonId());

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
//        if (optional.isEmpty()) {
//            resultDto.setIsFailure(true);
//            resultDto.setMessage("指定されたユーザが存在しません");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDto);
//        }
//
//        String email = optional.get().getEmail();
//        String newPassword = passwordEncoder.encode(capsuleDto.getNewPassword());
//        
//        resultDto.setIsFailure(!customUserDetailsManager.changePassword(email,
//                passwordEncoder.encode(capsuleDto.getOldPassword()), newPassword));
//
//        if (resultDto.getIsFailure()) {
//            // パスワード切り替え失敗時
//            resultDto.setIsFailure(true);
//            resultDto.setMessage("パスワード更新に失敗しました");
//        } else {
//            // パスワード切り替え成功時
//            resultDto.setMessage("パスワード更新に成功しました");
//            // 再ログイン
//            SecurityContextHolder.clearContext();
//            
//            // ログイン処理
//            Authentication authentication = daoAuthenticationProvider
//                    .authenticate(new UsernamePasswordAuthenticationToken(email, capsuleDto.getNewPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

}
