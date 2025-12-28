package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputShokugyouDto;


/**
 * 関連者個人Dto
 */
public class KanrenshaPersonDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 個人氏名入力Dto */
    private InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();

    /** 住所入力Dto */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /** 連絡先入力Dto */
    private InputAccessDto inputAccessDto = new InputAccessDto();

    /** 職業入力Dto */
    private InputShokugyouDto inputShokugyouDto = new InputShokugyouDto();

    /**
     * 個人氏名入力Dtoを取得する
     *
     * @return 個人氏名入力Dto
     */
    public InputPersonNameDto getInputPersonNameDto() {
        return inputPersonNameDto;
    }

    /**
     * 個人氏名入力Dtoを設定する
     *
     * @param inputPersonNameDto 個人氏名入力Dto
     */
    public void setInputPersonNameDto(final InputPersonNameDto inputPersonNameDto) {
        this.inputPersonNameDto = inputPersonNameDto;
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
     * 連絡先入力Dtoを取得する
     *
     * @return 連絡先入力Dto
     */
    public InputAccessDto getInputAccessDto() {
        return inputAccessDto;
    }

    /**
     * 連絡先入力Dtoを設定する
     *
     * @param inputAccessDto 連絡先入力Dto
     */
    public void setInputAccessDto(final InputAccessDto inputAccessDto) {
        this.inputAccessDto = inputAccessDto;
    }

    /**
     * 職業入力Dtoを取得する
     *
     * @return 職業入力Dto
     */
    public InputShokugyouDto getInputShokugyouDto() {
        return inputShokugyouDto;
    }

    /**
     * 職業入力Dtoを設定する
     *
     * @param inputShokugyouDto 職業入力Dto
     */
    public void setInputShokugyouDto(final InputShokugyouDto inputShokugyouDto) {
        this.inputShokugyouDto = inputShokugyouDto;
    }

    /** 外国籍該否 */
    private Boolean isForeign = INIT_Boolean;

    /**
     * 外国籍該否を取得する
     *
     * @return 外国籍該否
     */
    public Boolean getIsForeign() {
        return isForeign;
    }

    /**
     * 外国籍該否を設定する
     *
     * @param isForeign 外国籍該否
     */
    public void setIsForeign(final Boolean isForeign) {
        this.isForeign = isForeign;
    }

    /** 関連者個人マスタテーブルId */
    private Integer masterId = INIT_Integer;

    /** 関連者個人連絡先テーブルId */
    private Integer accessId = INIT_Integer;

    /** 関連者個人住所テーブルId */
    private Integer addressId = INIT_Integer;

    /** 関連者個人属性テーブルId */
    private Integer propertyId = INIT_Integer;

    /**
     * 関連者個人マスタテーブルIdを取得する
     *
     * @return 関連者個人マスタテーブルId
     */
    public Integer getMasterId() {
        return masterId;
    }

    /**
     * 関連者個人マスタテーブルIdを設定する
     *
     * @param masterId 関連者個人マスタテーブルId
     */
    public void setMasterId(final Integer masterId) {
        this.masterId = masterId;
    }

    /**
     * 関連者個人連絡先テーブルIdを取得する
     *
     * @return 関連者個人連絡先テーブルId
     */
    public Integer getAccessId() {
        return accessId;
    }

    /**
     * 関連者個人連絡先テーブルIdを設定する
     *
     * @param accessId 関連者個人連絡先テーブルId
     */
    public void setAccessId(final Integer accessId) {
        this.accessId = accessId;
    }

    /**
     * 関連者個人住所テーブルIdを取得する
     *
     * @return 関連者個人住所テーブルId
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 関連者個人住所テーブルIdを設定する
     *
     * @param addressId 関連者個人住所テーブルId
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

    /** 関連者個人コード */
    private String personKanrenshaCode = INIT_String;

    /**
     * 関連者個人コードを取得する
     *
     * @return 関連者個人コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 関連者個人コードを設定する
     *
     * @param personKanrenshaCode 関連者個人コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
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
