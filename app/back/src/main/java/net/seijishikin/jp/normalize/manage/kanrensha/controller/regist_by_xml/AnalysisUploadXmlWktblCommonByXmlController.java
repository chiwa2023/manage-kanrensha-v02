package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_by_xml;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RegistDataByXmlCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.CopyTempToUseSavedFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml.AnalysisUploadXmlWktblCommonByXmlService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.FileTypeConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * アップロード済XMLファイル解析ワークテーブル複写Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/analysis-xml")
public class AnalysisUploadXmlWktblCommonByXmlController {

    /** アップロード済XMLファイル解析ワークテーブル複写Service */
    @Autowired
    private AnalysisUploadXmlWktblCommonByXmlService analysisUploadXmlWktblCommonByXmlService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private CopyTempToUseSavedFileService copyTempToUseSavedFileService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @return レスポンス
     */
    @PostMapping("/execute")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final RegistDataByXmlCapsuleDto capsuleDto) {

        LocalDateTime dateTimeStart = LocalDateTime.now();
        Integer year = dateTimeStart.getYear();
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskPlanCode = 0;
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            // TODO Queryはfront連結後決定
            Map<String, String> mapParam = new TreeMap<>();

            TaskPlanWithUseFileDto planFileDto = copyTempToUseSavedFileService.practice(dateTimeStart,
                    capsuleDto.getStorageFileDto(), userDto, FileTypeConstants.FILE_TYPE,
                    TaskInfoConstants.WKTBL_KANRENSHA_XML, mapParam);
            taskPlanCode = planFileDto.getTaskPlanCode();

            analysisUploadXmlWktblCommonByXmlService.practice(year, capsuleDto, planFileDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, year, taskPlanCode);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

}
