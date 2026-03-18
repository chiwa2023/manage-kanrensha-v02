package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBuildingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchAddressBuildingService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchAddressFloorPostalService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 住所建物検索Contrroller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-search")
public class PostalCodeBuildingController {

    /** 住所建物まで検索Service */
    @Autowired
    private SearchAddressBuildingService searchAddressBuildingService;

    /** 住所建物まで郵便番号建物階割振り検索Service */
    @Autowired
    private SearchAddressFloorPostalService searchAddressFloorPostalService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/building")
    public ResponseEntity<PostalCodeBuildingResultDto> practice(final @RequestBody PostalCodeCapsuleDto capsuleDto) {

        if (capsuleDto.getIsGyouseikuData()) {
            return ResponseEntity
                    .ok(searchAddressBuildingService.practice(capsuleDto.getLgCode(), capsuleDto.getSelectedBlock()));

        } else {
            return ResponseEntity.ok(searchAddressFloorPostalService.practice(capsuleDto.getLgCode(),
                    capsuleDto.getPostal1(), capsuleDto.getPostal2()));
        }

    }

}
