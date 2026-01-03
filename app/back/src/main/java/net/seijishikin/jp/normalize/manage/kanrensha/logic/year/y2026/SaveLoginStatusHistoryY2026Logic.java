package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.LoginHistory2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.LoginHistory2026Repository;

/**
 * 前回のログイン状態を履歴に複写する(2026)
 */
@Component
public class SaveLoginStatusHistoryY2026Logic {

    /** ログイン履歴Respository(2026) */
    @Autowired
    private LoginHistory2026Repository loginHistory2026Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン状態Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2026Entity historyEntity = new LoginHistory2026Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2026Repository.save(historyEntity).getLoginHistoryId();
    }
}
