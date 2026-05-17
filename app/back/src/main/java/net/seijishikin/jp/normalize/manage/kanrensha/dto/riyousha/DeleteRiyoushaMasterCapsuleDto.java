package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 利用者マスタ取得条件Dto
 */
public class DeleteRiyoushaMasterCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者コード */
    private Integer riyoushaCode = INIT_INTEGER;

    /** 利用者権限 */
    private String riyoushaRole = INIT_STRING;

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
     * 利用者権限を取得する
     * 
     * @return 利用者権限
     */
    public String getRiyoushaRole() {
        return riyoushaRole;
    }

    /**
     * 利用者権限を設定する
     * 
     * @param riyoushaRole 利用者権限
     */
    public void setRiyoushaRole(final String riyoushaRole) {
        this.riyoushaRole = riyoushaRole;
    }

}
