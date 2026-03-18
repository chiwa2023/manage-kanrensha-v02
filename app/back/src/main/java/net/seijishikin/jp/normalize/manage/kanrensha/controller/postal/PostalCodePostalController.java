package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodePostalResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchAddressPostalService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 住所郵便番号までController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-search")
public class PostalCodePostalController {

    /** 住所郵便番号まで検索Service */
    @Autowired
    private SearchAddressPostalService searchAddressPostalService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/postal")
    public ResponseEntity<PostalCodePostalResultDto> practice(final @RequestBody PostalCodeCapsuleDto capsuleDto) {

        return ResponseEntity.ok(searchAddressPostalService.practice(capsuleDto.getPostal1(), capsuleDto.getPostal2()));
    }

}
