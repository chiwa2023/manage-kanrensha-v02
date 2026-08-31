package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.GetAutoTaskExecuteUserUtil;

/**
 * 予約実行Service
 */
@EnableScheduling
@Service
public class TimerYoteiExecuteService {

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    /** 予定実行起動Service */
    @Autowired
    private WakeupTimerYoteiService wakeupTimerYoteiService;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** テーブル履歴設定Util */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** システム自動処理ユーザ首都l区Util */
    @Autowired
    private GetAutoTaskExecuteUserUtil getAutoTaskExecuteUserUtil;

    /**
     * 規定の時間が来たら処理を行う
     */
    @Transactional
    @Scheduled(cron = "0 0 * * * *")
    public Integer practice() {

        // 基本的には1時間1件しか動かさない想定。TODO 起動メモリ的に十分に動作することが確認出来てからリスト化する
        LocalDateTime now = LocalDateTime.now();
        Optional<TimerYoteiEntity> optional = timerYoteiRepository
                .findByNextTimestampLessThanEqualAndIsLatestTrueOrderByNextTimestampAscInsertTimestampAsc(now);

        // 特に予定がなければ離脱
        if (optional.isEmpty()) {
            return 0;
        }

        // 起動したときに作業は終わっていないが起動だけは完了していることの確認を取る
        // 起動が完了で来ているときのみ、現在の最新を履歴にして次の履歴を作成する
        try {
            LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();
            if (wakeupTimerYoteiService.practice(optional.get(), userDto)) {

                TimerYoteiEntity entitySrc = optional.get();

                // 過去データを履歴にして保存
                setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
                timerYoteiRepository.save(entitySrc);

                // 繰り返しありかつ中断していないとき
                if (entitySrc.getIsRepeat() && !entitySrc.getIsPause()) {
                    TimerYoteiEntity entityNext = this.createNextYotey(entitySrc, now);

                    // 新データを最新に
                    setTableDataHistoryUtil.practiceInsert(userDto, entityNext);
                    entityNext.setTimerYoteiId(0); // auto increment

                    return timerYoteiRepository.save(entityNext).getTimerYoteiId();
                } else {
                    // 最新かつ次回予定のない履歴を積む
                    TimerYoteiEntity entityFinish = this.createFinish(entitySrc, now);
                    setTableDataHistoryUtil.practiceInsert(userDto, entityFinish);
                    entityFinish.setTimerYoteiId(0); // auto increment

                    return timerYoteiRepository.save(entityFinish).getTimerYoteiId();
                }
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, now.getYear(), 0);
        }
        return 0;
    }

    /**
     * 次回作業実施予定を作成する
     * 
     * @param entitySrc 呼び出し予定実行
     * @param now       この処理起動時刻
     * @return 次回作業実施予定
     */
    private TimerYoteiEntity createNextYotey(final TimerYoteiEntity entitySrc, final LocalDateTime now) {

        TimerYoteiEntity entity = new TimerYoteiEntity();
        BeanUtils.copyProperties(entitySrc, entity);

        // 前回時間 ＝ 今回実施時間
        entity.setPreviousTimestamp(now);
        entity.setEndTimestamp(this.addPeriod(entitySrc.getEndTimestamp(), entity));
        entity.setSabunTimestamp(this.addPeriod(entitySrc.getSabunTimestamp(), entity));

        LocalDateTime nextTime;
        if (entity.getIsPeriod()) {
            // 実施日から算出(次回までの間隔優先、起動の差分日時、終了日時を見ないパターン)
            nextTime = this.addPeriod(now, entity);
        } else {
            // 次回指定日(常に設定した基準の日付に戻ろうとする。起動の差分日時、終了日時を見るパターン)
            nextTime = this.addPeriod(LocalDateTime.of(entity.getYearPointed(), entity.getMonthPointed(),
                    entity.getDayPointed(), entity.getHourPointed(), 0, 0), entity);
        }

        // 設定した次回時刻と一致させる
        entity.setNextTimestamp(nextTime);
        entity.setYearPointed(nextTime.getYear());
        entity.setMonthPointed(nextTime.getMonthValue());
        entity.setDayPointed(nextTime.getDayOfMonth());
        entity.setHourPointed(nextTime.getHour());

        // コードを取得する
        Integer code = 1;
        Optional<TimerYoteiEntity> optionalCode = timerYoteiRepository.findFirstByOrderByTimerYoteiCodeDesc();
        if (!optionalCode.isEmpty()) { //
            code += optionalCode.get().getTimerYoteiCode();
        }
        entity.setTimerYoteiCode(code);

        return entity;
    }

    /**
     * 作業完了最新を作成する
     * 
     * @param entitySrc 呼び出し予定実行
     * @param now       この処理起動時刻
     * @return 作業完了最新
     */
    private TimerYoteiEntity createFinish(final TimerYoteiEntity entitySrc, final LocalDateTime now) {

        TimerYoteiEntity entity = new TimerYoteiEntity();
        BeanUtils.copyProperties(entitySrc, entity);
        entity.setPreviousTimestamp(now);
        entity.setNextTimestamp(null); // 次回実行しないためにnullでなければならない
        entity.setYearPointed(null);
        entity.setMonthPointed(null);
        entity.setDayPointed(null);
        entity.setHourPointed(null);
        entity.setSabunTimestamp(entity.getEndTimestamp());
        entity.setEndTimestamp(null);// 起動条件は次回起動日が決まる必要がある

        return entity;
    }

    private LocalDateTime addPeriod(final LocalDateTime src, final TimerYoteiEntity entity) {

        return src.plusYears(entity.getYearPeriod()).plusMonths(entity.getMonthPeriod()) //
                .plusDays(entity.getDayPeriod()).plusHours(entity.getHourPeriod()).withMinute(0).withSecond(0);
    }
}
