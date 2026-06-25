package net.seijishikin.jp.normalize.manage.kanrensha.controller.lgcode;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.PagingIntegerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressCityDeleteResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode.SearchAllCityDeleteService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 地方自治体コード削除検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/lgcode-delete")
public class SearchAllCityDeleteController {

    /** 地方自治体コード削除検索Service */
    @Autowired
    private SearchAllCityDeleteService searchAllCityDeleteService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchAddressCityDeleteResultDto> practice(
            final @RequestBody PagingIntegerCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(searchAllCityDeleteService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            SearchAddressCityDeleteResultDto resultDto = new SearchAddressCityDeleteResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
