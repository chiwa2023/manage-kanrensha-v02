package net.seijishikin.jp.normalize.manage.kanrensha.controller.address_rsdt;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SabunPreparAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt.SabunPreparAddressRsdtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * 住居差分準備非同期Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/wktbl-address-rsdt")
public class SabunPreparAddressRsdtController {

    /** 住居差分準備非同期Service */
    @Autowired
    private SabunPreparAddressRsdtService sabunPreparAddressRsdtService;

    /** 郵便番号差分準備非同期Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 住居差分作成準備Dto
     * @return レスポンス
     */
    @PostMapping("/prepare")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SabunPreparAddressRsdtCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

            LocalDateTime dateTimeStart = LocalDateTime.now();

            // タスクを登録してバッチ実行
            // TODO Queryはfront連結後決定
            Map<String, String> mapParam = new TreeMap<>();

            LeastUserDto userDto = capsuleDto.getUserDto();
            InsertTaskPlanResultDto taskPlanDto = switchYearInsertTaskPlanService.practice(null, userDto, dateTimeStart,
                    TaskInfoConstants.PREPARE_ADDRESS_BASE_CSV, mapParam);

            // バッチ起動
            sabunPreparAddressRsdtService.practice(userDto, taskPlanDto, capsuleDto.getRsdtFileDto(),
                    capsuleDto.getParcelFileDto(), capsuleDto.getLgCode());

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
