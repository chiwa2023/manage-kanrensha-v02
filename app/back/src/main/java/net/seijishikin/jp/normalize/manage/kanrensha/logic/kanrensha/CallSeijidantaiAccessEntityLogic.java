package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;

/**
 * 関連者政治団体コードに紐づく政治団体連絡先取得Logic
 *
 */
@Component
public class CallSeijidantaiAccessEntityLogic {

    /** マスタ政治団体連絡先Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /**
     * 処理を行う
     *
     * @param seijidantaiKanrenshaCode 関連者政治団体コード
     * @return 政治団体連絡先情報
     */
    public KanrenshaSeijidantaiAccessEntity practice(final String seijidantaiKanrenshaCode) {
        // データベースよりseijidantaiKanrenshaCodeが一致し、かつテーブルIdの降順で、リストを取得
        List<KanrenshaSeijidantaiAccessEntity> entities = kanrenshaSeijidantaiAccessRepository
                .findBySeijidantaiKanrenshaCodeOrderByKanrenshaSeijidantaiAccessIdDesc(seijidantaiKanrenshaCode);

        // 取得したリストが空の場合、新たにEntityのインスタンスを作成し、seijidantaiKanrenshaCodeだけを設定して返却
        if (entities.isEmpty()) {
            KanrenshaSeijidantaiAccessEntity newEntity = new KanrenshaSeijidantaiAccessEntity();
            newEntity.setSeijidantaiKanrenshaCode(seijidantaiKanrenshaCode);
            return newEntity;
        }

        // 取得したリストをisLatestがtrueでフィルタ
        List<KanrenshaSeijidantaiAccessEntity> latestEntities = entities.stream()
                .filter(KanrenshaSeijidantaiAccessEntity::getIsLatest).collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        // isLatestがtrueのものが1件の場合、その1件のEntityを返却
        if (nomalCnt == latestEntities.size()) {
            return latestEntities.get(0);
        }

        // isLatestがtrueのものが複数の場合、例外を投げる
        if (nomalCnt < latestEntities.size()) {
            throw new DataRetrievalFailureException(
                    "Multiple latest records found for seijidantaiKanrenshaCode: " + seijidantaiKanrenshaCode);
        }

        // isLatestがtrueのものが0件の場合、フィルタ前の最初の1件(isLatestがfalseでテーブルIdが最も大きい1件)を返却
        return entities.get(0);
    }
}
