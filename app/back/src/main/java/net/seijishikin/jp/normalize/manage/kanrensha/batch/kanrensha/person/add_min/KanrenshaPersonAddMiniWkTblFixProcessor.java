package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;

/**
 * 関連者個人処理結果ワークテーブル変換Processor
 */
@Component
public class KanrenshaPersonAddMiniWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaPersonAddMinResultEntity, WkTblKanrenshaPersonAddMinEntity> {

    /** 関連者個人登録最小限Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaPersonAddMinEntity process(final WkTblKanrenshaPersonAddMinResultEntity item) throws Exception {

        WkTblKanrenshaPersonAddMinEntity entity = new WkTblKanrenshaPersonAddMinEntity();

        Optional<WkTblKanrenshaPersonAddMinEntity> optional = wkTblKanrenshaPersonAddMinRepository
                .findById(item.getWkTblKanrenshaPersonAddMinId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
