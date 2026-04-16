package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterRepository;

/**
 * 関連者個人マスタ標準処理結果登録Processor
 */
@Component
public class MasterPersonAddStdWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaPersonMasterResultEntity, WkTblKanrenshaPersonMasterEntity> {

    /** 関連者個人マスタ標準Repository */
    @Autowired
    private WkTblKanrenshaPersonMasterRepository wkTblKanrenshaPersonMasterRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaPersonMasterEntity process(final WkTblKanrenshaPersonMasterResultEntity item) throws Exception {

        WkTblKanrenshaPersonMasterEntity entity = new WkTblKanrenshaPersonMasterEntity();

        Optional<WkTblKanrenshaPersonMasterEntity> optional = wkTblKanrenshaPersonMasterRepository
                .findById(item.getWkTblKanrenshaPersonMasterId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }

}
