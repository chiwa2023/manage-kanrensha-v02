package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 関連者コード移動履歴検索Dto
 */
public class MoveKanrenshaCodeHistoryCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 廃止コード */
    private String codeAbolish = INIT_STRING;

    /** 存続コード */
    private String codeOrgin = INIT_STRING;

    /** 関連者区分 */
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * 廃止コードを取得する
     * 
     * @return 廃止コード
     */
    public String getCodeAbolish() {
        return codeAbolish;
    }

    /**
     * 廃止コードを設定する
     * 
     * @param codeAbolish 廃止コード
     */
    public void setCodeAbolish(final String codeAbolish) {
        this.codeAbolish = codeAbolish;
    }

    /**
     * 存続コードを取得する
     * 
     * @return 存続コード
     */
    public String getCodeOrgin() {
        return codeOrgin;
    }

    /**
     * 存続コードを設定する
     * 
     * @param codeOrgin 存続コード
     */
    public void setCodeOrgin(final String codeOrgin) {
        this.codeOrgin = codeOrgin;
    }

    /**
     * 関連者区分を取得する
     * 
     * @return 関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 関連者区分を設定する
     * 
     * @param kanrenshaKbn 関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

}
