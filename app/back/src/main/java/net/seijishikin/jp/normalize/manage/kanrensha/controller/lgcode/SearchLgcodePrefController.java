package net.seijishikin.jp.normalize.manage.kanrensha.controller.lgcode;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 兼政治団体コードoption取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/lgcode-pref")
public class SearchLgcodePrefController {

    /** 兼政治団体UtilService */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<List<SelectOptionStringDto>> practice() {

        try {

            return ResponseEntity.status(HttpStatus.OK).body(getPrefectureLgCodeService.getOptions());

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
