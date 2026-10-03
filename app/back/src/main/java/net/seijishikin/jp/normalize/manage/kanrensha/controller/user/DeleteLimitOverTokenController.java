package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.service.security.DeleteLimitOverTokenService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 期限切れトークン・コード削除Controller
 */
@RestController
public class DeleteLimitOverTokenController {

    /** 期限切れトークン・コード削除Service */
    @Autowired
    private DeleteLimitOverTokenService deleteLimitOverTokenService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** 削除期限(2か月前) */
    private static final int LIMIT_MONTH = 2;

    /**
     * 処理を行う
     * 
     * @return 起動結果
     */
    public boolean practice() {

        LocalDate limitDate = LocalDate.now().minusMonths(LIMIT_MONTH);

        try {
            deleteLimitOverTokenService.practice(limitDate);
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            // 起動直後にだけなんかあったら中止連絡
            // 起動しているのが非同期処理なので処理結果は取得できない
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return false;
        }
        return true;
    }

}
