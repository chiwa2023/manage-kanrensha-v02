package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 郵便番号Csv行読み取りMapper
 */
@Component
public class PostalCodeJigyoushoLineMapper implements LineMapper<PostalCodeCsvJigyoushoDto> {

    /** 自治体コード読み取り位置 */
    private static final int POS_LOCAL_GOV = 0;

    /** 郵便番号読み取り位置 */
    private static final int POS_POSTALCODE = 7;

    /** 市区町村記載位置 */
    private static final int POS_ADDRESS_PREF = 3;

    /** 市区町村記載位置 */
    private static final int POS_ADDRESS_CITY = 4;

    /** 住所記載位置 */
    private static final int POS_ADDRESS_ORG = 5;

    /** 住所記載位置 */
    private static final int POS_ADDRESS_BLOCK = 6;

    /** ダブルクォーテーション */
    private static final String QUATE = "\"";
    /** 空文字 */
    private static final String BALNK = "";

    /**
     * 処理を行う
     */
    @Override
    public PostalCodeCsvJigyoushoDto mapLine(final String line, final int lineNumber) throws Exception {

        String[] cell = line.split(",");

        PostalCodeCsvJigyoushoDto csvDto = new PostalCodeCsvJigyoushoDto();

        csvDto.setLgCode(cell[POS_LOCAL_GOV].replaceAll(QUATE, BALNK));
        csvDto.setPostalcode(cell[POS_POSTALCODE].replaceAll(QUATE, BALNK));
        csvDto.setPref(cell[POS_ADDRESS_PREF].replaceAll(QUATE, BALNK));
        csvDto.setCity(cell[POS_ADDRESS_CITY].replaceAll(QUATE, BALNK));
        csvDto.setAddressOrg(cell[POS_ADDRESS_ORG].replaceAll(QUATE, BALNK));
        csvDto.setAddressBlock(cell[POS_ADDRESS_BLOCK].replaceAll(QUATE, BALNK));

        return csvDto;
    }

}
