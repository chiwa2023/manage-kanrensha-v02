package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;

/**
 * SE権限ユーザDto
 */
public class RiyoushaOrgDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    private Integer riyoushaOrgMasterId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getRiyoushaOrgMasterId() {
        return riyoushaOrgMasterId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param riyoushaOrgMasterId テーブルId
     */
    public void setRiyoushaOrgMasterId(final Integer riyoushaOrgMasterId) {
        this.riyoushaOrgMasterId = riyoushaOrgMasterId;
    }

    /** 利用者組織コード */
    private Integer riyoushaOrgMasterCode = INIT_INTEGER;

    /**
     * 利用者組織コードを取得する
     *
     * @return 利用者組織コード
     */
    public Integer getRiyoushaOrgMasterCode() {
        return riyoushaOrgMasterCode;
    }

    /**
     * 利用者組織コードを設定する
     *
     * @param riyoushaOrgMasterCode 利用者組織コード
     */
    public void setRiyoushaOrgMasterCode(final Integer riyoushaOrgMasterCode) {
        this.riyoushaOrgMasterCode = riyoushaOrgMasterCode;
    }

    /** 利用者組織属性id */
    private Integer riyoushaOrgPropertyId = INIT_INTEGER;

    /**
     * 利用者組織属性idを取得する
     *
     * @return 利用者組織属性id
     */
    public Integer getRiyoushaOrgPropertyId() {
        return riyoushaOrgPropertyId;
    }

    /**
     * 利用者組織属性idを設定する
     *
     * @param riyoushaOrgPropertyId 利用者組織属性id
     */
    public void setRiyoushaOrgPropertyId(final Integer riyoushaOrgPropertyId) {
        this.riyoushaOrgPropertyId = riyoushaOrgPropertyId;
    }

    /** 利用者組織属性コード */
    private Integer riyoushaOrgPropertyCode = INIT_INTEGER;

    /**
     * 利用者組織属性コードを取得する
     *
     * @return 利用者組織属性コード
     */
    public Integer getRiyoushaOrgPropertyCode() {
        return riyoushaOrgPropertyCode;
    }

    /**
     * 利用者組織属性コードを設定する
     *
     * @param riyoushaOrgPropertyCode 利用者組織属性コード
     */
    public void setRiyoushaOrgPropertyCode(final Integer riyoushaOrgPropertyCode) {
        this.riyoushaOrgPropertyCode = riyoushaOrgPropertyCode;
    }

    /** 組織名称入力 */
    private InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();

    /** 住所入力 */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /** 連絡先入力 */
    private InputAccessDto inputAccessDto = new InputAccessDto();

    /**
     * 組織名称入力を取得する
     * 
     * @return 組織名称入力
     */
    public InputOrgNameDto getInputOrgNameDto() {
        return inputOrgNameDto;
    }

    /**
     * 組織名称入力を設定する
     * 
     * @param inputOrgNameDto 組織名称入力
     */
    public void setInputOrgNameDto(final InputOrgNameDto inputOrgNameDto) {
        this.inputOrgNameDto = inputOrgNameDto;
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

    /** 組織構成員リスト */
    private List<RiyoushaCombineOrgEntity> listPersonCombine = new ArrayList<>();

    /**
     * 組織構成員リストを取得する
     * 
     * @return 組織構成員リスト
     */
    public List<RiyoushaCombineOrgEntity> getListPersonCombine() {
        return listPersonCombine;
    }

    /**
     * 組織構成員リストを設定する
     * 
     * @param listPersonCombine 組織構成員リスト
     */
    public void setListPersonCombine(final List<RiyoushaCombineOrgEntity> listPersonCombine) {
        this.listPersonCombine = listPersonCombine;
    }

}
