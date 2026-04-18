package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_master_min;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.RegistDataByCsvFileCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.CopyTempToUseSavedFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min.ExecuteBatchMasterMinKigyouDtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.FileTypeConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * 企業団体マスタ最小Csv登録Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/regist-bulk-master-min")
public class ExecuteBatchMasterMinKigyouDtController {

    /** 非同期処理登録専用Service */
    @Autowired
    private ExecuteBatchMasterMinKigyouDtService executeBatchMasterMinKigyouDtService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private CopyTempToUseSavedFileService copyTempToUseSavedFileService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto Csv登録バッチ起動条件Dto
     * @return 処理受付レスポンス
     */
    @PostMapping("/execute-kigyoudt")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RegistDataByCsvFileCapsuleDto capsuleDto) {

        LocalDateTime dateTimeStart = LocalDateTime.now();
        Integer year = dateTimeStart.getYear();
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskPlanCode = 0;
        try {
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            // TODO Queryはfront連結後決定
            Map<String, String> mapParam = new TreeMap<>();

            TaskPlanWithUseFileDto planFileDto = copyTempToUseSavedFileService.practice(dateTimeStart,
                    capsuleDto.getStorageFileDto(), userDto, FileTypeConstants.FILE_TYPE,
                    TaskInfoConstants.FILE_KIGYOU_MIN, mapParam);
            taskPlanCode = planFileDto.getTaskPlanCode();

            executeBatchMasterMinKigyouDtService.practice(year, capsuleDto.getUserDto(), planFileDto);

            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, year, taskPlanCode);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();
        }
    }

}
