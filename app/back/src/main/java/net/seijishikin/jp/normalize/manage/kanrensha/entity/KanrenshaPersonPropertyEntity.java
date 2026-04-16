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
 * kanrensha_person_property接続用Entity
 */
@Entity
@Table(name = "kanrensha_person_property")
public class KanrenshaPersonPropertyEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_person_property_id")
    private Integer kanrenshaPersonPropertyId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaPersonPropertyId() {
        return kanrenshaPersonPropertyId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaPersonPropertyId テーブルId
     */
    public void setKanrenshaPersonPropertyId(final Integer kanrenshaPersonPropertyId) {
        this.kanrenshaPersonPropertyId = kanrenshaPersonPropertyId;
    }

    /** 関連者個人Id */
    @Column(name = "kanrensha_person_id")
    private Integer kanrenshaPersonId = INIT_INTEGER;

    /**
     * 関連者個人Idを取得する
     *
     * @return 関連者個人Id
     */
    public Integer getKanrenshaPersonId() {
        return kanrenshaPersonId;
    }

    /**
     * 関連者個人Idを設定する
     *
     * @param kanrenshaPersonId 関連者個人Id
     */
    public void setKanrenshaPersonId(final Integer kanrenshaPersonId) {
        this.kanrenshaPersonId = kanrenshaPersonId;
    }

    /** 関連者個人コード */
    @Column(name = "person_kanrensha_code")
    private String personKanrenshaCode = INIT_STRING;

    /**
     * 関連者個人コードを取得する
     *
     * @return 関連者個人コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 関連者個人コードを設定する
     *
     * @param personKanrenshaCode 関連者個人コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

    /** 関連者個人名称 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 関連者個人名称を取得する
     *
     * @return 関連者個人名称
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 関連者個人名称を設定する
     *
     * @param kanrenshaName 関連者個人名称
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

    /** 姓名の姓 */
    @Column(name = "last_name")
    private String lastName = INIT_STRING;

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
    private String firstName = INIT_STRING;

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
    private String middleName = INIT_STRING;

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
    private String lastNameKana = INIT_STRING;

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
    private String firstNameKana = INIT_STRING;

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
    private String middleNameKana = INIT_STRING;

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
    private String gyoushu = INIT_STRING;

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
    private String yakushoku = INIT_STRING;

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
    private String shokugyouUserWrite = INIT_STRING;

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
    @Column(name = "kigyou_dt_no")
    private String kigyouDtNo = INIT_STRING;

    /**
     * 企業番号を取得する
     *
     * @return 企業番号
     */
    public String getKigyouDtNo() {
        return kigyouDtNo;
    }

    /**
     * 企業番号を設定する
     *
     * @param kigyouDtNo 企業番号
     */
    public void setKigyouDtNo(final String kigyouDtNo) {
        this.kigyouDtNo = kigyouDtNo;
    }

    /** 企業所在地 */
    @Column(name = "kigyou_dt_address")
    private String kigyouDtAddress = INIT_STRING;

    /**
     * 企業所在地を取得する
     *
     * @return 企業所在地
     */
    public String getKigyouDtAddress() {
        return kigyouDtAddress;
    }

    /**
     * 企業所在地を設定する
     *
     * @param kigyouDtAddress 企業所在地
     */
    public void setKigyouDtAddress(final String kigyouDtAddress) {
        this.kigyouDtAddress = kigyouDtAddress;
    }

    /** 企業名 */
    @Column(name = "kigyou_dt_name")
    private String kigyouDtName = INIT_STRING;

    /**
     * 企業名を取得する
     *
     * @return 企業名
     */
    public String getKigyouDtName() {
        return kigyouDtName;
    }

    /**
     * 企業名を設定する
     *
     * @param kigyouDtName 企業名
     */
    public void setKigyouDtName(final String kigyouDtName) {
        this.kigyouDtName = kigyouDtName;
    }

    /** 職業編集該否 */
    @Column(name = "is_shokyou_edit")
    private Boolean isShokyouEdit = INIT_BOOLEAN;

    /**
     * 職業編集該否を取得する
     *
     * @return 職業編集該否
     */
    public Boolean getIsShokyouEdit() {
        return isShokyouEdit;
    }

    /**
     * 職業編集該否を設定する
     *
     * @param isShokyouEdit 職業編集該否
     */
    public void setIsShokyouEdit(final Boolean isShokyouEdit) {
        this.isShokyouEdit = isShokyouEdit;
    }

    /** 職業編集承認該否 */
    @Column(name = "is_shokyou_accept")
    private Boolean isShokyouAccept = INIT_BOOLEAN;

    /**
     * 職業編集承認該否を取得する
     *
     * @return 職業編集承認該否
     */
    public Boolean getIsShokyouAccept() {
        return isShokyouAccept;
    }

    /**
     * 職業編集承認該否を設定する
     *
     * @param isShokyouAccept 職業編集承認該否
     */
    public void setIsShokyouAccept(final Boolean isShokyouAccept) {
        this.isShokyouAccept = isShokyouAccept;
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
