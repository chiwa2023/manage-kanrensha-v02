package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;

/**
 * 郵便番号更新内容Dto
 */
public class SaveWktblPostalCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 郵便番号差分ワークテーブルEntity */
    private WkTblPostalEditEntity editEntity = new WkTblPostalEditEntity();

    /** 削除該否 */
    private Boolean isDelete = INIT_BOOLEAN;

    /**
     * 郵便番号差分ワークテーブルEntityを取得する
     * 
     * @return 郵便番号差分ワークテーブルEntity
     */
    public WkTblPostalEditEntity getEditEntity() {
        return editEntity;
    }

    /**
     * 郵便番号差分ワークテーブルEntityを設定する
     * 
     * @param editEntity 郵便番号差分ワークテーブルEntity
     */
    public void setEditEntity(final WkTblPostalEditEntity editEntity) {
        this.editEntity = editEntity;
    }

    /**
     * 削除該否を取得する
     * 
     * @return 削除該否
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 削除該否を設定する
     * 
     * @param isDelete 削除該否
     */
    public void setIsDelete(final Boolean isDelete) {
        this.isDelete = isDelete;
    }

}
