package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_combine_org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.SearchWkTblCombineOrgPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org.SearchCombineOrganizationService;
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

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/search-poli-org")
    public ResponseEntity<SearchWkTblCombineOrgPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {

        return ResponseEntity.ok(searchCombineOrganizationService.practice(KanrenshaKbnConstants.SEIJIDANTAI, capsuleDto));
    }

}
