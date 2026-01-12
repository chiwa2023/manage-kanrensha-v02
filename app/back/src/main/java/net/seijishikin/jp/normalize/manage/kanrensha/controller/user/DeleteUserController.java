package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.DeleteUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.DeleteUserService;

/**
 * ユーザ削除Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/edit-user")
public class DeleteUserController {

    /** ユーザ削除Service */
    @Autowired
    private DeleteUserService deleteUserService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/delete")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final DeleteUserCapsuleDto capsuleDto) {
        // 削除作業
        FrameworkMessageAndResultDto resultDto = deleteUserService.practice(capsuleDto);

        if (resultDto.getIsFailure()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);
        }

    }

}
