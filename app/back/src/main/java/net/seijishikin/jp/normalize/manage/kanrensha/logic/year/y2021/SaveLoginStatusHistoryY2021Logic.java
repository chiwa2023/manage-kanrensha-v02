package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.LoginHistory2021Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021.LoginHistory2021Repository;

/**
 * 前回のログイン状態を履歴に複写する(2021)
 */
@Component
public class SaveLoginStatusHistoryY2021Logic {

    /** ログイン履歴Respository(2021) */
    @Autowired
    private LoginHistory2021Repository loginHistory2021Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン状態Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2021Entity historyEntity = new LoginHistory2021Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2021Repository.save(historyEntity).getLoginHistoryId();
    }
}
