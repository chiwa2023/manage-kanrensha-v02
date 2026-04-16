package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;

/**
 * 関連者政治団体判定から編集用のワークテーブルを設定する
 */
@Component
public class KanrenshaSeijidantaiWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaSeijidantaiHistoryResultEntity, WkTblKanrenshaSeijidantaiHistoryEntity> {

    /** 関連者政治団体ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTblKanrenshaSeijidantaiHistoryRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaSeijidantaiHistoryEntity process(final WkTblKanrenshaSeijidantaiHistoryResultEntity item) throws Exception {

        WkTblKanrenshaSeijidantaiHistoryEntity entity = new WkTblKanrenshaSeijidantaiHistoryEntity();

        Optional<WkTblKanrenshaSeijidantaiHistoryEntity> optional = wkTblKanrenshaSeijidantaiHistoryRepository
                .findById(item.getWkKanrenshaSeijidantaiHistoryId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsAffected(item.getIsAffected());
            entity.setJudgeReason(item.getJudgeReason());
        }

        return entity;
    }

}
