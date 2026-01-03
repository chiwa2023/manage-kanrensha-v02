package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ユーザ削除Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user")
public class DeleteUserController {

//    /** ユーザ詳細Manager */
//    @Autowired
//    private CustomUserDetailsManager customUserDetailsManager;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/delete")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final FrameworkCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
//        UserPersonLeastDto userLeastDto = capsuleDto.getUserPersonLeastDto();
//        // TODO front側で削除・操作ユーザをセットするよう修正する
//        resultDto.setIsFailure(!customUserDetailsManager.deleteUser(userLeastDto, userLeastDto));

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);

    }

}
