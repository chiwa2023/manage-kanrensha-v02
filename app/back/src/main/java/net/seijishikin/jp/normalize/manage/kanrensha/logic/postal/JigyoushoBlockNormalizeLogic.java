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
    private ConvertAddressNumberFormatLogic convertAddressNumberFormatLogic;

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

        String answer = convertAddressNumberFormatLogic.practice(data);

        int posGou = answer.indexOf(KEY_GOU);

        // 私書箱表示の場合は私書箱表示をいったんカットして終了。『５丁目３番地(郵便局私書箱１号)中央ビル４Ｆ』のような表示はないと信じたい
        answer = this.convertShishobako(answer);

        // 英数ハイフンの両端が数字の場合は番地に変換する
        if (answer.indexOf(KEY_HYPHEN) != -1 && this.isEdgeNumber(answer, KEY_HYPHEN)) {
            answer = answer.replaceFirst(KEY_HYPHEN, KEY_BANCHI);
        }

        // 号チェックはすでに住所分のみとなっている(私書箱に含まれる号表示はすでにカットした)
        // 最後が号で終わっていない場合は号の後を1文字開ける(ただし最初のみ、、後の文字が数字の場合は除く)
        if (posGou != -1 && posGou != answer.length() - 1 && this.hasNotProNumber(answer, KEY_GOU)) {
            answer = answer.replaceFirst(KEY_GOU, KEY_GOU + WIDE_SPACE);
        }

        int posBanchi = answer.indexOf(KEY_BANCHI);

        // 最後が番地で終わっていない場合は番地の後を1文字開ける(ただし最初のみ、、後の文字が数字の場合は除く)
        if (posGou == -1 && posBanchi != -1 && this.hasNotProNumber(answer, KEY_BANCHI)) {
            answer = answer.replaceFirst(KEY_BANCHI, KEY_BANCHI + WIDE_SPACE);
        }

        // 最後が丁目で終わっていない場合は丁目の後を1文字開ける(ただし最初のみ、、後の文字が数字の場合は除く)
        int posChoume = answer.indexOf(KEY_CHOUME);
        if (posGou == -1 && posBanchi == -1 && posChoume != -1 && this.hasNotProNumber(answer, KEY_CHOUME)) {
            answer = answer.replaceFirst(KEY_CHOUME, KEY_CHOUME + WIDE_SPACE);
        }

        // 「番地 の4」の表記は作成した空白を閉じる
        answer = answer.replaceAll("番地　の", "番地の");

        return answer;
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
