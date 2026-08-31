package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;

/**
 * MasterPersonAddressEntityをDBから呼び出して返却するLogic
 *
 */
@Component
public class CallPersonAddressEntityLogic {

    /** 関連者個人住所リポジトリ */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /**
     * MasterPersonAddressEntityをDBから呼び出して返却する
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return MasterPersonAddressEntity
     */
    public KanrenshaPersonAddressEntity practice(final String personKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<KanrenshaPersonAddressEntity> addressList = kanrenshaPersonAddressRepository
                .findByPersonKanrenshaCodeOrderByKanrenshaPersonAddressIdDesc(personKanrenshaCode);

        if (addressList.isEmpty()) {
            KanrenshaPersonAddressEntity newEntity = new KanrenshaPersonAddressEntity();
            newEntity.setPersonKanrenshaCode(personKanrenshaCode);
            return newEntity;
        }

        List<KanrenshaPersonAddressEntity> latestList = addressList.stream()
                .filter(KanrenshaPersonAddressEntity::getIsLatest).collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        }

        if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. personKanrenshaCode: " + personKanrenshaCode);
        }
        
        return addressList.get(0);
    }
}
