package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblCombineOrgResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaCombineOrgEntity wkTblKanrenshaCombineOrgEntity = new WkTblKanrenshaCombineOrgEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaCombineOrgEntity getWkTblKanrenshaCombineOrgEntity() {
        return wkTblKanrenshaCombineOrgEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaCombineOrgEntity 編集対象Entity
     */
    public void setWkTblKanrenshaCombineOrgEntity(final WkTblKanrenshaCombineOrgEntity wkTblKanrenshaCombineOrgEntity) {
        this.wkTblKanrenshaCombineOrgEntity = wkTblKanrenshaCombineOrgEntity;
    }

}
