package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * 番地まで住所から住居詳細を取得する
 */
@Service
public class GetPostalCodeRegistoryByBlockService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param lgCode 地方自治体コード
     * @param block  番地まで住所
     */
    public AddressRsdtTemplateEntity practice(final String lgCode, final String block) {

        // 選択した建物まで住所に紐づく住所詳細を取得する
        // TODO 基本的には1番地まで住所+建物なしは一意になるようにする予定だが仮でorder by で一意にしておく
        try {
            String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_block = '" + block
                    + "' AND address_building = '' AND is_latest = 1 ORDER BY address_rsdt_id DESC";
            Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
            return (AddressRsdtTemplateEntity) query.getResultList().get(0);

        } catch (IndexOutOfBoundsException | SQLGrammarException exception) {
            // テーブルが存在しない、または0件で呼び出せない場合はnull
            return null;
        }

    }

}
