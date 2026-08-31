package net.seijishikin.jp.normalize.manage.kanrensha.controller.address_rsdt;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressRsdtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt.SearchAddressRsdtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 住居検索Controller
 */
@Service
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/address-rsdt")
public class SearchAddressRsdtController {

    /** 住居検索Service */
    @Autowired
    private SearchAddressRsdtService searchAddressRsdtService;

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
    public ResponseEntity<SearchAddressRsdtResultDto> practice(
            final @RequestBody SearchAddressRsdtCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(searchAddressRsdtService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            SearchAddressRsdtResultDto resultDto = new SearchAddressRsdtResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
