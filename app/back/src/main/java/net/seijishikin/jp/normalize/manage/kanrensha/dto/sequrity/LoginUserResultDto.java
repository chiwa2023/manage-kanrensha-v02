package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * ログイン結果Dto
 */
public class LoginUserResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザ最小限Dto */
    private LeastUserDto userDto = new LeastUserDto();

    /** Jwtトークン */
    private JwtTokenDto jwtTokenDto;

    
    /**
     * ユーザ最小限Dtoを取得する
     * 
     * @return ユーザ最小限Dto
     */
    public LeastUserDto getUserDto() {
        return userDto;
    }

    /**
     * ユーザ最小限Dtoを設定する
     * 
     * @param userDto ユーザ最小限Dto
     */
    public void setUserDto(final LeastUserDto userDto) {
        this.userDto = userDto;
    }

    /**
     * Jwtトークンを取得する
     *
     * @return Jwtトークン
     */
    public JwtTokenDto getJwtTokenDto() {
        return jwtTokenDto;
    }

    /**
     * Jwtトークンを設定する
     *
     * @param jwtTokenDto Jwtトークン
     */
    public void setJwtTokenDto(final JwtTokenDto jwtTokenDto) {
        this.jwtTokenDto = jwtTokenDto;
    }

}
