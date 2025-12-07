package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;

/**
 * 個人団体紐づけワークテーブル修正Processor
 */
@Component
public class CombineOrgWkTblFixProcessor
        implements ItemProcessor<WkTblKanrenshaCombineOrgResultEntity, WkTblKanrenshaCombineOrgEntity> {

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaCombineOrgEntity process(final WkTblKanrenshaCombineOrgResultEntity item) throws Exception {

        WkTblKanrenshaCombineOrgEntity entity = new WkTblKanrenshaCombineOrgEntity();

        Optional<WkTblKanrenshaCombineOrgEntity> optional = wkTblKanrenshaCombineOrgRepository
                .findById(item.getWkTblKanrenshaCombineOrgId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsFinish(true);
            entity.setJudgeReason("正常終了");
        }

        return entity;

    }

}
