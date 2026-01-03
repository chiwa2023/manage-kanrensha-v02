package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

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
 * ユーザ検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user")
public class SearchUserController {

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/search")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final FrameworkCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpStatus.OK).body(new FrameworkMessageAndResultDto());

    }

}
