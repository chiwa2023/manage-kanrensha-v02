package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * year_option接続用Entity
 */
@Entity
@Table(name = "year_option")
public class YearOptionEntity // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 選択年 */
    @Id
    @Column(name = "selected_year")
    private Integer selectedYear = INIT_INTEGER;

    /** 選択該否 */
    @Column(name = "is_selected")
    private Boolean isSelected = INIT_BOOLEAN;

    /**
     * 選択年
     * 
     * @return 選択年
     */
    public Integer getSelectedYear() {
        return selectedYear;
    }

    /**
     * 選択年
     * 
     * @param selectedYear 選択年
     */
    public void setSelectedYear(final Integer selectedYear) {
        this.selectedYear = selectedYear;
    }

    /**
     * 選択該否
     * 
     * @return 選択該否
     */
    public Boolean getIsSelected() {
        return isSelected;
    }

    /**
     * 選択該否
     * 
     * @param isSelected 選択該否
     */
    public void setIsSelected(final Boolean isSelected) {
        this.isSelected = isSelected;
    }

}
