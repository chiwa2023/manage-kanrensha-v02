package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetUserDtoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetUserDtoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.GetUserLeastByIdService;

/**
 * ユーザ編集対象取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/edit-user")
public class GetUserLeastByIdController {

    /** ユーザ編集対象取得Service */
    @Autowired
    private GetUserLeastByIdService getUserLeastByIdService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 編集Id格納Dto
     * @return レスポンス情報
     */
    @PostMapping("/get")
    public ResponseEntity<GetUserDtoResultDto> practice(final @RequestBody GetUserDtoCapsuleDto capsuleDto) {

        try {
            GetUserDtoResultDto resultDto = getUserLeastByIdService.practcie(capsuleDto.getEditUserid());

            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) {
            GetUserDtoResultDto resultDto = new GetUserDtoResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("システム例外が発生しました");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
