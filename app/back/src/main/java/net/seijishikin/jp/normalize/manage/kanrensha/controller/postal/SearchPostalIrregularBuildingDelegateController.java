package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalIllegularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalIllegularResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchPostalIrregularBuildingDelegateService;


/**
 * 郵便番号不規則検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-irregular")
public class SearchPostalIrregularBuildingDelegateController {

    /** 郵便番号不規則検索Service */
    @Autowired
    private SearchPostalIrregularBuildingDelegateService searchPostalIrregularBuildingDelegateService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/building")
    public ResponseEntity<SearchPostalIllegularResultDto> practice(
            final @RequestBody SearchPostalIllegularCapsuleDto capsuleDto) {

        SearchPostalIllegularResultDto resultDto = searchPostalIrregularBuildingDelegateService.practice(capsuleDto);

        if (resultDto.getIsFailure()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
        } else {
            return ResponseEntity.ok(resultDto);
        }
    }

}
