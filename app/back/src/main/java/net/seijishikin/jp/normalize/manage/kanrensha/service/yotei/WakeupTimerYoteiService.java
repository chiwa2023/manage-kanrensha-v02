package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.YoteiTaskConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force.ForceDumpHistoryController;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force.ForceDumpHistorySabunController;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force.ForceDumpMinMasterController;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force.ForceDumpMinMasterSabunController;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force.ForceDumpStdMasterController;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force.ForceDumpStdMasterSabunController;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;

/**
 * 予定作業と定義されたタスクを起動する
 */
@Service
public class WakeupTimerYoteiService {

    /** 関連者履歴出力Controller */
    @Autowired
    private ForceDumpHistoryController forceDumpHistoryController;

    /** 関連者履歴出力差分Controller */
    @Autowired
    private ForceDumpHistorySabunController forceDumpHistorySabunController;

    /** 関連者最小出力Controller */
    @Autowired
    private ForceDumpMinMasterController forceDumpMinMasterController;

    /** 関連者最小出力差分Controller */
    @Autowired
    private ForceDumpMinMasterSabunController forceDumpMinMasterSabunController;

    /** 関連者標準出力Controller */
    @Autowired
    private ForceDumpStdMasterController forceDumpStdMasterController;

    /** 関連者標準出力差分Controller */
    @Autowired
    private ForceDumpStdMasterSabunController forceDumpStdMasterSabunController;

    /**
     * 処理を行う
     * 
     * @param timerYoteiEntity 予定実行
     * @return タスク計画登録該否
     */
    public boolean practice(final TimerYoteiEntity timerYoteiEntity, final LeastUserDto userDto) {

        try {
            switch ((short) timerYoteiEntity.getYoyakuTaskKbn()) { // NOPMD NOt Cast

                /* 関連者ファイルダンプ */
                case YoteiTaskConstants.DUMP_HISTORY:
                    return !forceDumpHistoryController.practice( // NOPMD LawOfDemeter
                            this.createDumpCapsuleDto(timerYoteiEntity, userDto)).getBody().getIsFailure();
                case YoteiTaskConstants.DUMP_HISTORY_SABUN:
                    return !forceDumpHistorySabunController.practice( // NOPMD LawOfDemeter
                            this.createDumpCapsuleDto(timerYoteiEntity, userDto)).getBody().getIsFailure();
                case YoteiTaskConstants.DUMP_MIN:
                    return !forceDumpMinMasterController.practice( // NOPMD LawOfDemeter
                            this.createDumpCapsuleDto(timerYoteiEntity, userDto)).getBody().getIsFailure();
                case YoteiTaskConstants.DUMP_MIN_SABUN:
                    return !forceDumpMinMasterSabunController.practice( // NOPMD LawOfDemeter
                            this.createDumpCapsuleDto(timerYoteiEntity, userDto)).getBody().getIsFailure();
                case YoteiTaskConstants.DUMP_STD:
                    return !forceDumpStdMasterController.practice( // NOPMD LawOfDemeter
                            this.createDumpCapsuleDto(timerYoteiEntity, userDto)).getBody().getIsFailure();
                case YoteiTaskConstants.DUMP_STD_SABUN:
                    return !forceDumpStdMasterSabunController.practice( // NOPMD LawOfDemeter
                            this.createDumpCapsuleDto(timerYoteiEntity, userDto)).getBody().getIsFailure();

                /* TODO アップロード一時ファイル整理 */

                /* TODO 長期アクセスなしユーザ対応 */

                default:
                    // その他の未指定は該当なしで何もしない
                    return false;
            }
        } catch (NullPointerException exception) { // NOPMD CatchingNPE
            // bodyのないレスポンスが戻ってきた場合は、各タスクで例外のレスポンスが発生している(屋上屋になるのでstacktraceの保存はしない)
            return false;
        }
    }

    private ForceDumpCapsuleDto createDumpCapsuleDto(final TimerYoteiEntity timerYoteiEntity,
            final LeastUserDto userDto) {

        ForceDumpCapsuleDto capsuleDto = new ForceDumpCapsuleDto();
        capsuleDto.setIsExecutePerson(true);
        capsuleDto.setIsExecuteKigyouDt(true);
        capsuleDto.setIsExecuteSeijidantai(true);
        capsuleDto.setDateEnd(timerYoteiEntity.getEndTimestamp().toLocalDate());
        capsuleDto.setDateStart(timerYoteiEntity.getSabunTimestamp().toLocalDate());
        capsuleDto.setUserDto(userDto);

        return capsuleDto;
    }

}
