package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import java.text.Normalizer;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PublishCodeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PublishCodeRepository;

/**
 * 政治団体向け関連者コードを生成する
 */
@Component
public class CreateDokujiCodeForSeijidantaiUtil {

    /** ハイフンを除いた文字数 */
    private static final int CODE_LENGTH = 20;

    /** 発行済コードRepository */
    @Autowired
    private PublishCodeRepository publishCodeRepository;

    /**
     * 処理を行う
     *
     * @param dataSeiki 正規コード
     * @return 仮関連者コード
     */
    public String practice(final String dataSeiki) {

        // 3回試行
        // オーバーラップコードが19文字でランダム不可1文字の場合
        // アルファベット大文字小文字+数字 → 1/ 62*62*62 の確率で重複が起きる
        final int times = 3;
        for (int i = 0; i < times; i++) {

            String tmpCode = this.createCode(dataSeiki);

            if (tmpCode.equals(this.checkCode(tmpCode))) {
                return tmpCode;
            }
        }

        throw new DuplicateKeyException("重複のあるコードしか生成できませんでした。オーバラップするコードを見直してください");
    }

    private String createCode(final String dataSeiki) {
        // 123-4567-8901-2345-67890が最終形

        final String hyphen = "-";
        final String empty = "";

        String words = empty;
        if (!Objects.isNull(dataSeiki)) {
            words = dataSeiki;
        }

        // 余分なハイフンを除去して20文字に
        String seiki = Normalizer.normalize(words, Normalizer.Form.NFKC).replaceAll(hyphen, "");

        int size = CODE_LENGTH - seiki.length();
        String randomText = RandomStringUtils.secure().nextAlphanumeric(size);

        String allText = seiki + randomText;
        StringBuilder builder = new StringBuilder();
        final int pos1 = 3;
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

    private String checkCode(final String tmpCode) {

        if (publishCodeRepository.findById(tmpCode).isEmpty()) {
            PublishCodeEntity entity = new PublishCodeEntity();
            entity.setKanrenshaCode(tmpCode);
            return publishCodeRepository.saveAndFlush(entity).getKanrenshaCode();
        }

        return "";
    }

}
