package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;

/**
 * マスタEntityから利用者APIパートナー編集用利用者取得条件Dto
 */
public class GetRiyoushaPartnerApiByEntityCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者APIパートナーマスタEntity */
    private RiyoushaPartnerApiMasterEntity masterEntity;

    /**
     * 利用者APIパートナーマスタEntityを取得する
     * 
     * @return 利用者APIパートナーマスタEntity
     */
    public RiyoushaPartnerApiMasterEntity getMasterEntity() {
        return masterEntity;
    }

    /**
     * 利用者APIパートナーマスタEntityを設定する
     * 
     * @param masterEntity 利用者APIパートナーマスタEntity
     */
    public void setMasterEntity(final RiyoushaPartnerApiMasterEntity masterEntity) {
        this.masterEntity = masterEntity;
    }

}
