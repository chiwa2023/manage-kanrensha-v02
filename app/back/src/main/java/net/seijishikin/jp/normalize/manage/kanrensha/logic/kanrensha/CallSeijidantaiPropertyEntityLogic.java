package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;

/**
 * MasterPoliticalOrganizationPropertyEntityをDBから呼び出して返却するLogic
 *
 */
@Component
public class CallSeijidantaiPropertyEntityLogic {

    /** 関連者政治団体住所リポジトリ */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    /**
     * MasterPoliticalOrganizationPropertyEntityをDBから呼び出して返却する
     *
     * @param seijidantaiKanrenshaCode 関連者政治団体コード
     * @return MasterPoliticalOrganizationPropertyEntity
     */
    public KanrenshaSeijidantaiPropertyEntity practice(final String seijidantaiKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UnchekedException
        List<KanrenshaSeijidantaiPropertyEntity> propertyList = kanrenshaSeijidantaiPropertyRepository
                .findBySeijidantaiKanrenshaCodeOrderByKanrenshaSeijidantaiPropertyIdDesc(
                        seijidantaiKanrenshaCode);

        if (propertyList.isEmpty()) {
            KanrenshaSeijidantaiPropertyEntity newEntity = new KanrenshaSeijidantaiPropertyEntity();
            newEntity.setSeijidantaiKanrenshaCode(seijidantaiKanrenshaCode);
            return newEntity;
        }

        List<KanrenshaSeijidantaiPropertyEntity> latestList = propertyList.stream()
                .filter(KanrenshaSeijidantaiPropertyEntity::getIsLatest).collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        }
        if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. seijidantaiKanrenshaCode: " + seijidantaiKanrenshaCode);
        }

        return propertyList.get(0);
    }
}
