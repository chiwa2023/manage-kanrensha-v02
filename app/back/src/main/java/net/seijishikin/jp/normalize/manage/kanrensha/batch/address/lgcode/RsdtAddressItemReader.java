package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.data.AbstractPaginatedDataItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * 指定住居最新ItemReader
 */
@Component
@StepScope
public class RsdtAddressItemReader extends AbstractPaginatedDataItemReader<AddressRsdtBaseEntity> {

    /** EntityManager */
    private final EntityManager entityManager;

    /** 地方自治体コード */
    private final String lgCode;

    /**
     * コンストラクタ
     * 
     * @param entityManager EntityManager
     * @param lgCode        地方自治体コード
     */
    public RsdtAddressItemReader(final EntityManager entityManager,
            final @Value("#{jobParameters['lgCode']}") String lgCode) {
        super();
        this.setName("rsdtAddressItemReader"); // NOPMD CallOveridableMethod
        this.entityManager = entityManager;
        this.lgCode = lgCode;
    }

    /**
     * 処理を行う
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Iterator<AddressRsdtBaseEntity> doPageRead() {
        String sql = "SELECT * FROM address_rsdt_" + lgCode + " WHERE is_latest = 1 AND address_building = ''";

        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);

        // ページング位置の設定（AbstractPaginatedDataItemReader が裏で管理している値を適用）
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);

        // データを取得して Iterator として返す
        List<AddressRsdtBaseEntity> results = (List<AddressRsdtBaseEntity>) query.getResultList();
        return results.iterator();
    }

}
