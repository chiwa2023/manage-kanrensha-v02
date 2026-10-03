package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;
import java.time.LocalDateTime;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 関連者コード移動履歴Dto
 */
public class KanrenshaCodeMoveHistoryDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * 
     * @param userPersonId    ユーザId
     * @param userPersonCode  ユーザコード
     * @param userPersonName  ユーザ名称
     * @param isLatest        最新該非
     * @param role            権限
     * @param kanrenshaCode   関連者コード
     * @param insertTimestamp 挿入時間
     */
    public KanrenshaCodeMoveHistoryDto(final Integer userPersonId, final Integer userPersonCode,
            final String userPersonName, final Byte isLatest, final String role, final String kanrenshaCode,
            final LocalDateTime insertTimestamp) {
        super();
        this.userPersonId = userPersonId;
        this.userPersonCode = userPersonCode;
        this.userPersonName = userPersonName;
        this.isLatest = isLatest;
        this.role = role;
        this.kanrenshaCode = kanrenshaCode;
        this.insertTimestamp = insertTimestamp;
    }

    /** テーブルId */
    private final Integer userPersonId;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getUserPersonId() {
        return userPersonId;
    }

    /** ユーザコード */
    private final Integer userPersonCode;

    /**
     * ユーザコードを取得する
     *
     * @return ユーザコード
     */
    public Integer getUserPersonCode() {
        return userPersonCode;
    }

    /** ユーザ名称 */
    private final String userPersonName;

    /**
     * ユーザ名称を取得する
     *
     * @return ユーザ名称
     */
    public String getUserPersonName() {
        return userPersonName;
    }

    /** 最新該否 */
    private final Byte isLatest; // NOPMD LinguisticNaming

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    public Byte getIsLatest() {
        return isLatest;
    }

    /** 役割 */
    private final String role;

    /**
     * 役割を取得する
     *
     * @return 役割
     */
    public String getRole() {
        return role;
    }

    /** 関連者コード */
    private final String kanrenshaCode;

    /**
     * 関連者コードを取得する
     *
     * @return 関連者コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /** 挿入日時 */
    private final LocalDateTime insertTimestamp;

    /**
     * 挿入日時を取得する
     *
     * @return 挿入日時
     */
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

}
