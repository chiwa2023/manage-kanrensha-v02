package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.PostalCodeCsvOneLineDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * 字・大字必要確認Logic
 */
@Component
public class CheckPostalToMachiazaLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 空白文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     * 
     * @param item   郵便番号情報
     * @param lgCode 地方自治体コード(チェックデジットあり6桁)
     * @return 付加文字
     */
    public String practice(final PostalCodeCsvOneLineDto item, final String lgCode) {

        String addressCity = item.getPref() + item.getCity();
        String addressName = GetPreParenthesesLogic.practice(item.getAddressOrg());

        String checkOrg = this.checkOrg(lgCode, addressCity, addressName);
        if (!Objects.isNull(checkOrg)) {
            return checkOrg;
        }
        String checkAza = this.checkAza(lgCode, addressCity, addressName);
        if (!Objects.isNull(checkAza)) {
            return checkAza;
        }

        String checkOhAza = this.checkOhAza(lgCode, addressCity, addressName);
        if (!Objects.isNull(checkOhAza)) {
            return checkOhAza;
        }

        return BLANK;
    }

    private String checkOrg(final String lgCode, final String addressCity, final String addressName) {
        return this.checkData(lgCode, addressCity, addressName, BLANK);
    }

    private String checkAza(final String lgCode, final String addressCity, final String addressName) {

        return this.checkData(lgCode, addressCity, addressName, "字");
    }

    private String checkOhAza(final String lgCode, final String addressCity, final String addressName) {

        return this.checkData(lgCode, addressCity, addressName, "大字");
    }

    private String checkData(final String lgCode, final String addressCity, final String addressName,
            final String plus) {

        // (その他)の前までの住所が共通である住所が存在すればOK
        String sql = "SELECT * FROM address_rsdt_" + lgCode + " WHERE address_block LIKE '" + addressCity + plus
                + addressName + "%' LIMIT 1";
        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);

        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return plus;
        }
    }

}
