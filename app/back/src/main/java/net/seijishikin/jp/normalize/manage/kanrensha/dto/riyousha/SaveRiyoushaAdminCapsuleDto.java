package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;


/**
 * 運営者ユーザー格納Dto
 */
public class SaveRiyoushaAdminCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者運営者Dto */
    private RiyoushaAdminDto riyoushaAdminDto = new RiyoushaAdminDto();

    /**
     * 利用者運営者Dtoを取得する
     *
     * @return 利用者運営者Dto
     */
    public RiyoushaAdminDto getRiyoushaAdminDto() {
        return riyoushaAdminDto;
    }

    /**
     * 利用者運営者Dtoを設定する
     *
     * @param riyoushaAdminDto 利用者運営者Dto
     */
    public void setRiyoushaAdminDto(final RiyoushaAdminDto riyoushaAdminDto) {
        this.riyoushaAdminDto = riyoushaAdminDto;
    }

}
