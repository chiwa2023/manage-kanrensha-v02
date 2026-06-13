package net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * 法人番号検索条件検証Logic
 */
@Component
public class ValidateCreateSearchHoujinNoUrlLogic {

    /** 地方自治体Repository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** 空文字 */
    private static final String BLANK = "";

    /** 制度開始日（法施行日） */
    private static final LocalDate LAW_EFFECT_DATE = LocalDate.of(2025, 10, 5);

    /** 地方自治体コード制限桁数 */
    private static final int LG_CODE_LIMIT  = 5;

    /** 地方自治体コード制限桁数 */
    private static final int LG_CODE_LIMIT_MYAPP  = 6;

    /**
     * 検索条件を検証する
     * 
     * @param domainUrl  ドメインURL
     * @param capsuleDto 検索条件
     */
    public void practice(final String domainUrl, final SearchHoujinNoCapsuleDto capsuleDto) {
        
        if (BLANK.equals(this.getValue(domainUrl))) {
            throw new IllegalArgumentException("接続URLが未指定です");
        }

        // capsuleDtoがnullは早く落として実装ミスを見つける
        if (Objects.isNull(capsuleDto)) {
            throw new IllegalArgumentException("検索条件が未指定です");
        }
        
        String appId = this.getValue(capsuleDto.getAppId());
        if (BLANK.equals(appId)) {
            throw new IllegalArgumentException("アプリケーションIdが未指定です");
        }

        String nameWord = this.getValue(capsuleDto.getName());
        if (BLANK.equals(nameWord)) {
            throw new IllegalArgumentException("検索条件名称が未指定です");
        }
        
        // 各種項目の個別検証メソッドを呼び出すことでNPath複雑度を低減する
        this.validateMode(this.getValue(capsuleDto.getMode()));
        this.validateTarget(this.getValue(capsuleDto.getTarget()));
        this.validateAddress(this.getValue(capsuleDto.getAddress()));
        this.validateKind(this.getValue(capsuleDto.getKind()));
        this.validateChange(this.getValue(capsuleDto.getChange()));
        this.validateClose(this.getValue(capsuleDto.getClose()));
        this.validateDate(capsuleDto.getFrom(), "指定年月日開始");
        this.validateDate(capsuleDto.getTo(), "指定年月日終了");
    }

    /**
     * 名称検索方式を検証する
     * 
     * @param mode 名称検索方式
     */
    private void validateMode(final String mode) {
        if (BLANK.equals(mode)) {
            return;
        }
        if (!"1".equals(mode) && !"2".equals(mode)) {
            throw new IllegalArgumentException("名称検索方式の指定値が不正です");
        }
    }

    /**
     * 名称検索対象を検証する
     * 
     * @param target 名称検索対象
     */
    private void validateTarget(final String target) {
        if (BLANK.equals(target)) {
            return;
        }
        if (!"1".equals(target) && !"2".equals(target) && !"3".equals(target)) {
            throw new IllegalArgumentException("名称検索対象の指定値が不正です");
        }
    }

    /**
     * 市区町村コード（住所）を検証する
     * 
     * @param address 住所（市区町村コードまたは都道府県コード）
     */
    private void validateAddress(final String address) {
        if (BLANK.equals(address)) {
            return;
        }

        final int length = address.length();
        if (length != 2 && length != LG_CODE_LIMIT && length != LG_CODE_LIMIT_MYAPP) {
            throw new IllegalArgumentException("市区町村コードの桁数が不正です");
        }

        if (!address.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("市区町村コードの形式が不正です");
        }

        if (length == LG_CODE_LIMIT_MYAPP) {
            if (this.addressAllCityRepository.findByLgCodeAndIsLatestTrue(address).isEmpty()) {
                throw new IllegalArgumentException("存在しない市区町村コードが指定されています");
            }
        } else {
            if (this.addressAllCityRepository.findByLgCodeStartingWith(address).isEmpty()) {
                throw new IllegalArgumentException("存在しない市区町村コードが指定されています");
            }
        }
    }

    /**
     * 法人種別を検証する
     * 
     * @param kind 法人種別
     */
    private void validateKind(final String kind) {
        if (BLANK.equals(kind)) {
            return;
        }
        if (!"01".equals(kind) && !"02".equals(kind) && !"03".equals(kind) && !"04".equals(kind)) {
            throw new IllegalArgumentException("法人種別の指定値が不正です");
        }
    }

    /**
     * 履歴該否を検証する
     * 
     * @param change 履歴該否
     */
    private void validateChange(final String change) {
        if (BLANK.equals(change)) {
            return;
        }
        if (!"0".equals(change) && !"1".equals(change)) {
            throw new IllegalArgumentException("履歴該否の指定値が不正です");
        }
    }

    /**
     * 登記記録の閉鎖該否を検証する
     * 
     * @param close 登記記録の閉鎖該否
     */
    private void validateClose(final String close) {
        if (BLANK.equals(close)) {
            return;
        }
        if (!"0".equals(close) && !"1".equals(close)) {
            throw new IllegalArgumentException("登記記録の閉鎖該否の指定値が不正です");
        }
    }

    /**
     * 日付指定（法施行前2025-10-05以前を指定するとエラー）を検証する
     * 
     * @param localDate 検査対象日付
     * @param label     項目名
     */
    private void validateDate(final LocalDate localDate, final String label) {
        if (Objects.isNull(localDate) || DtoEntityInitialValueInterface.INIT_DATE.equals(localDate)) {
            return;
        }
        if (!localDate.isAfter(LAW_EFFECT_DATE)) {
            throw new IllegalArgumentException(label + "に2025-10-05以前の日付は指定できません");
        }
    }

    private String getValue(final String data) {
        if (Objects.isNull(data)) {
            return BLANK;
        }

        return data;
    }

}
