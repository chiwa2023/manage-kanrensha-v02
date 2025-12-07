package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiMasterRepository;


/**
 * 関連者個人マスタ標準処理結果登録Processor
 */
@Component
public class MasterSeijidantaiAddStdWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaSeijidantaiMasterResultEntity, WkTblKanrenshaSeijidantaiMasterEntity> {

    /** 関連者個人マスタ標準Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiMasterRepository wkTblKanrenshaSeijidantaiMasterRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaSeijidantaiMasterEntity process(final WkTblKanrenshaSeijidantaiMasterResultEntity item) throws Exception {

        WkTblKanrenshaSeijidantaiMasterEntity entity = new WkTblKanrenshaSeijidantaiMasterEntity();

        Optional<WkTblKanrenshaSeijidantaiMasterEntity> optional = wkTblKanrenshaSeijidantaiMasterRepository
                .findById(item.getWkTblKanrenshaSeijidantaiMasterId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }

}
