package net.seijishikin.jp.normalize.manage.kanrensha.controller.lgcode;

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

/**
 * 兼政治団体コードoption取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/lgcode-pref")
public class SearchLgcodePrefController {

    /** 兼政治団体UtilService */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /**
     * 処理を行う
     * 
     * @return レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<List<SelectOptionStringDto>> practoice() {

        return ResponseEntity.status(HttpStatus.OK).body(getPrefectureLgCodeService.getOptions());

    }

}
