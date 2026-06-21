package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;

/**
 * 住居差分ワークテーブル変更Dto
 */
public class EditWktblRsdtChangeCapsuleDto extends FrameworkCapsuleDto //
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 住所差分ワークテーブル変更Entity */
    private WkTblAddressRsdtChangeEntity editEntity = new WkTblAddressRsdtChangeEntity();

    /**
     * 住所差分ワークテーブル変更Entityを取得する
     * 
     * @return 住所差分ワークテーブル変更Entity
     */
    public WkTblAddressRsdtChangeEntity getEditEntity() {
        return editEntity;
    }

    /**
     * 住所差分ワークテーブル変更Entityを設定する
     * 
     * @param editEntity 住所差分ワークテーブル変更Entity
     */
    public void setEditEntity(final WkTblAddressRsdtChangeEntity editEntity) {
        this.editEntity = editEntity;
    }

}
