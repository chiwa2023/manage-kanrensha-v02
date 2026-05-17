package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 運営者ユーザー格納Dto
 */
public class SaveRiyoushaOrgCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者運営者Dto */
    private RiyoushaOrgDto riyoushaOrgDto = new RiyoushaOrgDto();

    /**
     * 利用者運営者Dtoを取得する
     * 
     * @return 利用者運営者Dto
     */
    public RiyoushaOrgDto getRiyoushaOrgDto() {
        return riyoushaOrgDto;
    }

    /**
     * 利用者運営者Dtoを設定する
     * 
     * @param riyoushaOrgDto 利用者運営者Dto
     */
    public void setRiyoushaOrgDto(final RiyoushaOrgDto riyoushaOrgDto) {
        this.riyoushaOrgDto = riyoushaOrgDto;
    }

    /** 利用者名 */
    private String riyoushaName = INIT_STRING;

    /** 利用者コード */
    private Integer riyoushaCode = INIT_INTEGER;

    /** 利用者権限 */
    private String riyoushaRole = INIT_STRING;

    /**
     * 利用者名を設定する
     * 
     * @return 利用者名
     */
    public String getRiyoushaName() {
        return riyoushaName;
    }

    /**
     * 利用者名を指定する
     * 
     * @param riyoushaName 利用者名
     */
    public void setRiyoushaName(final String riyoushaName) {
        this.riyoushaName = riyoushaName;
    }

    /**
     * 利用者名コードを取得する
     * 
     * @return 利用者名コード
     */
    public Integer getRiyoushaCode() {
        return riyoushaCode;
    }

    /**
     * 利用者名コードを設定する
     * 
     * @param riyoushaCode 利用者名コード
     */
    public void setRiyoushaCode(final Integer riyoushaCode) {
        this.riyoushaCode = riyoushaCode;
    }

    /**
     * 利用者名権限を取得する
     * 
     * @return 利用者名権限
     */
    public String getRiyoushaRole() {
        return riyoushaRole;
    }

    /**
     * 利用者名権限を設定する
     * 
     * @param riyoushaRole 利用者名権限
     */
    public void setRiyoushaRole(final String riyoushaRole) {
        this.riyoushaRole = riyoushaRole;
    }

}
