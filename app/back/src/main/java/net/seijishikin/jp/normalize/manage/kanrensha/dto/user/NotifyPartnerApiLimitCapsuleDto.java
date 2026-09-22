package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * APIパートナー長期トークン期限切れ通知(強制)Dto
 */
public class NotifyPartnerApiLimitCapsuleDto extends FrameworkCapsuleDto
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 通知確認日付 */
    private LocalDate checkDate = INIT_DATE;

    /**
     * 通知確認日付を指定する
     * 
     * @return 通知確認日付
     */
    public LocalDate getCheckDate() {
        return checkDate;
    }

    /**
     * 通知確認日付を設定する
     * 
     * @param checkDate 通知確認日付
     */
    public void setCheckDate(final LocalDate checkDate) {
        this.checkDate = checkDate;
    }
}
