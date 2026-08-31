package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 利用者組織紐づけ仮情報取得Dto
 */
public class GetTempRiyoushaOrgCombineCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 個人コード */
    private Integer personCode = INIT_INTEGER;

    /** 組織コード */
    private Integer orgCode = INIT_INTEGER;

    /** 個人権限 */
    private String userRole = INIT_STRING;

    /**
     * 個人コードを取得する
     * 
     * @return 個人コード
     */
    public Integer getPersonCode() {
        return personCode;
    }

    /**
     * 個人コードを設定する
     * 
     * @param personCode 個人コード
     */
    public void setPersonCode(final Integer personCode) {
        this.personCode = personCode;
    }

    /**
     * 組織コードを取得する
     * 
     * @return 組織コード
     */
    public Integer getOrgCode() {
        return orgCode;
    }

    /**
     * 組織コードを設定する
     * 
     * @param orgCode 組織コード
     */
    public void setOrgCode(final Integer orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 個人権限を取得する
     * 
     * @return 個人権限
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * 個人権限を設定する
     * 
     * @param userRole 個人権限
     */
    public void setUserRole(final String userRole) {
        this.userRole = userRole;
    }

}
