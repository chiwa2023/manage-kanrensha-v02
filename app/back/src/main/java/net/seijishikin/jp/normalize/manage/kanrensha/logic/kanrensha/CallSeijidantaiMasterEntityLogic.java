package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;

/**
 * 関連者個人コードに紐づく個人連絡先取得Logic
 *
 */
@Component
public class CallSeijidantaiMasterEntityLogic {

    /** 個人マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /**
     * 処理を行う
     *
     * @param seijidantaiKanrenshaCode 関連者個人コード
     * @return 個人連絡先情報
     */
    public KanrenshaSeijidantaiMasterEntity practice(final String seijidantaiKanrenshaCode) {
        // データベースよりpersonKanrenshaCodeが一致し、かつテーブルIdの降順で、リストを取得
        List<KanrenshaSeijidantaiMasterEntity> entities = kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeOrderByKanrenshaSeijidantaiMasterIdDesc(seijidantaiKanrenshaCode);

        // 取得したリストが空の場合、新たにEntityのインスタンスを作成し、personKanrenshaCodeだけを設定して返却
        if (entities.isEmpty()) {
            KanrenshaSeijidantaiMasterEntity newEntity = new KanrenshaSeijidantaiMasterEntity();
            newEntity.setSeijidantaiKanrenshaCode(seijidantaiKanrenshaCode);
            return newEntity;
        }

        // 取得したリストをisLatestがtrueでフィルタ
        List<KanrenshaSeijidantaiMasterEntity> latestEntities = entities.stream().filter(KanrenshaSeijidantaiMasterEntity::getIsLatest)
                .collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        // isLatestがtrueのものが1件の場合、その1件のEntityを返却
        if (nomalCnt == latestEntities.size()) {
            return latestEntities.get(0);
        }

        // isLatestがtrueのものが複数の場合、例外を投げる
        if (nomalCnt < latestEntities.size()) {
            throw new DataRetrievalFailureException(
                    "Multiple latest records found for personKanrenshaCode: " + seijidantaiKanrenshaCode);
        }

        // isLatestがtrueのものが0件の場合、フィルタ前の最初の1件(isLatestがfalseでテーブルIdが最も大きい1件)を返却
        return entities.get(0);
    }
}
