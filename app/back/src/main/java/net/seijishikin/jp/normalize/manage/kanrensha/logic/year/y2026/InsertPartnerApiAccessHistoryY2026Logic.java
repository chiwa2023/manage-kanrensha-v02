package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.PartnerAccessHistory2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.PartnerAccessHistory2026Repository;

/**
 * APIパートナー履歴保存Logic(2026)
 */
@Component
public class InsertPartnerApiAccessHistoryY2026Logic {

    /** APIパートナー履歴Repository(2026) */
    @Autowired
    private PartnerAccessHistory2026Repository partnerAccessHistory2026Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2026Entity entity = new PartnerAccessHistory2026Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2026Repository.save(entity).getPartnerAccessHistoryId();
    }
}
