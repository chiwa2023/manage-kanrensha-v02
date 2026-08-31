package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.infrastructure.item.file.LineMapper;
// import org.springframework.batch.infrastructure.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 郵便番号差分ファイル読み取りLinMapper
 */
@Component
public class EditPostalCodeLineMapper implements LineMapper<EditPostalCodeOneLineDto> {

    /** 自治体コード読み取り位置 */
    private static final int POS_LOCAL_GOV = 0;

    /** 郵便番号5桁読み取り位置 */
    private static final int POS_POSTALCODE5 = 1;

    /** 郵便番号7桁読み取り位置 */
    private static final int POS_POSTALCODE7 = 2;

    /** 県カナ読み取り位置 */
    private static final int POS_PREF_KANA = 3;

    /** 市町村カナ読み取り位置 */
    private static final int POS_CITY_KANA = 4;

    /** 原文書カナ読み取り位置 */
    private static final int POS_ORG_KANA = 5;

    /** 県名ド読み取り位置 */
    private static final int POS_PREF = 6;

    /** 市町村名読み取り位置 */
    private static final int POS_CITY = 7;

    /** 原文書名読み取り位置 */
    private static final int POS_ORG = 8;

    /** 属選1フラグ読み取り位置 */
    private static final int POS_PROP1 = 9;

    /** 属選2フラグ読み取り位置 */
    private static final int POS_PROP2 = 10;

    /** 属選3フラグ取り位置 */
    private static final int POS_PROP3 = 11;

    /** 属選4フラグ読み取り位置 */
    private static final int POS_PROP4 = 12;

    /** 更新表示読み取り位置 */
    private static final int POS_KOUSHIN = 13;

    /** 変更理由読み取り位置 */
    private static final int POS_HENKOU_RIYUU = 14;

    /** ダブルクォーテーション */
    private static final String QUATE = "\"";
    /** 空文字 */
    private static final String BALNK = "";

    /**
     * 処理を行う
     */
    @Override
    public EditPostalCodeOneLineDto mapLine(final String line, final int lineNumber) throws Exception {

        String[] cell = line.split(",");

        EditPostalCodeOneLineDto dto = new EditPostalCodeOneLineDto();

        dto.setLgCode(cell[POS_LOCAL_GOV].replaceAll(QUATE, BALNK));
        dto.setPostalcode5(cell[POS_POSTALCODE5].replaceAll(QUATE, BALNK));
        dto.setPostalcode7(cell[POS_POSTALCODE7].replaceAll(QUATE, BALNK));
        dto.setPrefNameKana(cell[POS_PREF_KANA].replaceAll(QUATE, BALNK));
        dto.setCityNameKana(cell[POS_CITY_KANA].replaceAll(QUATE, BALNK));
        dto.setOrgNameKana(cell[POS_ORG_KANA].replaceAll(QUATE, BALNK));
        dto.setPrefName(cell[POS_PREF].replaceAll(QUATE, BALNK));
        dto.setCityName(cell[POS_CITY].replaceAll(QUATE, BALNK));
        dto.setOrgName(cell[POS_ORG].replaceAll(QUATE, BALNK));

        dto.setFlgProp1(cell[POS_PROP1].replaceAll(QUATE, BALNK));
        dto.setFlgProp2(cell[POS_PROP2].replaceAll(QUATE, BALNK));
        dto.setFlgProp3(cell[POS_PROP3].replaceAll(QUATE, BALNK));
        dto.setFlgProp4(cell[POS_PROP4].replaceAll(QUATE, BALNK));

        dto.setFlgKoushin(cell[POS_KOUSHIN].replaceAll(QUATE, BALNK));
        dto.setFlgHenkouRiyu(cell[POS_HENKOU_RIYUU].replaceAll(QUATE, BALNK));

        return dto;
    }
}
