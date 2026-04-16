package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import jakarta.persistence.Column;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * APIパートナー長期トークン発行条件Dto
 */
public class PartnerApiTokenCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** IPアドレス */
    @Column(name = "ip_address")
    private String ipAddress = INIT_STRING;

    /**
     * IPアドレスを取得する
     *
     * @return IPアドレス
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * IPアドレスを設定する
     *
     * @param ipAddress IPアドレス
     */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
