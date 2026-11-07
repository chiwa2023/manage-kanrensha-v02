package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 関連者紐づけマスタ最小取得Dto
 */
public class GetKanrenshaCombineLeastCapsuleDto extends FrameworkCapsuleDto //
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者コード */
    private String kanrenshaCode = INIT_STRING;

    /**
     * 関連者コードを取得する
     * 
     * @return 関連者コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 関連者コードを設定する
     * 
     * @param kanrenshaCode 関連者コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

}
