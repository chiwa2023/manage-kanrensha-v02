package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;

/**
 * 関連者政治団体処理結果ワークテーブル変換Processor
 */
@Component
public class KanrenshaSeijidantaiAddMiniWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaSeijidantaiAddMinResultEntity, WkTblKanrenshaSeijidantaiAddMinEntity> {

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaSeijidantaiAddMinEntity process(final WkTblKanrenshaSeijidantaiAddMinResultEntity item) throws Exception {

        WkTblKanrenshaSeijidantaiAddMinEntity entity = new WkTblKanrenshaSeijidantaiAddMinEntity();

        Optional<WkTblKanrenshaSeijidantaiAddMinEntity> optional = wkTblKanrenshaSeijidantaiAddMinRepository
                .findById(item.getWkTblKanrenshaSeijidantaiAddMinId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
