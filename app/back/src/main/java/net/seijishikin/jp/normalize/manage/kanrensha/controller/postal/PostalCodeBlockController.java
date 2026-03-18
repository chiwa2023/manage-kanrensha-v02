package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchAddressBlockService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 住所番地までContrroller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-search")
public class PostalCodeBlockController {

    /** 住所番地までまで検索Service */
    @Autowired
    private SearchAddressBlockService searchAddressBlockService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/block")
    public ResponseEntity<PostalCodeBlockResultDto> practice(final @RequestBody PostalCodeCapsuleDto capsuleDto) {

        return ResponseEntity.ok(
                searchAddressBlockService.practice(capsuleDto.getSelectedPostal(), capsuleDto.getIsGyouseikuData()));
    }
}
