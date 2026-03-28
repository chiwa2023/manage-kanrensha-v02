package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * 旧字不存在検索Logic
 */
@Component
public class SearchNotExistKyuAzaLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param lgCode 地方行政区コード
     * @param kyuAza 旧字地名
     * @return 存在有無確認
     */
    public Boolean practice(final String lgCode, final String kyuAza) {

        // 旧字名称が存在しないことを確認
        String sql = "SELECT * FROM address_rsdt_" + lgCode + " WHERE address_block LIKE '%" + kyuAza + "%' "
                + " AND is_latest =1 LIMIT 1";
        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);

        return query.getResultList().isEmpty();
    }

}
