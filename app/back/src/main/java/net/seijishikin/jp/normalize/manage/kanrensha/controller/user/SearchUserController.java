package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserEntityResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.SearchUserServcie;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * ユーザ検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/edit-user")
public class SearchUserController {

    /** ユーザ検索Service */
    @Autowired
    private SearchUserServcie searchUserServcie;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/search")
    public ResponseEntity<SearchUserEntityResultDto> practice(@RequestBody final SearchUserCapsuleDto capsuleDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(searchUserServcie.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);
            
            SearchUserEntityResultDto resultDto = new SearchUserEntityResultDto();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
