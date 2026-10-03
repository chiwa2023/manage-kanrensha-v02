package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import jakarta.persistence.Column;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * 関連者コード移行申請Dto
 */
public class MoveKanrenshaCodePromoteCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者区分 */
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * 関連者区分を取得する
     *
     * @return 関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 関連者区分を設定する
     *
     * @param kanrenshaKbn 関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /** 併合先コード */
    private String originKanrenshaCode = INIT_STRING;

    /**
     * 併合先コードを取得する
     *
     * @return 併合先コード
     */
    public String getOriginKanrenshaCode() {
        return originKanrenshaCode;
    }

    /**
     * 併合先コードを設定する
     *
     * @param originKanrenshaCode 併合先コード
     */
    public void setOriginKanrenshaCode(final String originKanrenshaCode) {
        this.originKanrenshaCode = originKanrenshaCode;
    }

    /** 併合先コード名称 */
    private String originName = INIT_STRING;

    /**
     * 併合先コード名称を取得する
     *
     * @return 併合先コード名称
     */
    public String getOriginName() {
        return originName;
    }

    /**
     * 併合先コード名称を設定する
     *
     * @param originName 併合先コード名称
     */
    public void setOriginName(final String originName) {
        this.originName = originName;
    }

    /** 廃止コード */
    private String abolishKanrenshaCode = INIT_STRING;

    /**
     * 廃止コードを取得する
     *
     * @return 廃止コード
     */
    public String getAbolishKanrenshaCode() {
        return abolishKanrenshaCode;
    }

    /**
     * 廃止コードを設定する
     *
     * @param abolishKanrenshaCode 廃止コード
     */
    public void setAbolishKanrenshaCode(final String abolishKanrenshaCode) {
        this.abolishKanrenshaCode = abolishKanrenshaCode;
    }

    /** 廃止コード名称 */
    private String abolishKanrenshaName = INIT_STRING;

    /**
     * 廃止コード名称を取得する
     *
     * @return 廃止コード名称
     */
    public String getAbolishKanrenshaName() {
        return abolishKanrenshaName;
    }

    /**
     * 廃止コード名称を設定する
     *
     * @param abolishKanrenshaName 廃止コード名称
     */
    public void setAbolishKanrenshaName(final String abolishKanrenshaName) {
        this.abolishKanrenshaName = abolishKanrenshaName;
    }

    /** ストレージファイルDto */
    private StorageFileDto storageFileDto;

    /**
     * ストレージファイルDtoを取得する
     * 
     * @return ストレージファイルDto
     */
    public StorageFileDto getStorageFileDto() {
        return storageFileDto;
    }

    /**
     * ストレージファイルDtoを設定する
     * 
     * @param storageFileDto ストレージファイルDto
     */
    public void setStorageFileDto(final StorageFileDto storageFileDto) {
        this.storageFileDto = storageFileDto;
    }

    /** 移動理由 */
    private String moveReason = INIT_STRING;

    /**
     * 移動理由を取得する
     * 
     * @return 移動理由
     */
    public String getMoveReason() {
        return moveReason;
    }

    /**
     * 移動理由を設定する
     * 
     * @param moveReason 移動理由
     */
    public void setMoveReason(final String moveReason) {
        this.moveReason = moveReason;
    }

    /** 廃止コード最新該当 */
    @Column(name = "is_abolish_last")
    private Boolean isAbolishLast = INIT_BOOLEAN;

    /**
     * 廃止コード最新該当を取得する
     *
     * @return 廃止コード最新該当
     */
    public Boolean getIsAbolishLast() {
        return isAbolishLast;
    }

    /**
     * 廃止コード最新該当を設定する
     *
     * @param isAbolishLast 廃止コード最新該当
     */
    public void setIsAbolishLast(final Boolean isAbolishLast) {
        this.isAbolishLast = isAbolishLast;
    }

}
