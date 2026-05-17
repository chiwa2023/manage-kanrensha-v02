package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.VerifyNewUserService;

/**
 * 新規ユーザ用URLトークン検証Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/add-user")
public class VerifyNewUserCodeController {

    /** 新規ユーザ用URLトークン検証Service */
    @Autowired
    private VerifyNewUserService verifyNewUserService;

    /**
     * 新規登録ユーザのURLトークンを検証する
     *
     * @param newComerDto メールアドレス・有効期限を持つDto
     * @return 登録したDto
     */
    @PostMapping("/verify")
    public ResponseEntity<NewComerDto> verify(@RequestBody final NewComerDto newComerDto) {
        NewComerDto resultDto = verifyNewUserService.practice(newComerDto);
        if (resultDto.getIsFailure()) {
            return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        }
    }
}
