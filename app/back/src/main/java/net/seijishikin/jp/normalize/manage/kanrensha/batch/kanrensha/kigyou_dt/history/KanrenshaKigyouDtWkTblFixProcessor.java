package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryRepository;

/**
 * 関連者企業・団体判定から編集用のワークテーブルを設定する
 */
@Component
public class KanrenshaKigyouDtWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaKigyouDtHistoryResultEntity, WkTblKanrenshaKigyouDtHistoryEntity> {

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTblKanrenshaKigyouDtHistoryRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaKigyouDtHistoryEntity process(final WkTblKanrenshaKigyouDtHistoryResultEntity item) throws Exception {

        WkTblKanrenshaKigyouDtHistoryEntity entity = new WkTblKanrenshaKigyouDtHistoryEntity();

        Optional<WkTblKanrenshaKigyouDtHistoryEntity> optional = wkTblKanrenshaKigyouDtHistoryRepository
                .findById(item.getWkKanrenshaKigyouDtHistoryId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsAffected(item.getIsAffected());
            entity.setJudgeReason(item.getJudgeReason());
        }

        return entity;
    }

}
