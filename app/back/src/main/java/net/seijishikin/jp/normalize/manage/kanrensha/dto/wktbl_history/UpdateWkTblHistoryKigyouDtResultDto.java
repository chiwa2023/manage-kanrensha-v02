package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;


/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblHistoryKigyouDtResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblKanrenshaKigyouDtHistoryEntity wkTblKanrenshaKigyouDtHistoryEntity = new WkTblKanrenshaKigyouDtHistoryEntity();

    /**
     * 編集対象Entityを取得する
     * 
     * @return 編集対象Entity
     */
    public WkTblKanrenshaKigyouDtHistoryEntity getWkTblKanrenshaKigyouDtHistoryEntity() {
        return wkTblKanrenshaKigyouDtHistoryEntity;
    }

    /**
     * 編集対象Entityを設定する
     * 
     * @param wkTblKanrenshaKigyouDtHistoryEntity 編集対象Entity
     */
    public void setWkTblKanrenshaKigyouDtHistoryEntity(
            final WkTblKanrenshaKigyouDtHistoryEntity wkTblKanrenshaKigyouDtHistoryEntity) {
        this.wkTblKanrenshaKigyouDtHistoryEntity = wkTblKanrenshaKigyouDtHistoryEntity;
    }

}
