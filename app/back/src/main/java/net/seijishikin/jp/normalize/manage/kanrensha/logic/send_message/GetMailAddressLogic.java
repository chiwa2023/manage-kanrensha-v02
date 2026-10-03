package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;

/**
 * メールアドレス取得Logic
 */
@Component
public class GetMailAddressLogic {

    /** ユーザ権限Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /**
     * 処理を行う
     * 
     * @param userCode ユーザコード
     * @return 最新ユーザemailアドレス
     */
    public String practice(final Integer userCode) {
        Optional<UserPersonEntity> optional = userPersonRepository
                .findFirstByUserPersonCodeAndIsLatestTrueOrderByInsertTimestampDesc(userCode);

        // 取得できないときはnullを取得できたら最新のメールアドレスを返却する
        if (optional.isEmpty()) {
            return null;
        } else {
            return optional.get().getEmail();
        }
    }
}
