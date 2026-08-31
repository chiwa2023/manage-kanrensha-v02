package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.batch.infrastructure.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory.WriteLogAddressFormatLogic;

/**
 * アドレス・ベース・レジストリ住居表示－住居Csv(mt_rsdtdsp_rsdt_prefxx.csv)変換LineMapper
 */
@Component
public class RsdtAddressCsvLineMapper implements LineMapper<RsdtAddressCsvDto> {
    // CHECKSTYLE:OFF MagicNumber
    // 列の最初から該当カラムまでカラム順に取得する処理なので、変に定数で位置指定しないのが正しい書法と想定

    /** 日付変換Formatter */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Logger */
    @Autowired
    private WriteLogAddressFormatLogic writeLogAddressFormatLogic;

    /** 空白文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     */
    @Override
    public RsdtAddressCsvDto mapLine(final String line, final int lineNumber) throws Exception {

        RsdtAddressCsvDto dto = new RsdtAddressCsvDto();

        String[] cell = line.split(",");

        // 地方自治体コード
        dto.setLgCode(cell[0]);
        // 町字コード
        dto.setMachiazaId(cell[1]);
        // 街区コード
        dto.setBlkId(cell[2]);
        // 住居lコード
        dto.setRsdtId(cell[3]);
        // 住居コード2
        dto.setRsdt2Id(cell[4]);
        // 市区町村名
        dto.setCity(cell[5]);
        // 政令市区名
        dto.setWard(cell[6]);
        // 大字・町名
        dto.setOazaCho(cell[7]);
        // 丁目名
        dto.setChome(cell[8]);
        // 小字名
        dto.setKoaza(cell[9]);
        // 同一町字識別情報
        dto.setMachiazaDist(cell[10]);
        // 街区符号
        dto.setBlkNum(cell[11]);
        // 住居番号
        dto.setRsdtNum(cell[12]);
        // 住居番号2
        dto.setRsdtNum2(cell[13]);

        // 基礎番号・住居番号区分
        String basicRsdtDiv = cell[14];
        if (!"".equals(basicRsdtDiv)) {
            dto.setBasicRsdtDiv(this.convertInteger(basicRsdtDiv, lineNumber));
        }

        // 住居表示フラグ
        // 住居表示フラグは必須項目のため空文字でもエラーにする
        dto.setRsdtAddrFlg(this.convertInteger(cell[15], lineNumber));

        // 住居表示方式コード
        String rsdtAddrMtdCode = cell[16];
        if (!"".equals(rsdtAddrMtdCode)) {
            dto.setRsdtAddrMtdCode(this.convertInteger(cell[16], lineNumber));
        }

        // 状態フラグ
        String statusFlg = cell[17];
        if (!"".equals(statusFlg)) {
            dto.setStatusFlg(this.convertInteger(statusFlg, lineNumber));
        }

        // 適用開始日
        String effect = cell[18];
        if (!"".equals(effect)) {
            dto.setEffectDate(this.convertLocalDate(effect, lineNumber));
        }
        // 廃止日
        String abolition = cell[19];
        if (BLANK.equals(abolition)) {
            dto.setAbolishDate(null);
        } else {
            dto.setAbolishDate(this.convertLocalDate(abolition, lineNumber));
        }

        return dto;
    }

    private LocalDate convertLocalDate(final String dateText, final int lineNumber) {
        try {
            return LocalDate.parse(dateText, formatter);
        } catch (DateTimeParseException e) {
            writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, dateText, String.valueOf(lineNumber),
                    "行目日付変換不可");

        }
        return DtoEntityInitialValueInterface.INIT_DATE;
    }

    private Integer convertInteger(final String codeText, final int lineNumber) {

        try {
            return Integer.parseInt(codeText);
        } catch (NumberFormatException e) {
            writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, codeText, String.valueOf(lineNumber),
                    "行目数値変換不可");
        }
        return 0;

    }
}
