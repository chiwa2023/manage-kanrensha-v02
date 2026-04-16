package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * レジストリコード取得Service
 */
@Service
public class GetPostalCodeRegistoryCodeService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param lgCode 地方自治体コード
     * @param rsdtId 住所テーブルId
     */
    public AddressRsdtTemplateEntity practice(final String lgCode, final Integer rsdtId) {

        // 選択した建物まで住所に紐づく住所詳細を取得する
        try {
            String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_rsdt_id = " + rsdtId;
            Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
            return (AddressRsdtTemplateEntity) query.getSingleResult(); // NOPMD LawDemeter
            
        }catch (NoResultException | SQLGrammarException exception) {
            // テーブルが存在しない、またはIdで呼び出せない場合はnull
            return null;
        }

    }

}
