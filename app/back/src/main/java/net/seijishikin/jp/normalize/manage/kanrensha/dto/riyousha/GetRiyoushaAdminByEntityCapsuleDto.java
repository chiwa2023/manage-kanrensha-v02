package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaAdminMasterEntity;

/**
 * マスタEntityから利用者SE権限編集用利用者取得条件Dto
 */
public class GetRiyoushaAdminByEntityCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者SE権限マスタEntity */
    private RiyoushaAdminMasterEntity masterEntity;

    /**
     * 利用者SE権限マスタEntityを取得する
     * 
     * @return 利用者SE権限マスタEntity
     */
    public RiyoushaAdminMasterEntity getMasterEntity() {
        return masterEntity;
    }

    /**
     * 利用者SE権限マスタEntityを設定する
     * 
     * @param masterEntity 利用者SE権限マスタEntity
     */
    public void setMasterEntity(final RiyoushaAdminMasterEntity masterEntity) {
        this.masterEntity = masterEntity;
    }

}
