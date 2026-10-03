package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2019.LoginHistory2019Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2019.LoginHistory2019Repository;

/**
 * 前回のログイン状態を履歴に複写する(2019)
 */
@Component
public class SaveLoginStatusHistoryY2019Logic {

    /** ログイン履歴Respository(2019) */
    @Autowired
    private LoginHistory2019Repository loginHistory2019Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン履歴Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2019Entity historyEntity = new LoginHistory2019Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2019Repository.save(historyEntity).getLoginHistoryId();
    }
}
