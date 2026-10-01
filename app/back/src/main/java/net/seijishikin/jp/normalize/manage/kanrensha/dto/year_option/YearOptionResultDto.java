package net.seijishikin.jp.normalize.manage.kanrensha.dto.year_option;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;

/**
 * 年切り替え選択肢取得結果Dto
 */
public class YearOptionResultDto extends FrameworkMessageAndResultDto //
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 年切り替えリスト */
    private List<YearOptionEntity> listEntity = new ArrayList<>();

    /**
     * 年切り替えリストを取得
     * 
     * @return 年切り替えリスト
     */
    public List<YearOptionEntity> getListEntity() {
        return listEntity;
    }

    /**
     * 年切り替えリストを設定する
     * 
     * @param listEntity 年切り替えリスト
     */
    public void setListEntity(final List<YearOptionEntity> listEntity) {
        this.listEntity = listEntity;
    }
}
