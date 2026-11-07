package net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * ダンプ記録検索条件Dto
 */
public class GetDumpRecordCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ダンプ記録リスト */
    private List<Integer> listTaskCode = new ArrayList<>();

    /**
     * ダンプ記録リストを取得する
     * 
     * @return ダンプ記録リスト
     */
    public List<Integer> getListTaskCode() {
        return listTaskCode;
    }

    /**
     * ダンプ記録リストを設定する
     * 
     * @param listTaskCode ダンプ記録リスト
     */
    public void setListTaskCode(final List<Integer> listTaskCode) {
        this.listTaskCode = listTaskCode;
    }

}
