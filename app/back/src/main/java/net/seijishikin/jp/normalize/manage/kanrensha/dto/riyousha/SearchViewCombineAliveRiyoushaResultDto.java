package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * 利用者全権限最新Dto
 */
public class SearchViewCombineAliveRiyoushaResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者Id */
    @Id
    @Column(name = "riyousha_id")
    private Integer riyoushaId;

    /** 利用者コード */
    @Id
    @Column(name = "riyousha_code")
    private Integer riyoushaCode;

    /** 権限基礎 */
    @Id
    @Column(name = "role_base")
    private String roleBase;

    /** 権限保持 */
    @Id
    @Column(name = "role_has")
    private String roleHas;

    /** 氏名 */
    @Column(name = "all_name")
    private String allName;

    /**
     * コンストラクタ
     * 
     * @param riyoushaId   利用者Id
     * @param riyoushaCode 利用者コード
     * @param roleBase     権限基礎
     * @param roleHas      権限保持
     * @param allName      氏名
     */
    public SearchViewCombineAliveRiyoushaResultDto(final Integer riyoushaId, final Integer riyoushaCode,
            final String roleBase, final String roleHas, final String allName) {
        super();
        this.riyoushaId = riyoushaId;
        this.riyoushaCode = riyoushaCode;
        this.roleBase = roleBase;
        this.roleHas = roleHas;
        this.allName = allName;
    }

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
}
