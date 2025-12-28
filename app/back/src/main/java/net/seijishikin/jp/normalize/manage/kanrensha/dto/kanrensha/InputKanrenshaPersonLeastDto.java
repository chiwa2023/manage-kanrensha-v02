package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

/**
 * 関連者個人最小Dto
 */
public class InputKanrenshaPersonLeastDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 関連者個人姓名 */
    private String personName = INIT_String;

    /** 関連者個人コード */
    private String personKanrenshaCode = INIT_String;

    /**
     * 関連者個人姓名を取得する
     *
     * @return 関連者個人姓名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 関連者個人姓名を設定する
     *
     * @param personName 関連者個人姓名
     */
    public void setPersonName(final String personName) {
        this.personName = personName;
    }

    /**
     * 関連者個人コードを取得する
     *
     * @return 関連者個人コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 関連者個人コードを設定する
     *
     * @param personKanrenshaCode 関連者個人コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

}
