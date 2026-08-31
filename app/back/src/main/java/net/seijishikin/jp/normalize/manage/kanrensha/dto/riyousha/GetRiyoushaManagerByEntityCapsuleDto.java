package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;

/**
 * マスタEntityから利用者運営者編集用利用者取得条件Dto
 */
public class GetRiyoushaManagerByEntityCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者運営者マスタEntity */
    private RiyoushaManagerMasterEntity masterEntity;

    /**
     * 利用者運営者マスタEntityを取得する
     * 
     * @return 利用者運営者マスタEntity
     */
    public RiyoushaManagerMasterEntity getMasterEntity() {
        return masterEntity;
    }

    /**
     * 利用者運営者マスタEntityを設定する
     * 
     * @param masterEntity 利用者運営者マスタEntity
     */
    public void setMasterEntity(final RiyoushaManagerMasterEntity masterEntity) {
        this.masterEntity = masterEntity;
    }

}
