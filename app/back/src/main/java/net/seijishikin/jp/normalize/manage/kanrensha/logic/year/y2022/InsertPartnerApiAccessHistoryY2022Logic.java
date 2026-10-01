package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.PartnerAccessHistory2022Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022.PartnerAccessHistory2022Repository;

/**
 * APIパートナー履歴保存Logic(2022)
 */
@Component
public class InsertPartnerApiAccessHistoryY2022Logic {

    /** APIパートナー履歴Repository(2022) */
    @Autowired
    private PartnerAccessHistory2022Repository partnerAccessHistory2022Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2022Entity entity = new PartnerAccessHistory2022Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2022Repository.save(entity).getPartnerAccessHistoryId();
    }
}
