package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterRepository;


/**
 * 関連者企業・団体マスタ標準処理結果登録Processor
 */
@Component
public class MasterKigyouDtAddStdWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaKigyouDtMasterResultEntity, WkTblKanrenshaKigyouDtMasterEntity> {

    /** 関連者企業・団体マスタ標準Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterRepository wkTblKanrenshaKigyouDtMasterRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaKigyouDtMasterEntity process(final WkTblKanrenshaKigyouDtMasterResultEntity item) throws Exception {

        WkTblKanrenshaKigyouDtMasterEntity entity = new WkTblKanrenshaKigyouDtMasterEntity();

        Optional<WkTblKanrenshaKigyouDtMasterEntity> optional = wkTblKanrenshaKigyouDtMasterRepository
                .findById(item.getWkTblKanrenshaKigyouDtMasterId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }

}
