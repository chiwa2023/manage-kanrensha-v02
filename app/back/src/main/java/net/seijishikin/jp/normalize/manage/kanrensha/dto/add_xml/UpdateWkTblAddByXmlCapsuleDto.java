package net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;


/**
 * XML一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblAddByXmlCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblMasterAllByXmlEntity getWkTblMasterAllByXmlEntity() {
        return wkTblMasterAllByXmlEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblMasterAllByXmlEntity 編集対象Entity
     */
    public void setWkTblMasterAllByXmlEntity(final WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity) {
        this.wkTblMasterAllByXmlEntity = wkTblMasterAllByXmlEntity;
    }

}
