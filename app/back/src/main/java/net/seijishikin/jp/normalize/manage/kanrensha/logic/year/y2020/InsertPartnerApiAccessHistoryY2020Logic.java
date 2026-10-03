package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2020.PartnerAccessHistory2020Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2020.PartnerAccessHistory2020Repository;

/**
 * APIパートナー履歴保存Logic(2020)
 */
@Component
public class InsertPartnerApiAccessHistoryY2020Logic {

    /** APIパートナー履歴Repository(2020) */
    @Autowired
    private PartnerAccessHistory2020Repository partnerAccessHistory2020Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2020Entity entity = new PartnerAccessHistory2020Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2020Repository.save(entity).getPartnerAccessHistoryId();
    }
}
