package net.seijishikin.jp.normalize.manage.kanrensha.controller.lgcode;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAllCityLgcodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalCodeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode.SearchLgcodeCityService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 地方自治体コード県条件検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/lgcode-city")
public class SearchLgcodeCityController {

    /** StackTrace保存Service */
    @Autowired
    private SearchLgcodeCityService searchLgcodeCityService;

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
    public ResponseEntity<List<SelectOptionStringDto>> practice(
            final @RequestBody SearchAllCityLgcodeCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(searchLgcodeCityService.practice(capsuleDto));

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            SearchPostalCodeResultDto resultDto = new SearchPostalCodeResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            List<SelectOptionStringDto> list = new ArrayList<>();
            list.add(new SelectOptionStringDto("", ""));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(list);
        }

    }
}
