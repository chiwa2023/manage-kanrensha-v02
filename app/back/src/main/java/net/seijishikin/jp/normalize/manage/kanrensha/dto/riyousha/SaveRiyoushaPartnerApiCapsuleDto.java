package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * APIユーザ格納Dto
 */
public class SaveRiyoushaPartnerApiCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者API接続Dto */
    private RiyoushaPartnerApiDto riyoushaPartnerApiDto = new RiyoushaPartnerApiDto();

    /**
     * 利用者API接続Dtoを取得する
     * 
     * @return 利用者API接続Dto
     */
    public RiyoushaPartnerApiDto getRiyoushaPartnerApiDto() {
        return riyoushaPartnerApiDto;
    }

    /**
     * 利用者API接続Dtoを設定する
     * 
     * @param riyoushaPartnerApiDto 利用者API接続Dto
     */
    public void setRiyoushaPartnerApiDto(final RiyoushaPartnerApiDto riyoushaPartnerApiDto) {
        this.riyoushaPartnerApiDto = riyoushaPartnerApiDto;
    }

}
