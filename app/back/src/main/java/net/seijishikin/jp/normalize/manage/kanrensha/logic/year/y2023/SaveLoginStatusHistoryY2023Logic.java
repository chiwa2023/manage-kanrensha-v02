package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.LoginHistory2023Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023.LoginHistory2023Repository;

/**
 * 前回のログイン状態を履歴に複写する(2023)
 */
@Component
public class SaveLoginStatusHistoryY2023Logic {

    /** ログイン履歴Respository(2023) */
    @Autowired
    private LoginHistory2023Repository loginHistory2023Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン状態Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2023Entity historyEntity = new LoginHistory2023Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2023Repository.save(historyEntity).getLoginHistoryId();
    }
}
