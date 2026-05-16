package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * ViewCombineAliveRiyousha主キークラス
 */
@Embeddable
public class ViewCombineAliveRiyoushaPrimaryKey // NOPMD DataClass
        implements DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** email */
    @Column(name = "email")
    private String email = INIT_STRING;
    /** 利用者Id */
    @Id
    private Integer riyoushaId = INIT_INTEGER;

    /** 利用者コード */
    @Id
    private Integer riyoushaCode = INIT_INTEGER;

    /** 権限基礎 */
    @Id
    private String roleBase = INIT_STRING;

    /** 権限保持 */
    @Id
    private String roleHas = INIT_STRING;

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
     * hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, riyoushaCode, riyoushaId, roleBase, roleHas);
    }

    /**
     * equals
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ViewCombineAliveRiyoushaPrimaryKey other = (ViewCombineAliveRiyoushaPrimaryKey) obj;
        return Objects.equals(email, other.email) && Objects.equals(riyoushaCode, other.riyoushaCode)
                && Objects.equals(riyoushaId, other.riyoushaId) && Objects.equals(roleBase, other.roleBase)
                && Objects.equals(roleHas, other.roleHas);
    }

}
