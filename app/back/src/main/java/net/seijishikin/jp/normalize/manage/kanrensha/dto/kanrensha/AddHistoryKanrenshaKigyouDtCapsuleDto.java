package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistoryKigyouDtDto;

/**
 * 関連者企業・団体履歴追加条件Dto
 */
public class AddHistoryKanrenshaKigyouDtCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者企業・団体履歴テーブル */
    private KanrenshaTableHistoryKigyouDtDto editDto;

    /**
     * 関連者企業・団体履歴テーブルを取得する
     * 
     * @return 関連者企業・団体履歴テーブル
     */
    public KanrenshaTableHistoryKigyouDtDto getEditDto() {
        return editDto;
    }

    /**
     * 関連者企業・団体履歴テーブルを設定する
     * 
     * @param editDto 関連者企業・団体履歴テーブル
     */
    public void setEditDto(final KanrenshaTableHistoryKigyouDtDto editDto) {
        this.editDto = editDto;
    }

}
