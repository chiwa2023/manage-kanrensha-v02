package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;


/**
 * XMLによる最小マスタ登録処理結果ワークテーブル変換Processor
 */
@Component
public class KanrenshaByXmlMinWkTblFixProcessor
        implements ItemProcessor<WkTblMasterAllByXmlResultEntity, WkTblMasterAllByXmlEntity> {

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblMasterAllByXmlEntity process(final WkTblMasterAllByXmlResultEntity item) throws Exception {

        WkTblMasterAllByXmlEntity entity = new WkTblMasterAllByXmlEntity();

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findById(item.getWkTblMasterAllByXmlId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;
    }
}
