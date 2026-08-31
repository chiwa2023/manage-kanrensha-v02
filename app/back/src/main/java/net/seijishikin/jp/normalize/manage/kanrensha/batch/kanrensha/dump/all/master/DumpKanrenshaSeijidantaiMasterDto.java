package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * master_political_organization接続用Entity
 */
public class DumpKanrenshaSeijidantaiMasterDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public DumpKanrenshaSeijidantaiMasterDto() { // NOPMD TODO 安定した段階でフィールドを引数とするコンストラクタを作成

    }

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_seijidantai_master_id")
    private Integer kanrenshaSeijidantaiMasterId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaSeijidantaiMasterId() {
        return kanrenshaSeijidantaiMasterId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaSeijidantaiMasterId テーブルId
     */
    public void setKanrenshaSeijidantaiMasterId(final Integer kanrenshaSeijidantaiMasterId) {
        this.kanrenshaSeijidantaiMasterId = kanrenshaSeijidantaiMasterId;
    }

    /** 政治団体関連者コード */
    @Column(name = "seijidantai_kanrensha_code")
    private String seijidantaiKanrenshaCode = INIT_STRING;

    /**
     * 政治団体関連者コードを取得する
     *
     * @return 政治団体関連者コード
     */
    public String getSeijidantaiKanrenshaCode() {
        return seijidantaiKanrenshaCode;
    }

    /**
     * 政治団体関連者コードを設定する
     *
     * @param seijidantaiKanrenshaCode 政治団体関連者コード
     */
    public void setSeijidantaiKanrenshaCode(final String seijidantaiKanrenshaCode) {
        this.seijidantaiKanrenshaCode = seijidantaiKanrenshaCode;
    }

    /** 政治団体番号 */
    @Column(name = "poli_org_no")
    private String poliOrgNo = INIT_STRING;

    /**
     * 政治団体番号を取得する
     *
     * @return 政治団体番号
     */
    public String getPoliOrgNo() {
        return poliOrgNo;
    }

    /**
     * 政治団体番号を設定する
     *
     * @param poliOrgNo 政治団体番号
     */
    public void setPoliOrgNo(final String poliOrgNo) {
        this.poliOrgNo = poliOrgNo;
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
    @Column(name = "seijidantai_delegate")
    private String seijidantaiDelegate = INIT_STRING;

    /**
     * 企業・団体代表者を取得する
     *
     * @return 企業・団体代表者
     */
    public String getSeijidantaiDelegate() {
        return seijidantaiDelegate;
    }

    /**
     * 企業・団体代表者を設定する
     *
     * @param seijidantaiDelegate 企業・団体代表者
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

    /** 政治団体区分名称 */
    private String dantaiKbnLabel = INIT_STRING;

    /**
     * 政治団体区分名称を取得する
     *
     * @return 政治団体区分名称
     */
    public String getDantaiKbnLabel() {
        return dantaiKbnLabel;
    }

    /**
     * 政治団体区分名称を設定する
     *
     * @param dantaiKbnLabel 政治団体区分名称
     */
    public void setDantaiKbnLabel(final String dantaiKbnLabel) {
        this.dantaiKbnLabel = dantaiKbnLabel;
    }

    /** 電話番号 */
    @Column(name = "phon")
    private String phon = INIT_STRING;

    /**
     * 電話番号を取得する
     *
     * @return 電話番号
     */
    public String getPhon() {
        return phon;
    }

    /**
     * 電話番号を設定する
     *
     * @param phon 電話番号
     */
    public void setPhon(final String phon) {
        this.phon = phon;
    }

    /** 代表(公式)サイトurl */
    @Column(name = "my_portal_url")
    private String myPortalUrl = INIT_STRING;

    /**
     * 代表(公式)サイトurlを取得する
     *
     * @return 代表(公式)サイトurl
     */
    public String getMyPortalUrl() {
        return myPortalUrl;
    }

    /**
     * 代表(公式)サイトurlを設定する
     *
     * @param myPortalUrl 代表(公式)サイトurl
     */
    public void setMyPortalUrl(final String myPortalUrl) {
        this.myPortalUrl = myPortalUrl;
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

    /** 会計責任者関連者個人コード */
    @Column(name = "account_mgr_code")
    private String accountMgrCode = INIT_STRING;

    /**
     * 会計責任者関連者個人コードを取得する
     *
     * @return 会計責任者関連者個人コード
     */
    public String getAccountMgrCode() {
        return accountMgrCode;
    }

    /**
     * 会計責任者関連者個人コードを設定する
     *
     * @param accountMgrCode 会計責任者関連者個人コード
     */
    public void setAccountMgrCode(final String accountMgrCode) {
        this.accountMgrCode = accountMgrCode;
    }

    /** 会計責任者関連者個人氏名 */
    @Column(name = "account_mgr_name")
    private String accountMgrName = INIT_STRING;

    /**
     * 会計責任者関連者個人氏名を取得する
     *
     * @return 会計責任者関連者個人氏名
     */
    public String getAccountMgrName() {
        return accountMgrName;
    }

    /**
     * 会計責任者関連者個人氏名を設定する
     *
     * @param accountMgrName 会計責任者関連者個人氏名
     */
    public void setAccountMgrName(final String accountMgrName) {
        this.accountMgrName = accountMgrName;
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

}
