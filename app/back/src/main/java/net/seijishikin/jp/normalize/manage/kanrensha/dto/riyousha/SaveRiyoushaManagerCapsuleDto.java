package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 利用者運営者保存Dto
 */
public class SaveRiyoushaManagerCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者運営者Dto */
    private RiyoushaManagerDto riyoushaManagerDto = new RiyoushaManagerDto();

    /**
     * 利用者運営者Dtoを取得する
     * 
     * @return 利用者運営者Dto
     */
    public RiyoushaManagerDto getRiyoushaManagerDto() {
        return riyoushaManagerDto;
    }

    /**
     * 利用者運営者Dtoを設定する
     * 
     * @param riyoushaManagerDto 利用者運営者Dto
     */
    public void setRiyoushaManagerDto(final RiyoushaManagerDto riyoushaManagerDto) {
        this.riyoushaManagerDto = riyoushaManagerDto;
    }

}