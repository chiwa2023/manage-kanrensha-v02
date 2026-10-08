package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2027.LoginHistory2027Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.LoginHistory2027Repository;

/**
 * 前回のログイン状態を履歴に複写する(2027)
 */
@Component
public class SaveLoginStatusHistoryY2027Logic {

    /** ログイン履歴Respository(2027) */
    @Autowired
    private LoginHistory2027Repository loginHistory2027Repository;

    /**
     * 処理を行う
     *
     * @param baseEntity ログイン状態Entity
     */
    public Integer practice(final LoginHistoryBaseEntity baseEntity) {

        LoginHistory2027Entity historyEntity = new LoginHistory2027Entity();
        BeanUtils.copyProperties(baseEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        return loginHistory2027Repository.save(historyEntity).getLoginHistoryId();
    }
}
