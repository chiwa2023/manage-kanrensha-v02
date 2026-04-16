package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;

/**
 * 利用者組織削除条件Dto
 */
public class DeleteRiyoushaOrgCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者組織マスタEntity */
    private RiyoushaOrgMasterEntity masterEntity = new RiyoushaOrgMasterEntity();

    /**
     * 利用者組織マスタEntityを取得する
     * 
     * @return 利用者組織マスタEntity
     */
    public RiyoushaOrgMasterEntity getMasterEntity() {
        return masterEntity;
    }

    /**
     * 利用者組織マスタEntityを設定する
     * 
     * @param masterEntity 利用者組織マスタEntity
     */
    public void setMasterEntity(final RiyoushaOrgMasterEntity masterEntity) {
        this.masterEntity = masterEntity;
    }

}
