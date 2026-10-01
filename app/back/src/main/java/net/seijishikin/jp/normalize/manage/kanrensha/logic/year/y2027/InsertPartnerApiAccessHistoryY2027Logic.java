package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2027.PartnerAccessHistory2027Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.PartnerAccessHistory2027Repository;

/**
 * APIパートナー履歴保存Logic(2027)
 */
@Component
public class InsertPartnerApiAccessHistoryY2027Logic {

    /** APIパートナー履歴Repository(2027) */
    @Autowired
    private PartnerAccessHistory2027Repository partnerAccessHistory2027Repository;

    /**
     * 処理を行う
     * 
     * @param baseEntity APIパートナー履歴共通Entity
     * @return 保存Id
     */
    public Integer practice(final PartnerAccessHistoryBaseEntity baseEntity) {

        PartnerAccessHistory2027Entity entity = new PartnerAccessHistory2027Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setPartnerAccessHistoryId(0); // auto increment明記

        return partnerAccessHistory2027Repository.save(entity).getPartnerAccessHistoryId();
    }
}
