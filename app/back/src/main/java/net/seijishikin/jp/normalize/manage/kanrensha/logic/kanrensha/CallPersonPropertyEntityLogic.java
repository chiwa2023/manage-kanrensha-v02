package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;

/**
 * MasterPersonPropertyEntityをDBから呼び出して返却するLogic
 *
 */
@Component
public class CallPersonPropertyEntityLogic {

    /** 関連者個人住所リポジトリ */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /**
     * MasterPersonPropertyEntityをDBから呼び出して返却する
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return MasterPersonPropertyEntity
     */
    public KanrenshaPersonPropertyEntity practice(final String personKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UnchekedException
        List<KanrenshaPersonPropertyEntity> propertyList = kanrenshaPersonPropertyRepository
                .findByPersonKanrenshaCodeOrderByKanrenshaPersonPropertyIdDesc(personKanrenshaCode);

        if (propertyList.isEmpty()) {
            KanrenshaPersonPropertyEntity newEntity = new KanrenshaPersonPropertyEntity();
            newEntity.setPersonKanrenshaCode(personKanrenshaCode);
            return newEntity;
        }

        List<KanrenshaPersonPropertyEntity> latestList = propertyList.stream()
                .filter(KanrenshaPersonPropertyEntity::getIsLatest).collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        }
        if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. personKanrenshaCode: " + personKanrenshaCode);
        }
        
        return propertyList.get(0);
    }
}
