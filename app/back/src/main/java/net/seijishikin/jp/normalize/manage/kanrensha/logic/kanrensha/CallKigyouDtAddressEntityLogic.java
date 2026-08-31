package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;

/**
 * MasterKigyouDtorationAddressEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallKigyouDtAddressEntityLogic {

    /** 関連者企業団体住所リポジトリ */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /**
     * MasterKigyouDtorationAddressEntityをDBから呼び出して返却する
     *
     * @param kigyouDtKanrenshaCode 関連者企業団体コード
     * @return MasterKigyouDtorationAddressEntity
     */
    public KanrenshaKigyouDtAddressEntity practice(final String kigyouDtKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<KanrenshaKigyouDtAddressEntity> addressList = kanrenshaKigyouDtAddressRepository
                .findByKigyouDtKanrenshaCodeOrderByKanrenshaKigyouDtAddressIdDesc(kigyouDtKanrenshaCode);

        if (addressList.isEmpty()) {
            KanrenshaKigyouDtAddressEntity newEntity = new KanrenshaKigyouDtAddressEntity();
            newEntity.setKigyouDtKanrenshaCode(kigyouDtKanrenshaCode);
            return newEntity;
        }

        List<KanrenshaKigyouDtAddressEntity> latestList = addressList.stream()
                .filter(KanrenshaKigyouDtAddressEntity::getIsLatest).collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        }
        if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. kigyouDtKanrenshaCode: " + kigyouDtKanrenshaCode);
        }
        
        return addressList.get(0);
    }
}
