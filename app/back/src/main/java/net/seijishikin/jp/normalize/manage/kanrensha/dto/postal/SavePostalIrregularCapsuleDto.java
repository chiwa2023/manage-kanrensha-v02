package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * 郵便番号不規則更新内容Dto
 */
public class SavePostalIrregularCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 郵便番号不規則Entity */
    private AddressPostalIrregularEntity addressPostalIrregularEntity;

    /**
     * 郵便番号不規則Entityを取得する
     *
     * @return 郵便番号不規則Entity
     */
    public AddressPostalIrregularEntity getAddressPostalIrregularEntity() {
        return addressPostalIrregularEntity;
    }

    /**
     * 郵便番号不規則Entityを設定する
     *
     * @param addressPostalIrregularEntity 郵便番号不規則Entity
     */
    public void setAddressPostalIrregularEntity(final AddressPostalIrregularEntity addressPostalIrregularEntity) {
        this.addressPostalIrregularEntity = addressPostalIrregularEntity;
    }

}
