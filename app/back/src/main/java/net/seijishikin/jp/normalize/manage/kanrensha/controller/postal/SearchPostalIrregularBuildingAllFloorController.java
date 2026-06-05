package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchPostalIrregularBuildingAllFloorService;

/**
 * 同一建物取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-irregular")
public class SearchPostalIrregularBuildingAllFloorController {

    /** 同一建物取得Service */
    @Autowired
    private SearchPostalIrregularBuildingAllFloorService searchPostalIrregularBuildingAllFloor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/building-detail")
    public ResponseEntity<GetDetailPostalIllegularResultDto> practice(
            final @RequestBody GetDetailPostalIllegularCapsuleDto capsuleDto) {

        return ResponseEntity.ok(searchPostalIrregularBuildingAllFloor.practice(capsuleDto));
    }
}
