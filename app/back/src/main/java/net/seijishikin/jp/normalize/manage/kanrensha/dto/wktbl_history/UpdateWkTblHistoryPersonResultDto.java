package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;

/**
 * 個人一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblHistoryPersonResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaPersonHistoryEntity wkTblKanrenshaPersonHistoryEntity = new WkTblKanrenshaPersonHistoryEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaPersonHistoryEntity getWkTblKanrenshaPersonHistoryEntity() {
        return wkTblKanrenshaPersonHistoryEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaPersonHistoryEntity 編集対象Entity
     */
    public void setWkTblKanrenshaPersonHistoryEntity(
            final WkTblKanrenshaPersonHistoryEntity wkTblKanrenshaPersonHistoryEntity) {
        this.wkTblKanrenshaPersonHistoryEntity = wkTblKanrenshaPersonHistoryEntity;
    }

}
