package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;

/**
 * SE権限追加推薦諾否Dto
 */
public class AcceptUserAdminCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** SE権限追加推薦Entity */
    private PromoteAdminEntity promoteAdminEntity;

    /**
     * SE権限追加推薦Entityを取得する
     * 
     * @return SE権限追加推薦Entity
     */
    public PromoteAdminEntity getPromoteAdminEntity() {
        return promoteAdminEntity;
    }

    /**
     * SE権限追加推薦Entityを設定する
     * 
     * @param promoteAdminEntity SE権限追加推薦Entity
     */
    public void setPromoteAdminEntity(final PromoteAdminEntity promoteAdminEntity) {
        this.promoteAdminEntity = promoteAdminEntity;
    }

}
