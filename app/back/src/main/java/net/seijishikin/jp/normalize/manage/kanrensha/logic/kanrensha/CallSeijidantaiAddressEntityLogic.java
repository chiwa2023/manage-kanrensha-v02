package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;

/**
 * MasterPoliticalOrganizationAddressEntityをDBから呼び出して返却するLogic
 *
 */
@Component
public class CallSeijidantaiAddressEntityLogic {

    /** 関連者政治団体住所リポジトリ */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /**
     * MasterPoliticalOrganizationAddressEntityをDBから呼び出して返却する
     *
     * @param seijidantaiKanrenshaCode 関連者政治団体コード
     * @return MasterPoliticalOrganizationAddressEntity
     */
    public KanrenshaSeijidantaiAddressEntity practice(final String seijidantaiKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<KanrenshaSeijidantaiAddressEntity> addressList = kanrenshaSeijidantaiAddressRepository
                .findBySeijidantaiKanrenshaCodeOrderByKanrenshaSeijidantaiAddressIdDesc(
                        seijidantaiKanrenshaCode);

        if (addressList.isEmpty()) {
            KanrenshaSeijidantaiAddressEntity newEntity = new KanrenshaSeijidantaiAddressEntity();
            newEntity.setSeijidantaiKanrenshaCode(seijidantaiKanrenshaCode);
            return newEntity;
        }

        List<KanrenshaSeijidantaiAddressEntity> latestList = addressList.stream()
                .filter(KanrenshaSeijidantaiAddressEntity::getIsLatest).collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        }
        if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. seijidantaiKanrenshaCode: " + seijidantaiKanrenshaCode);
        }
        
        return addressList.get(0);
    }
}
