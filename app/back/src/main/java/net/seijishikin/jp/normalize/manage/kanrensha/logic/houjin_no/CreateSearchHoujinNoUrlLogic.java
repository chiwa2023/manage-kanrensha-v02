package net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;

/**
 * 法人番号検索Url作成Logic
 */
@Component
public class CreateSearchHoujinNoUrlLogic {

    /** 空文字 */
    private static final String BLANK = "";

    /** 地方自治体コード制限桁数 */
    private static final int LG_CODE_LIMIT = 5;

    /**
     * 処理を行う
     * 
     * @param domainUrl  ドメインURL
     * @param capsuleDto 検索条件
     * @return 検索用URL
     */
    public String pracctice(final String domainUrl, final SearchHoujinNoCapsuleDto capsuleDto) {

        if (BLANK.equals(this.getValue(domainUrl))) {
            throw new IllegalArgumentException("接続URLが未指定です");
        }

        // capsuleDtoは早く落として早く実装ミスを見つける
        if (Objects.isNull(capsuleDto)) {
            throw new NullPointerException("検索条件の指定がありません"); // NOPMD ThrowingNullPointerException
        }

        StringBuilder builder = new StringBuilder(domainUrl);

        String appId = this.getValue(capsuleDto.getAppId());
        if (BLANK.equals(appId)) {
            throw new IllegalArgumentException("アプリケーションIdが未指定です");
        }
        builder.append("id=").append(appId);

        String nameWord = this.getValue(capsuleDto.getName());
        if (BLANK.equals(nameWord)) {
            throw new IllegalArgumentException("検索条件名称が未指定です");
        }
        builder.append("&name=") // NOPMD ConscutitiveLiteral
                .append(URLEncoder.encode(nameWord, StandardCharsets.UTF_8));

        // 必ずUTF-CSVで取得します
        builder.append("&type=").append("02"); // NOPMD ConscutitiveLiteral

        // オプショナルなクエリパラメータをヘルパーメソッドで追加（NPath複雑度の低減）
        this.appendQueryParam(builder, "mode", this.getValue(capsuleDto.getMode()));
        this.appendQueryParam(builder, "target", this.getValue(capsuleDto.getTarget()));
        this.appendAddressParam(builder, this.getValue(capsuleDto.getAddress()));
        this.appendQueryParam(builder, "change", this.getValue(capsuleDto.getChange()));
        this.appendQueryParam(builder, "close", this.getValue(capsuleDto.getClose()));
        this.appendQueryParam(builder, "from", this.getDateValue(capsuleDto.getFrom()));
        this.appendQueryParam(builder, "to", this.getDateValue(capsuleDto.getTo()));
        this.appendQueryParam(builder, "divide", this.getIntValue(capsuleDto.getDivide()));

        return builder.toString();
    }

    /**
     * 一般的なクエリパラメータを追加する
     * 
     * @param builder   StringBuilder
     * @param paramName パラメータ名
     * @param value     設定値
     */
    private void appendQueryParam(final StringBuilder builder, final String paramName, final String value) {
        if (!BLANK.equals(value)) {
            builder.append('&').append(paramName).append('=').append(value);
        }
    }

    /**
     * 住所（市区町村コード）パラメータを追加する（5桁以上にカット、それ以下はそのまま）
     * 
     * @param builder StringBuilder
     * @param address 住所コード
     */
    private void appendAddressParam(final StringBuilder builder, final String address) {
        if (!BLANK.equals(address)) {
            if (address.length() >= LG_CODE_LIMIT) {
                builder.append("&address=").append(address.substring(0, LG_CODE_LIMIT));
            } else {
                builder.append("&address=").append(address);
            }
        }
    }

    private String getValue(final String data) {
        if (Objects.isNull(data)) {
            return BLANK;
        }

        return data;
    }

    private String getIntValue(final Integer data) {
        if (Objects.isNull(data)) {
            return BLANK;
        }
        final Integer ZERO = 0;
        if (ZERO.equals(data)) {
            return BLANK;
        }

        return String.valueOf(data);
    }

    private String getDateValue(final LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return BLANK;
        }
        if (DtoEntityInitialValueInterface.INIT_DATE.equals(localDate)) {
            return BLANK;
        }

        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
