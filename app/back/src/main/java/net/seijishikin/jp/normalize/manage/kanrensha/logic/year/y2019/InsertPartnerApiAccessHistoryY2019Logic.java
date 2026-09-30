package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2019.PartnerAccessHistory2019Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2019.PartnerAccessHistory2019Repository;

/**
 * APIパートナー履歴保存Logic(2019)
 */
@Component
public class InsertPartnerApiAccessHistoryY2019Logic {

    /** APIパートナー履歴Repository(2019) */
    @Autowired
    private PartnerAccessHistory2019Repository partnerAccessHistory2019Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2019Entity entity = new PartnerAccessHistory2019Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2019Repository.save(entity).getPartnerAccessHistoryId();
    }
}
