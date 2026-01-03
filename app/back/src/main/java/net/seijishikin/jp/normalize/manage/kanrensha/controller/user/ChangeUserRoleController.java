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
 * ユーザ権限変更Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-role")
public class ChangeUserRoleController {

//    /** ユーザ権限変更Service */
//    @Autowired
//    private ChangeUserRoleService changeUserRoleService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/change")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final FrameworkCapsuleDto capsuleDto) {

        // return
        // ResponseEntity.status(HttpStatus.OK).body(changeUserRoleService.practice(capsuleDto));
        return ResponseEntity.status(HttpStatus.OK).body(new FrameworkMessageAndResultDto());
    }

}
