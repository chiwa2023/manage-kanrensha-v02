package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;

/**
 * 企業／団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblMinKigyouDtCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaKigyouDtAddMinEntity wkTblKanrenshaKigyouDtAddMinEntity = new WkTblKanrenshaKigyouDtAddMinEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaKigyouDtAddMinEntity getWkTblKanrenshaKigyouDtAddMinEntity() {
        return wkTblKanrenshaKigyouDtAddMinEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaKigyouDtAddMinEntity 編集対象Entity
     */
    public void setWkTblKanrenshaKigyouDtAddMinEntity(
            final WkTblKanrenshaKigyouDtAddMinEntity wkTblKanrenshaKigyouDtAddMinEntity) {
        this.wkTblKanrenshaKigyouDtAddMinEntity = wkTblKanrenshaKigyouDtAddMinEntity;
    }

}
