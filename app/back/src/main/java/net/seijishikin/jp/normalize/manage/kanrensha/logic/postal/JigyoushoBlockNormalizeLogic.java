package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 事業所郵便番号住所を標準化する
 */
@Component
public class JigyoushoBlockNormalizeLogic {

    /** 漢数字変換Utility */
    @Autowired
    private ConvertNumberToKansujiUtil convertNumberToKansujiUtil;

    /** 全角スペース */
    private static final String WIDE_SPACE = "　";

    /** キーワード丁目 */
    private static final String KEY_CHOUME = "丁目";

    /** キーワード番地 */
    private static final String KEY_BANCHI = "番地";

    /** キーワード号 */
    private static final String KEY_GOU = "号";

    /** キーワードハイフン */
    private static final String KEY_HYPHEN = "－";

    /**
     * 処理を行う
     * 
     * @param data 元番地データ
     * @return できる限りの正規化
     */
    public String practice(final String data) { // SUPPRESS CHECKSTYLE NPath NOPMD NPath

        if (Objects.isNull(data)) {
            return "";
        }

        String answer = this.convertNumbber(data);

        // 丁目表示はアドレス・ベース・レジストリに倣って漢数字表記が必要
        int posChoume = answer.indexOf(KEY_CHOUME);
        if (posChoume != -1) {
            try {
                Integer chou = Integer.parseInt(answer.substring(0, posChoume));
                answer = convertNumberToKansujiUtil.practice(chou) + answer.substring(posChoume, answer.length());
            } catch (Exception e) { // NOPMD
                // 何もしない
            }
        }

        int posBanchi = answer.indexOf(KEY_BANCHI);
        int posGou = answer.indexOf(KEY_GOU);

        // 私書箱表示の場合は私書箱表示をいったんカットして終了。『５丁目３番地(郵便局私書箱１号)中央ビル４Ｆ』のような表示はないと信じたい
        answer = this.convertShishobako(answer);

        // 号チェックはすでに住所分のみとなっている(私書箱に含まれる号表示はすでにreturnした)
        // 最後が号で終わっていない場合は号の後を1文字開ける(ただし最初のみ、、後の文字が数字の場合は除く)
        if (posGou != -1 && posGou != answer.length() - 1) {
            answer = answer.replace(KEY_GOU, KEY_GOU + WIDE_SPACE);
        }

        // 最後が番地で終わっていない場合は番地の後を1文字開ける(ただし最初のみ、、後の文字が数字の場合は除く)
        if (posGou == -1 && posBanchi != -1 && this.hasNotProNumber(answer, KEY_BANCHI)) {
            answer = answer.replace(KEY_BANCHI, KEY_BANCHI + WIDE_SPACE);
        }

        // 最後が丁目で終わっていない場合は丁目の後を1文字開ける(ただし最初のみ、、後の文字が数字の場合は除く)
        if (posGou == -1 && posBanchi == -1 && posChoume != -1 && this.hasNotProNumber(answer, KEY_CHOUME)) {
            answer = answer.replace(KEY_CHOUME, KEY_CHOUME + WIDE_SPACE);
        }

        // 英数ハイフンの両端が数字の場合は番地に変換する
        if (answer.indexOf(KEY_HYPHEN) != -1 && this.isEdgeNumber(answer, KEY_HYPHEN)) {
            answer = answer.replace(KEY_HYPHEN, KEY_BANCHI);
        }

        return answer;
    }

    private String convertNumbber(final String data) {
        String dt0 = data.replaceAll("０", "0");
        String dt1 = dt0.replaceAll("１", "1");
        String dt2 = dt1.replaceAll("２", "2");
        String dt3 = dt2.replaceAll("３", "3");
        String dt4 = dt3.replaceAll("４", "4");
        String dt5 = dt4.replaceAll("５", "5");
        String dt6 = dt5.replaceAll("６", "6");
        String dt7 = dt6.replaceAll("７", "7");
        String dt8 = dt7.replaceAll("８", "8");

        return dt8.replaceAll("９", "9");
    }

    private boolean hasNotProNumber(final String data, final String key) {

        int pos = data.indexOf(key);
        if (pos == -1) {
            // 実装上はあり得ないが念のため
            return false;
        }
        if (pos == (data.length() - key.length())) {
            // キーワード末尾の時は空白を追加しないのでfalse
            return false;
        }

        if (this.isNumber(data.substring(pos + key.length(), pos + key.length() + 1))) { // NOPMD SimplyReturn

            // 後の文字が数字である場合は要注意なので対象外
            return false;
        }

        return true;
    }

    private boolean isEdgeNumber(final String data, final String key) {

        int pos = data.indexOf(key);
        if (pos == -1) {
            // 実装上はあり得ないが念のため
            return false;
        }
        if (pos == (data.length() - key.length())) {
            // キーワード末尾の時は空白を追加しないのでfalse
            return false;
        }

        if (!this.isNumber(data.substring(pos + key.length(), pos + key.length() + 1))) {
            // 後の文字が数字でない場合は長音符と混在の可能性があるので除外
            return false;
        }

        if (!this.isNumber(data.substring(pos - key.length(), pos - key.length() + 1))) { // NOPMD SimplyReturn
            // 後の文字が数字でない場合は長音符と混在の可能性があるので除外
            return false;
        }

        return true;
    }

    private boolean isNumber(final String data) {

        switch (data) {
            case "1":
                return true;
            case "2":
                return true;
            case "3":
                return true;
            case "4":
                return true;
            case "5":
                return true;
            case "6":
                return true;
            case "7":
                return true;
            case "8":
                return true;
            case "9":
                return true;
            default:
                return false;
        }
    }

    private String convertShishobako(final String data) {
        int posEmp = data.indexOf("（");
        int posShishobako = data.indexOf("私書箱");

        // 私書箱表示の場合は私書箱表示をいったんカットして終了。『５丁目３番地(郵便局私書箱１号)中央ビル４Ｆ』のような表示はないと信じたい
        if (posEmp == -1 || posShishobako == -1) {
            return data;
        } else {
            return data.substring(0, posEmp);
        }

    }
}
