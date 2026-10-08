package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.LoginHistory2022Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022.LoginHistory2022Repository;

/**
 * 前回のログイン状態を履歴に複写する(2022)
 */
@Component
public class SaveLoginStatusHistoryY2022Logic {

    /** ログイン履歴Respository(2022) */
    @Autowired
    private LoginHistory2022Repository loginHistory2022Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン状態Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2022Entity historyEntity = new LoginHistory2022Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2022Repository.save(historyEntity).getLoginHistoryId();
    }
}
