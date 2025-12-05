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
 * kanrensha_kigyou_dt_property接続用Entity
 */
@Entity
@Table(name = "kanrensha_kigyou_dt_property")
public class KanrenshaKigyouDtPropertyEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_kigyou_dt_property_id")
    private Integer kanrenshaKigyouDtPropertyId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaKigyouDtPropertyId() {
        return kanrenshaKigyouDtPropertyId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaKigyouDtPropertyId テーブルId
     */
    public void setKanrenshaKigyouDtPropertyId(final Integer kanrenshaKigyouDtPropertyId) {
        this.kanrenshaKigyouDtPropertyId = kanrenshaKigyouDtPropertyId;
    }

    /** 関連者企業・団体Id */
    @Column(name = "kanrensha_kigyou_dt_id")
    private Integer kanrenshaKigyouDtId = INIT_INTEGER;

    /**
     * 関連者企業・団体Idを取得する
     *
     * @return 関連者企業・団体Id
     */
    public Integer getKanrenshaKigyouDtId() {
        return kanrenshaKigyouDtId;
    }

    /**
     * 関連者企業・団体Idを設定する
     *
     * @param kanrenshaKigyouDtId 関連者企業・団体Id
     */
    public void setKanrenshaKigyouDtId(final Integer kanrenshaKigyouDtId) {
        this.kanrenshaKigyouDtId = kanrenshaKigyouDtId;
    }

    /** 関連者企業・団体コード */
    @Column(name = "kigyou_dt_kanrensha_code")
    private String kigyouDtKanrenshaCode = INIT_STRING;

    /**
     * 関連者企業・団体コードを取得する
     *
     * @return 関連者企業・団体コード
     */
    public String getKigyouDtKanrenshaCode() {
        return kigyouDtKanrenshaCode;
    }

    /**
     * 関連者企業・団体コードを設定する
     *
     * @param kigyouDtKanrenshaCode 関連者企業・団体コード
     */
    public void setKigyouDtKanrenshaCode(final String kigyouDtKanrenshaCode) {
        this.kigyouDtKanrenshaCode = kigyouDtKanrenshaCode;
    }

    /** 関連者企業・団体名称 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 関連者企業・団体名称を取得する
     *
     * @return 関連者企業・団体名称
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 関連者企業・団体名称を設定する
     *
     * @param kanrenshaName 関連者企業・団体名称
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
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

    /** 法人種別 */
    @Column(name = "houjin_sbts")
    private String houjinSbts = INIT_STRING;

    /**
     * 法人種別を取得する
     *
     * @return 法人種別
     */
    public String getHoujinSbts() {
        return houjinSbts;
    }

    /**
     * 法人種別を設定する
     *
     * @param houjinSbts 法人種別
     */
    public void setHoujinSbts(final String houjinSbts) {
        this.houjinSbts = houjinSbts;
    }

    /** 外国籍該否 */
    @Column(name = "is_foreign")
    private Boolean isForeign = INIT_BOOLEAN;

    /**
     * 外国籍該否を取得する
     *
     * @return 外国籍該否
     */
    public Boolean getIsForeign() {
        return isForeign;
    }

    /**
     * 外国籍該否を設定する
     *
     * @param isForeign 外国籍該否
     */
    public void setIsForeign(final Boolean isForeign) {
        this.isForeign = isForeign;
    }

    /** 関連者団体名称かな */
    @Column(name = "org_name_kana")
    private String orgNameKana = INIT_STRING;

    /**
     * 関連者団体名称かなを取得する
     *
     * @return 関連者団体名称かな
     */
    public String getOrgNameKana() {
        return orgNameKana;
    }

    /**
     * 関連者団体名称かなを設定する
     *
     * @param orgNameKana 関連者団体名称かな
     */
    public void setOrgNameKana(final String orgNameKana) {
        this.orgNameKana = orgNameKana;
    }

    /** 支店該当 */
    @Column(name = "is_shiten")
    private Boolean isShiten = INIT_BOOLEAN;

    /**
     * 支店該当を取得する
     *
     * @return 支店該当
     */
    public Boolean getIsShiten() {
        return isShiten;
    }

    /**
     * 支店該当を設定する
     *
     * @param isShiten 支店該当
     */
    public void setIsShiten(final Boolean isShiten) {
        this.isShiten = isShiten;
    }

    /** 団体代表者関連者コード */
    @Column(name = "org_delegate_code")
    private String orgDelegateCode = INIT_STRING;

    /**
     * 団体代表者関連者コードを取得する
     *
     * @return 団体代表者関連者コード
     */
    public String getOrgDelegateCode() {
        return orgDelegateCode;
    }

    /**
     * 団体代表者関連者コードを設定する
     *
     * @param orgDelegateCode 団体代表者関連者コード
     */
    public void setOrgDelegateCode(final String orgDelegateCode) {
        this.orgDelegateCode = orgDelegateCode;
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
