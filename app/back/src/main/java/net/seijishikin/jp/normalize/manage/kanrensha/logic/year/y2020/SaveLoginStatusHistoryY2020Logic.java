package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2020.LoginHistory2020Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2020.LoginHistory2020Repository;

/**
 * 前回のログイン状態を履歴に複写する(2020)
 */
@Component
public class SaveLoginStatusHistoryY2020Logic {

    /** ログイン履歴Respository(2020) */
    @Autowired
    private LoginHistory2020Repository loginHistory2020Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン履歴Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2020Entity historyEntity = new LoginHistory2020Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2020Repository.save(historyEntity).getLoginHistoryId();
    }
}
