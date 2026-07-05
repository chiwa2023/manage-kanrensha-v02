package net.seijishikin.jp.normalize.manage.kanrensha.controller.houjin_no;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.houjin_no.GetHoujinNoService;

/**
 * 法人番号取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/houjin-no")
public class GetHoujinNoController {

    /** 法人番号取得Service */
    @Autowired
    private GetHoujinNoService getHoujinNoService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 法人番号検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/get-external")
    public ResponseEntity<SearchHoujinNoResultDto> practice(final @RequestBody SearchHoujinNoCapsuleDto capsuleDto) {

        // Serviceで処理全体をtry-catchでくくっているのでControllerでtry-catchは不要
        SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);

        if (resultDto.getIsFailure()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);
        }
    }

}
