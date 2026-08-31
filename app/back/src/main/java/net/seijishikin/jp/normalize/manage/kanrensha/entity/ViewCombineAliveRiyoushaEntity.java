package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 最新利用者権限混在Entity
 */
@Entity
@IdClass(ViewCombineAliveRiyoushaPrimaryKey.class)
@Table(name = "view_combine_alive_riyousha")
public class ViewCombineAliveRiyoushaEntity implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** email */
    @Id
    @Column(name = "email")
    private String email = INIT_STRING;

    /**
     * emailを取得する
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * emailを設定する
     *
     * @param email email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** 利用者Id */
    @Id
    @Column(name = "riyousha_id")
    private Integer riyoushaId = INIT_INTEGER;

    /** 利用者コード */
    @Id
    @Column(name = "riyousha_code")
    private Integer riyoushaCode = INIT_INTEGER;

    /** 権限基礎 */
    @Id
    @Column(name = "role_base")
    private String roleBase = INIT_STRING;

    /** 権限保持 */
    @Id
    @Column(name = "role_has")
    private String roleHas = INIT_STRING;

    /** 氏名 */
    @Column(name = "all_name")
    private String allName = INIT_STRING;

    /** 検索テキスト */
    @Column(name = "search_text")
    private String searchText = INIT_STRING;

    /**
     * 利用者Idを取得する
     * 
     * @return 利用者Id
     */
    public Integer getRiyoushaId() {
        return riyoushaId;
    }

    /**
     * 利用者Idを設定する
     * 
     * @param riyoushaId 利用者Id
     */
    public void setRiyoushaId(final Integer riyoushaId) {
        this.riyoushaId = riyoushaId;
    }

    /**
     * 利用者コードを取得する
     * 
     * @return 利用者コード
     */
    public Integer getRiyoushaCode() {
        return riyoushaCode;
    }

    /**
     * 利用者コードを設定する
     * 
     * @param riyoushaCode 利用者コード
     */
    public void setRiyoushaCode(final Integer riyoushaCode) {
        this.riyoushaCode = riyoushaCode;
    }

    /**
     * 権限基礎を取得する
     * 
     * @return 権限基礎
     */
    public String getRoleBase() {
        return roleBase;
    }

    /**
     * 権限基礎を設定する
     * 
     * @param roleBase 権限基礎
     */
    public void setRoleBase(final String roleBase) {
        this.roleBase = roleBase;
    }

    /**
     * 権限保持を取得する
     * 
     * @return 権限保持
     */
    public String getRoleHas() {
        return roleHas;
    }

    /**
     * 権限保持を設定する
     * 
     * @param roleHas 権限保持
     */
    public void setRoleHas(final String roleHas) {
        this.roleHas = roleHas;
    }

    /**
     * 氏名を取得する
     * 
     * @return 氏名
     */
    public String getAllName() {
        return allName;
    }

    /**
     * 氏名を設定する
     * 
     * @param allName 氏名
     */
    public void setAllName(final String allName) {
        this.allName = allName;
    }

    /**
     * 検索テキストを取得する
     * 
     * @return 検索テキスト
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 検索テキストを設定する
     * 
     * @param searchText 検索テキスト
     */
    public void setSearchText(final String searchText) {
        this.searchText = searchText;
    }
}
