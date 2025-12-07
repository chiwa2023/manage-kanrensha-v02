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
 * wk_tbl_kanrensha_kigyou_dt_add_min接続用Entity
 */
@Entity
@Table(name = "wk_tbl_kanrensha_kigyou_dt_add_min")
public class WkTblKanrenshaKigyouDtAddMinEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_kanrensha_kigyou_dt_add_min_id")
    private Integer wkTblKanrenshaKigyouDtAddMinId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblKanrenshaKigyouDtAddMinId() {
        return wkTblKanrenshaKigyouDtAddMinId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblKanrenshaKigyouDtAddMinId テーブルId
     */
    public void setWkTblKanrenshaKigyouDtAddMinId(final Integer wkTblKanrenshaKigyouDtAddMinId) {
        this.wkTblKanrenshaKigyouDtAddMinId = wkTblKanrenshaKigyouDtAddMinId;
    }

    /** 関連者企業・団体コード */
    @Column(name = "wk_tbl_kanrensha_kigyou_dt_add_min_code")
    private Integer wkTblKanrenshaKigyouDtAddMinCode = INIT_INTEGER;

    /**
     * 関連者企業・団体コードを取得する
     *
     * @return 関連者企業・団体コード
     */
    public Integer getWkTblKanrenshaKigyouDtAddMinCode() {
        return wkTblKanrenshaKigyouDtAddMinCode;
    }

    /**
     * 関連者企業・団体コードを設定する
     *
     * @param wkTblKanrenshaKigyouDtAddMinCode 関連者企業・団体コード
     */
    public void setWkTblKanrenshaKigyouDtAddMinCode(final Integer wkTblKanrenshaKigyouDtAddMinCode) {
        this.wkTblKanrenshaKigyouDtAddMinCode = wkTblKanrenshaKigyouDtAddMinCode;
    }

    /** 最新該否 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_BOOLEAN;

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 処理完了該否 */
    @Column(name = "is_finish")
    private Boolean isFinish = INIT_BOOLEAN;

    /**
     * 処理完了該否を取得する
     *
     * @return 処理完了該否
     */
    public Boolean getIsFinish() {
        return isFinish;
    }

    /**
     * 処理完了該否を設定する
     *
     * @param isFinish 処理完了該否
     */
    public void setIsFinish(final Boolean isFinish) {
        this.isFinish = isFinish;
    }

    /** 企業・団体名 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 企業・団体名を取得する
     *
     * @return 企業・団体名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 企業・団体名を設定する
     *
     * @param kanrenshaName 企業・団体名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 企業・団体全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_STRING;

    /**
     * 企業・団体全住所を取得する
     *
     * @return 企業・団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 企業・団体全住所を設定する
     *
     * @param allAddress 企業・団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 企業・団体代表者 */
    @Column(name = "kigyou_dt_delegate")
    private String kigyouDtDelegate = INIT_STRING;

    /**
     * 企業・団体代表者を取得する
     *
     * @return 企業・団体代表者
     */
    public String getKigyouDtDelegate() {
        return kigyouDtDelegate;
    }

    /**
     * 企業・団体代表者を設定する
     *
     * @param kigyouDtDelegate 企業・団体代表者
     */
    public void setKigyouDtDelegate(final String kigyouDtDelegate) {
        this.kigyouDtDelegate = kigyouDtDelegate;
    }

    /** 法人番号 */
    @Column(name = "houjin_no")
    private String houjinNo = INIT_STRING;

    /**
     * 法人番号を取得する
     *
     * @return 法人番号
     */
    public String getHoujinNo() {
        return houjinNo;
    }

    /**
     * 法人番号を設定する
     *
     * @param houjinNo 法人番号
     */
    public void setHoujinNo(final String houjinNo) {
        this.houjinNo = houjinNo;
    }

    /** 反映有無 */
    @Column(name = "is_affected")
    private Boolean isAffected = INIT_BOOLEAN;

    /**
     * 反映有無を取得する
     *
     * @return 反映有無
     */
    public Boolean getIsAffected() {
        return isAffected;
    }

    /**
     * 反映有無を設定する
     *
     * @param isAffected 反映有無
     */
    public void setIsAffected(final Boolean isAffected) {
        this.isAffected = isAffected;
    }

    /** 判定理由 */
    @Column(name = "judge_reason")
    private String judgeReason = INIT_STRING;

    /**
     * 判定理由を取得する
     *
     * @return 判定理由
     */
    public String getJudgeReason() {
        return judgeReason;
    }

    /**
     * 判定理由を設定する
     *
     * @param judgeReason 判定理由
     */
    public void setJudgeReason(final String judgeReason) {
        this.judgeReason = judgeReason;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_INTEGER;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
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
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
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
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
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
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
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
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
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
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
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
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
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
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

}
