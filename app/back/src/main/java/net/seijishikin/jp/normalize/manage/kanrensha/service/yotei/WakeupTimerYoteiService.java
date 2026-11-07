package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.YoteiTaskConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NotifyPartnerApiLimitCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.NotificationPartnerApiTokenLimitAsyncService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncDeleteLimitOverTokenService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncDeleteStorageTempFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncForceDumpHistorySabunService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncForceDumpHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncForceDumpKanrenshaCodeMoveService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncForceDumpMinMasterSabunService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncForceDumpMinMasterService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncForceDumpStdMasterSabunService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function.YoteiFuncForceDumpStdMasterService;

/**
 * 予定作業と定義されたタスクを起動する
 */
@Service
public class WakeupTimerYoteiService {

    /** 関連者履歴出力Controller */
    @Autowired
    private YoteiFuncForceDumpHistoryService yoteiFuncForceDumpHistoryService;

    /** 関連者履歴出力差分Controller */
    @Autowired
    private YoteiFuncForceDumpHistorySabunService yoteiFuncForceDumpHistorySabunService;

    /** 関連者最小出力Controller */
    @Autowired
    private YoteiFuncForceDumpMinMasterService yoteiFuncForceDumpMinMasterService;

    /** 関連者最小出力差分Controller */
    @Autowired
    private YoteiFuncForceDumpMinMasterSabunService yoteiFuncForceDumpMinMasterSabunService;

    /** 関連者標準出力Controller */
    @Autowired
    private YoteiFuncForceDumpStdMasterService yoteiFuncForceDumpStdMasterService;

    /** 関連者標準出力差分Controller */
    @Autowired
    private YoteiFuncForceDumpStdMasterSabunService yoteiFuncForceDumpStdMasterSabunService;

    /** 関連者コード移動ダンプController */
    @Autowired
    private YoteiFuncForceDumpKanrenshaCodeMoveService yoteiFuncForceDumpKanrenshaCodeMoveService;

    /** ストレージ一時ファイル削除Controller */
    @Autowired
    private YoteiFuncDeleteStorageTempFileService yoteiFuncDeleteStorageTempFileService;

    /** APIパートナ－長期トークン期限切れ通知非同期Service */
    @Autowired
    private NotificationPartnerApiTokenLimitAsyncService notificationPartnerApiTokenLimitAsyncService;

    /** 期限切れトークン削除Controller */
    @Autowired
    private YoteiFuncDeleteLimitOverTokenService yoteiFuncDeleteLimitOverTokenService;

    /**
     * 処理を行う
     * 
     * @param timerYoteiEntity 予定実行
     * @return タスク計画登録該否
     */
    public boolean practice(final TimerYoteiEntity timerYoteiEntity, final LeastUserDto userDto) {

        try {
            switch ((short) timerYoteiEntity.getYoyakuTaskKbn()) { // NOPMD NotCast

                /* 関連者ファイルダンプ */
                case YoteiTaskConstants.DUMP_HISTORY:
                    return yoteiFuncForceDumpHistoryService
                            .practice(this.createDumpCapsuleDto(timerYoteiEntity, userDto));
                case YoteiTaskConstants.DUMP_HISTORY_SABUN:
                    return yoteiFuncForceDumpHistorySabunService
                            .practice(this.createDumpCapsuleDto(timerYoteiEntity, userDto));
                case YoteiTaskConstants.DUMP_MIN:
                    return yoteiFuncForceDumpMinMasterService
                            .practice(this.createDumpCapsuleDto(timerYoteiEntity, userDto));
                case YoteiTaskConstants.DUMP_MIN_SABUN:
                    return yoteiFuncForceDumpMinMasterSabunService
                            .practice(this.createDumpCapsuleDto(timerYoteiEntity, userDto));
                case YoteiTaskConstants.DUMP_STD:
                    return yoteiFuncForceDumpStdMasterService
                            .practice(this.createDumpCapsuleDto(timerYoteiEntity, userDto));
                case YoteiTaskConstants.DUMP_STD_SABUN:
                    return yoteiFuncForceDumpStdMasterSabunService
                            .practice(this.createDumpCapsuleDto(timerYoteiEntity, userDto));
                case YoteiTaskConstants.DUMP_CODE_MOVE:
                    return yoteiFuncForceDumpKanrenshaCodeMoveService
                            .practice(this.createDumpCapsuleDto(timerYoteiEntity, userDto));

                /* アップロード一時ファイル整理 */
                case YoteiTaskConstants.TEMP_FILE_DELETE:
                    return yoteiFuncDeleteStorageTempFileService.practice();

                /* 不要アクセストークン削除 */
                case YoteiTaskConstants.EXPIRED_TOKEN_DELETE:
                    return yoteiFuncDeleteLimitOverTokenService.practice();

                /* APIパートナートークン期限切れ通知 */
                case YoteiTaskConstants.NOTIFY_TOKEN_LIMIT:
                    LocalDateTime createDateTime = LocalDateTime.now();
                    NotifyPartnerApiLimitCapsuleDto capsuleDto = new NotifyPartnerApiLimitCapsuleDto();
                    capsuleDto.setCheckDate(createDateTime.toLocalDate());
                    notificationPartnerApiTokenLimitAsyncService.practice(createDateTime, capsuleDto);
                    return true;

                default:
                    // その他の未指定は該当なしで何もしない
                    return false;
            }
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
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
