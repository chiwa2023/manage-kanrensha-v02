package net.seijishikin.jp.normalize.manage.kanrensha.controller.yotei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.SearchTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.SearchTimerYoteiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.SearchTimerYoteiService;

/**
 * 予定実行検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/timer-yotei")
public class SearchTimerYoteiContrroller {

    /** 予定実行検索Service */
    @Autowired
    private SearchTimerYoteiService searchTimerYoteiService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchTimerYoteiResultDto> practice(
            final @RequestBody SearchTimerYoteiCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpStatus.OK).body(searchTimerYoteiService.practice(capsuleDto));

    }

}
