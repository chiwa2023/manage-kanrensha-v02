package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;

/**
 * 企業／団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblStdKigyouDtCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaKigyouDtMasterEntity wkTblKanrenshaKigyouDtMasterEntity = new WkTblKanrenshaKigyouDtMasterEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaKigyouDtMasterEntity getWkTblKanrenshaKigyouDtMasterEntity() {
        return wkTblKanrenshaKigyouDtMasterEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaKigyouDtMasterEntity 編集対象Entity
     */
    public void setWkTblKanrenshaKigyouDtMasterEntity(
            final WkTblKanrenshaKigyouDtMasterEntity wkTblKanrenshaKigyouDtMasterEntity) {
        this.wkTblKanrenshaKigyouDtMasterEntity = wkTblKanrenshaKigyouDtMasterEntity;
    }

}
