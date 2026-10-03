package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.PartnerAccessHistory2021Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021.PartnerAccessHistory2021Repository;

/**
 * APIパートナー履歴保存Logic(2021)
 */
@Component
public class InsertPartnerApiAccessHistoryY2021Logic {

    /** APIパートナー履歴Repository(2021) */
    @Autowired
    private PartnerAccessHistory2021Repository partnerAccessHistory2021Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2021Entity entity = new PartnerAccessHistory2021Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2021Repository.save(entity).getPartnerAccessHistoryId();
    }
}
