package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;

/**
 * 関連者マスタ取得結果Dto
 */
public class GetKanrenshaMasterResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者個人マスタEntity */
    private KanrenshaPersonMasterEntity masterPersonEntity = new KanrenshaPersonMasterEntity();

    /** 関連者企業団体マスタEntity */
    private KanrenshaKigyouDtMasterEntity masterKigyouDtEntity = new KanrenshaKigyouDtMasterEntity();

    /** 関連者政治団体マスタEntity */
    private KanrenshaSeijidantaiMasterEntity masterSeijidantaiEntity = new KanrenshaSeijidantaiMasterEntity();

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
