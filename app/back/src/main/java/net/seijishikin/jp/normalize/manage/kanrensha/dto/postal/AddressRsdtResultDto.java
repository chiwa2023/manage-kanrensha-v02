package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * 住所住居明細検索結果Dto
 */
public class AddressRsdtResultDto extends FrameworkMessageAndResultDto //
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 住所住居詳細 */
    private AddressRsdtTemplateEntity addressRsdtEntity;

    /**
     * 住所住居詳細を取得する
     * 
     * @return 住所住居詳細
     */
    public AddressRsdtTemplateEntity getAddressRsdtEntity() {
        return addressRsdtEntity;
    }

    /**
     * 住所住居詳細を設定する
     * 
     * @param addressRsdtEntity 住所住居詳細
     */
    public void setAddressRsdtEntity(final AddressRsdtTemplateEntity addressRsdtEntity) {
        this.addressRsdtEntity = addressRsdtEntity;
    }

}
