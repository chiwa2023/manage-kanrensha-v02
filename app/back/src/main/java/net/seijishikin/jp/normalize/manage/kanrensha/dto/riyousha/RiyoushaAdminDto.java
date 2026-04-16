package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;

/**
 * SE権限ユーザDto
 */
public class RiyoushaAdminDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    private Integer riyoushaAdminMasterId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getRiyoushaAdminMasterId() {
        return riyoushaAdminMasterId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param riyoushaAdminMasterId テーブルId
     */
    public void setRiyoushaAdminMasterId(final Integer riyoushaAdminMasterId) {
        this.riyoushaAdminMasterId = riyoushaAdminMasterId;
    }

    /** 利用者運営者コード */
    private Integer riyoushaAdminMasterCode = INIT_INTEGER;

    /**
     * 利用者運営者コードを取得する
     *
     * @return 利用者運営者コード
     */
    public Integer getRiyoushaAdminMasterCode() {
        return riyoushaAdminMasterCode;
    }

    /**
     * 利用者運営者コードを設定する
     *
     * @param riyoushaAdminMasterCode 利用者運営者コード
     */
    public void setRiyoushaAdminMasterCode(final Integer riyoushaAdminMasterCode) {
        this.riyoushaAdminMasterCode = riyoushaAdminMasterCode;
    }

    /** 利用者個人属性id */
    private Integer riyoushaPersonPropertyId = INIT_INTEGER;

    /**
     * 利用者個人属性idを取得する
     *
     * @return 利用者個人属性id
     */
    public Integer getRiyoushaPersonPropertyId() {
        return riyoushaPersonPropertyId;
    }

    /**
     * 利用者個人属性idを設定する
     *
     * @param riyoushaPersonPropertyId 利用者個人属性id
     */
    public void setRiyoushaPersonPropertyId(final Integer riyoushaPersonPropertyId) {
        this.riyoushaPersonPropertyId = riyoushaPersonPropertyId;
    }

    /** 利用者個人属性コード */
    private Integer riyoushaPersonPropertyCode = INIT_INTEGER;

    /**
     * 利用者個人属性コードを取得する
     *
     * @return 利用者個人属性コード
     */
    public Integer getRiyoushaPersonPropertyCode() {
        return riyoushaPersonPropertyCode;
    }

    /**
     * 利用者個人属性コードを設定する
     *
     * @param riyoushaPersonPropertyCode 利用者個人属性コード
     */
    public void setRiyoushaPersonPropertyCode(final Integer riyoushaPersonPropertyCode) {
        this.riyoushaPersonPropertyCode = riyoushaPersonPropertyCode;
    }

    /** 個人姓名入力 */
    private InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();

    /** 住所入力 */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /** 連絡先入力 */
    private InputAccessDto inputAccessDto = new InputAccessDto();

    /**
     * 個人姓名入力を取得する
     *
     * @return 個人姓名入力
     */
    public InputPersonNameDto getInputPersonNameDto() {
        return inputPersonNameDto;
    }

    /**
     * 個人姓名入力を設定する
     *
     * @param inputPersonNameDto 個人姓名入力
     */
    public void setInputPersonNameDto(final InputPersonNameDto inputPersonNameDto) {
        this.inputPersonNameDto = inputPersonNameDto;
    }

    /**
     * 住所入力を取得する
     *
     * @return 住所入力
     */
    public InputAddressDto getInputAddressDto() {
        return inputAddressDto;
    }

    /**
     * 住所入力を設定する
     *
     * @param inputAddressDto 住所入力
     */
    public void setInputAddressDto(final InputAddressDto inputAddressDto) {
        this.inputAddressDto = inputAddressDto;
    }

    /**
     * 連絡先入力を取得する
     *
     * @return 連絡先入力
     */
    public InputAccessDto getInputAccessDto() {
        return inputAccessDto;
    }

    /**
     * 連絡先入力を設定する
     *
     * @param inputAccessDto 連絡先入力
     */
    public void setInputAccessDto(final InputAccessDto inputAccessDto) {
        this.inputAccessDto = inputAccessDto;
    }
}
