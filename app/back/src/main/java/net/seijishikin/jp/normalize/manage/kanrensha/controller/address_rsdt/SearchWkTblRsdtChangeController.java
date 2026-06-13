package net.seijishikin.jp.normalize.manage.kanrensha.controller.address_rsdt;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblRsdtChangeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt.SearchWkTblRsdtChangeService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 住所差分ワークテーブル更新検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/wktbl-address-rsdt")
public class SearchWkTblRsdtChangeController {

    /** 住所差分ワークテーブル更新検索Service */
    @Autowired
    private SearchWkTblRsdtChangeService searchWkTblRsdtChangeService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/change-search")
    public ResponseEntity<SearchWkTblRsdtChangeResultDto> practice(
            final @RequestBody SearchWkTblAddressRsdtCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(searchWkTblRsdtChangeService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            SearchWkTblRsdtChangeResultDto resultDto = new SearchWkTblRsdtChangeResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
