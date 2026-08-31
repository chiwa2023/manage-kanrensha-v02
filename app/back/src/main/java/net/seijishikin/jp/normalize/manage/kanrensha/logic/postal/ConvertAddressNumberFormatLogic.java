package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.utils.ConvertNumberUtil;

/**
 * 住所数字正規化Logic
 */
@Component
public class ConvertAddressNumberFormatLogic {

    /** 漢数字変換Utility */
    @Autowired
    private ConvertNumberToKansujiUtil convertNumberToKansujiUtil;

    /** キーワード丁目 */
    private static final String KEY_CHOUME = "丁目";

    /**
     * 処理を行う
     * 
     * @param data 処理対象
     * @return 変換後データ
     */
    public String practice(final String data) {

        if (Objects.isNull(data)) {
            return "";
        }

        String answer = ConvertNumberUtil.practice(data);

        // 丁目表示はアドレス・ベース・レジストリに倣って漢数字表記が必要
        int posChoume = answer.indexOf(KEY_CHOUME);
        if (posChoume == -1) {
            return answer;
        }

        int startNum = posChoume;
        while (startNum > 0 && Character.isDigit(answer.charAt(startNum - 1))) {
            startNum--;
        }

        if (startNum == posChoume) {
            return answer;
        }

        int chou = Integer.parseInt(answer.substring(startNum, posChoume));
        String kansuji = convertNumberToKansujiUtil.practice(chou);

        return answer.substring(0, startNum) + kansuji + answer.substring(posChoume);
    }

}
