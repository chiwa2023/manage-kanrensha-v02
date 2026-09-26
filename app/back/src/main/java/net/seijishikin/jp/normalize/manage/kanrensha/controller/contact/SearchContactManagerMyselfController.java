package net.seijishikin.jp.normalize.manage.kanrensha.controller.contact;

import java.time.Year;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.contact.SearchContactManagerMyselfService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 自分自身が問い合わせした問い合わせ検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/contact-manager")
public class SearchContactManagerMyselfController {

    /** 自分自身が問い合わせした問い合わせ検索Service */
    @Autowired
    private SearchContactManagerMyselfService searchContactManagerMyselfService;




    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto ページング含む検索条件格納Dto
     * @return 検索結果
     */
    @PostMapping("/search-myself")
    public ResponseEntity<SearchContactManagerResultDto> practice(
            final @RequestBody SearchContactManagerCapsuleDto capsuleDto) {

        SearchContactManagerResultDto resultDto = new SearchContactManagerResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            resultDto = searchContactManagerMyselfService.practice(capsuleDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);
        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            resultDto.setIsFailure(true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
