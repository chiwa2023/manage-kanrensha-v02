package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistorySeijidantaiDto;

/**
 * 関連者政治団体履歴追加条件Dto
 */
public class AddHistoryKanrenshaSeijidantaiCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者政治団体履歴テーブル */
    private KanrenshaTableHistorySeijidantaiDto editDto;

    /**
     * 関連者政治団体履歴テーブルを取得する
     * 
     * @return 関連者政治団体履歴テーブル
     */
    public KanrenshaTableHistorySeijidantaiDto getEditDto() {
        return editDto;
    }

    /**
     * 関連者政治団体履歴テーブルを設定する
     * 
     * @param editDto 関連者政治団体履歴テーブル
     */
    public void setEditDto(final KanrenshaTableHistorySeijidantaiDto editDto) {
        this.editDto = editDto;
    }

}
