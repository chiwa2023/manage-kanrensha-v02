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
 * wk_tbl_kanrensha_seijidantai_add_min接続用Entity
 */
@Entity
@Table(name = "wk_tbl_kanrensha_seijidantai_add_min")
public class WkTblKanrenshaSeijidantaiAddMinEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;
    
    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_kanrensha_seijidantai_add_min_id")
    private Integer wkTblKanrenshaSeijidantaiAddMinId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblKanrenshaSeijidantaiAddMinId() {
        return wkTblKanrenshaSeijidantaiAddMinId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblKanrenshaSeijidantaiAddMinId テーブルId
     */
    public void setWkTblKanrenshaSeijidantaiAddMinId(final Integer wkTblKanrenshaSeijidantaiAddMinId) {
        this.wkTblKanrenshaSeijidantaiAddMinId = wkTblKanrenshaSeijidantaiAddMinId;
    }

    /** 関連者政治団体コード */
    @Column(name = "wk_tbl_kanrensha_seijidantai_add_min_code")
    private Integer wkTblKanrenshaSeijidantaiAddMinCode = INIT_INTEGER;

    /**
     * 関連者政治団体コードを取得する
     *
     * @return 関連者政治団体コード
     */
    public Integer getWkTblKanrenshaSeijidantaiAddMinCode() {
        return wkTblKanrenshaSeijidantaiAddMinCode;
    }

    /**
     * 関連者政治団体コードを設定する
     *
     * @param wkTblKanrenshaSeijidantaiAddMinCode 関連者政治団体コード
     */
    public void setWkTblKanrenshaSeijidantaiAddMinCode(final Integer wkTblKanrenshaSeijidantaiAddMinCode) {
        this.wkTblKanrenshaSeijidantaiAddMinCode = wkTblKanrenshaSeijidantaiAddMinCode;
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

    /** 政治団体名 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 政治団体名を取得する
     *
     * @return 政治団体名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 政治団体名を設定する
     *
     * @param kanrenshaName 政治団体名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 政治団体全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_STRING;

    /**
     * 政治団体全住所を取得する
     *
     * @return 政治団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 政治団体全住所を設定する
     *
     * @param allAddress 政治団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 政治団体代表者 */
    @Column(name = "seijidantai_delegate")
    private String seijidantaiDelegate = INIT_STRING;

    /**
     * 政治団体代表者を取得する
     *
     * @return 政治団体代表者
     */
    public String getSeijidantaiDelegate() {
        return seijidantaiDelegate;
    }

    /**
     * 政治団体代表者を設定する
     *
     * @param seijidantaiDelegate 政治団体代表者
     */
    public void setSeijidantaiDelegate(final String seijidantaiDelegate) {
        this.seijidantaiDelegate = seijidantaiDelegate;
    }

    /** 政治団体区分 */
    @Column(name = "dantai_kbn")
    private String dantaiKbn = INIT_STRING;

    /**
     * 政治団体区分を取得する
     *
     * @return 政治団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 政治団体区分を設定する
     *
     * @param dantaiKbn 政治団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }

    /** 政治団体番号 */
    @Column(name = "poli_org_no")
    private String poliOrgNo = INIT_STRING;

    /**
     * 政治団体番号を取得する
     *
     * @return 政治団体番号
     */
    public String getSeijidantaiNo() {
        return poliOrgNo;
    }

    /**
     * 政治団体番号を設定する
     *
     * @param poliOrgNo 政治団体番号
     */
    public void setSeijidantaiNo(final String poliOrgNo) {
        this.poliOrgNo = poliOrgNo;
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
