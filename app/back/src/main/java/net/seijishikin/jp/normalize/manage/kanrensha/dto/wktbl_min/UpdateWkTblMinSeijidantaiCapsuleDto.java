package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;

/**
 * 政治団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblMinSeijidantaiCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaSeijidantaiAddMinEntity wkTblKanrenshaSeijidantaiAddMinEntity = new WkTblKanrenshaSeijidantaiAddMinEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaSeijidantaiAddMinEntity getWkTblKanrenshaSeijidantaiAddMinEntity() {
        return wkTblKanrenshaSeijidantaiAddMinEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaSeijidantaiAddMinEntity 編集対象Entity
     */
    public void setWkTblKanrenshaSeijidantaiAddMinEntity(
            final WkTblKanrenshaSeijidantaiAddMinEntity wkTblKanrenshaSeijidantaiAddMinEntity) {
        this.wkTblKanrenshaSeijidantaiAddMinEntity = wkTblKanrenshaSeijidantaiAddMinEntity;
    }

}
