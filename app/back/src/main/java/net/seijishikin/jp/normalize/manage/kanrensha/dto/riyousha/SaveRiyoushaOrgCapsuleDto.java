package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 運営者ユーザー格納Dto
 */
public class SaveRiyoushaOrgCapsuleDto extends FrameworkCapsuleDto implements Serializable {

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

}
