package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;



/**
 * timer_yotei接続用Entity
 */
@Entity
@Table(name = "timer_yotei")
public class TimerYoteiEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timer_yotei_id")
    private Integer timerYoteiId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getTimerYoteiId() {
        return timerYoteiId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param timerYoteiId テーブルId
     */
    public void setTimerYoteiId(final Integer timerYoteiId) {
        this.timerYoteiId = timerYoteiId;
    }

    /** 予約実行コード */
    @Column(name = "timer_yotei_code")
    private Integer timerYoteiCode = INIT_INTEGER;

    /**
     * 予約実行コードを取得する
     *
     * @return 予約実行コード
     */
    public Integer getTimerYoteiCode() {
        return timerYoteiCode;
    }

    /**
     * 予約実行コードを設定する
     *
     * @param timerYoteiCode 予約実行コード
     */
    public void setTimerYoteiCode(final Integer timerYoteiCode) {
        this.timerYoteiCode = timerYoteiCode;
    }

    /** 予約実行名称 */
    @Column(name = "timer_yotei_name")
    private String timerYoteiName = INIT_STRING;

    /**
     * 予約実行名称を取得する
     *
     * @return 予約実行名称
     */
    public String getTimerYoteiName() {
        return timerYoteiName;
    }

    /**
     * 予約実行名称を設定する
     *
     * @param timerYoteiName 予約実行名称
     */
    public void setTimerYoteiName(final String timerYoteiName) {
        this.timerYoteiName = timerYoteiName;
    }

    /** 最新該否 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_BOOLEAN;

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    @Override
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    @Override
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 予約作業区分 */
    @Column(name = "yoyaku_task_kbn")
    private Short yoyakuTaskKbn = INIT_SHORT;

    /**
     * 予約作業区分を取得する
     *
     * @return 予約作業区分
     */
    public Short getYoyakuTaskKbn() {
        return yoyakuTaskKbn;
    }

    /**
     * 予約作業区分を設定する
     *
     * @param yoyakuTaskKbn 予約作業区分
     */
    public void setYoyakuTaskKbn(final Short yoyakuTaskKbn) {
        this.yoyakuTaskKbn = yoyakuTaskKbn;
    }

    /** 次回実行日時 */
    @Column(name = "next_timestamp")
    private LocalDateTime nextTimestamp = INIT_TIMESTAMP;

    /**
     * 次回実行日時を取得する
     *
     * @return 次回実行日時
     */
    public LocalDateTime getNextTimestamp() {
        return nextTimestamp;
    }

    /**
     * 次回実行日時を設定する
     *
     * @param nextTimestamp 次回実行日時
     */
    public void setNextTimestamp(final LocalDateTime nextTimestamp) {
        this.nextTimestamp = nextTimestamp;
    }

    /** 前回実行日時 */
    @Column(name = "previous_timestamp")
    private LocalDateTime previousTimestamp = INIT_TIMESTAMP;

    /**
     * 前回実行日時を取得する
     *
     * @return 前回実行日時
     */
    public LocalDateTime getPreviousTimestamp() {
        return previousTimestamp;
    }

    /**
     * 前回実行日時を設定する
     *
     * @param previousTimestamp 前回実行日時
     */
    public void setPreviousTimestamp(final LocalDateTime previousTimestamp) {
        this.previousTimestamp = previousTimestamp;
    }

    /** 繰り返し有無 */
    @Column(name = "is_repeat")
    private Boolean isRepeat = INIT_BOOLEAN;

    /**
     * 繰り返し有無を取得する
     *
     * @return 繰り返し有無
     */
    public Boolean getIsRepeat() {
        return isRepeat;
    }

    /**
     * 繰り返し有無を設定する
     *
     * @param isRepeat 繰り返し有無
     */
    public void setIsRepeat(final Boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    /** 繰り返し中断 */
    @Column(name = "is_pause")
    private Boolean isPause = INIT_BOOLEAN;

    /**
     * 繰り返し中断を取得する
     *
     * @return 繰り返し中断
     */
    public Boolean getIsPause() {
        return isPause;
    }

    /**
     * 繰り返し中断を設定する
     *
     * @param isPause 繰り返し中断
     */
    public void setIsPause(final Boolean isPause) {
        this.isPause = isPause;
    }

    /** タスク条件終了日時 */
    @Column(name = "end_timestamp")
    private LocalDateTime endTimestamp = INIT_TIMESTAMP;

    /**
     * タスク条件終了日時を取得する
     *
     * @return タスク条件終了日時
     */
    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }

    /**
     * タスク条件終了日時を設定する
     *
     * @param endTimestamp タスク条件終了日時
     */
    public void setEndTimestamp(final LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    /** タスク条件差分日時 */
    @Column(name = "sabun_timestamp")
    private LocalDateTime sabunTimestamp = INIT_TIMESTAMP;

    /**
     * タスク条件差分日時を取得する
     *
     * @return タスク条件差分日時
     */
    public LocalDateTime getSabunTimestamp() {
        return sabunTimestamp;
    }

    /**
     * タスク条件差分日時を設定する
     *
     * @param sabunTimestamp タスク条件差分日時
     */
    public void setSabunTimestamp(final LocalDateTime sabunTimestamp) {
        this.sabunTimestamp = sabunTimestamp;
    }

    /** 間隔指定該否 */
    @Column(name = "is_period")
    private Boolean isPeriod = INIT_BOOLEAN;

    /**
     * 間隔指定該否を取得する
     *
     * @return 間隔指定該否
     */
    public Boolean getIsPeriod() {
        return isPeriod;
    }

    /**
     * 間隔指定該否を設定する
     *
     * @param isPeriod 間隔指定該否
     */
    public void setIsPeriod(final Boolean isPeriod) {
        this.isPeriod = isPeriod;
    }

    /** 間隔指定年条件 */
    @Column(name = "year_period")
    private Integer yearPeriod = INIT_INTEGER;

    /**
     * 間隔指定年条件を取得する
     *
     * @return 間隔指定年条件
     */
    public Integer getYearPeriod() {
        return yearPeriod;
    }

    /**
     * 間隔指定年条件を設定する
     *
     * @param yearPeriod 間隔指定年条件
     */
    public void setYearPeriod(final Integer yearPeriod) {
        this.yearPeriod = yearPeriod;
    }

    /** 間隔指定年条件 */
    @Column(name = "month_period")
    private Integer monthPeriod = INIT_INTEGER;

    /**
     * 間隔指定年条件を取得する
     *
     * @return 間隔指定年条件
     */
    public Integer getMonthPeriod() {
        return monthPeriod;
    }

    /**
     * 間隔指定年条件を設定する
     *
     * @param monthPeriod 間隔指定年条件
     */
    public void setMonthPeriod(final Integer monthPeriod) {
        this.monthPeriod = monthPeriod;
    }

    /** 間隔指定年条件 */
    @Column(name = "day_period")
    private Integer dayPeriod = INIT_INTEGER;

    /**
     * 間隔指定年条件を取得する
     *
     * @return 間隔指定年条件
     */
    public Integer getDayPeriod() {
        return dayPeriod;
    }

    /**
     * 間隔指定年条件を設定する
     *
     * @param dayPeriod 間隔指定年条件
     */
    public void setDayPeriod(final Integer dayPeriod) {
        this.dayPeriod = dayPeriod;
    }

    /** 間隔指定年条件 */
    @Column(name = "hour_period")
    private Integer hourPeriod = INIT_INTEGER;

    /**
     * 間隔指定年条件を取得する
     *
     * @return 間隔指定年条件
     */
    public Integer getHourPeriod() {
        return hourPeriod;
    }

    /**
     * 間隔指定年条件を設定する
     *
     * @param hourPeriod 間隔指定年条件
     */
    public void setHourPeriod(final Integer hourPeriod) {
        this.hourPeriod = hourPeriod;
    }

    /** 直接指定年条件 */
    @Column(name = "year_pointed")
    private Integer yearPointed = INIT_INTEGER;

    /**
     * 直接指定年条件を取得する
     *
     * @return 直接指定年条件
     */
    public Integer getYearPointed() {
        return yearPointed;
    }

    /**
     * 直接指定年条件を設定する
     *
     * @param yearPointed 直接指定年条件
     */
    public void setYearPointed(final Integer yearPointed) {
        this.yearPointed = yearPointed;
    }

    /** 直接指定年条件 */
    @Column(name = "month_pointed")
    private Integer monthPointed = INIT_INTEGER;

    /**
     * 直接指定年条件を取得する
     *
     * @return 直接指定年条件
     */
    public Integer getMonthPointed() {
        return monthPointed;
    }

    /**
     * 直接指定年条件を設定する
     *
     * @param monthPointed 直接指定年条件
     */
    public void setMonthPointed(final Integer monthPointed) {
        this.monthPointed = monthPointed;
    }

    /** 直接指定年条件 */
    @Column(name = "day_pointed")
    private Integer dayPointed = INIT_INTEGER;

    /**
     * 直接指定年条件を取得する
     *
     * @return 直接指定年条件
     */
    public Integer getDayPointed() {
        return dayPointed;
    }

    /**
     * 直接指定年条件を設定する
     *
     * @param dayPointed 直接指定年条件
     */
    public void setDayPointed(final Integer dayPointed) {
        this.dayPointed = dayPointed;
    }

    /** 直接指定年条件 */
    @Column(name = "hour_pointed")
    private Integer hourPointed = INIT_INTEGER;

    /**
     * 直接指定年条件を取得する
     *
     * @return 直接指定年条件
     */
    public Integer getHourPointed() {
        return hourPointed;
    }

    /**
     * 直接指定年条件を設定する
     *
     * @param hourPointed 直接指定年条件
     */
    public void setHourPointed(final Integer hourPointed) {
        this.hourPointed = hourPointed;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_INTEGER;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    @Override
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    @Override
    public void setInsertUserId(final Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    /** 挿入ユーザコード */
    @Column(name = "insert_user_code")
    private Integer insertUserCode = INIT_INTEGER;

    /**
     * 挿入ユーザコードを取得する
     *
     * @return 挿入ユーザコード
     */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** 挿入ユーザ名称 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_STRING;

    /**
     * 挿入ユーザ名称を取得する
     *
     * @return 挿入ユーザ名称
     */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }

    /** 挿入日時 */
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp = INIT_TIMESTAMP;

    /**
     * 挿入日時を取得する
     *
     * @return 挿入日時
     */
    @Override
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
    @Override
    public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** 無効ユーザId */
    @Column(name = "delete_user_id")
    private Integer deleteUserId = INIT_INTEGER;

    /**
     * 無効ユーザIdを取得する
     *
     * @return 無効ユーザId
     */
    @Override
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
    @Override
    public void setDeleteUserId(final Integer deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    /** 無効ユーザコード */
    @Column(name = "delete_user_code")
    private Integer deleteUserCode = INIT_INTEGER;

    /**
     * 無効ユーザコードを取得する
     *
     * @return 無効ユーザコード
     */
    @Override
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
    @Override
    public void setDeleteUserCode(final Integer deleteUserCode) {
        this.deleteUserCode = deleteUserCode;
    }

    /** 無効ユーザ名称 */
    @Column(name = "delete_user_name")
    private String deleteUserName = INIT_STRING;

    /**
     * 無効ユーザ名称を取得する
     *
     * @return 無効ユーザ名称
     */
    @Override
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
    @Override
    public void setDeleteUserName(final String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    /** 無効日時 */
    @Column(name = "delete_timestamp")
    private LocalDateTime deleteTimestamp = INIT_TIMESTAMP;

    /**
     * 無効日時を取得する
     *
     * @return 無効日時
     */
    @Override
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    @Override
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

}
