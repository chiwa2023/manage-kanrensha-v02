package net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.GetDumpRecordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.GetDumpRecordResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record.GetDupmRunRecordService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * ダンプ記録取得Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/dump-record")
public class GetDupmRunRecordController {

    /** ユーザ妥当性検証Logic */
    @Autowired
    private GetDupmRunRecordService getDupmRunRecordService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/get")
    public ResponseEntity<GetDumpRecordResultDto> practice(final @RequestBody GetDumpRecordCapsuleDto capsuleDto) {

        GetDumpRecordResultDto resultDto = new GetDumpRecordResultDto();
        try {
            // 未ログインでデータ取得できるのでログインチェックをしない

            resultDto.setListEntiy(getDupmRunRecordService.practice(capsuleDto.getListTaskCode()));

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
