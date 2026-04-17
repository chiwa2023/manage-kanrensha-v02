package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;

/**
 * SE権限追加推薦Dto
 */
public class PromoteUserAdminCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** SE権限推薦者 */
    private UserPersonEntity entityUserPromote = new UserPersonEntity();

    /**
     * SE権限推薦者を取得する
     * 
     * @return SE権限推薦者
     */
    public UserPersonEntity getEntityUserPromote() {
        return entityUserPromote;
    }

    /**
     * SE権限推薦者を設定する
     * 
     * @param entityUserPromote SE権限推薦者
     */
    public void setEntityUserPromote(final UserPersonEntity entityUserPromote) {
        this.entityUserPromote = entityUserPromote;
    }

}
