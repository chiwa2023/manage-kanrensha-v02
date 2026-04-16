package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;

/**
 * 関連者企業・団体処理結果ワークテーブル変換Processor
 */
@Component
public class KanrenshaKigyouDtAddMiniWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaKigyouDtAddMinResultEntity, WkTblKanrenshaKigyouDtAddMinEntity> {

    /** 関連者企業・団体登録最小限Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaKigyouDtAddMinEntity process(final WkTblKanrenshaKigyouDtAddMinResultEntity item) throws Exception {

        WkTblKanrenshaKigyouDtAddMinEntity entity = new WkTblKanrenshaKigyouDtAddMinEntity();

        Optional<WkTblKanrenshaKigyouDtAddMinEntity> optional = wkTblKanrenshaKigyouDtAddMinRepository
                .findById(item.getWkTblKanrenshaKigyouDtAddMinId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
