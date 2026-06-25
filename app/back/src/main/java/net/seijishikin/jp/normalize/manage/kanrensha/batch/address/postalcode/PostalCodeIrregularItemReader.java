package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号不規則データ抽出ItemReader
 */
@Component
public class PostalCodeIrregularItemReader extends RepositoryItemReader<AddressPostalEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalRepository 郵便番号Repository
     */
    public PostalCodeIrregularItemReader(final @Autowired AddressPostalRepository addressPostalRepository) {
        super(addressPostalRepository, new HashMap<String, Direction>());
        super.setMethodName("findByIsGyoseikuDataAndIsLatestTrue");

        List<Object> list = new ArrayList<>();
        list.add(false);
        super.setArguments(list); // NOPMD
    }
}
