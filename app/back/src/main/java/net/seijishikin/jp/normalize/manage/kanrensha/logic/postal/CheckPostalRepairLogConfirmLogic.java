package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepairLogRepository;

/**
 * 郵便番号修復ログ修正予定残確認Logic
 */
@Component
public class CheckPostalRepairLogConfirmLogic {

    /** 郵便番号修復ログRepository */
    @Autowired
    private AddressPostalRepairLogRepository addressPostalRepairLogRepository;

    /**
     * 処理を行う
     * 
     * @return 処理を実行フラグ(True)
     * @throws IncorrectResultSizeDataAccessException データ不存在が期待されているが存在する
     */
    public Boolean practice(final String lgCodePref) throws IncorrectResultSizeDataAccessException { // NOPMD

        if (0 != addressPostalRepairLogRepository
                .countByLgCodeStartingWithAndIsLatestTrueAndIsConfirmTrue(lgCodePref)) {
            throw new IncorrectResultSizeDataAccessException(0);
        }
        return true;
    }
}
