package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * Jwtトークン返却Dto
 */
public class JwtTokenResultDto extends FrameworkMessageAndResultDto {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** Jwtトークン返却Dto */
    private JwtTokenDto jwtTokenDto;

    /**
     * Jwtトークン返却Dtoを設定する
     * 
     * @return Jwtトークン返却Dto
     */
    public JwtTokenDto getJwtTokenDto() {
        return jwtTokenDto;
    }

    /**
     * Jwtトークン返却Dtoを取得する
     * 
     * @param jwtTokenDto Jwtトークン返却Dto
     */
    public void setJwtTokenDto(final JwtTokenDto jwtTokenDto) {
        this.jwtTokenDto = jwtTokenDto;
    }

}
