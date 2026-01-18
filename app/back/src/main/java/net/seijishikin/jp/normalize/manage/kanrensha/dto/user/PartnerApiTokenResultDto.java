package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * APIパートナー長期トークン発行Dto
 */
public class PartnerApiTokenResultDto extends FrameworkMessageAndResultDto { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** トークン */
    private String token = INIT_STRING;

    /**
     * トークンを取得する
     * 
     * @return トークン
     */
    public String getToken() {
        return token;
    }

    /**
     * トークンを設定する
     * 
     * @param token トークン
     */
    public void setToken(final String token) {
        this.token = token;
    }

}
