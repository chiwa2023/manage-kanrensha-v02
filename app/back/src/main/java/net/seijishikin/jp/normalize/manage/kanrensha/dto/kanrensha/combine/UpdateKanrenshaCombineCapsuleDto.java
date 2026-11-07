package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;

/**
 * 関連者紐づけ更新Dto
 */
public class UpdateKanrenshaCombineCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者紐づけワークテーブルEntity */
    private WkTblKanrenshaCombineOrgEntity combineOrgEntity = new WkTblKanrenshaCombineOrgEntity();

    /**
     * 関連者紐づけワークテーブルEntityを取得する
     * 
     * @return 関連者紐づけワークテーブルEntity
     */
    public WkTblKanrenshaCombineOrgEntity getCombineOrgEntity() {
        return combineOrgEntity;
    }

    /**
     * 関連者紐づけワークテーブルEntityを設定する
     * 
     * @param combineOrgEntity 関連者紐づけワークテーブルEntity
     */
    public void setCombineOrgEntity(final WkTblKanrenshaCombineOrgEntity combineOrgEntity) {
        this.combineOrgEntity = combineOrgEntity;
    }

}
