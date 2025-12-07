package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * 関連者企業・団体の一意キー取得Dto
 */
public class KanrenshaKigyouDtUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param kanrenshaName       企業団体名称
     * @param allAddress        全住所
     * @param KigyouDtDelegate      団体代表者
     * @param KigyouDtKanrenshaCode 関連者コード
     */
    public KanrenshaKigyouDtUniquekeyDto(final String kanrenshaName, final String allAddress, final String KigyouDtDelegate,
            final String KigyouDtKanrenshaCode) {
        super();
        this.kanrenshaName = kanrenshaName;
        this.allAddress = allAddress;
        this.KigyouDtDelegate = KigyouDtDelegate;
        this.KigyouDtKanrenshaCode = KigyouDtKanrenshaCode;
    }

    /** 企業・団体名 */
    @Id
    @Column(name = "kanrensha_name")
    private String kanrenshaName;

    /**
     * 企業・団体名を取得する
     *
     * @return 企業・団体名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 企業・団体名を設定する
     *
     * @param kanrenshaName 企業・団体名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 企業・団体全住所 */
    @Id
    @Column(name = "all_address")
    private String allAddress;

    /**
     * 企業・団体全住所を取得する
     *
     * @return 企業・団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 企業・団体全住所を設定する
     *
     * @param allAddress 企業・団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 企業・団体代表者 */
    @Id
    @Column(name = "KigyouDt_delegate")
    private String KigyouDtDelegate;

    /**
     * 企業・団体代表者を取得する
     *
     * @return 企業・団体代表者
     */
    public String getKigyouDtDelegate() {
        return KigyouDtDelegate;
    }

    /**
     * 企業・団体代表者を設定する
     *
     * @param KigyouDtDelegate 企業・団体代表者
     */
    public void setKigyouDtDelegate(final String KigyouDtDelegate) {
        this.KigyouDtDelegate = KigyouDtDelegate;
    }

    /** 企業・団体関連者コード */
    @Id
    @Column(name = "KigyouDt_kanrensha_code")
    private String KigyouDtKanrenshaCode;

    /**
     * 企業・団体関連者コードを取得する
     *
     * @return 企業・団体関連者コード
     */
    public String getKigyouDtKanrenshaCode() {
        return KigyouDtKanrenshaCode;
    }

    /**
     * 企業・団体関連者コードを設定する
     *
     * @param KigyouDtKanrenshaCode 企業・団体関連者コード
     */
    public void setKigyouDtKanrenshaCode(final String KigyouDtKanrenshaCode) {
        this.KigyouDtKanrenshaCode = KigyouDtKanrenshaCode;
    }

}
