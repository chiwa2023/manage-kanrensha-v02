package net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * 法人番号検索結果DTO
 */
public class SearchHoujinNoResultDto extends FrameworkMessageAndResultDto // NOPMD DataClasss
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 最終更新年月日 (ヘッダ1項目目) */
    private String updateDate = INIT_STRING;

    /** 総件数 (ヘッダ2項目目) */
    private Integer totalCount = INIT_INTEGER;

    /** 分割番号 (ヘッダ3項目目) */
    private Integer divideNumber = INIT_INTEGER;

    /** 分割数 (ヘッダ4項目目) */
    private Integer divideCount = INIT_INTEGER;

    /** 法人番号情報リスト */
    private List<HoujinNoDto> houjinNoList = new ArrayList<>();

    /**
     * 最終更新年月日を取得する
     * 
     * @return 最終更新年月日
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 最終更新年月日を設定する
     * 
     * @param updateDate 最終更新年月日
     */
    public void setUpdateDate(final String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 総件数を取得する
     * 
     * @return 総件数
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 総件数を設定する
     * 
     * @param totalCount 総件数
     */
    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 分割番号を取得する
     * 
     * @return 分割番号
     */
    public Integer getDivideNumber() {
        return divideNumber;
    }

    /**
     * 分割番号を設定する
     * 
     * @param divideNumber 分割番号
     */
    public void setDivideNumber(final Integer divideNumber) {
        this.divideNumber = divideNumber;
    }

    /**
     * 分割数を取得する
     * 
     * @return 分割数
     */
    public Integer getDivideCount() {
        return divideCount;
    }

    /**
     * 分割数を設定する
     * 
     * @param divideCount 分割数
     */
    public void setDivideCount(final Integer divideCount) {
        this.divideCount = divideCount;
    }

    /**
     * 法人番号情報リストを取得する
     * 
     * @return 法人番号情報リスト
     */
    public List<HoujinNoDto> getHoujinNoList() {
        return houjinNoList;
    }

    /**
     * 法人番号情報リストを設定する
     * 
     * @param houjinNoList 法人番号情報リスト
     */
    public void setHoujinNoList(final List<HoujinNoDto> houjinNoList) {
        this.houjinNoList = houjinNoList;
    }
}
