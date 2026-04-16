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
 * kanrensha_person_access接続用Entity
 */
@Entity
@Table(name = "kanrensha_person_access")
public class KanrenshaPersonAccessEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_person_access_id")
    private Integer kanrenshaPersonAccessId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaPersonAccessId() {
        return kanrenshaPersonAccessId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaPersonAccessId テーブルId
     */
    public void setKanrenshaPersonAccessId(final Integer kanrenshaPersonAccessId) {
        this.kanrenshaPersonAccessId = kanrenshaPersonAccessId;
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

    /** 電話番号1 */
    @Column(name = "phon1")
    private String phon1 = INIT_STRING;

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
    private String phon2 = INIT_STRING;

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
    private String phon3 = INIT_STRING;

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
    private String email = INIT_STRING;

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

    /** SNSサービスコード */
    @Column(name = "sns_service_id")
    private Integer snsServiceId = INIT_INTEGER;

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
    private Integer snsServiceCode = INIT_INTEGER;

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
    private String snsServiceName = INIT_STRING;

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

    /** SNS玄関url */
    @Column(name = "sns_portal_url")
    private String snsPortalUrl = INIT_STRING;

    /**
     * SNS玄関urlを取得する
     *
     * @return SNS玄関url
     */
    public String getSnsPortalUrl() {
        return snsPortalUrl;
    }

    /**
     * SNS玄関urlを設定する
     *
     * @param snsPortalUrl SNS玄関url
     */
    public void setSnsPortalUrl(final String snsPortalUrl) {
        this.snsPortalUrl = snsPortalUrl;
    }

    /** SNSサービスアカウント */
    @Column(name = "sns_account")
    private String snsAccount = INIT_STRING;

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
