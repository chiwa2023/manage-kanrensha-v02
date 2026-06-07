package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * 郵便番号更新内容Dto
 */
public class SavePostalCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 郵便番号Entity */
    private AddressPostalEntity addressPostalEntity = new AddressPostalEntity();

    /**
     * 郵便番号Entityを設定する
     * 
     * @return 郵便番号Entity
     */
    public AddressPostalEntity getAddressPostalEntity() {
        return addressPostalEntity;
    }

    /**
     * 郵便番号Entityを取得する
     * 
     * @param addressPostalEntity 郵便番号Entity
     */
    public void setAddressPostalEntity(final AddressPostalEntity addressPostalEntity) {
        this.addressPostalEntity = addressPostalEntity;
    }

}
