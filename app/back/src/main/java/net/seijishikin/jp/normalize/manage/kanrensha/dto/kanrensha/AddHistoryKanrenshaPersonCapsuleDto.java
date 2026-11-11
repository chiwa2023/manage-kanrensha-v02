package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistoryPersonDto;

/**
 * 関連者個人履歴追加条件Dto
 */
public class AddHistoryKanrenshaPersonCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者個人履歴テーブル */
    private KanrenshaTableHistoryPersonDto editDto;

    /**
     * 関連者個人履歴テーブルを取得する
     * 
     * @return 関連者個人履歴テーブル
     */
    public KanrenshaTableHistoryPersonDto getEditDto() {
        return editDto;
    }

    /**
     * 関連者個人履歴テーブルを設定する
     * 
     * @param editDto 関連者個人履歴テーブル
     */
    public void setEditDto(final KanrenshaTableHistoryPersonDto editDto) {
        this.editDto = editDto;
    }

}
