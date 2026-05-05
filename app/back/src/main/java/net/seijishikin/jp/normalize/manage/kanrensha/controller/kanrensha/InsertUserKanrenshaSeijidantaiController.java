package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaSeijidantaiService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 関連者政治団体追加Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-kanrensha")
public class InsertUserKanrenshaSeijidantaiController {

    /** 関連者政治団体編集Service */
    @Autowired
    private InsertKanrenshaSeijidantaiService insertKanrenshaSeijidantaiService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/add-seijidantai")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveKanrenshaSeijidantaiCapsuleDto capsuleDto) {
        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer newId = insertKanrenshaSeijidantaiService.practice(capsuleDto);
            if (0 == newId) {
                resultDto.setMessage("登録できませんでした");
                resultDto.setIsFailure(true);
            } else {
                resultDto.setMessage("登録できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            resultDto.setIsFailure(true);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);

    }

}
