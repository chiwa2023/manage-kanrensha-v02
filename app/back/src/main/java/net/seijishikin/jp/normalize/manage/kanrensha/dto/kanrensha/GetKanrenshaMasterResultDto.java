package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaKigyouDtMasterDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaPersonMasterDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaSeijidantaiMasterDto;

/**
 * 関連者マスタ取得結果Dto
 */
public class GetKanrenshaMasterResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者個人マスタEntity */
    private KanrenshaPersonMasterDto masterPersonEntity = new KanrenshaPersonMasterDto();

    /** 関連者企業団体マスタEntity */
    private KanrenshaKigyouDtMasterDto masterKigyouDtEntity = new KanrenshaKigyouDtMasterDto();

    /** 関連者政治団体マスタEntity */
    private KanrenshaSeijidantaiMasterDto masterSeijidantaiEntity = new KanrenshaSeijidantaiMasterDto();

    /**
     * 関連者個人マスタEntityを取得する
     * 
     * @return 関連者個人マスタEntity
     */
    public KanrenshaPersonMasterDto getMasterPersonEntity() {
        return masterPersonEntity;
    }

    /**
     * 関連者個人マスタEntityを設定する
     * 
     * @param masterPersonEntity 関連者個人マスタEntity
     */
    public void setMasterPersonEntity(final KanrenshaPersonMasterDto masterPersonEntity) {
        this.masterPersonEntity = masterPersonEntity;
    }

    /**
     * 関連者企業団体マスタEntityを取得する
     * 
     * @return 関連者企業団体マスタEntity
     */
    public KanrenshaKigyouDtMasterDto getMasterKigyouDtEntity() {
        return masterKigyouDtEntity;
    }

    /**
     * 関連者企業団体マスタEntityを設定する
     * 
     * @param masterKigyouDtEntity 関連者企業団体マスタEntity
     */
    public void setMasterKigyouDtEntity(final KanrenshaKigyouDtMasterDto masterKigyouDtEntity) {
        this.masterKigyouDtEntity = masterKigyouDtEntity;
    }

    /**
     * 関連者政治団体マスタEntityを取得する
     * 
     * @return 関連者政治団体マスタEntity
     */
    public KanrenshaSeijidantaiMasterDto getMasterSeijidantaiEntity() {
        return masterSeijidantaiEntity;
    }

    /**
     * 関連者政治団体マスタEntityを設定する
     * 
     * @param masterSeijidantaiEntity 関連者政治団体マスタEntity
     */
    public void setMasterSeijidantaiEntity(final KanrenshaSeijidantaiMasterDto masterSeijidantaiEntity) {
        this.masterSeijidantaiEntity = masterSeijidantaiEntity;
    }

}
