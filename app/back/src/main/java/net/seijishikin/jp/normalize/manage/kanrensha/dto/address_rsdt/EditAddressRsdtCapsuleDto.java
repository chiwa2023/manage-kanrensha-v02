package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * アドレス・ベース・レジストリ編集Dto
 */
public class EditAddressRsdtCapsuleDto extends FrameworkCapsuleDto //
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** アドレス・ベース・レジストリEntity */
    private AddressRsdtTemplateEntity editEntity = new AddressRsdtTemplateEntity();

    /**
     * アドレス・ベース・レジストリEntityを取得する
     * 
     * @return アドレス・ベース・レジストリEntity
     */
    public AddressRsdtTemplateEntity getEditEntity() {
        return editEntity;
    }

    /**
     * アドレス・ベース・レジストリEntityを設定する
     * 
     * @param editEntity アドレス・ベース・レジストリEntity
     */
    public void setEditEntity(final AddressRsdtTemplateEntity editEntity) {
        this.editEntity = editEntity;
    }

}
