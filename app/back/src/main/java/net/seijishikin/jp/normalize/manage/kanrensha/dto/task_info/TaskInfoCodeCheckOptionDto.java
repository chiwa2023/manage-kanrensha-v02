package net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * タスク情報選択肢Dto
 */
public class TaskInfoCodeCheckOptionDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * 
     * @param isChecked チェック該非
     * @param codeValue タスク情報コード
     * @param codeName  タスク情報名称
     */
    public TaskInfoCodeCheckOptionDto(final Long isChecked, final Integer codeValue, final String codeName) {
        super();
        this.isChecked = isChecked == 1L;
        this.codeValue = codeValue;
        this.codeName = codeName;
    }

    /** checkboxの値 */
    @Column(name = "is_checked")
    private Boolean isChecked;

    /** タスク情報コード */
    @Column(name = "code_value")
    private Integer codeValue;

    /** コード名称 */
    @Column(name = "code_name")
    private String codeName;

    /**
     * checkboxの値を取得する
     * 
     * @return checkboxの値
     */
    public Boolean getIsChecked() {
        return isChecked;
    }

    /**
     * checkboxの値を設定する
     * 
     * @param isChecked checkboxの値
     */
    public void setIsChecked(final Boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * タスク情報コードを取得する
     * 
     * @return タスク情報コード
     */
    public Integer getCodeValue() {
        return codeValue;
    }

    /**
     * タスク情報コードを設定する
     * 
     * @param codeValue タスク情報コード
     */
    public void setCodeValue(final Integer codeValue) {
        this.codeValue = codeValue;
    }

    /**
     * コード名称を取得する
     * 
     * @return コード名称
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * コード名称を設定する
     * 
     * @param codeName コード名称
     */
    public void setCodeName(final String codeName) {
        this.codeName = codeName;
    }

}
