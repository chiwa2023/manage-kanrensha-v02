package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;

/**
 * 関連者企業団体コードに紐づく企業団体連絡先取得Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallKigyouDtAccessEntityLogic {

    /** マスタ企業団体連絡先Repository */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /**
     * 処理を行う
     *
     * @param kigyouDtKanrenshaCode 関連者企業団体コード
     * @return 企業団体連絡先情報
     */
    public KanrenshaKigyouDtAccessEntity practice(final String kigyouDtKanrenshaCode) {
        // データベースよりkigyouDtKanrenshaCodeが一致し、かつテーブルIdの降順で、リストを取得
        List<KanrenshaKigyouDtAccessEntity> entities = kanrenshaKigyouDtAccessRepository
                .findByKigyouDtKanrenshaCodeOrderByKanrenshaKigyouDtAccessIdDesc(kigyouDtKanrenshaCode);

        // 取得したリストが空の場合、新たにEntityのインスタンスを作成し、kigyouDtKanrenshaCodeだけを設定して返却
        if (entities.isEmpty()) {
            KanrenshaKigyouDtAccessEntity newEntity = new KanrenshaKigyouDtAccessEntity();
            newEntity.setKigyouDtKanrenshaCode(kigyouDtKanrenshaCode);
            return newEntity;
        }

        // 取得したリストをisLatestがtrueでフィルタ
        List<KanrenshaKigyouDtAccessEntity> latestEntities = entities.stream()
                .filter(KanrenshaKigyouDtAccessEntity::getIsLatest).collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        // isLatestがtrueのものが1件の場合、その1件のEntityを返却
        if (nomalCnt == latestEntities.size()) {
            return latestEntities.get(0);
        }

        // isLatestがtrueのものが複数の場合、例外を投げる
        if (nomalCnt < latestEntities.size()) {
            throw new DataRetrievalFailureException(
                    "Multiple latest records found for kigyouDtKanrenshaCode: " + kigyouDtKanrenshaCode);
        }

        // isLatestがtrueのものが0件の場合、フィルタ前の最初の1件(isLatestがfalseでテーブルIdが最も大きい1件)を返却
        return entities.get(0);
    }
}
