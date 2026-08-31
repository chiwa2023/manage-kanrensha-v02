package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;


/**
 * 政治団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblStdSeijidantaiResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaSeijidantaiMasterEntity wkTblKanrenshaSeijidantaiMasterEntity = new WkTblKanrenshaSeijidantaiMasterEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaSeijidantaiMasterEntity getWkTblKanrenshaSeijidantaiMasterEntity() {
        return wkTblKanrenshaSeijidantaiMasterEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaSeijidantaiMasterEntity 編集対象Entity
     */
    public void setWkTblKanrenshaSeijidantaiMasterEntity(
            final WkTblKanrenshaSeijidantaiMasterEntity wkTblKanrenshaSeijidantaiMasterEntity) {
        this.wkTblKanrenshaSeijidantaiMasterEntity = wkTblKanrenshaSeijidantaiMasterEntity;
    }
}
