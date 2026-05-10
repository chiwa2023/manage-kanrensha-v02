package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;

/**
 * 関連者マスタ取得結果Dto
 */
public class GetRiyoushaMasterResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 運営者マスタEntity */
    private RiyoushaManagerMasterEntity managerMasterEntity = new RiyoushaManagerMasterEntity();

    /** APIユーザマスタEntity */
    private RiyoushaPartnerApiMasterEntity partnerApiMasterEntity = new RiyoushaPartnerApiMasterEntity();

    /**
     * 運営者マスタEntityを取得する
     * 
     * @return 運営者マスタEntity
     */
    public RiyoushaManagerMasterEntity getManagerMasterEntity() {
        return managerMasterEntity;
    }

    /**
     * 運営者マスタEntityを設定する
     * 
     * @param managerMasterEntity 運営者マスタEntity
     */
    public void setManagerMasterEntity(final RiyoushaManagerMasterEntity managerMasterEntity) {
        this.managerMasterEntity = managerMasterEntity;
    }

    /**
     * APIユーザマスタEntityを取得する
     * 
     * @return APIユーザマスタEntity
     */
    public RiyoushaPartnerApiMasterEntity getPartnerApiMasterEntity() {
        return partnerApiMasterEntity;
    }

    /**
     * APIユーザマスタEntityを設定する
     * 
     * @param partnerApiMasterEntity APIユーザマスタEntity
     */
    public void setPartnerApiMasterEntity(final RiyoushaPartnerApiMasterEntity partnerApiMasterEntity) {
        this.partnerApiMasterEntity = partnerApiMasterEntity;
    }

}
