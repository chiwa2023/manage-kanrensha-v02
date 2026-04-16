package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * 関連者政治団体の一意キー取得Dto
 */
public class KanrenshaSeijidantaiUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param kanrenshaName          団体名称
     * @param allAddress           全住所
     * @param seijidantaiDelegate      団体だ評者
     * @param seijidantaiKanrenshaCode 関連者コード政治団体
     */
    public KanrenshaSeijidantaiUniquekeyDto(final String kanrenshaName, final String allAddress, final String seijidantaiDelegate,
            final String seijidantaiKanrenshaCode) {
        super();
        this.kanrenshaName = kanrenshaName;
        this.allAddress = allAddress;
        this.seijidantaiDelegate = seijidantaiDelegate;
        this.seijidantaiKanrenshaCode = seijidantaiKanrenshaCode;
    }

    /** 政治団体名 */
    @Id
    @Column(name = "kanrensha_name")
    private String kanrenshaName;

    /**
     * 政治団体名を取得する
     *
     * @return 政治団体名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 政治団体名を設定する
     *
     * @param kanrenshaName 政治団体名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 政治団体全住所 */
    @Id
    @Column(name = "all_address")
    private String allAddress;

    /**
     * 政治団体全住所を取得する
     *
     * @return 政治団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 政治団体全住所を設定する
     *
     * @param allAddress 政治団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 政治団体代表者 */
    @Id
    @Column(name = "seijidantai_delegate")
    private String seijidantaiDelegate;

    /**
     * 政治団体代表者を取得する
     *
     * @return 政治団体代表者
     */
    public String getSeijidantaiDelegate() {
        return seijidantaiDelegate;
    }

    /**
     * 政治団体代表者を設定する
     *
     * @param seijidantaiDelegate 政治団体代表者
     */
    public void setSeijidantaiDelegate(final String seijidantaiDelegate) {
        this.seijidantaiDelegate = seijidantaiDelegate;
    }

    /** 政治団体関連者コード */
    @Id
    @Column(name = "seijidantai_kanrensha_code")
    private String seijidantaiKanrenshaCode;

    /**
     * 政治団体関連者コードを取得する
     *
     * @return 政治団体関連者コード
     */
    public String getSeijidantaiKanrenshaCode() {
        return seijidantaiKanrenshaCode;
    }

    /**
     * 政治団体関連者コードを設定する
     *
     * @param seijidantaiKanrenshaCode 政治団体関連者コード
     */
    public void setSeijidantaiKanrenshaCode(final String seijidantaiKanrenshaCode) {
        this.seijidantaiKanrenshaCode = seijidantaiKanrenshaCode;
    }

}
