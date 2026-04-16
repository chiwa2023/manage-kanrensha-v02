package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;

/**
 * MasterKigyouDtorationPropertyEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallKigyouDtPropertyEntityLogic {

    /** 関連者企業団体住所リポジトリ */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /**
     * MasterKigyouDtorationPropertyEntityをDBから呼び出して返却する
     *
     * @param kigyouDtKanrenshaCode 関連者企業団体コード
     * @return MasterKigyouDtorationPropertyEntity
     */
    public KanrenshaKigyouDtPropertyEntity practice(final String kigyouDtKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UnchekedException
        List<KanrenshaKigyouDtPropertyEntity> propertyList = kanrenshaKigyouDtPropertyRepository
                .findByKigyouDtKanrenshaCodeOrderByKanrenshaKigyouDtPropertyIdDesc(kigyouDtKanrenshaCode);

        if (propertyList.isEmpty()) {
            KanrenshaKigyouDtPropertyEntity newEntity = new KanrenshaKigyouDtPropertyEntity();
            newEntity.setKigyouDtKanrenshaCode(kigyouDtKanrenshaCode);
            return newEntity;
        }

        List<KanrenshaKigyouDtPropertyEntity> latestList = propertyList.stream()
                .filter(KanrenshaKigyouDtPropertyEntity::getIsLatest).collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        }
        if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. kigyouDtKanrenshaCode: " + kigyouDtKanrenshaCode);
        }
        
        return propertyList.get(0);
    }
}
