package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

/**
 * 関連者マスタ取得条件Dto
 */
public class GetKanrenshaMasterCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者コード */
    private String kanrenshaCode;

    /** 関連者権限 */
    private String kanrenshaRole;

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

    /**
     * 関連者権限を取得する
     * 
     * @return 関連者権限
     */
    public String getKanrenshaRole() {
        return kanrenshaRole;
    }

    /**
     * 関連者権限を設定する
     * 
     * @param kanrenshaRole 関連者権限
     */
    public void setKanrenshaRole(final String kanrenshaRole) {
        this.kanrenshaRole = kanrenshaRole;
    }

}
