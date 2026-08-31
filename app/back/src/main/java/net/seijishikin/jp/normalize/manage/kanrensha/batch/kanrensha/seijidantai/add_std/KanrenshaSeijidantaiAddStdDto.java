package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * 関連者企業・団体履歴Dto
 */
public class KanrenshaSeijidantaiAddStdDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 政治団体名 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_String;

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
    private String allAddress = INIT_String;

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
    private String seijidantaiDelegate = INIT_String;

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
    private String dantaiKbn = INIT_String;

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

    /** 住所郵便番号 */
    @Column(name = "address_postal")
    private String addressPostal = INIT_String;

    /**
     * 住所郵便番号を取得する
     *
     * @return 住所郵便番号
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所郵便番号を設定する
     *
     * @param addressPostal 住所郵便番号
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /** 住所番地 */
    @Column(name = "address_block")
    private String addressBlock = INIT_String;

    /**
     * 住所番地を取得する
     *
     * @return 住所番地
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地を設定する
     *
     * @param addressBlock 住所番地
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /** 住所建物 */
    @Column(name = "address_building")
    private String addressBuilding = INIT_String;

    /**
     * 住所建物を取得する
     *
     * @return 住所建物
     */
    public String getAddressBuilding() {
        return addressBuilding;
    }

    /**
     * 住所建物を設定する
     *
     * @param addressBuilding 住所建物
     */
    public void setAddressBuilding(final String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    /** 政治団体番号 */
    @Column(name = "poli_org_no")
    private String poliOrgNo = INIT_String;

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

    /** 住所郵便番号 */
    @Column(name = "postalcode1")
    private String postalcode1 = INIT_String;

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostalcode1() {
        return postalcode1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postalcode1 郵便番号1
     */
    public void setPostalcode1(final String postalcode1) {
        this.postalcode1 = postalcode1;
    }

    /** 郵便番号2 */
    @Column(name = "postalcode2")
    private String postalcode2 = INIT_String;

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostalcode2() {
        return postalcode2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postalcode2 郵便番号2
     */
    public void setPostalcode2(final String postalcode2) {
        this.postalcode2 = postalcode2;
    }

    /** 地番コード */
    @Column(name = "prc_id")
    private String prcId = INIT_String;

    /**
     * 地番コードを取得する
     *
     * @return 地番コード
     */
    public String getPrcId() {
        return prcId;
    }

    /**
     * 地番コードを設定する
     *
     * @param prcId 地番コード
     */
    public void setPrcId(final String prcId) {
        this.prcId = prcId;
    }

    /** 地方自治体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

    /**
     * 地方自治体コードを取得する
     *
     * @return 地方自治体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方自治体コードを設定する
     *
     * @param lgCode 地方自治体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /** 町字コード */
    @Column(name = "machiaza_id")
    private String machiazaId = INIT_String;

    /**
     * 町字コードを取得する
     *
     * @return 町字コード
     */
    public String getMachiazaId() {
        return machiazaId;
    }

    /**
     * 町字コードを設定する
     *
     * @param machiazaId 町字コード
     */
    public void setMachiazaId(final String machiazaId) {
        this.machiazaId = machiazaId;
    }

    /** 街区コード */
    @Column(name = "blk_id")
    private String blkId = INIT_String;

    /**
     * 街区コードを取得する
     *
     * @return 街区コード
     */
    public String getBlkId() {
        return blkId;
    }

    /**
     * 街区コードを設定する
     *
     * @param blkId 街区コード
     */
    public void setBlkId(final String blkId) {
        this.blkId = blkId;
    }

    /** 住居コード */
    @Column(name = "rsdt_id")
    private String rsdtId = INIT_String;

    /**
     * 住居コードを取得する
     *
     * @return 住居コード
     */
    public String getRsdtId() {
        return rsdtId;
    }

    /**
     * 住居コードを設定する
     *
     * @param rsdtId 住居コード
     */
    public void setRsdtId(final String rsdtId) {
        this.rsdtId = rsdtId;
    }

    /** 住居2コード */
    @Column(name = "rsdt2_id")
    private String rsdt2Id = INIT_String;

    /**
     * 住居2コードを取得する
     *
     * @return 住居2コード
     */
    public String getRsdt2Id() {
        return rsdt2Id;
    }

    /**
     * 住居2コードを設定する
     *
     * @param rsdt2Id 住居2コード
     */
    public void setRsdt2Id(final String rsdt2Id) {
        this.rsdt2Id = rsdt2Id;
    }

    /** 電話番号1 */
    @Column(name = "phon1")
    private String phon1 = INIT_String;

    /**
     * 電話番号1を取得する
     *
     * @return 電話番号1
     */
    public String getPhon1() {
        return phon1;
    }

    /**
     * 電話番号1を設定する
     *
     * @param phon1 電話番号1
     */
    public void setPhon1(final String phon1) {
        this.phon1 = phon1;
    }

    /** 電話番号2 */
    @Column(name = "phon2")
    private String phon2 = INIT_String;

    /**
     * 電話番号2を取得する
     *
     * @return 電話番号2
     */
    public String getPhon2() {
        return phon2;
    }

    /**
     * 電話番号2を設定する
     *
     * @param phon2 電話番号2
     */
    public void setPhon2(final String phon2) {
        this.phon2 = phon2;
    }

    /** 電話番号3 */
    @Column(name = "phon3")
    private String phon3 = INIT_String;

    /**
     * 電話番号3を取得する
     *
     * @return 電話番号3
     */
    public String getPhon3() {
        return phon3;
    }

    /**
     * 電話番号3を設定する
     *
     * @param phon3 電話番号3
     */
    public void setPhon3(final String phon3) {
        this.phon3 = phon3;
    }

    /** 電子メール */
    @Column(name = "email")
    private String email = INIT_String;

    /**
     * 電子メールを取得する
     *
     * @return 電子メール
     */
    public String getEmail() {
        return email;
    }

    /**
     * 電子メールを設定する
     *
     * @param email 電子メール
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**  */
    @Column(name = "my_portal_url")
    private String myPortalUrl = INIT_String;

    /**
     * 所有(代表)サイトurlを取得する
     *
     * @return 所有(代表)サイトurl
     */
    public String getMyPortalUrl() {
        return myPortalUrl;
    }

    /**
     * 所有(代表)サイトurlを設定する
     *
     * @param myPortalUrl 所有(代表)サイトurl
     */
    public void setMyPortalUrl(final String myPortalUrl) {
        this.myPortalUrl = myPortalUrl;
    }

    /** SNSサービス名称 */
    @Column(name = "sns_service_name")
    private String snsServiceName = INIT_String;

    /**
     * SNSサービス名称を取得する
     *
     * @return SNSサービス名称
     */
    public String getSnsServiceName() {
        return snsServiceName;
    }

    /**
     * SNSサービス名称を設定する
     *
     * @param snsServiceName SNSサービス名称
     */
    public void setSnsServiceName(final String snsServiceName) {
        this.snsServiceName = snsServiceName;
    }

    /** SNSサービスアカウント */
    @Column(name = "sns_account")
    private String snsAccount = INIT_String;

    /**
     * SNSサービスアカウントを取得する
     *
     * @return SNSサービスアカウント
     */
    public String getSnsAccount() {
        return snsAccount;
    }

    /**
     * SNSサービスアカウントを設定する
     *
     * @param snsAccount SNSサービスアカウント
     */
    public void setSnsAccount(final String snsAccount) {
        this.snsAccount = snsAccount;
    }

    /** 関連者団体名称かな */
    @Column(name = "org_name_kana")
    private String orgNameKana = INIT_String;

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
    private String orgDelegateCode = INIT_String;

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
    private String accountMgrCode = INIT_String;

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
    private String accountMgrName = INIT_String;

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

}
