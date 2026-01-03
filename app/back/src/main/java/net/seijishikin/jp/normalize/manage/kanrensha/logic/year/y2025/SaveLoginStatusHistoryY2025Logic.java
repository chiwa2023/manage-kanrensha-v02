package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.LoginHistory2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.LoginHistory2025Repository;

/**
 * 前回のログイン状態を履歴に複写する(2025)
 */
@Component
public class SaveLoginStatusHistoryY2025Logic {

    /** ログイン履歴Respository(2025) */
    @Autowired
    private LoginHistory2025Repository loginHistory2025Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン履歴Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2025Entity historyEntity = new LoginHistory2025Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2025Repository.save(historyEntity).getLoginHistoryId();
    }
}
