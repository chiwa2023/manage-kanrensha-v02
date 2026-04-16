package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;

/**
 * 関連者個人判定から編集用のワークテーブルを設定する
 */
@Component
public class KanrenshaPersonWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaPersonHistoryResultEntity, WkTblKanrenshaPersonHistoryEntity> {

    /** 関連者個人ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTblKanrenshaPersonHistoryRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaPersonHistoryEntity process(final WkTblKanrenshaPersonHistoryResultEntity item) throws Exception {

        WkTblKanrenshaPersonHistoryEntity entity = new WkTblKanrenshaPersonHistoryEntity();

        Optional<WkTblKanrenshaPersonHistoryEntity> optional = wkTblKanrenshaPersonHistoryRepository
                .findById(item.getWkKanrenshaPersonHistoryId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsAffected(item.getIsAffected());
            entity.setJudgeReason(item.getJudgeReason());
        }

        return entity;
    }

}
