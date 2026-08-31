package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchPostalIrregularBuildingAllFloorService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 同一建物取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-irregular")
public class SearchPostalIrregularBuildingAllFloorController {

    /** 同一建物取得Service */
    @Autowired
    private SearchPostalIrregularBuildingAllFloorService searchPostalIrregularBuildingAllFloor;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/building-detail")
    public ResponseEntity<GetDetailPostalIllegularResultDto> practice(
            final @RequestBody GetDetailPostalIllegularCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.ok(searchPostalIrregularBuildingAllFloor.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
