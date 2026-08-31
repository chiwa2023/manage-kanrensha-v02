package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;

/**
 * 関連者政治団体格納Dto
 */
public class GetKanrenshaSeijidantaiCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者政治団体マスタEntity */
    private KanrenshaSeijidantaiMasterEntity masterSeijidantaiEntity = new KanrenshaSeijidantaiMasterEntity();

    /**
     * 関連者政治団体マスタEntityを取得する
     * 
     * @return 関連者政治団体マスタEntity
     */
    public KanrenshaSeijidantaiMasterEntity getMasterSeijidantaiEntity() {
        return masterSeijidantaiEntity;
    }

    /**
     * 関連者政治団体マスタEntityを設定する
     * 
     * @param masterSeijidantaiEntity 関連者政治団体マスタEntity
     */
    public void setMasterSeijidantaiEntity(final KanrenshaSeijidantaiMasterEntity masterSeijidantaiEntity) {
        this.masterSeijidantaiEntity = masterSeijidantaiEntity;
    }

}
