package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.PartnerAccessHistory2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.PartnerAccessHistory2025Repository;

/**
 * APIパートナー履歴保存Logic(2025)
 */
@Component
public class InsertPartnerApiAccessHistoryY2025Logic {

    /** APIパートナー履歴Repository(2025) */
    @Autowired
    private PartnerAccessHistory2025Repository partnerAccessHistory2025Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2025Entity entity = new PartnerAccessHistory2025Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2025Repository.save(entity).getPartnerAccessHistoryId();
    }
}
