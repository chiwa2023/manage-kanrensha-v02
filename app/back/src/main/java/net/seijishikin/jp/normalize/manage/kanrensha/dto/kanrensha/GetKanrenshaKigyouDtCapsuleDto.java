package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;

/**
 * 関連者企業団体格納Dto
 */
public class GetKanrenshaKigyouDtCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者企業団体マスタEntity */
    private KanrenshaKigyouDtMasterEntity masterKigyouDtEntity = new KanrenshaKigyouDtMasterEntity();

    /**
     * 関連者企業団体マスタEntityを取得する
     * 
     * @return 関連者企業団体マスタEntity
     */
    public KanrenshaKigyouDtMasterEntity getMasterKigyouDtEntity() {
        return masterKigyouDtEntity;
    }

    /**
     * 関連者企業団体マスタEntityを設定する
     * 
     * @param masterKigyouDtEntity 関連者企業団体マスタEntity
     */
    public void setMasterKigyouDtEntity(final KanrenshaKigyouDtMasterEntity masterKigyouDtEntity) {
        this.masterKigyouDtEntity = masterKigyouDtEntity;
    }

}
