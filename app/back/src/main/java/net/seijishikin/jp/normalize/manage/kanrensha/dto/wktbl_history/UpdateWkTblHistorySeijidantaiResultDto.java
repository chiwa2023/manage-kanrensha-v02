package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;

/**
 * 政治団体一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblHistorySeijidantaiResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaSeijidantaiHistoryEntity wkTblKanrenshaSeijidantaiHistoryEntity = new WkTblKanrenshaSeijidantaiHistoryEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaSeijidantaiHistoryEntity getWkTblKanrenshaSeijidantaiHistoryEntity() {
        return wkTblKanrenshaSeijidantaiHistoryEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaSeijidantaiHistoryEntity 編集対象Entity
     */
    public void setWkTblKanrenshaSeijidantaiHistoryEntity(
            final WkTblKanrenshaSeijidantaiHistoryEntity wkTblKanrenshaSeijidantaiHistoryEntity) {
        this.wkTblKanrenshaSeijidantaiHistoryEntity = wkTblKanrenshaSeijidantaiHistoryEntity;
    }

}
