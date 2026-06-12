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
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RetryWktblBatchCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt.UpdateWkTblAddressRsdtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * 住居差分編集内容反映非同期Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/wktbl-address-rsdt")
public class UpdateWkTblAddressRsdtController {

    /** 住居差分編集内容反映非同期Service */
    @Autowired
    private UpdateWkTblAddressRsdtService updateWkTblAddressRsdtService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集後再試行条件Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RetryWktblBatchCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        LocalDateTime dateTimeStart = LocalDateTime.now();

        // タスクを登録してバッチ実行
        // TODO Queryはfront連結後決定
        Map<String, String> mapParam = new TreeMap<>();

        InsertTaskPlanResultDto planDto = switchYearInsertTaskPlanService.practice(null, capsuleDto.getUserDto(),
                dateTimeStart, TaskInfoConstants.CHANGE_ADDRESS_BASE_CSV, mapParam);

        updateWkTblAddressRsdtService.practice(capsuleDto.getUserDto(), planDto);

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

}
