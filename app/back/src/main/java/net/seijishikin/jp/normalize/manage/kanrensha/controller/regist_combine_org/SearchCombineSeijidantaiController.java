package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_combine_org;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.SearchWkTblCombineOrgPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org.SearchCombineOrganizationService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 個人政治団体紐づけワークテーブル検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-combine")
public class SearchCombineSeijidantaiController {

    /** 個人団体紐づけ検索Service */
    @Autowired
    private SearchCombineOrganizationService searchCombineOrganizationService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/search-seijidantai")
    public ResponseEntity<SearchWkTblCombineOrgPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {

        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            return ResponseEntity
                    .ok(searchCombineOrganizationService.practice(KanrenshaKbnConstants.SEIJIDANTAI, capsuleDto));

        } catch (UsernameNotFoundException exception) {
            // resultDto.setIsFailure(true);
            // resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
