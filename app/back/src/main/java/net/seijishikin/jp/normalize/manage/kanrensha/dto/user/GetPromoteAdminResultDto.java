package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;

/**
 * SE権限追加推薦取得Dto
 */
public class GetPromoteAdminResultDto extends FrameworkMessageAndResultDto { // NOPMD DataClass

    /** Serialize id */
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

    /** 取得件数 */
    private Integer promoteCount = INIT_INTEGER;

    /**
     * 取得件数を取得する
     * 
     * @return 取得件数
     */
    public Integer getPromoteCount() {
        return promoteCount;
    }

    /**
     * 取得件数を設定する
     * 
     * @param promoteCount 取得件数
     */
    public void setPromoteCount(final Integer promoteCount) {
        this.promoteCount = promoteCount;
    }

}
