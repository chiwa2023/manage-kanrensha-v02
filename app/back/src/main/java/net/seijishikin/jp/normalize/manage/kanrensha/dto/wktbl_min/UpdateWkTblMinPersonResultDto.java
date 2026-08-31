package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;


/**
 * 個人一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblMinPersonResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaPersonAddMinEntity wkTblKanrenshaPersonAddMinEntity = new WkTblKanrenshaPersonAddMinEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaPersonAddMinEntity getWkTblKanrenshaPersonAddMinEntity() {
        return wkTblKanrenshaPersonAddMinEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaPersonAddMinEntity 編集対象Entity
     */
    public void setWkTblKanrenshaPersonAddMinEntity(
            final WkTblKanrenshaPersonAddMinEntity wkTblKanrenshaPersonAddMinEntity) {
        this.wkTblKanrenshaPersonAddMinEntity = wkTblKanrenshaPersonAddMinEntity;
    }
}
