package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;

/**
 * 地方自治体コード削除編集条件Dto
 */
public class EditAddressCityDeleteCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード削除編集Entity */
    private AddressCityDeleteEntity editEntity = new AddressCityDeleteEntity();

    /**
     * 地方自治体コード削除編集Entityを取得する
     * 
     * @return 地方自治体コード削除編集Entity
     */
    public AddressCityDeleteEntity getEditEntity() {
        return editEntity;
    }

    /**
     * 地方自治体コード削除編集Entityを設定する
     * 
     * @param editEntity 地方自治体コード削除編集Entity
     */
    public void setEditEntity(final AddressCityDeleteEntity editEntity) {
        this.editEntity = editEntity;
    }

    /** 移行地方自治体コード */
    private String moveLgCode = INIT_STRING;

    /** 編集地方自治体名(置換対象) */
    private String srcLgName = INIT_STRING;

    /** 移行地方自治体名(置換対象) */
    private String moveLgName = INIT_STRING;

    /**
     * 移行地方自治体コードを取得する
     * 
     * @return 移行地方自治体コード
     */
    public String getMoveLgCode() {
        return moveLgCode;
    }

    /**
     * 移行地方自治体コードを設定する
     * 
     * @param moveLgCode 移行地方自治体コード
     */
    public void setMoveLgCode(final String moveLgCode) {
        this.moveLgCode = moveLgCode;
    }

    /**
     * 編集地方自治体名(置換対象)を取得する
     * 
     * @return 編集地方自治体名(置換対象)
     */
    public String getSrcLgName() {
        return srcLgName;
    }

    /**
     * 編集地方自治体名(置換対象)を設定する
     * 
     * @param srcLgName 編集地方自治体名(置換対象)
     */
    public void setSrcLgName(final String srcLgName) {
        this.srcLgName = srcLgName;
    }

    /**
     * 移行地方自治体名(置換対象)を取得する
     * 
     * @return 移行地方自治体名(置換対象)
     */
    public String getMoveLgName() {
        return moveLgName;
    }

    /**
     * 移行地方自治体名(置換対象)を設定する
     * 
     * @param moveLgName 移行地方自治体名(置換対象)
     */
    public void setMoveLgName(final String moveLgName) {
        this.moveLgName = moveLgName;
    }

}
