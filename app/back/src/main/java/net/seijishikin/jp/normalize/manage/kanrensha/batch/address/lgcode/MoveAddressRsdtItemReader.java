package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.AbstractPaginatedDataItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * アドレス・ベース・レジストリ移動ItemReader
 */
@Component
@StepScope
public class MoveAddressRsdtItemReader extends AbstractPaginatedDataItemReader<AddressRsdtBaseEntity> {

    /** EntityManager */
    private final EntityManager entityManager;

    /** 地方自治体コード */
    private final String srcLgCode;

    /**
     * コンストラクタ
     * 
     * @param entityManager EntityManager
     * @param srcLgCode     地方自治体コード
     */
    public MoveAddressRsdtItemReader(final EntityManager entityManager,
            final @Value("#{jobParameters['srcLgCode']}") String srcLgCode) {
        super();
        this.setName("rsdtAddressItemReader"); // NOPMD CallOveridableMethod
        this.entityManager = entityManager;
        this.srcLgCode = srcLgCode;
    }

    /**
     * 処理を行う
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Iterator<AddressRsdtBaseEntity> doPageRead() {
        String sql = "SELECT * FROM address_rsdt_" + srcLgCode + " WHERE is_latest = 1";

        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);

        // ページング位置の設定（AbstractPaginatedDataItemReader が裏で管理している値を適用）
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);

        // データを取得して Iterator として返す
        List<AddressRsdtBaseEntity> results = (List<AddressRsdtBaseEntity>) query.getResultList();
        return results.iterator();
    }

}
