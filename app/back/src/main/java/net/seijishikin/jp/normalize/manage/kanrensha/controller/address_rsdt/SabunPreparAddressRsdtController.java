package net.seijishikin.jp.normalize.manage.kanrensha.controller.address_rsdt;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt.SabunPreparAddressRsdtService;
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
                capsuleDto.getParcelFileDto());

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

}
