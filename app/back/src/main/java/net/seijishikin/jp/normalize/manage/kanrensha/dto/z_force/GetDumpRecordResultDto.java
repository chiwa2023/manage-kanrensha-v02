package net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.DumpRecordEntity;

/**
 * ダンプ記録取得結果Dto
 */
public class GetDumpRecordResultDto extends FrameworkMessageAndResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ダンプ記録リスト */
    private List<DumpRecordEntity> listEntiy = new ArrayList<>();

    /**
     * ダンプ記録リストを取得する
     * 
     * @return ダンプ記録リスト
     */
    public List<DumpRecordEntity> getListEntiy() {
        return listEntiy;
    }

    /**
     * ダンプ記録リストを設定する
     * 
     * @param listEntiy ダンプ記録リスト
     */
    public void setListEntiy(final List<DumpRecordEntity> listEntiy) {
        this.listEntiy = listEntiy;
    }
}
