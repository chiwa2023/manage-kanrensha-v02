package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.PartnerAccessHistory2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.PartnerAccessHistory2024Repository;

/**
 * APIパートナー履歴保存Logic(2024)
 */
@Component
public class InsertPartnerApiAccessHistoryY2024Logic {

    /** APIパートナー履歴Repository(2024) */
    @Autowired
    private PartnerAccessHistory2024Repository partnerAccessHistory2024Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2024Entity entity = new PartnerAccessHistory2024Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2024Repository.save(entity).getPartnerAccessHistoryId();
    }
}
