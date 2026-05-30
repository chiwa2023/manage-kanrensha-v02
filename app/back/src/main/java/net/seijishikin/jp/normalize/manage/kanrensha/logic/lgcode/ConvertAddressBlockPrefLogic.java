package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * 番地まで住所に県前付加Logic
 */
@Component
public class ConvertAddressBlockPrefLogic {

    /** 市区町村Repository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * 処理を行う
     * 
     * @param entity アドレス・ベース・レジストリEntity
     * @return 番地まで住所
     */
    public String practice(final AddressRsdtBaseEntity entity) {

        List<AddressAllCityEntity> list = addressAllCityRepository.findByLgCodeAndIsLatestTrue(entity.getLgCode());

        if (list.isEmpty()) {
            return "行政区コードなし:" + entity.getAddressBlock();
        } else {
            AddressAllCityEntity cityEntity = list.get(0);

            String address = entity.getAddressBlock();

            if (!EMPTY.equals(cityEntity.getWard()) && address.startsWith(cityEntity.getWard())) {
                return cityEntity.getPref() + cityEntity.getCounty() + cityEntity.getCity() + entity.getAddressBlock();
            }

            if (!EMPTY.equals(cityEntity.getCity()) && address.startsWith(cityEntity.getCity())) {
                return cityEntity.getPref() + cityEntity.getCounty() + entity.getAddressBlock();
            }

            if (!EMPTY.equals(cityEntity.getCounty()) && address.startsWith(cityEntity.getCounty())) {
                return cityEntity.getPref() + entity.getAddressBlock();
            }

            if (address.startsWith(cityEntity.getPref())) {
                return entity.getAddressBlock();
            }
            return "コード不一致:" + entity.getAddressBlock();
        }
    }

}
