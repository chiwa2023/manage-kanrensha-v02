package net.seijishikin.jp.normalize.manage.kanrensha.controller.lgcode;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressCityDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode.MoveAddressRsdtByLgcodeService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * ワークテーブル編集後再試行XML最小マスタController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/lgcode-delete")
public class MoveAddressRsdtByLgcodeController {

    /** 地方自治体コード移動非同期Servce */
    @Autowired
    private MoveAddressRsdtByLgcodeService moveAddressRsdtByLgcodeService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 地方自治体コード移動Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/move")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody EditAddressCityDeleteCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        LocalDateTime dateTimeStart = LocalDateTime.now();
        Integer year = dateTimeStart.getYear();

        // タスクを登録してバッチ実行
        // TODO Queryはfront連結後決定
        Map<String, String> mapParam = new TreeMap<>();

        InsertTaskPlanResultDto planDto = switchYearInsertTaskPlanService.practice(null, capsuleDto.getUserDto(),
                dateTimeStart, TaskInfoConstants.MOVE_ADDRESS_LGCODE, mapParam);
        moveAddressRsdtByLgcodeService.practice(year, planDto, capsuleDto);

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

}
