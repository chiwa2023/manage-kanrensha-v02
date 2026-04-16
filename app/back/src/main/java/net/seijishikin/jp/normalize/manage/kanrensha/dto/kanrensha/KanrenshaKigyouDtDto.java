package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;


/**
 * 関連者企業団体Dto
 */
public class KanrenshaKigyouDtDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 団体名称入力Dto */
    private InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();

    /** 住所入力Dto */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /** 連絡先Dto */
    private InputAccessDto inputAccessDto = new InputAccessDto();

    /** 団体代表者関連者最低限Dto */
    private InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();

    /**
     * 団体名称入力Dtoを取得する
     *
     * @return 団体名称入力Dto
     */
    public InputOrgNameDto getInputOrgNameDto() {
        return inputOrgNameDto;
    }

    /**
     * 団体名称入力Dtoを設定する
     *
     * @param inputOrgNameDto 団体名称入力Dto
     */
    public void setInputOrgNameDto(final InputOrgNameDto inputOrgNameDto) {
        this.inputOrgNameDto = inputOrgNameDto;
    }

    /**
     * 住所入力Dtoを取得する
     *
     * @return 住所入力Dto
     */
    public InputAddressDto getInputAddressDto() {
        return inputAddressDto;
    }

    /**
     * 住所入力Dtoを設定する
     *
     * @param inputAddressDto 住所入力Dto
     */
    public void setInputAddressDto(final InputAddressDto inputAddressDto) {
        this.inputAddressDto = inputAddressDto;
    }

    /**
     * 連絡先Dtoを取得する
     *
     * @return 連絡先Dto
     */
    public InputAccessDto getInputAccessDto() {
        return inputAccessDto;
    }

    /**
     * 連絡先Dtoを設定する
     *
     * @param inputAccessDto 連絡先Dto
     */
    public void setInputAccessDto(final InputAccessDto inputAccessDto) {
        this.inputAccessDto = inputAccessDto;
    }

    /** 支店該当 */
    private Boolean isShiten = INIT_Boolean;

    /** 法人種別 */
    private String houjinSbts = INIT_String;

    /**
     * 法人種別を取得する
     *
     * @return 法人種別
     */
    public String getHoujinSbts() {
        return houjinSbts;
    }

    /**
     * 法人種別を設定する
     *
     * @param houjinSbts 法人種別
     */
    public void setHoujinSbts(final String houjinSbts) {
        this.houjinSbts = houjinSbts;
    }

    /**
     * 支店該当を取得する
     *
     * @return 支店該当
     */
    public Boolean getIsShiten() {
        return isShiten;
    }

    /**
     * 支店該当を設定する
     *
     * @param isShiten 支店該当
     */
    public void setIsShiten(final Boolean isShiten) {
        this.isShiten = isShiten;
    }

    /**
     * 団体代表者関連者最低限Dtoを取得する
     *
     * @return 団体代表者関連者最低限Dto
     */
    public InputKanrenshaPersonLeastDto getOrgDelegateLeastDto() {
        return orgDelegateLeastDto;
    }

    /**
     * 団体代表者関連者最低限Dtoを設定する
     *
     * @param orgDelegateLeastDto 団体代表者関連者最低限Dto
     */
    public void setOrgDelegateLeastDto(final InputKanrenshaPersonLeastDto orgDelegateLeastDto) {
        this.orgDelegateLeastDto = orgDelegateLeastDto;
    }

    /** 法人番号 */
    private String houjinNo = INIT_String;

    /**
     * 法人番号を取得する
     *
     * @return 法人番号
     */
    public String getHoujinNo() {
        return houjinNo;
    }

    /**
     * 法人番号を設定する
     *
     * @param houjinNo 法人番号
     */
    public void setHoujinNo(final String houjinNo) {
        this.houjinNo = houjinNo;
    }

    /** 関連者企業団体マスタテーブルId */
    private Integer masterId = INIT_Integer;

    /** 関連者企業団体連絡先テーブルId */
    private Integer accessId = INIT_Integer;

    /** 関連者企業団体住所テーブルId */
    private Integer addressId = INIT_Integer;

    /** 関連者企業団体属性テーブルId */
    private Integer propertyId = INIT_Integer;

    /**
     * 関連者企業団体マスタテーブルIdを取得する
     *
     * @return 関連者企業団体マスタテーブルId
     */
    public Integer getMasterId() {
        return masterId;
    }

    /**
     * 関連者企業団体マスタテーブルIdを設定する
     *
     * @param masterId 関連者企業団体マスタテーブルId
     */
    public void setMasterId(final Integer masterId) {
        this.masterId = masterId;
    }

    /**
     * 関連者企業団体連絡先テーブルIdを取得する
     *
     * @return 関連者企業団体連絡先テーブルId
     */
    public Integer getAccessId() {
        return accessId;
    }

    /**
     * 関連者企業団体連絡先テーブルIdを設定する
     *
     * @param accessId 関連者企業団体連絡先テーブルId
     */
    public void setAccessId(final Integer accessId) {
        this.accessId = accessId;
    }

    /**
     * 関連者企業団体住所テーブルIdを取得する
     *
     * @return 関連者企業団体住所テーブルId
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 関連者企業団体住所テーブルIdを設定する
     *
     * @param addressId 関連者企業団体住所テーブルId
     */
    public void setAddressId(final Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 関連者企業団体属性テーブルIdを取得する
     *
     * @return 関連者企業団体属性テーブルId
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * 関連者企業団体属性テーブルIdを設定する
     *
     * @param propertyId 関連者企業団体属性テーブルId
     */
    public void setPropertyId(final Integer propertyId) {
        this.propertyId = propertyId;
    }

    /** 関連者企業団体コード */
    private String kigyouDtKanrenshaCode = INIT_String;

    /**
     * 関連者企業団体コードを取得する
     *
     * @return 関連者企業団体コード
     */
    public String getKigyouDtKanrenshaCode() {
        return kigyouDtKanrenshaCode;
    }

    /**
     * 関連者企業団体コードを設定する
     *
     * @param kigyouDtKanrenshaCode 関連者企業団体コード
     */
    public void setKigyouDtKanrenshaCode(final String kigyouDtKanrenshaCode) {
        this.kigyouDtKanrenshaCode = kigyouDtKanrenshaCode;
    }

    /** 関連者ユーザ紐づけ該否 */
    private Boolean isCombineUser = INIT_Boolean;

    /**
     * 関連者ユーザ紐づけ該否を取得する
     *
     * @return 関連者ユーザ紐づけ該否
     */
    public Boolean getIsCombineUser() {
        return isCombineUser;
    }

    /**
     * 関連者ユーザ紐づけ該否を設定する
     *
     * @param isCombineUser 関連者ユーザ紐づけ該否
     */
    public void setIsCombineUser(final Boolean isCombineUser) {
        this.isCombineUser = isCombineUser;
    }

}
