package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import java.text.Normalizer;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * 個人向け関連者コードを生成する
 */
@Component
public class CreateDokujiCodeForPersonUtil {
    
    /** ハイフンを除いた文字数 */
    private static final int CODE_LENGTH = 20;
    
    /**
     * 処理を行う
     *
     * @param dataSeiki 正規コード
     * @return 仮関連者コード
     */
    public String practice(final String dataSeiki) {
        // 12-34567-8901-2345-67890が最終形

        final String hyphen = "-";
        final String empty = "";

        String words = empty;
        if(!Objects.isNull(dataSeiki)) {
            words = dataSeiki;
        }
        
        // 余分なハイフンを除去して20文字に
        String seiki = Normalizer.normalize(words, Normalizer.Form.NFKC).replaceAll(hyphen, "");
        
        int size = CODE_LENGTH - seiki.length();
        String randomText = RandomStringUtils.secure().nextAlphanumeric(size);

        String allText = seiki + randomText;
        StringBuilder builder = new StringBuilder();
        final int pos1 = 2;
        final int pos2 = 7;
        final int pos3 = 11;
        final int pos4 = 15;
        
        builder.append(allText.substring(0, pos1)).append(hyphen) // 改行
                .append(allText.substring(pos1, pos2)).append(hyphen) // 改行
                .append(allText.substring(pos2, pos3)).append(hyphen) // 改行
                .append(allText.substring(pos3, pos4)).append(hyphen) // 改行
                .append(allText.substring(pos4, CODE_LENGTH));

        return builder.toString();
    }

}
