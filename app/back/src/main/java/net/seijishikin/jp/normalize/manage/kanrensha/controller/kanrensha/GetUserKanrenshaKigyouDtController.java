package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;


/**
 * 関連者個人Dto取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/user-kanrensha")
public class GetUserKanrenshaKigyouDtController {

    /** 関連者個人Dto取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;


    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/get-kigyou-dt")
    public ResponseEntity<GetKanrenshaKigyouDtResultDto> practice(
            @RequestBody final GetKanrenshaKigyouDtCapsuleDto capsuleDto) {

        GetKanrenshaKigyouDtResultDto resultDto = new GetKanrenshaKigyouDtResultDto();

        try {
            KanrenshaKigyouDtDto kanrenshaKigyouDtDto = getKanrenshaKigyouDtDtoService
                    .practice(capsuleDto.getMasterKigyouDtEntity());
            resultDto.setKanrenshaKigyouDtDto(kanrenshaKigyouDtDto);
            
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (DataRetrievalFailureException exception) {
            // 最新が2件以上
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("データにエラーがあります。システム運営者にお問い合わせください");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
