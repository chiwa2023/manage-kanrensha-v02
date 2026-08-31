package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import java.util.Objects;

/**
 * 漢数字変換Util
 */
public final class ConvertKansujiUtil {

    /** 漢数字文字 */
    private static final String[] KANSUJI = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
    /** 桁漢数字配列 */
    private static final String[] UNITS = { "", "十", "百", "千" };

    /**
     * コンストラクタ(インスタンス生成除け)
     */
    private ConvertKansujiUtil() {

    }

    /**
     * 処理を行う
     * 
     * @param input 入力後
     * @return 変換結果
     */
    public static String practice(final String input) {

        if (Objects.isNull(input)) {
            return "";
        }

        StringBuilder builder = new StringBuilder(input.length() * 2);
        int length = input.length();
        int index = 0;

        while (index < length) {
            char oneChar = input.charAt(index);

            // 全角数字の始まりを検知
            if (oneChar >= '０' && oneChar <= '９') {
                // 数字がどこまで続くか末尾を確認
                int start = index;
                while (index < length && input.charAt(index) >= '０' && input.charAt(index) <= '９') {
                    index++;
                }

                // 切り出した数字の塊を漢数字に変換して追加
                appendKansujiChunk(builder, input, start, index);
            } else {
                // 数字以外はそのまま追加
                builder.append(oneChar);
                index++;
            }
        }

        return builder.toString();
    }

    // 文字列を切り出さず、インデックス指定で直接StringBuilderへ書き込む
    private static void appendKansujiChunk(final StringBuilder builder, final String input, final int start,
            final int end) {
        int chunkLen = end - start;

        // 単体の「０」の場合
        if (chunkLen == 1 && input.charAt(start) == '０') {
            builder.append('〇');
            return;
        }

        for (int i = 0; i < chunkLen; i++) {
            int digit = input.charAt(start + i) - '０';
            int position = chunkLen - 1 - i;

            if (digit != 0) {
                // 10以上の位で、数字が「1」の場合は「一」を省略
                if (position > 0 && digit == 1) {
                    builder.append(UNITS[position]);
                } else {
                    builder.append(KANSUJI[digit]).append(UNITS[position]);
                }
            }
        }
    }
}