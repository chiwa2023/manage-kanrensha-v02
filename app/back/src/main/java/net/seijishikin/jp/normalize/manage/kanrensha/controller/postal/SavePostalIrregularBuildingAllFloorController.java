package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SavePostalIrregularBuildingAllFloorService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 郵便番号同一建物フロア住所更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-irregular")
public class SavePostalIrregularBuildingAllFloorController {

    /** 郵便番号同一建物フロア住所更新 */
    @Autowired
    private SavePostalIrregularBuildingAllFloorService savePostalIrregularBuildingAllFloorService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 処理結果Dto
     */
    @PostMapping("/save-building")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SavePostalIrregularCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            resultDto = savePostalIrregularBuildingAllFloorService.practice(capsuleDto);
            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
