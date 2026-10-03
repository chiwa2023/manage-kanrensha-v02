package net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * ダウンロードファイル呼び出しDto
 */
public class DownloadFileCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ファイル保存年 */
    private Integer storageYear = INIT_INTEGER;

    /** ファイル保存Id */
    private Integer storageId = INIT_INTEGER;

    /**
     * ファイル保存年を取得する
     * 
     * @return ファイル保存年
     */
    public Integer getStorageYear() {
        return storageYear;
    }

    /**
     * ファイル保存年を設定する
     * 
     * @param storageYear ファイル保存年
     */
    public void setStorageYear(final Integer storageYear) {
        this.storageYear = storageYear;
    }

    /**
     * ファイル保存Idを取得する
     * 
     * @return ファイル保存Id
     */
    public Integer getStorageId() {
        return storageId;
    }

    /**
     * ファイル保存Idを設定する
     * 
     * @param storageId ファイル保存Id
     */
    public void setStorageId(final Integer storageId) {
        this.storageId = storageId;
    }

    /** 関連者コード */
    private String kanrenshaCode = INIT_STRING;

    /** 関連者コード申請者 */
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * 関連者コードを取得する
     * 
     * @return 関連者コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 関連者コードを設定する
     * 
     * @param kanrenshaCode 関連者コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

    /**
     * 関連者コード申請者を取得する
     * 
     * @return 関連者コード申請者
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 関連者コード申請者を設定する
     * 
     * @param kanrenshaKbn 関連者コード申請者
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

}
