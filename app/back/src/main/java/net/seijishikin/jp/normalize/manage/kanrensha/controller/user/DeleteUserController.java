package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.DeleteUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.DeleteUserService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * ユーザ削除Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/edit-user")
public class DeleteUserController {

    /** ユーザ削除Service */
    @Autowired
    private DeleteUserService deleteUserService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService stackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/delete")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final DeleteUserCapsuleDto capsuleDto) {

        // 削除作業
        try {
            FrameworkMessageAndResultDto resultDto = deleteUserService.practice(capsuleDto);
            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (IllegalStateException | UsernameNotFoundException e) {
            stackTraceService.practice(e, LocalDate.now().getYear(), 0);
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("ユーザ名が見つかりません");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);

        } catch (Exception exception) { // NOPMD GenericException 業務的な理由から積極的に許容
            stackTraceService.practice(exception, LocalDate.now().getYear(), 0);
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("システムエラーが発生しました");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
