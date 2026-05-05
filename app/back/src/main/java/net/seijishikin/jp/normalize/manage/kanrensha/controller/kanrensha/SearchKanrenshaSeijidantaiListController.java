package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaSeijidantaiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.SearchKanrenshaSeijidantaiListService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者政治団体リスト検索Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-kanrensha")
public class SearchKanrenshaSeijidantaiListController {

    /** 関連者個人リスト検索Service */
    @Autowired
    private SearchKanrenshaSeijidantaiListService searchKanrenshaSeijidantaiListService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ページング含む検索条件格納Dto
     * @return 検索結果
     */
    @PostMapping("/search-seijidantai")
    public ResponseEntity<SearchKanrenshaSeijidantaiResultDto> practice(
            final @RequestBody SearchKanrenshaSeijidantaiCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(searchKanrenshaSeijidantaiListService.practice(capsuleDto));
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
