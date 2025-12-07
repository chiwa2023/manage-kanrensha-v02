package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * 関連者個人履歴Dto
 */
public class KanrenshaPersonAddStdDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 個人名 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_String;

    /**
     * 個人名を取得する
     *
     * @return 個人名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 個人名を設定する
     *
     * @param kanrenshaName 個人名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 個人全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_String;

    /**
     * 個人全住所を取得する
     *
     * @return 個人全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 個人全住所を設定する
     *
     * @param allAddress 個人全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 個人職業 */
    @Column(name = "person_shokugyou")
    private String personShokugyou = INIT_String;

    /**
     * 個人職業を取得する
     *
     * @return 個人職業
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

    /**
     * 個人職業を設定する
     *
     * @param personShokugyou 個人職業
     */
    public void setPersonShokugyou(final String personShokugyou) {
        this.personShokugyou = personShokugyou;
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


    /** 郵便番号1 */
    @Column(name = "postal1")
    private String postal1 = INIT_String;

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostal1() {
        return postal1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postal1 郵便番号1
     */
    public void setPostal1(final String postal1) {
        this.postal1 = postal1;
    }

    /** 郵便番号2 */
    @Column(name = "postal2")
    private String postal2 = INIT_String;

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostal2() {
        return postal2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postal2 郵便番号2
     */
    public void setPostal2(final String postal2) {
        this.postal2 = postal2;
    }


    /** 姓名の姓 */
    @Column(name = "last_name")
    private String lastName = INIT_String;

    /**
     * 姓名の姓を取得する
     *
     * @return 姓名の姓
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 姓名の姓を設定する
     *
     * @param lastName 姓名の姓
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /** 姓名の名 */
    @Column(name = "first_name")
    private String firstName = INIT_String;

    /**
     * 姓名の名を取得する
     *
     * @return 姓名の名
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 姓名の名を設定する
     *
     * @param firstName 姓名の名
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /** 姓名のミドルネーム */
    @Column(name = "middle_name")
    private String middleName = INIT_String;

    /**
     * 姓名のミドルネームを取得する
     *
     * @return 姓名のミドルネーム
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 姓名のミドルネームを設定する
     *
     * @param middleName 姓名のミドルネーム
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /** 姓名の姓かな */
    @Column(name = "last_name_kana")
    private String lastNameKana = INIT_String;

    /**
     * 姓名の姓かなを取得する
     *
     * @return 姓名の姓かな
     */
    public String getLastNameKana() {
        return lastNameKana;
    }

    /**
     * 姓名の姓かなを設定する
     *
     * @param lastNameKana 姓名の姓かな
     */
    public void setLastNameKana(final String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    /** 姓名の名かな */
    @Column(name = "first_name_kana")
    private String firstNameKana = INIT_String;

    /**
     * 姓名の名かなを取得する
     *
     * @return 姓名の名かな
     */
    public String getFirstNameKana() {
        return firstNameKana;
    }

    /**
     * 姓名の名かなを設定する
     *
     * @param firstNameKana 姓名の名かな
     */
    public void setFirstNameKana(final String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

    /** 姓名のミドルネームかな */
    @Column(name = "middle_name_kana")
    private String middleNameKana = INIT_String;

    /**
     * 姓名のミドルネームかなを取得する
     *
     * @return 姓名のミドルネームかな
     */
    public String getMiddleNameKana() {
        return middleNameKana;
    }

    /**
     * 姓名のミドルネームかなを設定する
     *
     * @param middleNameKana 姓名のミドルネームかな
     */
    public void setMiddleNameKana(final String middleNameKana) {
        this.middleNameKana = middleNameKana;
    }

    /** 職業の業種 */
    @Column(name = "gyoushu")
    private String gyoushu = INIT_String;

    /**
     * 職業の業種を取得する
     *
     * @return 職業の業種
     */
    public String getGyoushu() {
        return gyoushu;
    }

    /**
     * 職業の業種を設定する
     *
     * @param gyoushu 職業の業種
     */
    public void setGyoushu(final String gyoushu) {
        this.gyoushu = gyoushu;
    }

    /** 職業の役職 */
    @Column(name = "yakushoku")
    private String yakushoku = INIT_String;

    /**
     * 職業の役職を取得する
     *
     * @return 職業の役職
     */
    public String getYakushoku() {
        return yakushoku;
    }

    /**
     * 職業の役職を設定する
     *
     * @param yakushoku 職業の役職
     */
    public void setYakushoku(final String yakushoku) {
        this.yakushoku = yakushoku;
    }

    /** ユーザ記述の職業 */
    @Column(name = "shokugyou_user_write")
    private String shokugyouUserWrite = INIT_String;

    /**
     * ユーザ記述の職業を取得する
     *
     * @return ユーザ記述の職業
     */
    public String getShokugyouUserWrite() {
        return shokugyouUserWrite;
    }

    /**
     * ユーザ記述の職業を設定する
     *
     * @param shokugyouUserWrite ユーザ記述の職業
     */
    public void setShokugyouUserWrite(final String shokugyouUserWrite) {
        this.shokugyouUserWrite = shokugyouUserWrite;
    }

    /** 企業番号 */
    @Column(name = "KigyouDt_no")
    private String KigyouDtNo = INIT_String;

    /**
     * 企業番号を取得する
     *
     * @return 企業番号
     */
    public String getKigyouDtNo() {
        return KigyouDtNo;
    }

    /**
     * 企業番号を設定する
     *
     * @param KigyouDtNo 企業番号
     */
    public void setKigyouDtNo(final String KigyouDtNo) {
        this.KigyouDtNo = KigyouDtNo;
    }

    /** 企業所在地 */
    @Column(name = "KigyouDt_address")
    private String KigyouDtAddress = INIT_String;

    /**
     * 企業所在地を取得する
     *
     * @return 企業所在地
     */
    public String getKigyouDtAddress() {
        return KigyouDtAddress;
    }

    /**
     * 企業所在地を設定する
     *
     * @param KigyouDtAddress 企業所在地
     */
    public void setKigyouDtAddress(final String KigyouDtAddress) {
        this.KigyouDtAddress = KigyouDtAddress;
    }

    /** 企業名 */
    @Column(name = "KigyouDt_name")
    private String KigyouDtName = INIT_String;

    /**
     * 企業名を取得する
     *
     * @return 企業名
     */
    public String getKigyouDtName() {
        return KigyouDtName;
    }

    /**
     * 企業名を設定する
     *
     * @param KigyouDtName 企業名
     */
    public void setKigyouDtName(final String KigyouDtName) {
        this.KigyouDtName = KigyouDtName;
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

    /** 自分の公式サイトUrl */
    @Column(name = "my_portal_url")
    private String myPortalUrl = INIT_String;

    /**
     * 自分の公式サイトUrlを取得する
     *
     * @return 自分の公式サイトUrl
     */
    public String getMyPortalUrl() {
        return myPortalUrl;
    }

    /**
     * 自分の公式サイトUrlを設定する
     *
     * @param myPortalUrl 自分の公式サイトUrl
     */
    public void setMyPortalUrl(final String myPortalUrl) {
        this.myPortalUrl = myPortalUrl;
    }

    /** SNSサービスコード */
    @Column(name = "sns_service_id")
    private Integer snsServiceId = INIT_Integer;

    /**
     * SNSサービスコードを取得する
     *
     * @return SNSサービスコード
     */
    public Integer getSnsServiceId() {
        return snsServiceId;
    }

    /**
     * SNSサービスコードを設定する
     *
     * @param snsServiceId SNSサービスコード
     */
    public void setSnsServiceId(final Integer snsServiceId) {
        this.snsServiceId = snsServiceId;
    }

    /** SNSサービスコード */
    @Column(name = "sns_service_code")
    private Integer snsServiceCode = INIT_Integer;

    /**
     * SNSサービスコードを取得する
     *
     * @return SNSサービスコード
     */
    public Integer getSnsServiceCode() {
        return snsServiceCode;
    }

    /**
     * SNSサービスコードを設定する
     *
     * @param snsServiceCode SNSサービスコード
     */
    public void setSnsServiceCode(final Integer snsServiceCode) {
        this.snsServiceCode = snsServiceCode;
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

    /** SNS玄関Url */
    @Column(name = "sns_portal_url")
    private String snsPortalUrl = INIT_String;

    /**
     * SNS玄関Urlを取得する
     *
     * @return SNS玄関Url
     */
    public String getSnsPortalUrl() {
        return snsPortalUrl;
    }

    /**
     * SNS玄関Urlを設定する
     *
     * @param snsPortalUrl SNS玄関Url
     */
    public void setSnsPortalUrl(final String snsPortalUrl) {
        this.snsPortalUrl = snsPortalUrl;
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

    /** 外国籍該否 */
    @Column(name = "is_foreign")
    private Boolean isForeign = INIT_Boolean;

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

    
}
