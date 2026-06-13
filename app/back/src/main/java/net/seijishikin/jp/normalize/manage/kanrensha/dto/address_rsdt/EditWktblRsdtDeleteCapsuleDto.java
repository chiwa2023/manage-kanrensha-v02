package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;

/**
 * 住居差分ワークテーブル削除Dto
 */
public class EditWktblRsdtDeleteCapsuleDto extends FrameworkCapsuleDto //
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 住所差分ワークテーブル削除Entity */
    private WkTblAddressRsdtDeleteEntity editEntiy = new WkTblAddressRsdtDeleteEntity();

    /**
     * 住所差分ワークテーブル削除Entityを取得する
     * 
     * @return 住所差分ワークテーブル削除Entity
     */
    public WkTblAddressRsdtDeleteEntity getEditEntiy() {
        return editEntiy;
    }

    /**
     * 住所差分ワークテーブル削除Entityを設定する
     * 
     * @param editEntiy 住所差分ワークテーブル削除Entity
     */
    public void setEditEntiy(final WkTblAddressRsdtDeleteEntity editEntiy) {
        this.editEntiy = editEntiy;
    }

}
