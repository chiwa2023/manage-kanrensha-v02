package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;

/**
 * 個人一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblStdPersonCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaPersonMasterEntity wkTblKanrenshaPersonMasterEntity = new WkTblKanrenshaPersonMasterEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaPersonMasterEntity getWkTblKanrenshaPersonMasterEntity() {
        return wkTblKanrenshaPersonMasterEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaPersonMasterEntity 編集対象Entity
     */
    public void setWkTblKanrenshaPersonMasterEntity(
            final WkTblKanrenshaPersonMasterEntity wkTblKanrenshaPersonMasterEntity) {
        this.wkTblKanrenshaPersonMasterEntity = wkTblKanrenshaPersonMasterEntity;
    }

}
