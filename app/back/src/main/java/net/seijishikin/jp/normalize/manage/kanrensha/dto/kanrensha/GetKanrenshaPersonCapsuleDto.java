package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;

/**
 * 関連者個人格納Dto
 */
public class GetKanrenshaPersonCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者個人マスタEntity */
    private KanrenshaPersonMasterEntity masterPersonEntity = new KanrenshaPersonMasterEntity();

    /**
     * 関連者個人マスタEntityを取得する
     * 
     * @return 関連者個人マスタEntity
     */
    public KanrenshaPersonMasterEntity getMasterPersonEntity() {
        return masterPersonEntity;
    }

    /**
     * 関連者個人マスタEntityを設定する
     * 
     * @param masterPersonEntity 関連者個人マスタEntity
     */
    public void setMasterPersonEntity(final KanrenshaPersonMasterEntity masterPersonEntity) {
        this.masterPersonEntity = masterPersonEntity;
    }

}
